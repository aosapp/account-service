package com.advantage.accountsoap.config;

import com.advantage.accountsoap.dao.AccountRepository;
import com.advantage.accountsoap.dao.AddressRepository;
import com.advantage.accountsoap.dao.CountryRepository;
import com.advantage.accountsoap.dao.PaymentPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kubany on 1/2/2017.
 */
@Component
public class BeansManager {

    @Autowired
    @Qualifier("accountRepository")
    private AccountRepository accountRepository;

    @Autowired
    @Qualifier("addressRepository")
    private AddressRepository addressRepository;

    @Autowired
    @Qualifier("paymentPreferencesRepository")
    public PaymentPreferencesRepository paymentPreferencesRepository;

    @Autowired
    @Qualifier("countryRepository")
    public CountryRepository countryRepository;

    public AccountRepository getAccountRepository() {
        return accountRepository;
    }

    public AddressRepository getAddressRepository() {
        return addressRepository;
    }

    public PaymentPreferencesRepository getPaymentPreferencesRepository() {
        return paymentPreferencesRepository;
    }

    public CountryRepository getCountryRepository() {
        return countryRepository;
    }

    @Autowired
    private Set<Injectable> injectables = new HashSet();

    @PostConstruct
    private void inject() {
        for (Injectable injectableItem : injectables) {
            injectableItem.inject(this);
        }
    }
}
