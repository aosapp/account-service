package com.advantage.accountsoap.dao;

import com.advantage.accountsoap.dto.account.AccountStatusResponse;
import com.advantage.accountsoap.dto.country.CountryID;
import com.advantage.accountsoap.model.Account;
import com.advantage.common.dao.DefaultCRUDOperations;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AccountRepository extends DefaultCRUDOperations<Account> {

    Account createAppUser(Integer appUserType, String lastName, String firstName, String loginName,
                          String password, CountryID country, String phoneNumber, String stateProvince,
                          String cityName, String address, String zipcode, String email,
                          boolean agreeToReceiveOffersAndPromotions);

    //  For User-Management API
    @Transactional
    AccountStatusResponse create(Integer appUserType, String lastName, String firstName, String loginName,
                                 String password, CountryID country, String phoneNumber, String stateProvince,
                                 String cityName, String address, String zipcode, String email,
                                 boolean agreeToReceiveOffersAndPromotions);

    Account addUnsuccessfulLoginAttempt(Account account);

    String getBlockedUntilTimestamp(long milliSeconds);

    Account updateAppUser(Account account);

    AccountStatusResponse updateAccount(long acccountId, Integer appUserType, String lastName, String firstName,CountryID country,
                                        String phoneNumber, String stateProvince, String cityName, String address,
                                        String zipcode, String email, boolean agreeToReceiveOffersAndPromotions);

    String getFailureMessage();

    int deleteAccount(Account account);

    int deleteAccountPermanently(Account account);

    Account getAppUserByLogin(String login);

    AccountStatusResponse doLogin(String login, String password, String email);

    AccountStatusResponse doLogout(String login, String base64Token);

    List<Account> getAppUsersByCountry(Integer countryId);

    AccountStatusResponse changePassword(long accountId, String newPassword);

    //Collection<PaymentPreferences> getPaymentPreferences(long accountId);

    AccountStatusResponse removePaymentPreferences(long accountId, long paymentMethod);

    void deleteAllPaymentPreferences(long accountId);

    AccountStatusResponse dbRestoreFactorySettings();

    void deleteShippingAddress(long accountId);

    AccountStatusResponse deleteUserOrders(long accountId,String base64Token);
}
