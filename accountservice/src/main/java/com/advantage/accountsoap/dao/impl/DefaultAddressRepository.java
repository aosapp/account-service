package com.advantage.accountsoap.dao.impl;

import com.advantage.accountsoap.dao.AbstractRepository;
import com.advantage.accountsoap.dao.AddressRepository;
import com.advantage.accountsoap.dto.address.AddressDto;
import com.advantage.accountsoap.model.Account;
import com.advantage.accountsoap.model.ShippingAddress;
import com.advantage.accountsoap.services.AccountService;
import com.advantage.accountsoap.util.ArgumentValidationHelper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Component
@Qualifier("addressRepository")
@Repository
public class DefaultAddressRepository extends AbstractRepository implements AddressRepository {
    @Autowired
    AccountService accountService;

    @Override
    public int delete(ShippingAddress... entities) {
        int count = 0;
        for (ShippingAddress entity : entities) {
            if (entityManager.contains(entity)) {
                entityManager.remove(entity);
                count++;
            }
        }
        return count;
    }

//    @Override
//    public ShippingAddress deleteShippingAddress(long userId) {
//
//        ShippingAddress entity = get(userId);
//        if (entity != null) delete(entity);
//
//        return entity;
//    }
//    @Override
//    public int deleteShippingAddress(long userId) {
//
//        try {
//            final StringBuilder deleteShippingAddress = new StringBuilder("DELETE FROM ")
//                    .append("ShippingAddress")
//                    .append(" WHERE ")
//                    .append(ShippingAddress.FIELD_USER_ID).append("=").append(userId);
//            Query queryDelete = entityManager.createQuery(deleteShippingAddress.toString());
//
//            int result = queryDelete.executeUpdate();
//
//            List<ShippingAddress> addresses = new ArrayList<>(accountService.getById(userId).getAddresses());
//
//            return addresses.isEmpty() ? 0 : 1;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 1;
//        }
//    }

    @Override
    public List<ShippingAddress> getAll() {
        List<ShippingAddress> addresses =
                entityManager.createNamedQuery(ShippingAddress.QUERY_GET_ALL, ShippingAddress.class)
                        .getResultList();

        return addresses.isEmpty() ? null : addresses;
    }

    @Override
    public ShippingAddress get(Long entityId) {
        ArgumentValidationHelper.validateArgumentIsNotNull(entityId, "address id");

        return entityManager.find(ShippingAddress.class, entityId);
    }

    @Override
    public long addAddress(long userId, String addressLine1, String addressLine2, String city, String country,
                           String state, String postalCode) {
        Account account = accountService.getById(userId);
        if (account == null) return -1;
        ShippingAddress address = new ShippingAddress(addressLine1, addressLine2, account, city,
                country, state, postalCode);

        return create(address);
    }

    @Override
    public Long create(ShippingAddress entity) {
        entityManager.persist(entity);

        return entity.getId();
    }

    @Override
    public List<ShippingAddress> getByAccountId(Long accountId) {
        List<ShippingAddress> addresses = new ArrayList<>(accountService.getById(accountId).getAddresses());

        return addresses.isEmpty() ? null : addresses;
    }

    @Override
    public List<AddressDto> getByUserId(Long userId) {
//        List<ShippingAddress> addresses = entityManager.createNamedQuery(ShippingAddress.QUERY_GET_BY_USER_ID, ShippingAddress.class)
//                .setParameter(ShippingAddress.PARAM_USER_ID, userId)
//                .getResultList();
//
//        return addresses.isEmpty() ? null : addresses;
        SessionFactory sessionFactory = entityManager.getEntityManagerFactory().unwrap(SessionFactory.class);

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        /*
select  sa.id,
        sa.address_line1,
        sa.address_line2,
        sa.city,
        sa.country,
        sa.state,
        sa.postalcode,
        sa.user_id
from    shippingaddress sa
where   sa.user_id = 'US055169452'
order by sa.id
        */

        StringBuilder sqlQuery = new StringBuilder("SELECT sa.id, ")
                .append("sa.address_line1, sa.address_line2, ")
                .append("sa.city, sa.country, sa.state, sa.postalcode, ")
                .append("sa.user_id")
                .append("from shippingaddress sa")
                .append("where sa.user_id")
                .append(" = ")
                .append(" || ")
                .append("substring(o.fiscal_yr_quarter_display_code from 7 for 1), '999999') as fyq ")
                .append("from customerorder o ")
                .append("where o.customer_id").append(" = ").append(userId)
                .append("order by sa.id");

        org.hibernate.Query query = session.createSQLQuery(sqlQuery.toString());
        List resultSet =  session.createSQLQuery(sqlQuery.toString()).list();

        List<AddressDto> addressList = new ArrayList<>();

        for(Object object : resultSet) {
            System.out.println(object);

            Object[] row = (Object[]) object;

            //ShortProductDto shortProductDto = new ShortProductDto(amid, (String)row[1],(String)row[2], ((String)row[3]).intValue());
            AddressDto addressDto = new AddressDto((Long)row[0],
                    (String)row[1],(String)row[2],
                    (String)row[3], (String)row[4],
                    (String)row[5], (String)row[6],
                    userId);

            addressList.add(addressDto);
        }

        transaction.commit();
        session.close();

        return addressList.size() != 0 ? addressList : null;

    }

        @Override
    public ShippingAddress update(ShippingAddress address) {
        entityManager.persist(address);

        return address;
    }
}
