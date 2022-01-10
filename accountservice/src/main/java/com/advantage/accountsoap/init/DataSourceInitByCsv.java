package com.advantage.accountsoap.init;

import com.advantage.accountsoap.model.Account;
import com.advantage.accountsoap.model.Country;
import com.advantage.accountsoap.util.RestApiHelper;
import com.advantage.accountsoap.util.fs.FileSystemHelper;
import com.advantage.common.SystemParameters;
import com.advantage.common.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class DataSourceInitByCsv {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private Environment env;

    public void init() throws Exception {
//comment
        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select a from Account a where a.email=:email");
        query.setParameter("email", "mercury@microfocus.com");
        List l = query.list();

        if (!SystemParameters.getHibernateHbm2ddlAuto(env.getProperty("account.hibernate.db.hbm2ddlAuto")).equals("validate") || l.isEmpty()) {

            Transaction transaction;

            //  Get countries list in CSV (Comma Separated Values) file
            ClassPathResource filePathCSV = new ClassPathResource("countries_20150630.csv");
            String protocol = this.getClass().getResource("").getProtocol();



            ObjectMapper objectMapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            transaction = session.beginTransaction();

        /* Countries */
            List<String> countries;
            if(protocol.contains("jar")){
                countries = FileSystemHelper.readFileCsv(null, true, filePathCSV.getInputStream());
            } else {
                countries = FileSystemHelper.readFileCsv(filePathCSV.getFile().getAbsolutePath(), false, null);
            }
            Map<Long, Country> countryMap = new HashMap<>();

            for (String str : countries) {
                String[] substrings = str.split(",");
                Country country = new Country(substrings[1], substrings[2], Integer.valueOf(substrings[3]));
                session.persist(country);
                countryMap.put(country.getId(), country);
            }
            transaction.commit();

            transaction = session.beginTransaction();

        /*
        ===========================================================================================================================================================================================================
        new Account(accountType, lastName, firstName, loginName, password, countryCode+"L", phoneNumber, stateProvinceName, cityName, address, zipcodeString, email, offerPromotionBoolean)
        ===========================================================================================================================================================================================================
         */
            session.persist(new Account(AccountType.ADMIN.getAccountTypeCode(), "Mercury", "Admin User", "Mercury", "Mercury", countryMap.get(10L), "077-7654321", "Jerusalem1", "Jerusalem", "address", "9876543", "mercury@microfocus.com", true));
            session.persist(new Account(AccountType.ADMIN.getAccountTypeCode(), "Smith", "John", "admin", "adm1n", countryMap.get(40L), "480-222-1111", "NY", "New York", "address", "10017", "admin@admin.ad", true));
            session.persist(new Account(AccountType.USER.getAccountTypeCode(), "Gilat", "Naor", "gilat", "gG123", countryMap.get(128L), "052-7654321", "Jerusalem1", "Jerusalem", "address", "9876543", "d0r1@gmail.com", true));
            session.persist(new Account(AccountType.USER.getAccountTypeCode(), "Bukhantsov", "Kostya", "kostya", "kostya", countryMap.get(10L), "052-22222222", "Jerusalem1", "Jerusalem", "address", "9876543", "kostya@gmail.com", true));
            session.persist(new Account(AccountType.ADMIN.getAccountTypeCode(), "Brown", "John", "AppPulse", "AppPulse1", countryMap.get(40L), "617-527-5555", "MA", "Newton", "826 Morseland Ave.", "02458", "AppPlusedemo@aos.ad", true));
            transaction.commit();
        }
    }

    public void init(boolean runSmokingGunScenario) throws Exception {
//comment
        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select a from Account a where a.email=:email");
        query.setParameter("email", "mercury@microfocus.com");
        List l = query.list();

        if (runSmokingGunScenario) {

            Transaction transaction;

            //  Get countries list in CSV (Comma Separated Values) file
            ClassPathResource filePathCSV = new ClassPathResource("countries_20150630.csv");
            String protocol = this.getClass().getResource("").getProtocol();



            ObjectMapper objectMapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            transaction = session.beginTransaction();

            /* Countries */
            List<String> countries;
            if(protocol.contains("jar")){
                countries = FileSystemHelper.readFileCsv(null, true, filePathCSV.getInputStream());
            } else {
                countries = FileSystemHelper.readFileCsv(filePathCSV.getFile().getAbsolutePath(), false, null);
            }
            Map<Long, Country> countryMap = new HashMap<>();

            for (String str : countries) {
                String[] substrings = str.split(",");
                Country country = new Country(substrings[1], substrings[2], Integer.valueOf(substrings[3]));
                session.persist(country);
                countryMap.put(country.getId(), country);
            }
            transaction.commit();

            transaction = session.beginTransaction();

        /*
        ===========================================================================================================================================================================================================
        new Account(accountType, lastName, firstName, loginName, password, countryCode+"L", phoneNumber, stateProvinceName, cityName, address, zipcodeString, email, offerPromotionBoolean)
        ===========================================================================================================================================================================================================
         */
            session.persist(new Account(AccountType.ADMIN.getAccountTypeCode(), "Mercury", "Admin User", "Mercury", "Mercury", countryMap.get(10L), "077-7654321", "Jerusalem1", "Jerusalem", "address", "9876543", "mercury@microfocus.com", true));
            session.persist(new Account(AccountType.ADMIN.getAccountTypeCode(), "Smith", "John", "admin", "adm1n", countryMap.get(40L), "480-222-1111", "NY", "New York", "address", "10017", "admin@admin.ad", true));
            session.persist(new Account(AccountType.USER.getAccountTypeCode(), "Gilat", "Naor", "gilat", "gG123", countryMap.get(128L), "052-7654321", "Jerusalem1", "Jerusalem", "address", "9876543", "d0r1@gmail.com", true));
            session.persist(new Account(AccountType.USER.getAccountTypeCode(), "Bukhantsov", "Kostya", "kostya", "kostya", countryMap.get(10L), "052-22222222", "Jerusalem1", "Jerusalem", "address", "9876543", "kostya@gmail.com", true));
            session.persist(new Account(AccountType.ADMIN.getAccountTypeCode(), "Brown", "John", "AppPulse", "AppPulse1", countryMap.get(40L), "617-527-5555", "MA", "Newton", "826 Morseland Ave.", "02458", "AppPlusedemo@aos.ad", true));
            transaction.commit();
        }
    }
}
