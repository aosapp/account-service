package com.advantage.accountsoap.dao;

import com.advantage.accountsoap.dto.address.AddressDto;
import com.advantage.accountsoap.model.ShippingAddress;
import com.advantage.common.dao.DefaultCRUDOperations;

import java.util.List;

public interface AddressRepository extends DefaultCRUDOperations<ShippingAddress> {
    long addAddress(long userId, String addressLine1,
                    String addressLine2,
                    String city,
                    String country,
                    String state,
                    String postalCode);

    List<ShippingAddress> getByAccountId(Long accountId);

    List<AddressDto> getByUserId(Long accountId);

    ShippingAddress update(ShippingAddress address);

}
