
package com.advantage.accountrest.AccountserviceClient;

import accountservice.store.online.advantage.com.AccountStatusRequest;
import accountservice.store.online.advantage.com.PaymentPreferencesStatusRequest;
import com.advantage.accountsoap.dto.account.*;
import com.advantage.accountsoap.dto.address.*;
import com.advantage.accountsoap.dto.country.*;
import com.advantage.accountsoap.dto.payment.*;
import com.advantage.accountsoap.model.Country;
import com.advantage.accountsoap.model.PaymentPreferences;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.bind.annotation.*;

/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the accountservice.store.online.advantage.com package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AccountDto_QNAME = new QName("com.advantage.online.store.accountservice", "AccountDto");
    private final static QName _Address_QNAME = new QName("com.advantage.online.store.accountservice", "Address");
    private final static QName _PaymentPreferencesStatusResponse_QNAME = new QName("com.advantage.online.store.accountservice", "PaymentPreferencesStatusResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: accountservice.store.online.advantage.com
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AccountCreateResponse }
     *
     */
    public AccountCreateResponse createAccountCreateResponse() {
        return new AccountCreateResponse();
    }

    /**
     * Create an instance of {@link AccountDeleteResponse }
     *
     */
    public AccountDeleteResponse createAccountDeleteResponse() {
        return new AccountDeleteResponse();
    }

    /**
     * Create an instance of {@link AccountPermanentDeleteResponse }
     *
     */
    public AccountPermanentDeleteResponse createAccountPermanentDeleteResponse() {
        return new AccountPermanentDeleteResponse();
    }

    /**
     * Create an instance of {@link AccountLoginResponse }
     *
     */
    public AccountLoginResponse createAccountLoginResponse() {
        return new AccountLoginResponse();
    }

    /**
     * Create an instance of {@link AccountLogoutResponse }
     *
     */
    public AccountLogoutResponse createAccountLogoutResponse() {
        return new AccountLogoutResponse();
    }

    /**
     * Create an instance of {@link AccountUpdateResponse }
     *
     */
    public AccountUpdateResponse createAccountUpdateResponse() {
        return new AccountUpdateResponse();
    }

    /**
     * Create an instance of {@link AddAddressesRequest }
     *
     */
    public AddAddressesRequest createAddAddressesRequest() {
        return new AddAddressesRequest();
    }

    /**
     * Create an instance of {@link AddSafePayMethodResponse }
     *
     */
    public AddSafePayMethodResponse createAddSafePayMethodResponse() {
        return new AddSafePayMethodResponse();
    }

    /**
     * Create an instance of {@link AddressUpdateRequest }
     *
     */
    public AddressUpdateRequest createAddressUpdateRequest() {
        return new AddressUpdateRequest();
    }

    /**
     * Create an instance of {@link AddressUpdateResponse }
     *
     */
    public AddressUpdateResponse createAddressUpdateResponse() {
        return new AddressUpdateResponse();
    }

    /**
     * Create an instance of {@link ChangePasswordResponse }
     *
     */
    public ChangePasswordResponse createChangePasswordResponse() {
        return new ChangePasswordResponse();
    }

    /**
     * Create an instance of {@link DeletePaymentPreferenceResponse }
     *
     */
    public DeletePaymentPreferenceResponse createDeletePaymentPreferenceResponse() {
        return new DeletePaymentPreferenceResponse();
    }

    /**
     * Create an instance of {@link GetAccountByIdNewResponse }
     *
     */
    public GetAccountByIdNewResponse createGetAccountByIdNewResponse() {
        return new GetAccountByIdNewResponse();
    }

    /**
     * Create an instance of {@link GetAccountByIdResponse }
     *
     */
    public GetAccountByIdResponse createGetAccountByIdResponse() {
        return new GetAccountByIdResponse();
    }

    /**
     * Create an instance of {@link GetAddressesByAccountIdResponse }
     *
     */
    public GetAddressesByAccountIdResponse createGetAddressesByAccountIdResponse() {
        return new GetAddressesByAccountIdResponse();
    }

    /**
     * Create an instance of {@link GetAllAccountsResponse }
     *
     */
    public GetAllAccountsResponse createGetAllAccountsResponse() {
        return new GetAllAccountsResponse();
    }

    /**
     * Create an instance of {@link PaymentMethodUpdateResponse }
     *
     */
    public PaymentMethodUpdateResponse createPaymentMethodUpdateResponse() {
        return new PaymentMethodUpdateResponse();
    }

    /**
     * Create an instance of {@link PaymentPreferences }
     *
     */
    public PaymentPreferences createPaymentPreferences() {
        return new PaymentPreferences();
    }

    /**
     * Create an instance of {@link UpdateMasterCreditMethodResponse }
     *
     */
    public UpdateMasterCreditMethodResponse createUpdateMasterCreditMethodResponse() {
        return new UpdateMasterCreditMethodResponse();
    }

    /**
     * Create an instance of {@link UpdateSafePayMethodResponse }
     *
     */
    public UpdateSafePayMethodResponse createUpdateSafePayMethodResponse() {
        return new UpdateSafePayMethodResponse();
    }

    /**
     * Create an instance of {@link AccountCreateRequest }
     *
     */
    public AccountCreateRequest createAccountCreateRequest() {
        return new AccountCreateRequest();
    }

    /**
     * Create an instance of {@link AccountCreateResponse.StatusMessage }
     *
     */
    public AccountCreateResponse.StatusMessage createAccountCreateResponseStatusMessage() {
        return new AccountCreateResponse.StatusMessage();
    }

    /**
     * Create an instance of {@link AccountDeleteRequest }
     *
     */
    public AccountDeleteRequest createAccountDeleteRequest() {
        return new AccountDeleteRequest();
    }

    /**
     * Create an instance of {@link AccountPermanentDeleteRequest }
     *
     */
    public AccountPermanentDeleteRequest createAccountPermanentDeleteRequest() {
        return new AccountPermanentDeleteRequest();
    }

    /**
     * Create an instance of {@link AccountDeleteResponse.StatusMessage }
     *
     */
    public AccountDeleteResponse.StatusMessage createAccountDeleteResponseStatusMessage() {
        return new AccountDeleteResponse.StatusMessage();
    }

    /**
     * Create an instance of {@link AccountPermanentDeleteResponse.StatusMessage }
     *
     */
    public AccountPermanentDeleteResponse.StatusMessage createAccountPermanentDeleteResponseStatusMessage() {
        return new AccountPermanentDeleteResponse.StatusMessage();
    }

    /**
     * Create an instance of {@link AccountLoginRequest }
     *
     */
    public AccountLoginRequest createAccountLoginRequest() {
        return new AccountLoginRequest();
    }

    /**
     * Create an instance of {@link AccountLoginResponse.StatusMessage }
     *
     */
    public AccountLoginResponse.StatusMessage createAccountLoginResponseStatusMessage() {
        return new AccountLoginResponse.StatusMessage();
    }

    /**
     * Create an instance of {@link AccountLogoutRequest }
     *
     */
    public AccountLogoutRequest createAccountLogoutRequest() {
        return new AccountLogoutRequest();
    }

    /**
     * Create an instance of {@link AccountLogoutResponse.StatusMessage }
     *
     */
    public AccountLogoutResponse.StatusMessage createAccountLogoutResponseStatusMessage() {
        return new AccountLogoutResponse.StatusMessage();
    }

    /**
     * Create an instance of {@link AccountStatusResponse }
     *
     */
    public AccountStatusResponse createAccountStatusResponse() {
        return new AccountStatusResponse();
    }

    /**
     * Create an instance of {@link AccountStatusRequest }
     *
     */
    public AccountStatusRequest createAccountStatusRequest() {
        return new AccountStatusRequest();
    }

    /**
     * Create an instance of {@link AccountUpdateRequest }
     *
     */
    public AccountUpdateRequest createAccountUpdateRequest() {
        return new AccountUpdateRequest();
    }

    /**
     * Create an instance of {@link AccountUpdateResponse.StatusMessage }
     *
     */
    public AccountUpdateResponse.StatusMessage createAccountUpdateResponseStatusMessage() {
        return new AccountUpdateResponse.StatusMessage();
    }

    /**
     * Create an instance of {@link AddAddressesRequest.Address }
     *
     */
    public AddAddressesRequest.Address createAddAddressesRequestAddress() {
        return new AddAddressesRequest.Address();
    }

    /**
     * Create an instance of {@link AddMasterCreditMethodRequest }
     *
     */
    public AddMasterCreditMethodRequest createAddMasterCreditMethodRequest() {
        return new AddMasterCreditMethodRequest();
    }

    /**
     * Create an instance of {@link AddSafePayMethodRequest }
     *
     */
    public AddSafePayMethodRequest createAddSafePayMethodRequest() {
        return new AddSafePayMethodRequest();
    }

    /**
     * Create an instance of {@link AddSafePayMethodResponse.StatusMessage }
     *
     */
    public AddSafePayMethodResponse.StatusMessage createAddSafePayMethodResponseStatusMessage() {
        return new AddSafePayMethodResponse.StatusMessage();
    }

    /**
     * Create an instance of {@link AddressUpdateRequest.Address }
     *
     */
    public AddressUpdateRequest.Address createAddressUpdateRequestAddress() {
        return new AddressUpdateRequest.Address();
    }

    /**
     * Create an instance of {@link AddressUpdateResponse.StatusMessage }
     *
     */
    public AddressUpdateResponse.StatusMessage createAddressUpdateResponseStatusMessage() {
        return new AddressUpdateResponse.StatusMessage();
    }

    /**
     * Create an instance of {@link ChangePasswordRequest }
     *
     */
    public ChangePasswordRequest createChangePasswordRequest() {
        return new ChangePasswordRequest();
    }

    /**
     * Create an instance of {@link ChangePasswordResponse.StatusMessage }
     *
     */
    public ChangePasswordResponse.StatusMessage createChangePasswordResponseStatusMessage() {
        return new ChangePasswordResponse.StatusMessage();
    }

    /**
     * Create an instance of {@link Country }
     *
     */
    public Country createCountry() {
        return new Country();
    }

    /**
     * Create an instance of {@link CountryCreateRequest }
     *
     */
    public CountryCreateRequest createCountryCreateRequest() {
        return new CountryCreateRequest();
    }

    /**
     * Create an instance of {@link CountrySearchRequest }
     *
     */
    public CountrySearchRequest createCountrySearchRequest() {
        return new CountrySearchRequest();
    }

    /**
     * Create an instance of {@link CountrySearchResponse }
     *
     */
    public CountrySearchResponse createCountrySearchResponse() {
        return new CountrySearchResponse();
    }

    /**
     * Create an instance of {@link AccountConfigurationRequest }
     *
     */
    public AccountConfigurationRequest createAccountConfigurationRequest() {
        return new AccountConfigurationRequest();
    }

    /**
     * Create an instance of {@link DeletePaymentPreferenceResponse.StatusMessage }
     *
     */
    public DeletePaymentPreferenceResponse.StatusMessage createDeletePaymentPreferenceResponseStatusMessage() {
        return new DeletePaymentPreferenceResponse.StatusMessage();
    }

    /**
     * Create an instance of {@link DeletePaymentPreferenceRequest }
     *
     */
    public DeletePaymentPreferenceRequest createDeletePaymentPreferenceRequest() {
        return new DeletePaymentPreferenceRequest();
    }

    /**
     * Create an instance of {@link GetAccountByIdNewRequest }
     *
     */
    public GetAccountByIdNewRequest createGetAccountByIdNewRequest() {
        return new GetAccountByIdNewRequest();
    }

    /**
     * Create an instance of {@link GetAccountByIdNewResponse.AccountResponse }
     *
     */
    public GetAccountByIdNewResponse.AccountResponse createGetAccountByIdNewResponseAccountResponse() {
        return new GetAccountByIdNewResponse.AccountResponse();
    }

    /**
     * Create an instance of {@link GetAccountByIdRequest }
     *
     */
    public GetAccountByIdRequest createGetAccountByIdRequest() {
        return new GetAccountByIdRequest();
    }

    /**
     * Create an instance of {@link GetAccountByIdResponse.AccountResponse }
     *
     */
    public GetAccountByIdResponse.AccountResponse createGetAccountByIdResponseAccountResponse() {
        return new GetAccountByIdResponse.AccountResponse();
    }

    /**
     * Create an instance of {@link GetAccountConfigurationResponse }
     *
     */
    public GetAccountConfigurationResponse createGetAccountConfigurationResponse() {
        return new GetAccountConfigurationResponse();
    }

    /**
     * Create an instance of {@link GetAccountPaymentPreferencesRequest }
     *
     */
    public GetAccountPaymentPreferencesRequest createGetAccountPaymentPreferencesRequest() {
        return new GetAccountPaymentPreferencesRequest();
    }

    /**
     * Create an instance of {@link GetAddressesByAccountIdRequest }
     *
     */
    public GetAddressesByAccountIdRequest createGetAddressesByAccountIdRequest() {
        return new GetAddressesByAccountIdRequest();
    }

    /**
     * Create an instance of {@link GetAddressesByAccountIdResponse.ShippingAddress }
     *
     */
    public GetAddressesByAccountIdResponse.ShippingAddress createGetAddressesByAccountIdResponseShippingAddress() {
        return new GetAddressesByAccountIdResponse.ShippingAddress();
    }

    /**
     * Create an instance of {@link GetAllAccountsRequest }
     *
     */
    public GetAllAccountsRequest createGetAllAccountsRequest() {
        return new GetAllAccountsRequest();
    }

    /**
     * Create an instance of {@link GetAllAccountsResponse.Account }
     *
     */
    public GetAllAccountsResponse.Account createGetAllAccountsResponseAccount() {
        return new GetAllAccountsResponse.Account();
    }

    /**
     * Create an instance of {@link GetCountriesResponse }
     *
     */
    public GetCountriesResponse createGetCountriesResponse() {
        return new GetCountriesResponse();
    }

    /**
     * Create an instance of {@link GetCountriesRequest }
     *
     */
    public GetCountriesRequest createGetCountriesRequest() {
        return new GetCountriesRequest();
    }

    /**
     * Create an instance of {@link PaymentMethodUpdateRequest }
     *
     */
    public PaymentMethodUpdateRequest createPaymentMethodUpdateRequest() {
        return new PaymentMethodUpdateRequest();
    }

    /**
     * Create an instance of {@link PaymentMethodUpdateResponse.StatusMessage }
     *
     */
    public PaymentMethodUpdateResponse.StatusMessage createPaymentMethodUpdateResponseStatusMessage() {
        return new PaymentMethodUpdateResponse.StatusMessage();
    }

    /**
     * Create an instance of {@link com.advantage.accountrest.AccountserviceClient.PaymentPreferences.Preference }
     *
     */
    public com.advantage.accountrest.AccountserviceClient.PaymentPreferences.Preference createPaymentPreferencesPreference() {
        return new com.advantage.accountrest.AccountserviceClient.PaymentPreferences.Preference();
    }

    /**
     * Create an instance of {@link PaymentPreferencesDto }
     *
     */
    public PaymentPreferencesDto createPaymentPreferencesDto() {
        return new PaymentPreferencesDto();
    }

    /**
     * Create an instance of {@link PaymentPreferencesStatusRequest }
     *
     */
    public PaymentPreferencesStatusRequest createPaymentPreferencesStatusRequest() {
        return new PaymentPreferencesStatusRequest();
    }

    /**
     * Create an instance of {@link UpdateMasterCreditMethodRequest }
     *
     */
    public UpdateMasterCreditMethodRequest createUpdateMasterCreditMethodRequest() {
        return new UpdateMasterCreditMethodRequest();
    }

    /**
     * Create an instance of {@link UpdateMasterCreditMethodResponse.StatusMessage }
     *
     */
    public UpdateMasterCreditMethodResponse.StatusMessage createUpdateMasterCreditMethodResponseStatusMessage() {
        return new UpdateMasterCreditMethodResponse.StatusMessage();
    }

    /**
     * Create an instance of {@link UpdateSafePayMethodRequest }
     *
     */
    public UpdateSafePayMethodRequest createUpdateSafePayMethodRequest() {
        return new UpdateSafePayMethodRequest();
    }

    /**
     * Create an instance of {@link UpdateSafePayMethodResponse.StatusMessage }
     *
     */
    public UpdateSafePayMethodResponse.StatusMessage createUpdateSafePayMethodResponseStatusMessage() {
        return new UpdateSafePayMethodResponse.StatusMessage();
    }

    /**
     * Create an instance of {@link RestoreDBToFactorySettingRequest }
     *
     */
    public RestoreDBToFactorySettingRequest createRestoreDBToFactorySettingRequest() {
        return new RestoreDBToFactorySettingRequest();
    }

    /**
     * Create an instance of {@link RestoreDBToFactorySettingResponse }
     *
     */
    public RestoreDBToFactorySettingResponse createRestoreDBToFactorySettingResponse() {
        return new RestoreDBToFactorySettingResponse();
    }

    /**
     * Create an instance of {@link GetAccountFieldsRequest }
     *
     */
    public GetAccountFieldsRequest createGetAccountFieldsRequest() {
        return new GetAccountFieldsRequest();
    }

    /**
     * Create an instance of {@link GetAccountFieldsResponse }
     *
     */
    public GetAccountFieldsResponse createGetAccountFieldsResponse() {
        return new GetAccountFieldsResponse();
    }

    /**
     * Create an instance of {@link CountryStatusResponse }
     *
     */
    public CountryStatusResponse createCountryStatusResponse() {
        return new CountryStatusResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     *
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     */
    @XmlElementDecl(namespace = "com.advantage.online.store.accountservice", name = "AccountDto")
    public JAXBElement<Object> createAccountDto(Object value) {
        return new JAXBElement<Object>(_AccountDto_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     *
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     */
    @XmlElementDecl(namespace = "com.advantage.online.store.accountservice", name = "Address")
    public JAXBElement<Object> createAddress(Object value) {
        return new JAXBElement<Object>(_Address_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     *
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     */
    @XmlElementDecl(namespace = "com.advantage.online.store.accountservice", name = "PaymentPreferencesStatusResponse")
    public JAXBElement<Object> createPaymentPreferencesStatusResponse(Object value) {
        return new JAXBElement<Object>(_PaymentPreferencesStatusResponse_QNAME, Object.class, null, value);
    }

}
