package com.advantage.accountsoap.dao.impl;

import com.advantage.accountsoap.config.AccountConfiguration;
import com.advantage.accountsoap.dao.*;
import com.advantage.accountsoap.dto.DeleteOrderResponse;
import com.advantage.accountsoap.dto.account.AccountStatusResponse;
import com.advantage.accountsoap.dto.country.CountryID;
import com.advantage.accountsoap.model.*;
import com.advantage.accountsoap.util.AccountPassword;
import com.advantage.accountsoap.util.ArgumentValidationHelper;
import com.advantage.accountsoap.util.JPAQueryHelper;
import com.advantage.accountsoap.util.UrlResources;
import com.advantage.accountsoap.util.fs.FileSystemHelper;
import com.advantage.common.Constants;
import com.advantage.common.enums.AccountType;
import com.advantage.common.security.SecurityTools;
import com.advantage.common.security.Token;
import com.advantage.common.security.TokenJWT;

import com.advantage.accountsoap.util.RestApiHelper;
import com.advantage.common.utils.ValidationHelper;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
@Component
@Qualifier("accountRepository")
@Repository
public class DefaultAccountRepository extends AbstractRepository implements AccountRepository {

    private static final int TOTAL_ACCOUNTS_COUNT = 5;
    private static final int TOTAL_COUNTRIES_COUNT = 241;
    DefaultPaymentPreferencesRepository defaultPaymentPreferencesRepository;
    DefaultAddressRepository defaultAddressRepository;
    private AccountStatusResponse accountStatusResponse;
    private AccountRepository accountRepository;
    private String failureMessage;
    private static final Logger logger = Logger.getLogger(DefaultAccountRepository.class);

    @Autowired
    CountryRepository countryRepository;

    /*  Default application user configuration values - Begin   */
    //  3 failed login attempts will cause the user to be blocked for INTERVAL milliseconds.
    public static final int ENV_DEFAULT_NUMBER_OF_FAILED_LOGIN_ATTEMPTS_LIMIT = 3;

    //  Default 5 minutes
    public static final long ENV_DEFAULT_USER_LOGIN_ATTEMPTS_BLOCKED_INTERVAL = 300000;

    //  e-mail address is not mandatory in user details and does not take part in login/sign-in
    public static final String ENV_EMAIL_ADDRESS_IN_LOGIN = "NO";
    /*  Default application user configuration values - End     */

    public String getFailureMessage() {
        return this.failureMessage;
    }

    /**
     * Create a new {@link Account} in the database.
     * 1. Verify all parameters are not <code>null</code> or empty. <br/>
     * 2. Verify {@code loginName} comply with AOS policy. <br/>
     * 3. Verify {@code password} comply with AOS policy. <br/>
     * 4. Get country-id by country-name. <br/>
     * 5. Verify {@code phoneNumber} comply with AOS policy.
     * 6. Verify {@code email} contains a valid e-mail address. <br/>
     * <p>
     * Two more fields are managed and set internally: <br/>
     * unsuccessfulLoginAttempts Number of unsuccessful login attempts in a row made by the user. <br/>
     * userBlockedFromLoginUntil After user reached the limit of unsuccessful login attempts, he will be blocked for a period of time (set in application configuration). <br/>
     *
     * @param appUserType          User type: <b>10</b> = Administrator, <b>20</b> = User
     * @param lastName             User's last name
     * @param firstName            User's first name.
     * @param loginName            User login name, compliance with AOS policy.
     * @param password             User's password, compliance with AOS policy.
     * @param countryId            country-id of user's country of residence.
     * @param phoneNumber          Phone number including international country-code and area code.
     * @param stateProvince        State/province/region of residence.
     * @param cityName             City-name of residence.
     * @param address              postal address.
     * @param zipcode              new-user's zip-code of postal address.
     * @param email                New user's e-mail address.
     * @param allowOffersPromotion
     * @return {@link AccountStatusResponse} when successful:
     * <br/>
     * <b>{@code success}</b> = true, <b>{@code reason}</b> = &quat;New user created successfully&quat; <b>{@code userId}</b> = user-id of newly created user.
     * <br/>
     * if failed <b>{@code success}</b> = false, <b>{@code reason}</b> = failure reason, <b>{@code userId}</b> = -1.
     * <br/>
     */
    @Override
    public Account createAppUser(Integer appUserType, String lastName, String firstName, String loginName, String password, CountryID countryId, String phoneNumber, String stateProvince, String cityName, String address, String zipcode, String email, boolean allowOffersPromotion) {

        //  Validate Numeric Arguments
        ArgumentValidationHelper.validateArgumentIsNotNull(appUserType, "application user type");
        ArgumentValidationHelper.validateArgumentIsNotNull(countryId, "country id");

        ArgumentValidationHelper.validateNumberArgumentIsPositive(appUserType, "application user type");
//        ArgumentValidationHelper.validateNumberArgumentIsPositiveOrZero(countryId, "country id");

        //  Validate String Arguments - Mandatory columns
        ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(loginName, "login name");
        ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(password, "user password");
        ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(email, "email");
        ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(String.valueOf(allowOffersPromotion), "agree to receive offers and promotions");
        //ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(lastName, "last name");
        //ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(firstName, "first name");
        //ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(phoneNumber, "phone number");
        //ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(stateProvince, "state/provice/region");
        //ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(cityName, "city name");
        //ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(address, "address");
        //ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(zipcode, "zipcode");

        /*************** SQL injection vulnerability *****************/
        /*if(getAppUserByLoginName(loginName) == null){
            loginName +=loginName;
            //Moti Ostrovski: if not set countryID or equals 0=> set country USA
            countryId = countryId == 0 ? 40 : countryId;
            Country country = countryRepository.get(countryId);
            Account account = null;
            try {
                account = new Account(appUserType, lastName, firstName, loginName, password, country, phoneNumber, stateProvince, cityName, address, zipcode, email, allowOffersPromotion);
            } catch (Exception e) {
                e.printStackTrace();
            }
            entityManager.persist(account);


            //  New user created successfully.
            this.failureMessage = "New user created successfully";
            accountStatusResponse = new AccountStatusResponse(true, Account.MESSAGE_NEW_USER_CREATED_SUCCESSFULLY, account.getId());

            return account;
        } else{
            //  User with this login already exists
            this.failureMessage = "User name already exists";
            accountStatusResponse = new AccountStatusResponse(false, Account.MESSAGE_USER_NAME_ALREAY_EXISTS, -1);
            return null;

        }*/
        /*************** End Of SQL injection vulnerability *****************/



        /*************** Code without SQL injection vulnerability *****************/
        if (ValidationHelper.isValidLogin(loginName)) {
            if (ValidationHelper.isValidPassword(password)) {
                if (validatePhoneNumberAndEmail(phoneNumber, email)) {
                    if (getAppUserByLogin(loginName) == null) {

                        //Moti Ostrovski: if not set countryID or equals 0=> set country USA
                        countryId = countryId.equals("") ? CountryID.UNITED_STATES_US : countryId;
                        Country country = countryRepository.get(countryId.lValue());
                        Account account = null;
                        try {
                            account = new Account(appUserType, lastName, firstName, loginName, password, country, phoneNumber, stateProvince, cityName, address, zipcode, email, allowOffersPromotion);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        entityManager.persist(account);


                        //  New user created successfully.
                        this.failureMessage = "New user created successfully";
                        accountStatusResponse = new AccountStatusResponse(true, Account.MESSAGE_NEW_USER_CREATED_SUCCESSFULLY, account.getId());

                        return account;
                    } else {
                        //  User with this login already exists
                        this.failureMessage = "User name already exists";
                        accountStatusResponse = new AccountStatusResponse(false, Account.MESSAGE_USER_NAME_ALREAY_EXISTS, -1);
                        return null;

                    }
                } else {
                    //  accountStatusResponse is already set with values.
                    return null;
                }
            } else {
                //  Invalid password
                this.failureMessage = "Invalid password";
                accountStatusResponse = new AccountStatusResponse(false, Account.MESSAGE_USER_LOGIN_FAILED, -1);
                return null;
            }
        } else {
            //  Invalid login user-name.
            this.failureMessage = "Invalid login user-name";
            accountStatusResponse = new AccountStatusResponse(false, Account.MESSAGE_USER_LOGIN_FAILED, -1);
            return null;
        }

        /*************** End Of Code without SQL injection vulnerability *****************/

    }

    @Override
    public AccountStatusResponse updateAccount(long accountId, Integer accountType, String lastName, String firstName,
                                               CountryID countryId, String phoneNumber, String stateProvince, String cityName, String address,
                                               String zipcode, String email, boolean agreeToReceiveOffersAndPromotions) {
        ArgumentValidationHelper.validateArgumentIsNotNull(accountType, "application user type");
        ArgumentValidationHelper.validateArgumentIsNotNull(countryId, "country id");
        ArgumentValidationHelper.validateNumberArgumentIsPositive(accountType, "application user type");
        //  Validate String Arguments - Mandatory columns
        ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(email, "email");
        ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(String.valueOf(agreeToReceiveOffersAndPromotions), "agree to receive offers and promotions");
        ArgumentValidationHelper.validateArgumentIsNotNull(countryId, "country id");
        Account account = get(accountId);

        //Moti add validation fields
        ArgumentValidationHelper.validateFirstAndLastName(lastName);
        ArgumentValidationHelper.validateFirstAndLastName(firstName);
        ArgumentValidationHelper.validateCityName(cityName);
        ArgumentValidationHelper.validateStateProvice(stateProvince);
        ArgumentValidationHelper.validateAddress(address);
        ArgumentValidationHelper.validatePostalCode(zipcode);


        if (account == null) {
            return new AccountStatusResponse(false, "Invalid login user-name", -1);
        }

        if (!validatePhoneNumberAndEmail(phoneNumber, email)) {
            return new AccountStatusResponse(false,
                    "Invalid phone number or email",
                    account.getId());
        }

        //Moti Ostrovski: if not set countryID or equals 0=> set country USA
        countryId = countryId == null || countryId.lValue() == 0  ? CountryID.UNITED_STATES_US : countryId;
        Country country = countryRepository.get(countryId.lValue());

        account.setAccountType(accountType);
        account.setLastName(lastName);
        account.setFirstName(firstName);
        account.setCountry(country);
        account.setPhoneNumber(phoneNumber);
        account.setStateProvince(stateProvince);
        account.setCityName(cityName);
        account.setAddress(address);
        account.setZipcode(zipcode);
        account.setEmail(email);
        account.setAllowOffersPromotion(agreeToReceiveOffersAndPromotions);

        updateAppUser(account);

        return new AccountStatusResponse(true,
                "Account updated successfully",
                account.getId());
    }

    @Override
    public AccountStatusResponse create(Integer appUserType, String lastName, String firstName, String loginName,
                                        String password, CountryID countryId, String phoneNumber, String stateProvince,
                                        String cityName, String address, String zipcode, String email,
                                        boolean allowOffersPromotion) {
        //Moti add validation fields
        try{
            ArgumentValidationHelper.validateFirstAndLastName(lastName);
            ArgumentValidationHelper.validateFirstAndLastName(firstName);
            ArgumentValidationHelper.validateCityName(cityName);
            ArgumentValidationHelper.validateStateProvice(stateProvince);
            ArgumentValidationHelper.validateAddress(address);
            ArgumentValidationHelper.validatePostalCode(zipcode);
        } catch (IllegalArgumentException e){
            e.printStackTrace();
            return new AccountStatusResponse(false,
                    e.getMessage(),
                    -1);
        }
        
        Account account = createAppUser(appUserType, lastName, firstName, loginName, password, countryId, phoneNumber,
                stateProvince, cityName, address, zipcode, email, allowOffersPromotion);

        return new AccountStatusResponse(accountStatusResponse.isSuccess(),
                accountStatusResponse.getReason(),
                accountStatusResponse.getUserId());
    }

    @Override
    public int deleteAccount(Account account) {
        ArgumentValidationHelper.validateArgumentIsNotNull(account, "application user");

        Long userId = account.getId();

//        String hql = JPAQueryHelper.getDeleteByPkFieldQuery(Account.class,
//                Account.FIELD_ID,
//                userId);
//        Query query = entityManager.createQuery(hql);
//
//        return query.executeUpdate();

        if (account == null) {
            return 0;
        }

        account.setActive('N');

        entityManager.persist(account);

        return 1;
    }

    @Override
    public int deleteAccountPermanently(Account account) {
        try {
            ArgumentValidationHelper.validateArgumentIsNotNull(account, "application user");

            if (account == null) {
                return 0;
            }
            long accountId = account.getId();
            final StringBuilder hql = new StringBuilder("DELETE FROM ")
                    .append("Account")
                    .append(" WHERE ")
                    .append(Account.FIELD_ID).append("=").append(accountId);

            Query query = entityManager.createQuery(hql.toString());

            int result = query.executeUpdate();


            return result;

            } catch(Exception e){
                e.printStackTrace();
                return 1;
            }
        }

    @Override
    public List<Account> getAppUsersByCountry(Integer countryId) {
        List<Account> accounts = entityManager.createNamedQuery(Account.QUERY_GET_USERS_BY_COUNTRY, Account.class)
                .setParameter(Account.PARAM_COUNTRY, countryId)
                .setMaxResults(Account.MAX_NUM_OF_APP_USER)
                .getResultList();

        return accounts.isEmpty() ? null : accounts;
    }

    @Override
    public Account getAppUserByLogin(String userLogin) {
        ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(userLogin, "user login name");

        final Query query = entityManager.createNamedQuery(Account.QUERY_GET_BY_USER_LOGIN);

        query.setParameter(Account.PARAM_USER_LOGIN, userLogin);

        @SuppressWarnings("unchecked")
        List<Account> accounts = query.getResultList();

        final Account user;

        if (accounts.isEmpty()) {

            user = null;
        } else {

            user = accounts.get(0);
        }

        return user;

    }

    /*SQL Injection US#174005*/
    public Account getAppUserByLoginName(String userLogin) {

        final Query query = entityManager.createNativeQuery("Select * from Account  where login_name=" + userLogin);

        @SuppressWarnings("unchecked")
        List<Account> accounts = query.getResultList();

        final Account user;

        if (accounts.isEmpty()) {

            user = null;
        } else {

            user = accounts.get(0);
        }

        return user;

    }

    @Override
    public AccountStatusResponse doLogin(String loginUser, String loginPassword, String email) {
        //  Check arguments: Not NULL and Not BLANK
        if ((loginUser.isEmpty()) || (loginUser.length() == 0)) {
            return new AccountStatusResponse(false, Account.MESSAGE_USER_LOGIN_FAILED, -1);
        }

        if ((loginPassword == null) || (loginPassword.length() == 0)) {
            return new AccountStatusResponse(false, Account.MESSAGE_USER_LOGIN_FAILED, -1);
        }

        //if (AccountConfiguration.EMAIL_ADDRESS_IN_LOGIN.equalsIgnoreCase("Yes")) {
        //    if ((email == null) || (email.length() == 0)) {
        //        return new AccountStatusResponse(false, Account.MESSAGE_INVALID_EMAIL_ADDRESS, -1);
        //    }
        //}

        //  Try to get user details by login user-name
        Account account = getAppUserByLogin(loginUser);

        if (account == null) {
            //  Invalid user login.
            return new AccountStatusResponse(false, Account.MESSAGE_USER_LOGIN_FAILED, -1);
        }

        final long currentTimestamp = new Date().getTime();
        if (account.getInternalUserBlockedFromLoginUntil() > 0) {

            if (account.getInternalUserBlockedFromLoginUntil() < currentTimestamp) {
                //  User is no longer blocked from attempting to login - Reset INTERNAL fields
                account.setInternalUnsuccessfulLoginAttempts(0);
                account.setInternalUserBlockedFromLoginUntil(0);
            }

            if (account.getInternalUserBlockedFromLoginUntil() >= currentTimestamp) {
                //  User is still blocked from login attempt
                logger.debug("User " + loginUser + " is currently blocked");
                return new AccountStatusResponse(false, Account.MESSAGE_USER_IS_BLOCKED_FROM_LOGIN, -1);
            }
        }

        if ((!loginPassword.isEmpty()) && (loginPassword.trim().length() > 0)) {
            AccountPassword accountPassword = new AccountPassword(loginUser, loginPassword);
            if (account.getPassword().compareTo(accountPassword.getEncryptedPassword()) != 0) {
                account = addUnsuccessfulLoginAttempt(account);
                return new AccountStatusResponse(false, Account.MESSAGE_USER_LOGIN_FAILED, account.getId());
            }
        } else {
            //  password is empty
            account = addUnsuccessfulLoginAttempt(account);
            return new AccountStatusResponse(false, Account.MESSAGE_USER_LOGIN_FAILED, account.getId());
        }

        //  Check/Verify email address only if it is CONFIGURED to be shown in LOGIN
        //if (AccountConfiguration.EMAIL_ADDRESS_IN_LOGIN.toUpperCase().equalsIgnoreCase("Yes")) {
        //    if ((!email.isEmpty()) && (email.trim().length() > 0)) {
        //        if ((!account.getEmail().isEmpty()) && (account.getEmail().trim().length() > 0)) {
        //            if (account.getEmail().compareToIgnoreCase(email) != 0) {
        //                //  email does not match the email set in user details
        //                account = addUnsuccessfulLoginAttempt(account);
        //                return new AccountStatusResponse(false, Account.MESSAGE_INVALID_EMAIL_ADDRESS, account.getId());
        //            }
        //        } else {
        //            //
        //            account = addUnsuccessfulLoginAttempt(account);
        //            return new AccountStatusResponse(false, Account.MESSAGE_NO_EMAIL_EXISTS_FOR_USER, account.getId());
        //        }
        //
        //    } else {
        //        return new AccountStatusResponse(false, Account.MESSAGE_LOGIN_EMAIL_ADDRESS_IS_EMPTY, account.getId());
        //    }
        //}

        //  Reset user-blocking
        account.setInternalUnsuccessfulLoginAttempts(0);
        account.setInternalUserBlockedFromLoginUntil(0);

        //  Update changes
        updateAppUser(account);

        //  Return: Successful login attempt
        return new AccountStatusResponse(true, "Login Successful", account.getId(),
                getToken(account.getId(), account.getLoginName(), AccountType.valueOfCode(account.getAccountType())
                ).generateToken());
    }

    /**
     * Currently there's nothing to do in BACK-END for Logout.
     */
    @Override
    public AccountStatusResponse doLogout(String accountId, String base64Token) {
        //  Check arguments: Not NULL and Not BLANK

        AccountStatusResponse accountStatusFailResponse = new AccountStatusResponse(false, Account.MESSAGE_USER_LOGOUT_FAILED, -1);

        if (accountId.isEmpty()) {
            logger.warn("accountId is empty");
            return accountStatusFailResponse;
        } else {
            logger.debug("accountId=" + accountId);
        }

        Account account = null;
        if (base64Token == null || base64Token.isEmpty()) {
            logger.error("Token is empty: " + System.lineSeparator() + accountStatusFailResponse.toString());
            return accountStatusFailResponse;
        } else {
            logger.debug("Token = " + base64Token);

            //  Try to get user details by login user-name
            //Account account = getAppUserByLogin(accountId);
            account = get(Long.valueOf(accountId));
            if (account == null) {
                logger.warn("User login not found: " + System.lineSeparator() + accountStatusFailResponse.toString());
                return accountStatusFailResponse;
            }
            logger.info("Account " + accountId + " was found");

            //  Remove "Bearer " or "Basic " prefix in the base64Token
            String receivedToken = base64Token.substring(base64Token.indexOf(' ') + 1);

            if (SecurityTools.isBasic(base64Token)){
                String[] loginPassword = SecurityTools.decodeBase64(receivedToken).split(":");
                if(!loginPassword[0].equals(account.getLoginName()) ||
                        !(new AccountPassword(loginPassword[0], loginPassword[1]))
                                .getEncryptedPassword().equals(account.getPassword())){
                    logger.error("Wrong token: " + System.lineSeparator() + accountStatusFailResponse.toString());
                    return accountStatusFailResponse;
                }
            } else {
                Token token = getToken(account.getId(), account.getLoginName(), AccountType.valueOfCode(account.getAccountType()));
                if (!token.generateToken().equals(receivedToken)) {
                    logger.error("Wrong token: " + System.lineSeparator() + accountStatusFailResponse.toString());
                    return accountStatusFailResponse;
                }
            }
        }

        //  Return: Successful logout attempt, no need to create JWT Token
        AccountStatusResponse accountStatusSuccessResponse = new AccountStatusResponse(true, "Logout Successful", account.getId());
        logger.info("Successful logout attempt for account id = " + accountId + System.lineSeparator() + accountStatusSuccessResponse.toString());
        return accountStatusSuccessResponse;
    }

    private boolean validatePhoneNumberAndEmail(final String phoneNumber, final String email) {
        ////  Check phone number validation if not null
        //if ((phoneNumber != null) && (phoneNumber.trim().length() > 0)) {
        //    if (!ValidationHelper.isValidPhoneNumber(phoneNumber)) {
        //        accountStatusResponse = new AccountStatusResponse(false, "Invalid phone number", -1);
        //        return false;
        //    }
        //}

        //  Check e-mail address validation if not null
        if (email != null) {
            if (!ValidationHelper.isValidEmail(email)) {
                accountStatusResponse = new AccountStatusResponse(false, "Invalid e-mail address", -1);
                return false;
            }
        }

        return true;
    }

    @Override
    public Account addUnsuccessfulLoginAttempt(Account account) {
        //  Another unsuccessful (failed) login attempt
        logger.debug("Logon attempt for user " + account.getLoginName() + " = " + (account.getInternalUnsuccessfulLoginAttempts() + 1));
        account.setInternalUnsuccessfulLoginAttempts(account.getInternalUnsuccessfulLoginAttempts() + 1);

        //  Check the number of unsuccessful login attempts, block user if reached the limit
        //if (accountsoap.getInternalUnsuccessfulLoginAttempts() == ENV_DEFAULT_NUMBER_OF_FAILED_LOGIN_ATTEMPTS_LIMIT) {
        if (account.getInternalUnsuccessfulLoginAttempts() == AccountConfiguration.getNumberOfLoginAttemptsBeforeBlocking()) {
            logger.debug("User " + account.getLoginName() + " is blocked after " + (account.getInternalUnsuccessfulLoginAttempts() + 1) + " unsuccessful logon attempts ");
            //  Update Account class with timestamp when user can attempt login again according to configuration interval
            account.setInternalUserBlockedFromLoginUntil(Account.addMillisecondsIntervalToTimestamp((AccountConfiguration.getLoginBlockingIntervalInSeconds() * 1000)));
        }

        //  Update data changes made for application user into application users table
        account = updateAppUser(account);

        return account;
    }

    @Override
    public String getBlockedUntilTimestamp(long milliSeconds) {
        //return Account.addMillisecondsIntervalToTimestamp(milliSeconds);
        return Account.convertMillisecondsDateToString(Account.addMillisecondsIntervalToTimestamp(milliSeconds));
    }

    /**
     * Update table with data-changes made to application user detail.
     *
     * @param account Application User to update changes.
     * @return Updated Application User class.
     */
    @Override
    public Account updateAppUser(Account account) {
        entityManager.persist(account);
        return account;
    }

    private Token getToken(long accountId, String loginName, AccountType accountType) {
        return TokenJWT.createToken(accountId, loginName, accountType);
    }

    @Override
    public int delete(Account... entities) {
        for (Account account : entities) {
            //entityManager.remove(account);
            account.setActive('N');
            entityManager.persist(account);
        }
        return 0;
    }

    @Override
    public Account delete(Long id) {
        Account account = this.get(id);
        if (account == null) {
            return null;
        }

        account.setActive('N');
        entityManager.remove(account);

        return account;
    }

    @Override
    public List<Account> getAll() {
        List<Account> accounts = entityManager.createNamedQuery(Account.QUERY_GET_ALL, Account.class)
                .setMaxResults(Account.MAX_NUM_OF_APP_USER)
                .getResultList();

        return accounts.isEmpty() ? null : accounts;
    }

    @Override
    public Account get(Long entityId) {
        ArgumentValidationHelper.validateArgumentIsNotNull(entityId, "user id");

        //String hql = JPAQueryHelper.getSelectByPkFieldQuery(Account.class, Account.FIELD_ID, entityId);
        String hql = JPAQueryHelper.getSelectActiveByPkFieldQuery(Account.class, Account.FIELD_ID, entityId);

        Query query = entityManager.createQuery(hql);

        return (Account) query.getSingleResult();
    }

    @Override
    public AccountStatusResponse changePassword(long accountId, String newPassword) {
        ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(newPassword, "user password");
        if (!ValidationHelper.isValidPassword(newPassword)) {
            return new AccountStatusResponse(false, "Invalid password", -1);
        }
        Account account = get(accountId);
        if (account == null) {
            return new AccountStatusResponse(false, "Account not found", -1);
        }
        account.setPassword(newPassword);
        entityManager.persist(account);
        return new AccountStatusResponse(true, "Successfully", accountId);
    }
    @Override
    public AccountStatusResponse removePaymentPreferences(long accountId, long preferenceId) {

        final StringBuilder hql = new StringBuilder("DELETE FROM ")
                .append(PaymentPreferences.class.getName())
                .append(" WHERE ")
                .append(PaymentPreferences.FIELD_USER_ID).append("=").append(accountId);

        Query query = entityManager.createQuery(hql.toString());
        int result = query.executeUpdate();

        AccountStatusResponse accountStatusResponse;
        if (result == 1) {
            accountStatusResponse = new AccountStatusResponse(true, "Successfully", accountId);
        } else {
            accountStatusResponse = new AccountStatusResponse(false, "Payment preferences not deleted", accountId);
        }

        return accountStatusResponse;
    }
    //Will return success if there are no payment preferences for the user
    //even if non were deleted
    @Override
    public void deleteAllPaymentPreferences(long accountId) {


            final StringBuilder hql = new StringBuilder("DELETE FROM ")
                    .append(PaymentPreferences.class.getName())
                    .append(" WHERE ")
                    .append(PaymentPreferences.FIELD_USER_ID).append("=").append(accountId);

            Query query = entityManager.createQuery(hql.toString());
            query.executeUpdate();

    }

    @Override
    public void deleteShippingAddress(long userId) {


            final StringBuilder deleteShippingAddress = new StringBuilder("DELETE FROM ")
                    .append("ShippingAddress")
                    .append(" WHERE ")
                    .append(ShippingAddress.FIELD_USER_ID).append("=").append(userId);
            Query queryDelete = entityManager.createQuery(deleteShippingAddress.toString());

            queryDelete.executeUpdate();

    }

    @Override
    public AccountStatusResponse deleteUserOrders(long userId, String data) {


        String stringResponse = null;
        URL deleteOrdersForUser = null;
        URL orderApiUrl = UrlResources.getUrlOrder();

        try {

            deleteOrdersForUser = new URL(orderApiUrl + "orders/history/users/" + userId);
            stringResponse = RestApiHelper.httpGetWithAuthorization(deleteOrdersForUser, "account", "Authorization", data);
            logger.debug("stringResponse--" + stringResponse);

            switch (stringResponse) {
                case "CONFLICT":
                    return new AccountStatusResponse(false, "UserOrders not deleted server returned: CONFLICT", userId);

                case "NOT FOUND":
                    return new AccountStatusResponse(false, "UserOrders not deleted server returned: NOT FOUND", userId);

                case "FORBIDDEN":
                    return new AccountStatusResponse(false, "UserOrders not deleted server returned: FORBIDDEN", userId);

                case "UNAUTHORIZED":
                    return new AccountStatusResponse(false, "UserOrders not deleted server returned: UNAUTHORIZED", userId);

            }
            //Removing added value by apiHelper
            String stringResponseEdited = "{\"" + stringResponse.substring(17);
            ObjectMapper objectMapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            DeleteOrderResponse deleteOrderResponse = objectMapper.readValue(stringResponseEdited, DeleteOrderResponse.class);

            if(deleteOrderResponse.isSuccess()){
                return new AccountStatusResponse(true, "UserOrders were deleted successfully", userId);
            }
            else{
                return new AccountStatusResponse(true, "UserOrders were deleted successfully", userId);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            return new AccountStatusResponse(false, "UserOrders not deleted server returned: "+e, userId);
        }
        return new AccountStatusResponse(false, "UserOrders not deleted ", userId);
    }

    public AccountStatusResponse dbRestoreFactorySettings() {

        SessionFactory sessionFactory = entityManager.getEntityManagerFactory().unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        //  region TRUNCATE_ACCOUNT_SERVICE_TABLES()
        String resultTruncate = (String) entityManager.createNativeQuery("SELECT truncate_account_service_tables()")
                .getSingleResult();
        transaction.commit();
        session.flush();
        session.close();
        StringBuilder sb = new StringBuilder("Database Restore Factory Settings - ACCOUNT-SERVICE schema truncated successfully. ");
        System.out.println("Database Restore Factory Settings - ACCOUNT-SERVICE schema truncated successfully");
        logger.info("Database Restore Factory Settings - ACCOUNT-SERVICE schema truncated successfully");
        //  endregion
        sb.append("Database Restore Factory Settings: ");
        //  region COUNTRY
        Map<Long, Country> countryMap = new HashMap<>();
        try {
            ClassPathResource filePathCSV = new ClassPathResource("countries_20150630.csv");
            String protocol = this.getClass().getResource("").getProtocol();

            List<String> countries;
            if(protocol.contains("jar")){
                countries = FileSystemHelper.readFileCsv(null, true, filePathCSV.getInputStream());
            } else {
                countries = FileSystemHelper.readFileCsv(filePathCSV.getFile().getAbsolutePath(), false, null);
            }
            for (String str : countries) {
                String[] substrings = str.split(",");
                Country country = new Country(substrings[1], substrings[2], Integer.valueOf(substrings[3]));
                entityManager.persist(country);
                countryMap.put(country.getId(), country);
            }

            if (countryRepository.getAllCountries().size() == TOTAL_COUNTRIES_COUNT) {
                sb.append("Country").append(Constants.COMMA).append(Constants.SPACE);
                System.out.println("Database Restore Factory Settings successful - table 'country'");
                logger.info("Database Restore Factory Settings successful - table 'country'");
            } else {
                sb.append("Table 'Country' - FAILED").append(Constants.COMMA).append(Constants.SPACE);
                System.out.println("Database Restore Factory Settings - table 'country' - FAILED");
                logger.error("Database Restore Factory Settings - table 'country' - FAILED");
                return new AccountStatusResponse(false, "Database Restore Factory Settings - table 'country'", -1);
            }
        } catch (IOException e) {
            e.printStackTrace();
            sb.append("Table 'Country' - FAILED with Exception").append(Constants.COMMA).append(Constants.SPACE);
            System.out.println("Database Restore Factory Settings - table 'country' - FAILED with Exception");
            logger.error("Database Restore Factory Settings - table 'country' - FAILED with Exception");
            return new AccountStatusResponse(false, "Database Restore Factory Settings - table 'country' FAILED with Exception", -1);
        }


        //  region ACCOUNT
        try {
            entityManager.persist(new Account(AccountType.ADMIN.getAccountTypeCode(), "Mercury", "Admin User", "Mercury", "Mercury", countryMap.get(10L), "077-7654321", "Jerusalem1", "Jerusalem", "address", "9876543", "mercury@microfocus.com", true));
            entityManager.persist(new Account(AccountType.ADMIN.getAccountTypeCode(), "Smith", "John", "admin", "adm1n", countryMap.get(40L), "480-222-1111", "NY", "New York", "address", "10017", "admin@admin.ad", true));
            entityManager.persist(new Account(AccountType.USER.getAccountTypeCode(), "Gilat", "Naor", "gilat", "gG123", countryMap.get(128L), "052-7654321", "Jerusalem1", "Jerusalem", "address", "9876543", "d0r1@gmail.com", true));
            entityManager.persist(new Account(AccountType.USER.getAccountTypeCode(), "Bukhantsov", "Kostya", "kostya", "kostya", countryMap.get(10L), "052-22222222", "Jerusalem1", "Jerusalem", "address", "9876543", "kostya@gmail.com", true));
            entityManager.persist(new Account(AccountType.ADMIN.getAccountTypeCode(), "Brown", "John", "AppPulse", "AppPulse1", countryMap.get(40L), "617-527-5555", "MA", "Newton", "826 Morseland Ave.", "02458", "AppPlusedemo@aos.ad", true));

            if (this.getAll().size() == TOTAL_ACCOUNTS_COUNT) {
                sb.append("Account").append(Constants.COMMA).append(Constants.SPACE);
                System.out.println("Database Restore Factory Settings successful - table 'account'");
                logger.info("Database Restore Factory Settings successful - table 'account'");
            } else {
                sb.append("Table 'account' - FAILED").append(Constants.COMMA).append(Constants.SPACE);
                System.out.println("Database Restore Factory Settings - table 'account' - FAILED");
                logger.error("Database Restore Factory Settings - table 'account' - FAILED");
                return new AccountStatusResponse(false, "Database Restore Factory Settings - table 'account'", -1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            sb.append("Table 'Account' - FAILED with Exception").append(Constants.COMMA).append(Constants.SPACE);
            System.out.println("Database Restore Factory Settings - table 'account' - FAILED with Exception");
            logger.error("Database Restore Factory Settings - table 'account' - FAILED with Exception");
            return new AccountStatusResponse(false, "Restore factory settings FAILED - ACCOUNT table", -1);
        }
        return new AccountStatusResponse(true, sb.toString(), 1);
    }

    private String encode64(String source){
        return Base64
                .getEncoder()
                .encodeToString(source.getBytes());
    }
}
