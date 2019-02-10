package com.advantage.accountsoap.model;

import com.advantage.accountsoap.util.AccountPassword;
import com.advantage.common.Constants;

import com.advantage.accountsoap.config.WebServiceConfig;
import com.advantage.common.enums.AccountType;
import com.advantage.accountsoap.util.ArgumentValidationHelper;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name= "account")
@NamedQueries({
        @NamedQuery(
                name = Account.QUERY_GET_ALL,
                query = "SELECT a FROM Account a WHERE UPPER(active)='Y'"
        ),
        @NamedQuery(
        name = Account.QUERY_GET_BY_USER_LOGIN,
        query = "SELECT a FROM Account a WHERE UPPER(active)='Y' AND " + Account.FIELD_USER_LOGIN + " = :" + Account.PARAM_USER_LOGIN
        ),
        @NamedQuery(
        name = Account.QUERY_GET_USERS_BY_COUNTRY,
        query = "SELECT a FROM Account a WHERE UPPER(active)='Y' AND " + Account.FIELD_COUNTRY + " = :" + Account.PARAM_COUNTRY
        ),
        @NamedQuery(
        name = Account.QUERT_GET_ACCOUNT_BY_ID,
        query = "SELECT a FROM Account a WHERE UPPER(active)='Y' AND " + Account.FIELD_ID + " = :" + Account.PARAM_ID
        )
})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "account", namespace = WebServiceConfig.NAMESPACE_URI,
        propOrder = {

        })
public class Account {

    public static final int MAX_NUM_OF_APP_USER = 500;

    public static final String MESSAGE_NEW_USER_CREATED_SUCCESSFULLY = "New user created successfully.";
    public static final String MESSAGE_USER_LOGIN_FAILED = "Incorrect user name or password.";
    public static final String MESSAGE_USER_LOGOUT_FAILED = "Invalid user name or password";
    public static final String MESSAGE_USER_IS_BLOCKED_FROM_LOGIN = "User is temporary blocked from login";
    public static final String MESSAGE_USER_NAME_ALREAY_EXISTS = "User name already exists";
    public static final String MESSAGE_INVALID_EMAIL_ADDRESS = "Invalid email address.";
    public static final String MESSAGE_NO_EMAIL_EXISTS_FOR_USER = "No emails exists for user.";
    public static final String MESSAGE_LOGIN_EMAIL_ADDRESS_IS_EMPTY = "Login e-mail address is empty.";

    public static final String QUERY_GET_ALL = "appUser.getAll";
    public static final String QUERY_GET_BY_USER_LOGIN = "appUser.getAppUserByLogin";
    public static final String QUERY_GET_USERS_BY_COUNTRY = "appUser.getAppUsersByCountry";

//    public static final String QUERY_GET_CURRENT_TIMESTAMP = "appUser.getCurrentTimestamp";

    public static final String FIELD_ID = "USER_ID";

    public static final String FIELD_EMAIL = "EMAIL";
    public static final String PARAM_EMAIL = "PARAM_USER_EMAIL";
    public static final String PARAM_ID = "PARAM_USER_ID";

    public static final String FIELD_USER_LOGIN = "LOGIN_NAME";
    public static final String PARAM_USER_LOGIN = "PARAM_USER_LOGIN";

    public static final String FIELD_COUNTRY = "COUNTRY";
    public static final String PARAM_COUNTRY = "PARAM_USER_COUNTRY";
    public static final String QUERT_GET_ACCOUNT_BY_ID = "account.getById";

//    public static final String QUERY_GET_TIMESTAMP_WITH_INTERVAL = "appUser.getTimestampWithInterval";
//    public static final String PARAM_USER_LOGIN_BLOCKING = "PARAM_USER_LOGIN_BLOCKING";

    @Id
    @Column(name = FIELD_ID)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AccountIdGenerator")
    @GenericGenerator(name = "AccountIdGenerator",
            strategy = "com.advantage.accountsoap.util.BigIntSequenceGenerator",
            parameters = {
                    @Parameter(name="sequence",value = "account_id_sequence")
            })
    private long id;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = FIELD_USER_LOGIN)
    private String loginName;

    private String password;

    @Column(name = "USER_TYPE")
    private Integer accountType;        //  by enum AccountType

    @Column(name = "STATE_PROVINCE")
    private String stateProvince;

    @Column(name = "CITY_NAME")
    private String cityName;

    private String address;

    private String zipcode;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = FIELD_EMAIL)
    private String email;

    @Column(name = "AGREE_TO_RECEIVE_OFFERS", length = 1)
    private boolean allowOffersPromotion;

    @Column
    private int internalUnsuccessfulLoginAttempts;  //  Managed Internally

    @Column
    private long internalUserBlockedFromLoginUntil; //  Managed Internally

    @Column
    private long internalLastSuccesssulLogin;   //  Managed Internally

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ShippingAddress> addresses = new HashSet<>();

    //@OneToMany(orphanRemoval = true, mappedBy = "account", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    //private Set<PaymentPreferences> paymentPreferences = new HashSet<>();

    private long defaultPaymentMethodId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = Country.FIELD_ID)
    private Country country;

    @Column
    private char active;

    public Account() {

    }

    public Account(Integer accountType, String lastName, String firstName, String loginName, String password, Country country, String phoneNumber, String stateProvince, String cityName, String address, String zipcode, String email, boolean offersPromotion) throws Exception {

        //  Validate Numeric Arguments
        ArgumentValidationHelper.validateArgumentIsNotNull(accountType, "application user type");
        ArgumentValidationHelper.validateNumberArgumentIsPositive(accountType, "application user type");
        //ArgumentValidationHelper.validateNumberArgumentIsPositiveOrZero(countryId, "country id");

        //  Validate String Arguments
        ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(loginName, "login name");
        ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(password, "user password");
        ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(email, "email");
        //ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(lastName, "last name");
        //ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(firstName, "first name");
        //ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(phoneNumber, "phone number");
        //ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(stateProvince, "state/provice/region");
        //ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(cityName, "city name");
        //ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(address, "address");
        //ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(zipcode, "zipcode");
        ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(String.valueOf(offersPromotion), "agree to receive offers and promotions");

        this.setAccountType(accountType);
        this.setLastName(lastName);
        this.setFirstName(firstName);
        this.setLoginName(loginName);
        this.setPassword(password);
        this.setCountry(country);
        this.setPhoneNumber(phoneNumber);
        this.setStateProvince(stateProvince);
        this.setCityName(cityName);
        this.setAddress(address);
        this.setZipcode(zipcode);
        this.setEmail(email);
        this.setAllowOffersPromotion(offersPromotion);
        this.setInternalUnsuccessfulLoginAttempts(0);   //  Initial default value
        this.setInternalUserBlockedFromLoginUntil(0);   //  initial default value
        this.setInternalLastSuccesssulLogin(0);         //  initial default value
        this.setActive('Y');
    }

    public Account(AccountType accountType, String lastName, String firstName, String loginName, String password, Country country, String phoneNumber, String stateProvince, String cityName, String address, String zipcode, String email, boolean offersPromotion) throws Exception {
        this(accountType.getAccountTypeCode(), lastName, firstName, loginName, password, country, phoneNumber, stateProvince, cityName, address, zipcode, email, offersPromotion);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        AccountPassword accountPassword = new AccountPassword(getLoginName(), password);
        this.password = accountPassword.getEncryptedPassword();
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAllowOffersPromotion() {
        return allowOffersPromotion;
    }

    public void setAllowOffersPromotion(boolean allowOffersPromotion) {
        this.allowOffersPromotion = allowOffersPromotion;
    }

    public int getInternalUnsuccessfulLoginAttempts() {
        return internalUnsuccessfulLoginAttempts;
    }

    public void setInternalUnsuccessfulLoginAttempts(int internalUnsuccessfulLoginAttempts) {
        this.internalUnsuccessfulLoginAttempts = internalUnsuccessfulLoginAttempts;
    }

    public long getInternalUserBlockedFromLoginUntil() {
        return internalUserBlockedFromLoginUntil;
    }

    private String getUserBlockedFromLoginUntilAsString() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(internalUserBlockedFromLoginUntil);
    }

    public void setInternalUserBlockedFromLoginUntil(long internalUserBlockedFromLoginUntil) {
        this.internalUserBlockedFromLoginUntil = internalUserBlockedFromLoginUntil;
    }

    public long getInternalLastSuccesssulLogin() {
        return internalLastSuccesssulLogin;
    }

    public void setInternalLastSuccesssulLogin(long internalLastSuccesssulLogin) {
        this.internalLastSuccesssulLogin = internalLastSuccesssulLogin;
    }

    public Set<ShippingAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<ShippingAddress> addresses) {
        this.addresses = addresses;
    }

    /*
    public Set<PaymentPreferences> getPaymentPreferences() {
        return paymentPreferences;
    }

    public void setPaymentPreferences(Set<PaymentPreferences> paymentPreferences) {
        this.paymentPreferences = paymentPreferences;
    }
    */
    public long getDefaultPaymentMethodId() {
        return defaultPaymentMethodId;
    }

    public void setDefaultPaymentMethodId(long defaultPaymentMethodId) {
        this.defaultPaymentMethodId = defaultPaymentMethodId;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public char getActive() {
        return active;
    }

    public void setActive(char active) {
        this.active = Character.toUpperCase(active);
    }

    /**
     * Add milliseconds value interval to current {@link Date} and return the new {@link Date}
     * value as a {@link String} in &quat;yyyy-MM-dd HH:mm:ss&quat; date format.
     *
     * @param milliSeconds Number of milliseconds to add to current {@link Date}.
     * @return Current {@link Date} after adding milliseconds interval.
     */
    public static long addMillisecondsIntervalToTimestamp(long milliSeconds) {
        Date dateAfter = new Date(new Date().getTime() + milliSeconds);

        ////  Display user unblock Timestamp as String
        //System.out.println("date with milliseconds interval=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateAfter));

        //  user unblock Timestamp in milliseconds
        return new Date().getTime() + milliSeconds;
    }

    @Override
    public boolean equals(Object obj) {
        Account compareTo = (Account) obj;

        return (
                (this.getAccountType() == compareTo.getAccountType()) &&
                        (this.getFirstName() == compareTo.getFirstName()) &&
                        (this.getLastName() == compareTo.getLastName()) &&
                        (this.getLoginName() == compareTo.getLoginName()) &&
                        (this.getCountry() == compareTo.getCountry()) &&
                        (this.getStateProvince() == compareTo.getStateProvince()) &&
                        (this.getCityName() == compareTo.getCityName()) &&
                        (this.getAddress() == compareTo.getAddress()) &&
                        (this.getZipcode() == compareTo.getZipcode()) &&
                        (this.getPhoneNumber() == compareTo.getPhoneNumber()) &&
                        (this.getEmail() == compareTo.getEmail())
        );
    }

    @Override
    public String toString() {
        return "User Information: " +
                "id=" + this.getId() + Constants.SPACE +
                "type=" + this.getAccountType() + Constants.SPACE +
                "full name=\"" + this.getFirstName() + Constants.SPACE + this.getLastName() + "\" " +
                "login=\"" + this.getLoginName() + "\" " +
                "email=\"" + this.getEmail() + "\" " +
                "phone=\"" + this.getPhoneNumber() + "\" " +
                "country=" + this.getCountry() + Constants.SPACE +
                "state/province/region=\"" + this.getStateProvince() + "\" " +
                "city=\"" + this.getCityName() + "\" " +
                "address=\"" + this.getAddress() + "\" " +
                "postal code=" + this.getZipcode() + Constants.SPACE +
                "number of unsuccessful login attempts=" + this.getInternalUnsuccessfulLoginAttempts() + Constants.SPACE +
                "user blocked from login until=\"" + this.getUserBlockedFromLoginUntilAsString() + "\" " +
                "last successful login=\"" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.getInternalLastSuccesssulLogin()) + "\"" +
                "agree to receive offers and promotions=" + this.isAllowOffersPromotion();
    }

    public static String convertMillisecondsDateToString(long milliSecondsDate) {
        //System.out.println("date with milliseconds interval=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(milliSecondsDate));
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(milliSecondsDate);
    }

    public static String convertMillisecondsIntervalToString(long milliSeconds) {
        final int MILLISECONDS_IN_A_SECOND = 1000;
        final int MILLISECONDS_IN_A_MINUTE = 60000;
        final int MILLISECONDS_IN_A_HOUR = 3600000;
        final int MILLISECONDS_IN_A_DAY = 86400000;

        int numberOfDays = 0;
        int numberOfHours = 0;
        int numberOfMinutes = 0;
        int numberOfSeconds = 0;

        if (milliSeconds >= MILLISECONDS_IN_A_DAY) {
            numberOfDays = (int) (milliSeconds / MILLISECONDS_IN_A_DAY);
            milliSeconds %= MILLISECONDS_IN_A_DAY;
        }

        if (milliSeconds >= MILLISECONDS_IN_A_HOUR) {
            numberOfHours = (int) (milliSeconds / MILLISECONDS_IN_A_HOUR);
            milliSeconds %= MILLISECONDS_IN_A_HOUR;
        }

        if (milliSeconds >= MILLISECONDS_IN_A_MINUTE) {
            numberOfMinutes = (int) (milliSeconds / MILLISECONDS_IN_A_MINUTE);
            milliSeconds %= MILLISECONDS_IN_A_MINUTE;
        }

        if (milliSeconds >= MILLISECONDS_IN_A_SECOND) {
            numberOfSeconds = (int) (milliSeconds / MILLISECONDS_IN_A_SECOND);
            milliSeconds %= MILLISECONDS_IN_A_SECOND;
        }

        return (numberOfDays > 0 ? numberOfDays + " days " : "") +
                (numberOfHours > 0 ? numberOfHours + " hours " : "") +
                (numberOfMinutes > 0 ? numberOfMinutes + " minutes" : "") +
                (numberOfSeconds > 0 ? numberOfSeconds + " seconds" : "") +
                (milliSeconds > 0 ? milliSeconds + " milliseconds" : "");
    }

//    public static void main(String[] args) {
//        //new Account().addMillisecondsIntervalToTimestamp(30000000);
//        Date date = new Date(new Date().getTime());
//
//        System.out.println("date with milliseconds interval=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
//    }

}
