package com.advantage.accountsoap.services;

import com.advantage.accountsoap.config.BeansManager;
import com.advantage.accountsoap.config.Injectable;
import com.advantage.accountsoap.dao.PaymentPreferencesRepository;
import com.advantage.accountsoap.dto.payment.PaymentPreferencesDto;
import com.advantage.accountsoap.dto.payment.PaymentPreferencesStatusResponse;
import com.advantage.accountsoap.model.PaymentPreferences;
import com.advantage.accountsoap.util.ArgumentValidationHelper;
import com.advantage.common.enums.PaymentMethodEnum;
import com.advantage.common.utils.ValidationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.advantage.common.Constants.MASTER_CREDIT_IDENTICAL_CREDENTIALS_ERROR;

@Service
public class PaymentPreferencesService implements Injectable{
//    @Autowired
//    @Qualifier("paymentPreferencesRepository")
    public PaymentPreferencesRepository paymentPreferencesRepository;

    @Override
    public void inject(BeansManager beansManager){
        this.paymentPreferencesRepository =  beansManager.getPaymentPreferencesRepository();
    }

    @Transactional
    public PaymentPreferencesStatusResponse addSafePayMethod(long accountId, String safePayUsername, String safePayPassword) {

        //  region Arguments Validation
        if ((safePayUsername.length() < 1) || (safePayUsername.length() > 20)) {
            return new PaymentPreferencesStatusResponse(false, PaymentPreferences.MESSAGE_ERROR_INVALID_USERNAME_OR_PASSWORD, 0);
        }

        if ((safePayPassword.length() < 1) || (safePayPassword.length() > 20)) {
            return new PaymentPreferencesStatusResponse(false, PaymentPreferences.MESSAGE_ERROR_INVALID_USERNAME_OR_PASSWORD, 0);
        }

        if(safePayUsername.equals(safePayPassword)){
            return new PaymentPreferencesStatusResponse(false, PaymentPreferences.MESSAGE_ERROR_IDENTICAL_USERNAME_OR_PASSWORD, 0);
        }
        //  endregion

        PaymentPreferences paymentPreferences = paymentPreferencesRepository.find(accountId, PaymentMethodEnum.SAFE_PAY.getCode());

        if (paymentPreferences == null) {
            paymentPreferences = paymentPreferencesRepository.createSafePay(accountId, safePayUsername, safePayPassword);
        } else {
            paymentPreferences = paymentPreferencesRepository.updateSafePay(accountId, safePayUsername, safePayPassword);
        }

        if (paymentPreferences == null ) return new PaymentPreferencesStatusResponse(false, "addSafePayMethod: " + PaymentPreferences.MESSAGE_SAFE_PAY_DATA_FAILED_UPDATE, -1);

        return new PaymentPreferencesStatusResponse(true, PaymentPreferences.MESSAGE_SAFE_PAY_DATA_UPDATED_SUCCESSFULLY, 0);
    }

    @Transactional
    public PaymentPreferencesStatusResponse addMasterCreditMethod(String cardNumber, String expirationDate,
                                                                  String cvvNumber, String customerName, long accountId) {

        System.out.println("validate card number = " + cardNumber);
        if(!ValidationHelper.isValidMasterCreditCardNumber(cardNumber)) {
            return new PaymentPreferencesStatusResponse(false, "addMasterCreditMethod: " + PaymentPreferences.MESSAGE_ERROR_INVALID_CARD_NUMBER, -1);
        }

        System.out.println("validate CVV number = " + cvvNumber);
        if(!ValidationHelper.isValidMasterCreditCVVNumber(cvvNumber)) {
            return new PaymentPreferencesStatusResponse(false, "addMasterCreditMethod: " + PaymentPreferences.MESSAGE_ERROR_INVALID_CVV_NUMBER, -1);
        }

        /* convert expiration date "MMYYYY" to date format "DD.MM.YYYY" and validate it.    */
        System.out.println("validate ExpirationDate = \'" + expirationDate);
        StringBuilder sb = new StringBuilder("01.")
                                .append(expirationDate.substring(0, 2))
                                .append('.')
                                .append(expirationDate.substring(2, 6));

        System.out.println("ExpirationDate converted to date format dd.MM.yyyy = \'" + sb.toString() + "\'");
        if(!ValidationHelper.isValidDate(ValidationHelper.getLastDayOfMonthDate(sb.toString()))) {
            return new PaymentPreferencesStatusResponse(false, "addMasterCreditMethod: " + PaymentPreferences.MESSAGE_ERROR_EXPIRATION_DATE_FORMAT, -1);
        }

        if (ValidationHelper.isMCDateExpired(expirationDate)) {
            return new PaymentPreferencesStatusResponse(false, MASTER_CREDIT_IDENTICAL_CREDENTIALS_ERROR, -1);
        }

        PaymentPreferences paymentPreferences = paymentPreferencesRepository.find(accountId, PaymentMethodEnum.MASTER_CREDIT.getCode());
        if (paymentPreferences == null) {
            paymentPreferences = paymentPreferencesRepository.createMasterCredit(cardNumber, expirationDate,
                    cvvNumber, customerName, accountId);
        } else {
            paymentPreferences = paymentPreferencesRepository.updateMasterCredit(cardNumber, expirationDate, cvvNumber, customerName, accountId);
        }

        if (paymentPreferences == null) return new PaymentPreferencesStatusResponse(false, "addMasterCreditMethod: " + PaymentPreferences.MESSAGE_ERROR_GENERAL_FAILURE, -1);

        return new PaymentPreferencesStatusResponse(true, "Successful", 0);
    }

    @Transactional
    public PaymentPreferencesStatusResponse updateSafePayMethod(long userId, String safePayUsername, String safePayPassword) {

        //  region Arguments Validation
        if ((safePayUsername.length() < 1) || (safePayUsername.length() > 20)) {
            return new PaymentPreferencesStatusResponse(false, PaymentPreferences.MESSAGE_ERROR_INVALID_USERNAME_OR_PASSWORD, 0);
        }

//        if ((safePayPassword.length() < 1) || (safePayPassword.length() > 20)) {
//            return new PaymentPreferencesStatusResponse(false, PaymentPreferences.MESSAGE_ERROR_INVALID_USERNAME_OR_PASSWORD, 0);
//        }
        if (safePayPassword.length() < 1) {
            return new PaymentPreferencesStatusResponse(false, PaymentPreferences.MESSAGE_ERROR_INVALID_USERNAME_OR_PASSWORD, 0);
        }
        
        if(safePayUsername.equals(safePayPassword)){
            return new PaymentPreferencesStatusResponse(false, PaymentPreferences.MESSAGE_ERROR_IDENTICAL_USERNAME_OR_PASSWORD, 0);
        }
        //  endregion

        if (safePayPassword.length() < 21) {
            //  Password has been re-typed - Update new Password
            PaymentPreferences preferences = paymentPreferencesRepository.updateSafePay(userId, safePayUsername, safePayPassword);
            if (preferences == null) return new PaymentPreferencesStatusResponse(false, "updateSafePayMethod: user-id=" + userId + ", Failed", -1);
        }

        return new PaymentPreferencesStatusResponse(true, PaymentPreferences.MESSAGE_SAFE_PAY_DATA_UPDATED_SUCCESSFULLY, 0);
    }

    @Transactional
    public PaymentPreferencesStatusResponse updateMasterCreditMethod(long userId, String cardNumber, String expirationDate,
                                                                  String cvvNumber, String customerName, long referenceId) {
        if(!ValidationHelper.isValidMasterCreditCardNumber(cardNumber)) {
            return new PaymentPreferencesStatusResponse(false, "updateMasterCreditMethod: " + PaymentPreferences.MESSAGE_ERROR_INVALID_CARD_NUMBER, -1);
        }
        if(!ValidationHelper.isValidMasterCreditCVVNumber(cvvNumber)) {
            return new PaymentPreferencesStatusResponse(false, "updateMasterCreditMethod: " + PaymentPreferences.MESSAGE_ERROR_INVALID_CVV_NUMBER, -1);
        }

        System.out.println("validate ExpirationDate = \'" + expirationDate);
        StringBuilder sb = new StringBuilder("01.")
                .append(expirationDate.substring(0, 2))
                .append('.')
                .append(expirationDate.substring(2, 6));


        if (ValidationHelper.isMCDateExpired(expirationDate)) {
            return new PaymentPreferencesStatusResponse(false, MASTER_CREDIT_IDENTICAL_CREDENTIALS_ERROR, -1);
        }

        System.out.println("ExpirationDate converted to date format dd.MM.yyyy = \'" + sb.toString() + "\'");
        if(!ValidationHelper.isValidDate(ValidationHelper.getLastDayOfMonthDate(sb.toString()))) {
            return new PaymentPreferencesStatusResponse(false, "updateMasterCreditMethod: " + PaymentPreferences.MESSAGE_ERROR_EXPIRATION_DATE_FORMAT, -1);
        }
        PaymentPreferences preferences = paymentPreferencesRepository.updateMasterCredit(cardNumber, expirationDate,
                cvvNumber, customerName, userId);
        if(preferences == null ) return new PaymentPreferencesStatusResponse(false, "updateMasterCreditMethod: " + PaymentPreferences.MESSAGE_ERROR_GENERAL_FAILURE, -1);

        return new PaymentPreferencesStatusResponse(true, "Successful", 0);
    }

    @Transactional
    public PaymentPreferencesStatusResponse deletePaymentPreference(long userId, int paymentMethod) {
        PaymentPreferences paymentPreferences = paymentPreferencesRepository.delete(userId, paymentMethod);
        if (paymentPreferences == null ) return  new PaymentPreferencesStatusResponse(false, "deletePaymentPreference: " + PaymentPreferences.MESSAGE_SAFE_PAY_DATA_FAILED_UPDATE, -1);

        return  new PaymentPreferencesStatusResponse(true, PaymentPreferences.MESSAGE_SAFE_PAY_DATA_DELETED_SUCCESSFULLY, 0);
    }

    @Transactional
    public PaymentPreferencesStatusResponse deleteAllPaymentPreference(long userId) {
        int result  = paymentPreferencesRepository.deletePaymentPreferences(userId);
        if (result == 1 ) return  new PaymentPreferencesStatusResponse(false, "deletePaymentPreference: " + PaymentPreferences.MESSAGE_SAFE_PAY_DATA_FAILED_UPDATE, -1);

        return  new PaymentPreferencesStatusResponse(true, PaymentPreferences.MESSAGE_SAFE_PAY_DATA_DELETED_SUCCESSFULLY, 0);
    }

    @Transactional
    public List<PaymentPreferencesDto> getPaymentPreferencesByUserId(long userId) {
        ArgumentValidationHelper.validateLongArgumentIsPositive(userId, "user id");

        List<PaymentPreferences> prefs = paymentPreferencesRepository.getPaymentPreferencesByUserId(userId);

        if (prefs == null) { return null; }
        List<PaymentPreferencesDto> prefsDto = new ArrayList<>();
        for (PaymentPreferences pref : prefs) {
            PaymentPreferencesDto prefDto = new PaymentPreferencesDto(pref.getPaymentMethod(),
                    pref.getCardNumber(),
                    pref.getExpirationDate(),
                    pref.getCvvNumber(),
                    pref.getCustomerName(),
                    pref.getSafePayUsername(),
                    pref.getSafePayPassword(),
                    /* preferenceId */ 0);
            prefsDto.add(prefDto);
        }

        return prefsDto;
    }

    @Transactional
    public boolean isPaymentPreferencesExist(long accountId) {
        PaymentPreferences paymentPreferences = paymentPreferencesRepository.get(accountId);

        return (paymentPreferences != null);
    }
}
