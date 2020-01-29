package com.advantage.accountsoap.services;

import com.advantage.accountsoap.config.BeansManager;
import com.advantage.accountsoap.config.Injectable;
import com.advantage.accountsoap.dao.CountryRepository;
import com.advantage.accountsoap.dto.country.CountryDto;
import com.advantage.accountsoap.dto.country.CountryStatusResponse;
import com.advantage.accountsoap.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CountryService implements Injectable{

    public CountryRepository countryRepository;

    @Override
    public void inject(BeansManager beansManager){
        this.countryRepository = beansManager.getCountryRepository();
    }

    @Transactional
    public CountryStatusResponse create(final String name, final int phonePrefix) {
        return countryRepository.create(name, phonePrefix);
    }

    @Transactional
    public CountryStatusResponse create(final String name, final String isoName, final int phonePrefix) {
        return countryRepository.create(name, isoName, phonePrefix);
    }

    @Transactional(readOnly = true)
    public List<CountryDto> getAllCountries() {
        List<Country> countries = countryRepository.getAllCountries();

        return fillDto(countries);
    }

    @Transactional(readOnly = true)
    public List<CountryDto> getCountriesByPartialName(final String countryName) {
        return fillDto(countryRepository.getCountriesByPartialName(countryName));
    }

    @Transactional(readOnly = true)
    public List<CountryDto> getCountriesByPhonePrefix(final int phonePrefix) {
        return fillDto(countryRepository.getCountriesByPhonePrefix(phonePrefix));
    }

    private List<CountryDto> fillDto(Collection<Country> countries) {
        if(countries == null) return null;
        List<CountryDto> dtos = new ArrayList<>();
        for (Country country : countries) {
            dtos.add(new CountryDto(country.getId(), country.getName(), country.getIsoName(), country.getPhonePrefix()));
        }

        return dtos;
    }
}
