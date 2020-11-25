package com.advantage.accountsoap.services;

import com.advantage.accountsoap.config.BeansManager;
import com.advantage.accountsoap.config.Injectable;
import com.advantage.accountsoap.dao.AccountRepository;
import com.advantage.accountsoap.dao.AddressRepository;
import com.advantage.accountsoap.dto.DeleteOrderResponse;
import com.advantage.accountsoap.dto.account.AccountPermanentDeleteResponse;
import com.advantage.accountsoap.dto.account.AccountStatusResponse;
import com.advantage.accountsoap.dto.account.internal.AccountDto;
import com.advantage.accountsoap.dto.address.AddressDto;
import com.advantage.accountsoap.dto.country.CountryID;
import com.advantage.accountsoap.dto.payment.PaymentPreferencesDto;
import com.advantage.accountsoap.dto.payment.PaymentPreferencesStatusResponse;
import com.advantage.accountsoap.model.Account;
import com.advantage.accountsoap.model.PaymentPreferences;
import com.advantage.accountsoap.model.ShippingAddress;
import com.advantage.accountsoap.util.AccountPassword;

import com.advantage.accountsoap.util.UrlResources;
import com.advantage.common.enums.AccountType;
import com.advantage.common.exceptions.token.ContentTokenException;
import com.advantage.common.exceptions.token.TokenException;
import com.advantage.common.exceptions.token.VerificationTokenException;
import com.advantage.common.exceptions.token.WrongTokenTypeException;
import com.advantage.common.security.SecurityTools;
import com.advantage.common.security.TokenJWT;
import com.advantage.accountsoap.util.RestApiHelper;

import com.advantage.common.utils.StringHelper;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class AccountService implements Injectable {

    private AccountRepository accountRepository;

//    @Autowired
//    @Qualifier("countryRepository")
//    private CountryRepository countryRepository;


    private AddressRepository addressRepository;

    @Override
    public void inject(BeansManager beansManager) {
        this.accountRepository = beansManager.getAccountRepository();
        this.addressRepository = beansManager.getAddressRepository();
    }

    @Autowired
    private PaymentPreferencesService paymentPreferencesService;

    @Autowired
    private AddressService addressService;

    private static final Logger logger = Logger.getLogger(AccountService.class);

    @Transactional
    public AccountStatusResponse create(final Integer appUserType, final String lastName, final String firstName, final String loginName, final String password, final CountryID countryId, final String phoneNumber, final String stateProvince, final String cityName, final String address, final String zipcode, final String email, final boolean allowOffersPromotion) {

        AccountStatusResponse accountStatusResponse = accountRepository.create(appUserType, lastName, firstName, loginName, password, countryId, phoneNumber, stateProvince, cityName, address, zipcode, email, allowOffersPromotion);

        if (accountStatusResponse.isSuccess()) {
            //  Add user-account address to Shipping address (CountryId can be default 40L = United States of America)
            if ((!StringHelper.isNullOrEmpty(address)) ||
                    (!StringHelper.isNullOrEmpty(cityName)) ||
                    (!StringHelper.isNullOrEmpty(zipcode)) ||
                    (countryId.value() != null) ||
                    (!StringHelper.isNullOrEmpty(stateProvince))) {

                //  Update Shipping Address based on address in user-account
                String addressLine1 = address.length() > 50 ? address.substring(0, 50) : address;
                String addressLine2 = address.length() > 50 ? address.substring(50) : null;

                //addressRepository.addAddress(accountStatusResponse.getUserId(), addressLine1, addressLine2, cityName, countryRepository.getCountryIsoNameById(countryId).getIsoName(), stateProvince, zipcode);
                addressRepository.addAddress(accountStatusResponse.getUserId(), addressLine1, addressLine2, cityName, String.valueOf(countryId), stateProvince, zipcode);
            } else {
                //  No user-account address details entered (CountryId can be default 40L = United States of America)
            }

        }

        return accountStatusResponse;
    }

    @Transactional(readOnly = true)
    public Account getAppUserByLogin(final String loginUser) {
        return accountRepository.getAppUserByLogin(loginUser);
    }

    @Transactional(readOnly = true)
    public AccountStatusResponse doLogin(final String loginUser, final String loginPassword, final String email) {
        return accountRepository.doLogin(loginUser, loginPassword, email);
    }

    @Transactional(readOnly = true)
    public AccountStatusResponse doLogout(final String loginUser, final String base64Token) {
        return accountRepository.doLogout(loginUser, base64Token);
    }

    @Transactional(readOnly = true)
    public List<Account> getAllAppUsers() {
        return accountRepository.getAll();
    }

    @Transactional(readOnly = true)
    public List<AccountDto> getAllAppUsersDto() {
        return fillAccountsDto(accountRepository.getAll());
    }

    private List<AccountDto> fillAccountsDto(List<Account> accounts) {
        List<AccountDto> dtos = new ArrayList<>();
        for (Account account : accounts) {
            if (account.getAccountType() != 40)
                dtos.add(new AccountDto(account.getId(),
                    account.getLastName(),
                    account.getFirstName(),
                    account.getLoginName(),
                    account.getAccountType(),
                    account.getCountry() != null ? account.getCountry().getId() : 40,
                    account.getCountry() != null ? account.getCountry().getName() : "United States",
                    account.getCountry() != null ? account.getCountry().getIsoName() : "us",
                    account.getStateProvince(),
                    account.getCityName(),
                    account.getAddress(),
                    account.getZipcode(),
                    account.getPhoneNumber(),
                    account.getEmail(),
                    account.getDefaultPaymentMethodId(),
                    account.isAllowOffersPromotion(), account.getInternalUnsuccessfulLoginAttempts(),
                    account.getInternalUserBlockedFromLoginUntil(),
                    account.getInternalLastSuccesssulLogin(),
                    account.getPassword()));

        }

        return dtos;
    }

    @Transactional(readOnly = true)
    public boolean isExists(long userId) {
        boolean result = false;

        if (accountRepository.get(userId) != null) {
            result = true;
        }

        return result;
    }

    @Transactional
    public AccountStatusResponse updateAccount(long accountId, Integer accountType, String lastName, String firstName,
                                               final CountryID countryId, String phoneNumber, String stateProvince, String cityName,
                                               String address, String zipcode, String email, boolean allowOffersPromotion) {

        AccountStatusResponse accountStatusResponse = accountRepository.updateAccount(accountId, accountType, lastName, firstName, countryId, phoneNumber,
                stateProvince, cityName, address, zipcode, email, allowOffersPromotion);

        if (accountStatusResponse.isSuccess()) {
            //  Add user-account address to Shipping address
            if ((!StringHelper.isNullOrEmpty(address)) ||
                    (!StringHelper.isNullOrEmpty(cityName)) ||
                    (!StringHelper.isNullOrEmpty(zipcode)) ||
                    (!StringHelper.isNullOrEmpty(stateProvince))) {

                //  Update Shipping Address based on address in user-account
                String addressLine1 = address.length() > 50 ? address.substring(0, 50) : address;
                String addressLine2 = address.length() > 50 ? address.substring(50) : null;

                Account account = accountRepository.get(accountId);
                if (account != null) {
                    addressRepository.update(new ShippingAddress(addressLine1, addressLine2, account, cityName, String.valueOf(countryId), stateProvince, zipcode));
                } else {
                    //  Should be a "shipping address" update failure message?
                }
            }

        }

        return accountStatusResponse;
    }

    @Transactional
    public AccountStatusResponse updateDefaultPaymentMethod(long accountId, Integer paymentMethodId) {
        Account account = getById(accountId);
        if (account == null) {
            return new AccountStatusResponse(false, "Data not valid", -1);
        }

        if (!paymentPreferencesService.isPaymentPreferencesExist(accountId)) {
            return new AccountStatusResponse(false, "Data not valid", -2);
        }

        account.setDefaultPaymentMethodId(((long) Integer.valueOf(paymentMethodId)));

        return new AccountStatusResponse(true, "Update default payment method was successful", accountId);
    }

    @Transactional
    public Account getById(long id) {
        return accountRepository.get(id);
    }


    @Transactional
    public AccountStatusResponse accountDelete(long accountId) throws TokenException {
        Account account = accountRepository.get(accountId);

        int result = accountRepository.deleteAccount(account);

        AccountStatusResponse response = resultSolver(result,account);

        return response;
    }

    private AccountStatusResponse resultSolver (int result, Account account){

        AccountStatusResponse response = new AccountStatusResponse(false, "", account.getId());

        if (result == 1) {
            response.setSuccess(true);
            response.setReason("Account delete successful");
            response.setUserId(account.getId());
            logger.info("Account " + account.getId() + " delete successful");
        } else {
            response.setSuccess(false);
            response.setReason("Account delete failed");
            response.setUserId(account.getId());
            logger.warn("Account " + account.getId() + " delete failed");
        }
        return response;
    }

    private boolean deleteOrders(long accountId, String data) throws IOException{

        Account account = accountRepository.get(accountId);
        String stringResponse = null;
        URL deleteOrdersForUser = null;
        String authorizationKey = encode64(account.getLoginName() + ":" + data);
        URL orderApiUrl = UrlResources.getUrlOrder();

        try {

        deleteOrdersForUser = new URL(orderApiUrl + "orders/history/users/" + accountId);
        stringResponse = RestApiHelper.httpGetWithAuthorization(deleteOrdersForUser, "account", "Authorization", "Basic " + authorizationKey);
        logger.debug("stringResponse--" + stringResponse);

            switch (stringResponse) {
                case "CONFLICT":
                    return false;

                case "NOT FOUND":
                    return false;

                case "FORBIDDEN":
                    return false;

                case "UNAUTHORIZED":
                    return false;

            }
        //Removing added value by apiHelper
        String stringResponseEdited = "{\"" + stringResponse.substring(17);
        ObjectMapper objectMapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        DeleteOrderResponse deleteOrderResponse = objectMapper.readValue(stringResponseEdited, DeleteOrderResponse.class);

        if(deleteOrderResponse.isSuccess()){
            return true;
        }
        else{
            return false;
        }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Transactional
    public AccountStatusResponse accountPermanentDelete(long accountId){

            Account account = accountRepository.get(accountId);

            int result = accountRepository.deleteAccountPermanently(account);

            AccountStatusResponse response = resultSolver(result,account);

            return response;

    }

    /**
     * Change the password of a registered user. This can happen in 1 of 2 ways: <br/>
     * 1.  Registered user changes his own password (can be USER or ADMIN-USER). <br/>
     * <ul>NOTE:</ul> In this case the {@param accountId} and {@link TokenJWT#getUserId()} will be same. <br/>
     * (a) Get the registered user account details by accountId. <br/>
     * (b) Verify login user name with the one in the {@code token} and old password with argument. <br/>
     * (c) If <b>VERIFIED</b> then change the user's password, Return <b>Successful</b>with HTTP code 202 (Accepted). <br/>
     * (d) otherwise, return <b>FAILURE</b> with HTTP code 403 (Forbidden). </p>
     * <p>2.  Admin-User changes the password of another registered user (not his own). <br/>
     * <ul>NOTE:</ul> In this case {@link TokenJWT#getAccountType()} must by <i>ADMIN</i>. <br/>
     * (a) Verify the account type in the token is ADMIN. If NOT then return HTTP code 401 (Unauthorized).
     * (b) Get the registered user account details by accountId.
     * (c) Verify old password for the registered user.
     * (d) If <b>VERIFIED</b> then change the user's password, Return <b>Successful</b>with HTTP code 202 (Accepted).
     * (e) Return <b>Successful</b>with HTTP code 202 (Accepted). </p>
     *
     * @param accountId   user-id to update his password.
     * @param oldPassword Existing password of the user, for verification.
     * @param newPassword The password that will replace the old password.
     * @param base64Token The Base-64 Token that identifies the <b><i>CURRENT USER</i></b>.
     * @return {@link AccountStatusResponse}
     * @throws VerificationTokenException
     * @throws WrongTokenTypeException
     * @throws ContentTokenException
     */
    @Transactional
    public AccountStatusResponse changePassword(long accountId, String oldPassword, String newPassword, String base64Token) throws TokenException {

        Account accountToChange = accountRepository.get(accountId);
        AccountStatusResponse response = new AccountStatusResponse(false, "Initial", 0);
        AccountType accountToChangeType = AccountType.valueOfCode(accountToChange.getAccountType());
        AccountType userMakingTheChangeType = AccountType.USER ;
        long currentUserId;
        if(oldPassword == null || oldPassword.isEmpty()) {
            oldPassword = accountToChange.getPassword();
        }
        if (SecurityTools.isBasic(base64Token)) {
            currentUserId = accountId;
            accountToChangeType = AccountType.valueOfCode(accountToChange.getAccountType());
            String token = base64Token.substring(base64Token.indexOf(" ") + 1);
            String[] loginPassword = SecurityTools.decodeBase64(token).split(":");
            System.out.println(loginPassword[0] + "*****************" + loginPassword[1]);
            System.out.println((new AccountPassword(loginPassword[0], loginPassword[1])).getEncryptedPassword());
            if (!loginPassword[0].equals(accountToChange.getLoginName())
                    || !(new AccountPassword(loginPassword[0], loginPassword[1]))
                    .getEncryptedPassword().equals(accountToChange.getPassword())) {
                throw new VerificationTokenException("Not the same user and current user is not ADMIN");
            }
        } else {
            TokenJWT tokenJWT = TokenJWT.parseToken(base64Token);
            //  Get current user details from Token
            currentUserId = tokenJWT.getUserId();
            userMakingTheChangeType = AccountType.valueOfCode(accountRepository.get(currentUserId).getAccountType());

        }
        if ((oldPassword == null) || (oldPassword.isEmpty())) {
            //  Reset-Password FAILED! Old Password is empty and current user is not ADMIN-USER
            String message = "Old Password for user (" + currentUserId + ") is empty";
            logger.warn(message);
            response = new AccountStatusResponse(false, message, -1);
        } else if ((accountId != currentUserId) && (userMakingTheChangeType != AccountType.valueOfCode(AccountType.ADMIN.getAccountTypeCode()))) {
            //  Not the same user and current user is not ADMIN
            logger.error("Not the same user and current user is not ADMIN");
            throw new VerificationTokenException("Not the same user and current user is not ADMIN");

        }
        response = accountRepository.changePassword(accountId, newPassword);

//        else {
//            String parameterValue = RestApiHelper.getDemoAppConfigParameterValue("Implement_DevOps_Process");
            //  region PlaceHolder Feature 1789
//            if (parameterValue.equalsIgnoreCase("Yes")) {
                //  TODO DevOps - PlaceHolder Feature 2055
                //  Registered user and current user are the same

                    /*String encryptedPassword = new AccountPassword(account.getLoginName(), oldPassword)
                            .getEncryptedPassword();

                    if (account.getPassword().equals(encryptedPassword)) {
                        //  old password matches registered user password - OK to change password
                        response = accountRepository.changePassword(accountId, newPassword);
                    } else {
                        //  old Password does not match registered user password
                        String message = "Old Password does not match registered user password";
                        logger.warn(message);
                        response = new AccountStatusResponse(false, message, -1);
                    }*/
//            }
            //endregion
//        }
        return response;
    }

    /**
     * <p>
     * Reset the registered user's password. Only USER-ADMIN can perform this operation. <br/>
     * <ul>NOTE:</ul> {@link TokenJWT#getAccountType()} must by <i>ADMIN</i>. <br/>
     * (a) Verify the account type in the token is ADMIN. If NOT then return HTTP code 401 (Unauthorized). <br/>
     * (b) Get the registered user account details by accountId. <br/>
     * (c) Change the user's password, Return <b>Successful</b>with HTTP code 202 (Accepted). <br/>
     * </p>
     *
     * @param accountId   user-id to update his password.
     * @param newPassword The password that will replace the old password.
     * @param base64Token The Base-64 Token that identifies the <b><i>CURRENT USER</i></b>.
     * @return {@link AccountStatusResponse}
     * @throws VerificationTokenException
     * @throws WrongTokenTypeException
     * @throws ContentTokenException
     */
    @Transactional
    public AccountStatusResponse resetPassword(long accountId, String newPassword, String base64Token) throws TokenException {
        Account account = accountRepository.get(accountId);
        AccountStatusResponse response = new AccountStatusResponse(false, "", -1);
        TokenJWT tokenJWT = TokenJWT.parseToken(base64Token);

        //  Get current user details from Token
        long currentUserId = tokenJWT.getUserId();
        String currentUserLogin = tokenJWT.getLoginName();
        AccountType currentUserAccountType = tokenJWT.getAccountType();

        if (accountId != currentUserId) {
            //  Registered user and current user are not the same
            if (currentUserAccountType.getAccountTypeCode() == AccountType.ADMIN.getAccountTypeCode()) {
                //  Not the same user and current user is ADMIN
                String compareToPassword = new AccountPassword(account.getLoginName(), newPassword)
                        .getEncryptedPassword();

                //  Update new password into registered user account
                response = accountRepository.changePassword(accountId, newPassword);
            } else {
                //  Not the same user and current user is not ADMIN
                response = new AccountStatusResponse(false, HttpStatus.UNAUTHORIZED.getReasonPhrase(), -2);
            }
        }
        return response;
    }

    @Transactional
    public List<PaymentPreferencesDto> getPaymentPreferences(long accountId) {
        Account account = accountRepository.get(accountId);
        if (account == null) return null;

        //return fillPaymentPreferencesDto(account.getPaymentPreferences());
        List<PaymentPreferencesDto> paymentPreferencesDto = paymentPreferencesService.getPaymentPreferencesByUserId(accountId);

        return paymentPreferencesDto;
    }

    @Transactional
    public AccountStatusResponse removePaymentPreferences(long accountId, long paymentMethod) {
        return accountRepository.removePaymentPreferences(accountId,paymentMethod);
    }
    @Transactional
    public void deleteAllPaymentPreferences(long accountId) {
        accountRepository.deleteAllPaymentPreferences(accountId);
    }

    @Transactional
    public void deleteShippingAddress(long accountId) {
        accountRepository.deleteShippingAddress(accountId);

    }
    @Transactional
    public AccountStatusResponse deleteUserOrders(long accountId, String base64Token) {
        return accountRepository.deleteUserOrders(accountId, base64Token);
    }

    @Transactional(rollbackFor = Exception.class)
    public AccountStatusResponse dbRestoreFactorySettings() {
        try {
            AccountStatusResponse response = accountRepository.dbRestoreFactorySettings();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return new AccountStatusResponse(false, "Exception in dbRestoreFactorySettings" + e.toString(), -1);
        }
    }

    private List<PaymentPreferencesDto> fillPaymentPreferencesDto(Set<PaymentPreferences> paymentPreferences) {
        List<PaymentPreferencesDto> dtos = new ArrayList<>();
        for (PaymentPreferences item : paymentPreferences) {
            dtos.add(new PaymentPreferencesDto(item.getPaymentMethod(),
                    item.getCardNumber(),
                    item.getExpirationDate(),
                    item.getCvvNumber(),
                    item.getCustomerName(),
                    item.getSafePayUsername(),
                    item.getSafePayPassword(),
                    item.getUserId()));
        }

        return dtos;
    }
    private String encode64(String source){
        return Base64
                .getEncoder()
                .encodeToString(source.getBytes());
    }
    public static <T> T parseObjectFromString(String s, Class<T> clazz) throws Exception {
        return clazz.getConstructor(new Class[] {String.class }).newInstance(s);
    }

}
