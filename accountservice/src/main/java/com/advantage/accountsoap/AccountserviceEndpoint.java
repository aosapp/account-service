package com.advantage.accountsoap;

import com.advantage.accountsoap.config.WebServiceConfig;
import com.advantage.accountsoap.dto.IAdminRequest;
import com.advantage.accountsoap.dto.IUserRequest;
import com.advantage.accountsoap.dto.account.*;
import com.advantage.accountsoap.dto.account.internal.AccountDto;
import com.advantage.accountsoap.dto.account.internal.AccountDtoNew;
import com.advantage.accountsoap.dto.address.*;
import com.advantage.accountsoap.dto.country.*;
import com.advantage.accountsoap.dto.payment.*;
import com.advantage.accountsoap.model.Account;
import com.advantage.accountsoap.services.*;
import com.advantage.accountsoap.util.AccountPassword;
import com.advantage.common.Constants;
import com.advantage.common.enums.AccountType;
import com.advantage.common.exceptions.token.TokenException;
import com.advantage.common.exceptions.token.VerificationTokenException;
import com.advantage.common.security.SecurityTools;
import com.advantage.common.security.Token;
import com.advantage.common.security.TokenJWT;
import com.advantage.root.util.StringHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Endpoint
public class AccountserviceEndpoint {

    //  region Accountservice Services
    @Autowired
    private AccountService accountService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private PaymentPreferencesService paymentPreferencesService;

    @Autowired
    private AccountConfigurationService accountConfigurationService;

    @Autowired
    private DynamicConfiguration dynamicConfiguration;

    private static final Logger logger = Logger.getLogger(AccountserviceEndpoint.class);
    private int loggedUsers;

    public AccountserviceEndpoint() {
        logger.info(" *********************************** \n" +
                " ****** Account service start ****** \n" +
                " *********************************** ");
    }

    //  endregion
    //region Account
    @PayloadRoot(namespace = WebServiceConfig.NAMESPACE_URI, localPart = "GetAllAccountsRequest")
    @ResponsePayload
    public GetAllAccountsResponse getAllAccounts(@RequestPayload GetAllAccountsRequest request) throws TokenException {
        logger.debug(AccountserviceEndpoint.class.getName() + ".getAllAccounts(GetAllAccountsRequest) is calling method authorizeAsUser(..)");
        //  TODO-Benny: Verify that .NET and mobile version support this authorization
        //authorizeAsAdmin(request);
        if (logger.isDebugEnabled()) {
            logger.debug(request == null ? "Request is null" : request.toString());
        }
        List<AccountDto> appUsers = accountService.getAllAppUsersDto();
        GetAllAccountsResponse getAllAccountsResponse = new GetAllAccountsResponse();
        getAllAccountsResponse.setAccount(appUsers);

        return getAllAccountsResponse;
    }

    @PayloadRoot(namespace = WebServiceConfig.NAMESPACE_URI, localPart = "GetAccountByIdRequest")
    @ResponsePayload
    public GetAccountByIdResponse getAccount(@RequestPayload GetAccountByIdRequest accountRequest) throws TokenException {
        logger.debug(AccountserviceEndpoint.class.getName() + ".getAccount(GetAccountByIdRequest) is calling method authorizeAsUser(..)");
        //  TODO-Benny: Verify that .NET and mobile version support this authorization
        //authorizeAsUser(accountRequest);
        Account account = accountService.getById(accountRequest.getAccountId());
        if (account == null) {
            return null;
        }
        AccountDto dto = new AccountDto(account.getId(),
                account.getLastName(),
                account.getFirstName(),
                account.getLoginName(),
                account.getAccountType(),
                account.getCountry().getId(),
                account.getCountry().getName(),
                account.getCountry().getIsoName(),
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
                account.getPassword());

        return new GetAccountByIdResponse(dto);
    }

    @PayloadRoot(namespace = WebServiceConfig.NAMESPACE_URI, localPart = "GetAccountByLoginRequest")
    @ResponsePayload
    public GetAccountByLoginResponse getAccount(@RequestPayload GetAccountByLoginRequest accountRequest) throws TokenException {
        logger.debug(AccountserviceEndpoint.class.getName() + ".getAccount(GetAccountByLoginRequest) is calling method authorizeAsUser(..)");
        //  TODO-Benny: Verify that .NET and mobile version support this authorization
        //authorizeAsUser(accountRequest);
        Account account = accountService.getAppUserByLogin(accountRequest.getUserName());
        if (account == null) {
            return null;
        }
        AccountDto dto = new AccountDto(account.getId(),
                account.getLastName(),
                account.getFirstName(),
                account.getLoginName(),
                account.getAccountType(),
                account.getCountry().getId(),
                account.getCountry().getName(),
                account.getCountry().getIsoName(),
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
                account.getPassword());

        return new GetAccountByLoginResponse(dto);
    }

    @PayloadRoot(namespace = WebServiceConfig.NAMESPACE_URI, localPart = "GetAccountByIdNewRequest")
    @ResponsePayload
    public GetAccountByIdNewResponse getAccount(@RequestPayload GetAccountByIdNewRequest accountByIdRequest) throws TokenException {
        logger.debug(AccountserviceEndpoint.class.getName() + ".getAccount(GetAccountByIdNewRequest) calling method authorizeAsUser(..)");
        logger.debug(AccountserviceEndpoint.class.getName() + ".getAccount(GetAccountByIdNewRequest) GetAccountByIdNewRequest = { " +
                "\"accountId\": " + accountByIdRequest.getAccountId() + ", " +
                "\"base64Token\": \"" + ( accountByIdRequest.getBase64Token() != null ? accountByIdRequest.getBase64Token() : "null" ) + "\", " +
                " }");
        //  TODO-Benny: Verify that .NET and mobile version support this authorization
        //authorizeAsUser(accountByIdRequest);
        Account account = accountService.getById(accountByIdRequest.getAccountId());
        if (account == null) return null;
        AccountDtoNew dto = new AccountDtoNew(account.getId(),
                account.getLastName(),
                account.getFirstName(),
                account.getLoginName(),
                account.getAccountType(),
                account.getCountry().getId(),
                account.getCountry().getName(),
                account.getCountry().getIsoName(),
                account.getStateProvince(),
                account.getCityName(),
                account.getAddress(),
                account.getZipcode(),
                account.getPhoneNumber(),
                account.getEmail(),
                account.getDefaultPaymentMethodId(),
                account.isAllowOffersPromotion(),
                account.getInternalUnsuccessfulLoginAttempts(),
                account.getInternalUserBlockedFromLoginUntil(),
                account.getInternalLastSuccesssulLogin());

        GetAccountByIdNewResponse getAccountByIdNewResponse = new GetAccountByIdNewResponse(dto);
        return getAccountByIdNewResponse;
    }

    @PayloadRoot(namespace = WebServiceConfig.NAMESPACE_URI, localPart = "AccountLoginRequest")
    @ResponsePayload
    public AccountLoginResponse doLogin(@RequestPayload AccountLoginRequest account) {
        //todo set header
        int delayRequest = dynamicConfiguration.getDelayLength(loggedUsers + 1);
        logger.debug("delayRequest = " + delayRequest + " sec.");
        try {
            Thread.sleep(delayRequest * 1000);
        } catch (InterruptedException e) {
            logger.fatal(e);
        }

        if (!dynamicConfiguration.needReject(loggedUsers + 1)) {
            AccountStatusResponse response = accountService.doLogin(account.getLoginUser(),
                    account.getLoginPassword(),
                    account.getEmail());

            if (response.isSuccess()) {
                Date date = new Date();
                StringBuilder sessionId = new StringBuilder(Long.toHexString(date.getTime()))
                        .append(Constants.AT_SIGN)
                        .append(StringHelper.convertDateToStringHexadecimal(date))
                        .append(Constants.POWER)
                        .append("i")
                        .append(Constants.MODULU)
                        .append(response.getUserId());

                response.setSessionId(sessionId.toString());
                response.setT_authorization(encode64(account.getLoginUser()+":"+account.getLoginPassword()));
                response.setAccountType(accountService
                                .getById(response.getUserId())
                                .getAccountType());
                loggedUsers++;
                return new AccountLoginResponse(response);
            } else {
                return new AccountLoginResponse(response);
            }

        } else {
            //TODO-EVG change message
            logger.warn("Reject login request");
            return new AccountLoginResponse(new AccountStatusResponse(false, "Maximum number logged users", -1));
        }
    }


    @PayloadRoot(namespace = WebServiceConfig.NAMESPACE_URI, localPart = "AccountLogoutRequest")
    @ResponsePayload
    public AccountLogoutResponse doLogout(@RequestPayload AccountLogoutRequest request) throws TokenException {
        logger.debug(AccountserviceEndpoint.class.getName() + ".doLogout(..) is calling method authorizeAsUser(..)");
        //  TODO-Benny: Verify that .NET and mobile version support this authorization
        //authorizeAsUser(request);
        AccountStatusResponse response = accountService.doLogout(request.getStrAccountId(),
                request.getBase64Token());

        //if (response.isSuccess()) {
        //TODO-ALEX set session
            /*
            HttpSession session = request.getSession();
            session.setAttribute(Constants.UserSession.TOKEN, response.getBase64Token());
            session.setAttribute(Constants.UserSession.USER_ID, response.getUserId());
            session.setAttribute(Constants.UserSession.IS_SUCCESS, response.isSuccess());

            //  Set SessionID to Response Entity
            //response.getHeader().
            response.setSessionId(session.getAccountId());
            response.setSessionId("session_id");
            */
//                return new AccountLogoutResponse(response);
//            } else {
//                return new AccountLogoutResponse(response);
//            }
        loggedUsers = loggedUsers > 0 ? loggedUsers - 1 : 0;
        logger.info("Current login users = " + loggedUsers);
        return new AccountLogoutResponse(response);

    }

    @PayloadRoot(namespace = WebServiceConfig.NAMESPACE_URI, localPart = "AccountCreateRequest")
    @ResponsePayload
    public AccountCreateResponse createAccount(@RequestPayload AccountCreateRequest account) {
        AccountStatusResponse response = accountService.create(
                account.getAccountType(),
                account.getLastName(),
                account.getFirstName(),
                account.getLoginName(),
                account.getPassword(),
                account.getCountry(),
                account.getPhoneNumber(),
                account.getStateProvince(),
                account.getCityName(),
                account.getAddress(),
                account.getZipcode(),
                account.getEmail(),
                account.isAllowOffersPromotion());

        return new AccountCreateResponse(response);
    }

    @PayloadRoot(namespace = WebServiceConfig.NAMESPACE_URI, localPart = "AccountUpdateRequest")
    @ResponsePayload
    public AccountUpdateResponse updateAccount(@RequestPayload AccountUpdateRequest request) throws TokenException {
        //authorizeAsUser(request);
        AccountStatusResponse response = accountService.updateAccount(
                request.getAccountId(),
                request.getAccountType(),
                request.getLastName(),
                request.getFirstName(),
                request.getCountry(),
                request.getPhoneNumber(),
                request.getStateProvince(),
                request.getCityName(),
                request.getAddress(),
                request.getZipcode(),
                request.getEmail(),
                request.isAllowOffersPromotion());
        return new AccountUpdateResponse(response);
    }

    @PayloadRoot(namespace = WebServiceConfig.NAMESPACE_URI, localPart = "ChangePasswordRequest")
    @ResponsePayload
    public ChangePasswordResponse changePassword(@RequestPayload ChangePasswordRequest request) throws TokenException {
        //Token's validation already implemented in accountService.changePassword;
        AccountStatusResponse response = accountService.changePassword(request.getAccountId(), request.getOldPassword(), request.getNewPassword(), request.getBase64Token());
        return new ChangePasswordResponse(response);
    }

    @PayloadRoot(namespace = WebServiceConfig.NAMESPACE_URI, localPart = "AccountDeleteRequest")
    @ResponsePayload
    public AccountDeleteResponse accountDelete(@RequestPayload AccountDeleteRequest request) throws TokenException {
        logger.debug(AccountserviceEndpoint.class.getName() + ".accountDelete(..) is calling method authorizeAsUser(..)");
        //TODO-Benny Change to  authorizeAsAdmin(request) after the clients will implement filling filed base64Token in SOAP
        //temp_authorizeAsAdmin(request);
        AccountStatusResponse response = accountService.accountDelete(request.getAccountId());
        return new AccountDeleteResponse(response);
    }

    @PayloadRoot(namespace = WebServiceConfig.NAMESPACE_URI, localPart = "EncodePasswordRequest")
    @ResponsePayload
    public EncodePasswordResponse encodePassword(@RequestPayload EncodePasswordRequest request) throws TokenException{
        logger.debug(AccountserviceEndpoint.class.getName() + ".encodePassword(..) is calling method authorizeAsUser(..)");
        //  TODO-Benny: Verify that .NET and mobile version support this authorization
        //authorizeAsUser(request);
        AccountPassword accountPassword = new AccountPassword(request.getUserName(), request.getPassword());
        return new EncodePasswordResponse(accountPassword.getEncryptedPassword());
    }
    //endregion
    //region Countries
    @PayloadRoot(namespace = WebServiceConfig.NAMESPACE_URI, localPart = "GetCountriesRequest")
    @ResponsePayload
    public GetCountriesResponse getCountries() {
        GetCountriesResponse response = new GetCountriesResponse();
        response.setCountry(countryService.getAllCountries());

        return response;
    }

    @PayloadRoot(namespace = WebServiceConfig.NAMESPACE_URI, localPart = "CountryCreateRequest")
    @ResponsePayload
    public CountryCreateResponse createCountry(@RequestPayload CountryCreateRequest request) throws TokenException {
        logger.debug(AccountserviceEndpoint.class.getName() + ".createCountry(..) is calling method authorizeAsUser(..)");
        //  TODO-Benny: Verify that .NET and mobile version support this authorization
        //authorizeAsAdmin(request);
        CountryStatusResponse response = countryService.create(request.getName(),
                request.getIsoName(),
                request.getPhonePrefix());

        return new CountryCreateResponse(response);
    }

    @PayloadRoot(namespace = WebServiceConfig.NAMESPACE_URI, localPart = "CountrySearchRequest")
    @ResponsePayload
    public CountrySearchResponse searchInCountries(@RequestPayload CountrySearchRequest request) {
        List<CountryDto> countries;

        if (request == null) throw new IllegalArgumentException("Not valid parameters");
        if (!request.getStartOfName().isEmpty() && request.getInternationalPhonePrefix() != 0) {
            throw new IllegalArgumentException("Not valid parameters");
        } else if (request.getInternationalPhonePrefix() > 0) {
            countries = countryService.getCountriesByPhonePrefix(request.getInternationalPhonePrefix());
        } else {
            countries = countryService.getCountriesByPartialName(request.getStartOfName().toUpperCase());
        }
        if (countries == null || countries.isEmpty()) {
            return null;
        } else {
            return new CountrySearchResponse(countries);
        }
    }

    //endregion
    //region Address
    @PayloadRoot(namespace = WebServiceConfig.NAMESPACE_URI, localPart = "GetAddressesByAccountIdRequest")
    @ResponsePayload
    public GetAddressesByAccountIdResponse getAccountShippingAddress(@RequestPayload GetAddressesByAccountIdRequest request) throws TokenException {
        logger.debug(AccountserviceEndpoint.class.getName() + ".getAccountShippingAddress(..) is calling method authorizeAsUser(..)");
        //  TODO-Benny: Verify that .NET and mobile version support this authorization
        //authorizeAsUser(request);
        if (logger.isTraceEnabled()) {
            logger.trace(request == null ? "Request is null" : request.toString());
        }
        GetAddressesByAccountIdResponse response = new GetAddressesByAccountIdResponse();
        List<AddressDto> addresses = addressService.getByAccountId(request.getAccountId());
        response.setShippingAddress(addresses);

        return response;
    }

    @PayloadRoot(namespace = WebServiceConfig.NAMESPACE_URI, localPart = "AddAddressesRequest")
    @ResponsePayload
    public AddAddressesResponse addAddress(@RequestPayload AddAddressesRequest request) throws TokenException {
        logger.debug(AccountserviceEndpoint.class.getName() + ".addAddress(..) is calling method authorizeAsUser(..)");
        //  TODO-Benny: Verify that .NET and mobile version support this authorization
        //authorizeAsUser(request);
        AddressStatusResponse response = addressService.add(request.getAccountId(), request.getAddresses());
        return new AddAddressesResponse(response);
    }

    @PayloadRoot(namespace = WebServiceConfig.NAMESPACE_URI, localPart = "AddressUpdateRequest")
    @ResponsePayload
    public AddressUpdateResponse updateAddress(@RequestPayload AddressUpdateRequest request) throws TokenException {
        logger.debug(AccountserviceEndpoint.class.getName() + ".updateAddress(..) is calling method authorizeAsUser(..)");
        //  TODO-Benny: Verify that .NET and mobile version support this authorization
        //authorizeAsUser(request);
        AddressStatusResponse response = addressService.update(request.getAddress());

        return new AddressUpdateResponse(response);
    }
    //endregion

    //region Payment
    //  region MasterCredit
    @PayloadRoot(namespace = WebServiceConfig.NAMESPACE_URI, localPart = "AddMasterCreditMethodRequest")
    @ResponsePayload
    public AddMasterCreditMethodResponse addMasterCreditMethod(@RequestPayload AddMasterCreditMethodRequest request) throws TokenException {
        logger.debug(AccountserviceEndpoint.class.getName() + ".addMasterCreditMethod(..) is calling method authorizeAsUser(..)");
        //  TODO-Benny: Verify that .NET and mobile version support this authorization
        //authorizeAsUser(request);
        PaymentPreferencesStatusResponse response = paymentPreferencesService.addMasterCreditMethod(request.getCardNumber(),
                request.getExpirationDate(), request.getCvvNumber(), request.getCustomerName(), request.getAccountId());

        return new AddMasterCreditMethodResponse(response);
    }

    @PayloadRoot(namespace = WebServiceConfig.NAMESPACE_URI, localPart = "UpdateMasterCreditMethodRequest")
    @ResponsePayload
    public UpdateMasterCreditMethodResponse updateMasterCreditMethod(@RequestPayload UpdateMasterCreditMethodRequest request) throws TokenException {
        logger.debug(AccountserviceEndpoint.class.getName() + ".updateMasterCreditMethod(..) is calling method authorizeAsUser(..)");
        //  TODO-Benny: Verify that .NET and mobile version support this authorization
        //authorizeAsUser(request);
        PaymentPreferencesStatusResponse response = paymentPreferencesService.updateMasterCreditMethod(request.getUserId(),
                request.getCardNumber(), request.getExpirationDate(), request.getCvvNumber(), request.getCustomerName(), request.getReferenceId());

        return new UpdateMasterCreditMethodResponse(response);
    }

    //endregion
    //  region SafePay
    @PayloadRoot(namespace = WebServiceConfig.NAMESPACE_URI, localPart = "AddSafePayMethodRequest")
    @ResponsePayload
    public AddSafePayMethodResponse addSafePayMethod(@RequestPayload AddSafePayMethodRequest request) throws TokenException {
        logger.debug(AccountserviceEndpoint.class.getName() + ".addSafePayMethod(..) is calling method authorizeAsUser(..)");
        //  TODO-Benny: Verify that .NET and mobile version support this authorization
        //authorizeAsUser(request);
        PaymentPreferencesStatusResponse response = paymentPreferencesService.addSafePayMethod(request.getAccountId(),
                request.getSafePayUsername(),
                request.getSafePayPassword());

        return new AddSafePayMethodResponse(response);
    }

    @PayloadRoot(namespace = WebServiceConfig.NAMESPACE_URI, localPart = "UpdateSafePayMethodRequest")
    @ResponsePayload
    public UpdateSafePayMethodResponse updateSafePayMethod(@RequestPayload UpdateSafePayMethodRequest request) throws TokenException {
        logger.debug(AccountserviceEndpoint.class.getName() + ".updateSafePayMethod(..) is calling method authorizeAsUser(..)");
        //  TODO-Benny: Verify that .NET and mobile version support this authorization
        //authorizeAsUser(request);
        PaymentPreferencesStatusResponse response = paymentPreferencesService.updateSafePayMethod(request.getUserId(),
                request.getSafePayUsername(),
                request.getSafePayPassword());


        return new UpdateSafePayMethodResponse(response);
    }
    //endregion

    @PayloadRoot(namespace = WebServiceConfig.NAMESPACE_URI, localPart = "PaymentMethodUpdateRequest")
    @ResponsePayload
    public PaymentMethodUpdateResponse updateDefaultPaymentMethod(@RequestPayload PaymentMethodUpdateRequest request) throws TokenException {
        logger.debug(AccountserviceEndpoint.class.getName() + ".updateDefaultPaymentMethod(..) is calling method authorizeAsUser(..)");
        //  TODO-Benny: Verify that .NET and mobile version support this authorization
        //authorizeAsUser(request);
        AccountStatusResponse response = accountService.updateDefaultPaymentMethod(request.getAccountId(), request.getPaymentMethod());

        return new PaymentMethodUpdateResponse(response);
    }

    @PayloadRoot(namespace = WebServiceConfig.NAMESPACE_URI, localPart = "GetAccountPaymentPreferencesRequest")
    @ResponsePayload
    public GetAccountPaymentPreferencesResponse getAccountPaymentPreferences(@RequestPayload GetAccountPaymentPreferencesRequest request) throws TokenException {
        logger.debug(AccountserviceEndpoint.class.getName() + ".getAccountPaymentPreferences(..) is calling method authorizeAsUser(..)");
        //  TODO-Benny: Verify that .NET and mobile version support this authorization
        //authorizeAsUser(request);
        GetAccountPaymentPreferencesResponse response = new GetAccountPaymentPreferencesResponse();
        List<PaymentPreferencesDto> prefs = accountService.getPaymentPreferences(request.getAccountId());
        response.setPreferences(prefs);
        return response;
    }

    @PayloadRoot(namespace = WebServiceConfig.NAMESPACE_URI, localPart = "DeletePaymentPreferenceRequest")
    @ResponsePayload
    public DeletePaymentPreferenceResponse deletePaymentPreference(@RequestPayload DeletePaymentPreferenceRequest request) throws TokenException {
        logger.debug(AccountserviceEndpoint.class.getName() + ".deletePaymentPreference(DeletePaymentPreferenceRequest) is calling method authorizeAsUser(..)");
        //  TODO-Benny: Verify that .NET and mobile version support this authorization
        //authorizeAsUser(request);
        AccountStatusResponse response = accountService.removePaymentPreferences(request.getAccountId(), request.getId());

        return new DeletePaymentPreferenceResponse(response);
    }

    //endregion
    //region Configuration
    @PayloadRoot(namespace = WebServiceConfig.NAMESPACE_URI, localPart = "GetAccountConfigurationRequest")
    @ResponsePayload
    public GetAccountConfigurationResponse getAccountConfiguration() {
        GetAccountConfigurationResponse configurationResponse = accountConfigurationService.getAllConfigurationParameters();
        return configurationResponse;
    }
    //endregion

    private void authorizeAsAdmin(IAdminRequest request) throws TokenException {
        //temp_authorizeAsAdmin(request);
    }

    //TODO Change to  authorizeAsAdmin(request) after the clients will implement filling filed base64Token in SOAP
    private void temp_authorizeAsAdmin(IAdminRequest request) throws TokenException {
        if (request == null) {
            logger.error("Request is null");
            throw new IllegalArgumentException("Request is null");
        }
        String requestToken = request.getBase64Token();
        if (requestToken == null || requestToken.isEmpty()) {
            logger.error("temp_authorizeAsAdmin request.getBase64Token() returned Token is empty or null");
            throw new IllegalArgumentException("temp_authorizeAsAdmin: request.getBase64Token() returned Token is empty or null");
        }
        logger.debug("Token: " + requestToken);
        Token token = TokenJWT.parseToken(requestToken);

        if (!token.getAccountType().equals(AccountType.ADMIN)) {
            String message = "Your role is " + token.getAccountType().toString() + ", and haven't access right";
            logger.error(message);
            throw new VerificationTokenException(message);
        }
    }

    //TODO Enable after the clients will implement filling filed base64Token in SOAP
    private void authorizeAsUser(IUserRequest request) throws TokenException {
        if (request == null) {
            logger.error("Request is null");
            throw new IllegalArgumentException("Request is null");
        }
        String requestToken = request.getBase64Token();
        if (requestToken == null || requestToken.isEmpty()) {
            logger.error("authorizeAsUser: request.getBase64Token() returned Token is empty or null");
            throw new IllegalArgumentException("authorizeAsUser: request.getBase64Token() returned Token is empty or null");
        }
        boolean isBasic = SecurityTools.isBasic(requestToken);
        requestToken = requestToken.substring(requestToken.indexOf(" ")+1);
        logger.debug("Token: " + requestToken);
        if (isBasic){
            if (request instanceof GetAccountByLoginRequest){
                authorizeWithBasic(((GetAccountByLoginRequest) request).getUserName(), requestToken);
            } else {
                authorizeWithBasic(request.getAccountId(), requestToken);
            }
        } else {
            Token token = TokenJWT.parseToken(requestToken);
            authorizeWithBearer(request.getAccountId(), token);
        }
    }

    private void authorizeWithBasic(long requestAccountId, String requestToken) throws TokenException {
        Account account = accountService.getById(requestAccountId);
        verifyBasicToken(requestToken, account, ""+requestAccountId);
    }

    private void authorizeWithBasic(String appUserLogin, String requestToken) throws TokenException {
        Account account = accountService.getAppUserByLogin(appUserLogin);
        verifyBasicToken(requestToken, account, appUserLogin);
    }

    public void verifyBasicToken(String requestToken, Account account, String userId) throws TokenException {
        String[] loginPassword = SecurityTools.decodeBase64(requestToken).split(":");
        AccountPassword accountPassword = new AccountPassword(loginPassword[0], loginPassword[1]);
        if (!loginPassword[0].equals(account.getLoginName()) || !account.getPassword().equals(accountPassword.getEncryptedPassword())){
               String message = "User name or password is incorrect fot user " + userId;
            logger.error(message);
            throw new TokenException(message);
        } else {
            String message = "Request authorized for user " + userId + " successful";
            logger.debug(message);
        }
    }

    private void authorizeWithBearer(long requestAccountId, Token token) throws TokenException {
        long accountId = token.getUserId();
        if (accountId != requestAccountId) {
            String message = "Account id in request = " + requestAccountId + ", but account id in token = " + accountId;
            logger.error(message);
            throw new TokenException(message);
        }
        String message = "Request authorized for user " + requestAccountId + " successful";
        logger.debug(message);
    }

    private String encode64(String source){
        return Base64
                .getEncoder()
                .encodeToString(source.getBytes());
    }

}
