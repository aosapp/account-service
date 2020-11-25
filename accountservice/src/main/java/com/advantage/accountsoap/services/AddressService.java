package com.advantage.accountsoap.services;

import com.advantage.accountsoap.config.BeansManager;
import com.advantage.accountsoap.config.Injectable;
import com.advantage.accountsoap.dao.AccountRepository;
import com.advantage.accountsoap.dao.AddressRepository;
import com.advantage.accountsoap.dao.CountryRepository;
import com.advantage.accountsoap.dto.address.AddAddressDto;
import com.advantage.accountsoap.dto.address.AddressDto;
import com.advantage.accountsoap.dto.address.AddressStatusResponse;
import com.advantage.accountsoap.model.Account;
import com.advantage.accountsoap.model.Country;
import com.advantage.accountsoap.model.ShippingAddress;
import com.advantage.common.utils.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AddressService implements Injectable{

    private AddressRepository addressRepository;


    private AccountRepository accountRepository;

    @Override
    public void inject(BeansManager beansManager){
        this.accountRepository =  beansManager.getAccountRepository();
        this.addressRepository = beansManager.getAddressRepository();
    }

    @Autowired
    @Qualifier("countryRepository")
    private CountryRepository countryRepository;


    @Transactional
    public AddressStatusResponse add(long accountId, Collection<AddAddressDto> addresses) {
        if(accountRepository.get(accountId) == null) {
            return new AddressStatusResponse(false, "Account not found");
        }

        boolean updateUserAccountAddress = true;
        for (AddAddressDto address : addresses) {
            addressRepository.addAddress(accountId,
                    address.getAddressLine1(),
                    address.getAddressLine2(),
                    address.getCity(),
                    address.getCountry(),
                    address.getState(),
                    address.getPostalCode());

            //  region Update user-account address
            if (updateUserAccountAddress) {
                StringBuilder sb = new StringBuilder(address.getAddressLine1() != null ? address.getAddressLine1() : "")
                        .append(address.getAddressLine2() != null ? address.getAddressLine2() : "");

                updateUserAccountAddress(accountId,
                        sb.toString(),
                        address.getCity(),
                        address.getPostalCode(),
                        address.getState(),
                        countryRepository.get(Long.valueOf(address.getCountry())) != null ? countryRepository.get(Long.valueOf(address.getCountry())) : new Country());

                updateUserAccountAddress = false;
            }
            //  endregion

        }

        return new AddressStatusResponse(true, "Successful");
    }

    @Transactional
    public AddressStatusResponse add(long accountId, AddAddressDto address) {
        List<AddAddressDto> dtos = new ArrayList<>();
        dtos.add(address);

        return add(accountId, dtos);
    }

    @Transactional
    public List<AddressDto> getAll() {
        return fillDtos(addressRepository.getAll());
    }

    @Transactional
    public List<AddressDto> getByAccountId(long accountId) {
        return fillDtos(addressRepository.getByAccountId(accountId));
    }

    private void updateUserAccountAddress(long accountId, String address, String cityName, String zipcode, String stateProvince, Country country) {
        Account account = accountRepository.get(accountId);
        if (account != null) {
            account.setAddress(!StringHelper.isNullOrEmpty(address) ? address : "");
            account.setCityName(!StringHelper.isNullOrEmpty(cityName) ? cityName : "");
            account.setZipcode(!StringHelper.isNullOrEmpty(zipcode) ? zipcode : "");
            account.setStateProvince(!StringHelper.isNullOrEmpty(stateProvince) ? stateProvince : "");
            account.setCountry(country);

            accountRepository.updateAppUser(account);
        }
    }

    @Transactional
    public AddressStatusResponse update(AddressDto address) {
        ShippingAddress shippingAddress = addressRepository.get(address.getId());
        if(shippingAddress == null) return  new AddressStatusResponse(false, "Address not found");

        shippingAddress.setAddressLine1(address.getAddressLine1());
        shippingAddress.setAddressLine2(address.getAddressLine2());
        shippingAddress.setCity(address.getCity());
        shippingAddress.setCountry(address.getCountry());
        shippingAddress.setPostalCode(address.getPostalCode());
        shippingAddress.setState(address.getState());
        addressRepository.update(shippingAddress);

        //  region Update user-account address
        StringBuilder sb = new StringBuilder(address.getAddressLine1() != null ? address.getAddressLine1() : "")
                .append(address.getAddressLine2() != null ? address.getAddressLine2() : "");

        updateUserAccountAddress(shippingAddress.getAccount().getId(),
                sb.toString(),
                address.getCity(),
                address.getPostalCode(),
                address.getState(),
                !StringHelper.isNullOrEmpty(address.getCountry()) ? countryRepository.get(Long.valueOf(address.getCountry())) : countryRepository.get(40L));
        //  endregion

        return new AddressStatusResponse(true, "Successfully");
    }

    private List<AddressDto> fillDtos(Collection<ShippingAddress> addresses) {
        if(addresses == null) return null;

        List<AddressDto> dtos = new ArrayList<>();
        for (ShippingAddress address : addresses) {
            dtos.add(new AddressDto(address.getId(),
                    address.getAddressLine1(),
                    address.getAddressLine2(),
                    address.getCity(),
                    address.getCountry(),
                    address.getState(),
                    address.getPostalCode(),
                    address.getAccount().getId()));
        }

        return dtos;
    }
}
