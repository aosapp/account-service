package com.advantage.accountsoap.dao;

import com.advantage.accountsoap.model.PaymentPreferences;
import com.advantage.common.dao.DefaultCRUDOperations;

import java.util.List;

//public interface PaymentPreferencesRepository extends DefaultCRUDOperations<PaymentPreferences> {
public interface PaymentPreferencesRepository {

    List<PaymentPreferences> getPaymentPreferencesByUserId(long userId);

    PaymentPreferences get(long userId);

    PaymentPreferences find(long userId, int paymentMethod);

    void create(PaymentPreferences entity);

    PaymentPreferences createMasterCredit(String cardNumber, String expirationDate, String cvvNumber, String customerName, long accountId);

    PaymentPreferences createSafePay(long accountId, String safePayUsername, String safePayPassword);

    PaymentPreferences updateMasterCredit(String cardNumber, String expirationDate,
                                          String cvvNumber, String customerName, long userId);

    PaymentPreferences updateSafePay(long userId, String safePayUsername, String safePayPassword);

    int delete(PaymentPreferences... entities);

    PaymentPreferences delete(long userId, int paymentMethod);

    int deletePaymentPreferences(long userId);
}
