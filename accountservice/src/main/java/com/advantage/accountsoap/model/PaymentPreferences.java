package com.advantage.accountsoap.model;

import com.advantage.accountsoap.util.AccountPassword;
import com.advantage.common.enums.PaymentMethodEnum;

import javax.persistence.*;

@Entity
@IdClass(PaymentPreferencesPK.class)
@NamedQueries({
        @NamedQuery(
                name = PaymentPreferences.QUERY_GET_ALL,
                query = "SELECT p FROM PaymentPreferences p"
        )
        , @NamedQuery(
                name = PaymentPreferences.QUERY_GET_PAYMENT_PREFERENCES_BY_USER_ID,
                query = "select p from PaymentPreferences p" +
                        " where " + PaymentPreferences.FIELD_USER_ID + " = :" + PaymentPreferences.PARAM_USER_ID +
                        " order by p.paymentMethod ASC"
        )
        , @NamedQuery(
                name = PaymentPreferences.QUERY_GET_PAYMENT_PREFERENCES_BY_PK_COLUMNS,
                query = "select p from PaymentPreferences p " +
                        "where " + PaymentPreferences.FIELD_USER_ID + " = :" + PaymentPreferences.PARAM_USER_ID +
                        " AND " + PaymentPreferences.FIELD_PAYMENT_METHOD + " = :" + PaymentPreferences.PARAM_PAYMENT_METHOD
        )
})
public class PaymentPreferences {
    public static final String MESSAGE_ERROR_INVALID_USERNAME_OR_PASSWORD = "Error: Invalid SafePay user name or password";
    public static final String MESSAGE_SAFE_PAY_DATA_UPDATED_SUCCESSFULLY = "SafePay data updated successfully";
    public static final String MESSAGE_SAFE_PAY_DATA_DELETED_SUCCESSFULLY = "SafePay data deleted successfully";
    public static final String MESSAGE_SAFE_PAY_DATA_FAILED_UPDATE = "SafePay data failed update";
    public static final String MESSAGE_ERROR_IDENTICAL_USERNAME_OR_PASSWORD = "Error! Username and password cannot be identical";

    public static final String MESSAGE_ERROR_INVALID_CARD_NUMBER = "Error: Invalid card number";
    public static final String MESSAGE_ERROR_INVALID_CVV_NUMBER = "Error: Invalid CVV number";
    public static final String MESSAGE_ERROR_EXPIRATION_DATE_FORMAT = "Error: Invalid expiration date format";
    public static final String MESSAGE_ERROR_GENERAL_FAILURE = "Failed";


    public static final String QUERY_GET_ALL = "query.getAll";
    public static final String QUERY_GET_PAYMENT_PREFERENCES_BY_USER_ID = "query.getPaymentPreferencesByUserId";
    public static final String QUERY_GET_PAYMENT_PREFERENCES_BY_PK_COLUMNS = "query.getPaymentPreferencesByPkColumns";

    public static final String FIELD_USER_ID = "user_id";
    public static final String FIELD_PAYMENT_METHOD = "payment_method";

    public static final String PARAM_USER_ID = "PARAM_USER_ID";
    public static final String PARAM_PAYMENT_METHOD = "PARAM_PRODUCT_ID";


    @Id
    @Column(name = FIELD_USER_ID)
    private long userId;

    @Id
    @Column(name = FIELD_PAYMENT_METHOD)
    private int paymentMethod;

    @Column(name="card_number", length = 20)
    private String cardNumber;

    @Column(name="expiration_date", length = 6)
    private String expirationDate;

    @Column(name="cvv_number", length = 3)
    private String cvvNumber;

    @Column(name="customer_name", length = 50)
    private String customerName;

    @Column(name="safe_pay_username", length = 50)
    private String safePayUsername;

    @Column(name="safe_pay_password", length = 50)
    private String safePayPassword;

    public PaymentPreferences() {
    }

    public PaymentPreferences(long userId, int paymentMethod) {
        this.userId = userId;
        this.paymentMethod = paymentMethod;

        this.cardNumber = null;
        this.expirationDate = null;
        this.cvvNumber = null;
        this.customerName = null;
        this.safePayUsername = null;
    }

    public PaymentPreferences(long userId, String cardNumber, String expirationDate, String cvvNumber, String customerName) {
        this.userId = userId;
        this.setPaymentMethod(PaymentMethodEnum.MASTER_CREDIT.getCode());

        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvvNumber = cvvNumber;
        this.customerName = customerName;
    }

    public PaymentPreferences(long userId, String safePayUsername, String safePayPassword) throws Exception {
        this.setUserId(userId);
        this.setPaymentMethod(PaymentMethodEnum.SAFE_PAY.getCode());
        this.setSafePayUsername(safePayUsername);
        this.setSafePayPassword(safePayPassword);
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvvNumber() {
        return cvvNumber;
    }

    public void setCvvNumber(String cvvNumber) {
        this.cvvNumber = cvvNumber;
    }

    public String getSafePayUsername() {
        return safePayUsername;
    }

    public void setSafePayUsername(String safePayUsername) {
        this.safePayUsername = safePayUsername;
    }

    public String getSafePayPassword() {
        return this.safePayPassword;
    }

    public void setSafePayPassword(String safePayPassword) {
        AccountPassword accountPassword = new AccountPassword(getSafePayUsername(), safePayPassword);
        this.safePayPassword = accountPassword.getEncryptedPassword();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

}
