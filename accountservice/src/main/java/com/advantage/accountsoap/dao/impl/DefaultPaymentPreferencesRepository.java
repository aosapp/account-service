package com.advantage.accountsoap.dao.impl;

import com.advantage.accountsoap.dao.AbstractRepository;
import com.advantage.accountsoap.dao.PaymentPreferencesRepository;
import com.advantage.accountsoap.model.PaymentPreferences;
import com.advantage.accountsoap.model.PaymentPreferencesPK;
import com.advantage.accountsoap.services.AccountService;
import com.advantage.accountsoap.util.ArgumentValidationHelper;
import com.advantage.common.enums.PaymentMethodEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Component
@Qualifier("paymentPreferencesRepository")
@Repository
public class DefaultPaymentPreferencesRepository extends AbstractRepository implements PaymentPreferencesRepository {
    @Autowired
    AccountService accountService;

    /*
    public int delete(long userId, int paymentMethod) {

        PaymentPreferences paymentPreferences = this.find(userId, paymentMethod);
        int result = 0;

        if (paymentPreferences != null) {
            final StringBuilder hql = new StringBuilder("DELETE FROM ")
                    .append(PaymentPreferences.class.getName())
                    .append(" WHERE ")
                    .append(PaymentPreferences.FIELD_USER_ID).append("=").append(userId).append(" AND ")
                    .append(PaymentPreferences.FIELD_PAYMENT_METHOD).append("=").append(paymentMethod);

            Query query = entityManager.createQuery(hql.toString());

            result = query.executeUpdate();
        }

        return result;
    }
    */

    @Override
    public int delete(PaymentPreferences... entities) {
        int count = 0;
        for (PaymentPreferences entity : entities) {
            if (entityManager.contains(entity)) {
                entityManager.remove(entity);
                count++;
            }
        }
        return count;
    }
    @Override
    public int deletePaymentPreferences(long userId) {

        try {
            final StringBuilder deletePaymentPreferences = new StringBuilder("DELETE FROM ")
                    .append("PaymentPreferences")
                    .append(" WHERE ")
                    .append(PaymentPreferences.FIELD_USER_ID).append("=").append(userId);
            Query queryDelete = entityManager.createQuery(deletePaymentPreferences.toString());
            queryDelete.executeUpdate();

            if(getPaymentPreferencesByUserId(userId) ==null ){
                return 0;
            }else {return 1;}

        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
    @Override
    public PaymentPreferences delete(long userId, int paymentMethod) {

        PaymentPreferences entity = find(userId, paymentMethod);
        if (entity != null) delete(entity);

        return entity;
    }

    @Override
    public List<PaymentPreferences> getPaymentPreferencesByUserId(long userId) {
        List<PaymentPreferences> accounts = entityManager.createNamedQuery(PaymentPreferences.QUERY_GET_PAYMENT_PREFERENCES_BY_USER_ID, PaymentPreferences.class)
                                                            .setParameter(PaymentPreferences.PARAM_USER_ID, userId)
                                                            .getResultList();

        return accounts.isEmpty() ? null : accounts;
    }


    @Override
    public PaymentPreferences get(long userId) {
        //ArgumentValidationHelper.validateLongArgumentIsPositive(userId, "payment preferences user-id");

        PaymentPreferences paymentPreferences = this.find(userId, PaymentMethodEnum.MASTER_CREDIT.getCode());

        if (paymentPreferences == null) {
            paymentPreferences = this.find(userId, PaymentMethodEnum.SAFE_PAY.getCode());
        }

        return ( paymentPreferences != null ? paymentPreferences : null);
    }

    @Override
    public PaymentPreferences find(long userId, int paymentMethod) {

        ArgumentValidationHelper.validateLongArgumentIsPositive(userId, "payment preferences user-id");
        ArgumentValidationHelper.validateNumberArgumentIsPositive(paymentMethod, "payment preferences payment-method");

        PaymentPreferencesPK paymentPreferencesPk = new PaymentPreferencesPK(userId, paymentMethod);
        PaymentPreferences paymentPreferences = entityManager.find(PaymentPreferences.class, paymentPreferencesPk);

        return paymentPreferences;

    }

    @Override
    public void create(PaymentPreferences entity) {
        entityManager.persist(entity);
    }

    /**
     * Create SafePay Prefered payment method line
     * @param cardNumber MasterCredit card number.
     * @param expirationDate MasterCredit expiration date (MMYYYY).
     * @param cvvNumber MasterCredit CVV number.
     * @param customerName MasterCredit customer name 2-30 characters.
     * @param accountId User id who used this payment method.
     * @return {@link PaymentPreferences}
     */
    @Override
    public PaymentPreferences createMasterCredit(String cardNumber, String expirationDate, String cvvNumber, String customerName, long accountId) {

        PaymentPreferences paymentPreferences = new PaymentPreferences(accountId, cardNumber, expirationDate, cvvNumber, customerName);

        entityManager.persist(paymentPreferences);

        return paymentPreferences;
    }

    @Override
    public PaymentPreferences updateMasterCredit(String cardNumber, String expirationDate,
                                                 String cvvNumber, String customerName, long userId) {

        PaymentPreferences preferences = find(userId, PaymentMethodEnum.MASTER_CREDIT.getCode());
        if (preferences == null) return null;

        preferences.setCardNumber(cardNumber);
        preferences.setExpirationDate(expirationDate);
        preferences.setCvvNumber(cvvNumber);
        preferences.setCustomerName(customerName);

        entityManager.persist(preferences);

        return preferences;
    }

    /**
     * Create SafePay Prefered payment method line
     * @param accountId         user id who used this payment method
     * @param safePayUsername   SafePay user name
     * @param safePayPassword   SafePay password
     * @return {@link PaymentPreferences}
     */
    @Override
    public PaymentPreferences createSafePay(long accountId, String safePayUsername, String safePayPassword) {

        PaymentPreferences paymentPreferences = null;
        try {
            paymentPreferences = new PaymentPreferences(accountId, safePayUsername, safePayPassword);
            entityManager.persist(paymentPreferences);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return paymentPreferences;
    }

    @Override
    public PaymentPreferences updateSafePay(long userId, String safePayUsername, String safePayPassword) {

        PaymentPreferences paymentPreferences = find(userId, PaymentMethodEnum.SAFE_PAY.getCode());
        if (paymentPreferences == null) return null;

        paymentPreferences.setSafePayUsername(safePayUsername);
        try {
            paymentPreferences.setSafePayPassword(safePayPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }

        entityManager.persist(paymentPreferences);

        return paymentPreferences;
    }
}
