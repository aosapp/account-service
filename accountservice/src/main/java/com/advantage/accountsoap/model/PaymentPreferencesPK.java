package com.advantage.accountsoap.model;

import java.io.Serializable;

/**
 * Primary-Key class for entity {@link PaymentPreferences} which has composite
 * primary key, handled by {@code @ClassId} annotation.
 * @author Binyamin Regev on 07/02/2016.
 */
public class PaymentPreferencesPK implements Serializable {
    private long userId;
    private int paymentMethod;

    /**
     * Mandatory Definition: Default Constructor
     */
    public PaymentPreferencesPK() {
    }

    /**
     * Mandatory Definition: Full Primary Key Constructor
     * @param userId
     * @param paymentMethod
     */
    public PaymentPreferencesPK(long userId, int paymentMethod) {
        this.userId = userId;
        this.paymentMethod = paymentMethod;
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

    /**
     * Mandatory Definition override {@link #equals} method.
     *
     * @param o Object to compare to this object.
     * @return <b>true</b> if both objects are equal, <b>false</b> when objects are not equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentPreferencesPK that = (PaymentPreferencesPK) o;

        if (getUserId() != that.getUserId()) return false;
        return getPaymentMethod() == that.getPaymentMethod();

    }

    /**
     * Mandatory Definition override {@link #hashCode} method.
     * @return integer representing {@code hashCode}.
     */
    @Override
    public int hashCode() {
        int result = (int) (getUserId() ^ (getUserId() >>> 32));
        result = 31 * result + getPaymentMethod();
        return result;
    }
}
