package com.gogo.model.common.data.jpa.repository;

import com.gogo.model.common.data.jpa.entity.user.Address;
import com.gogo.model.common.data.jpa.entity.user.Customer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Address entity
 **/
@Repository
@ConditionalOnProperty(name = "spring.application.name", havingValue = "common-security")
public interface AddressRepository extends CrudRepository<Address, Long> {

    public List<Address> findByCustomer(Customer customer);

    public List<Address> findByCustomerId(Long customerId);

    @Query("SELECT a FROM Address a WHERE a.id = ?1 AND a.customer.id = ?2")
    public Address findByIdAndCustomer(Long addressId, Long customerId);

    @Query("DELETE FROM Address a WHERE a.id = ?1 AND a.customer.id = ?2")
    @Modifying
    public void deleteByIdAndCustomer(Long addressId, Long customerId);

    @Query(value = "UPDATE Address a SET a.defaultAddress = CASE WHEN a.id = :addressId THEN true ELSE false END WHERE a.customer.id = :customerId")
    @Modifying
    public void updateDefaultAddress(Long addressId, Long customerId);

    @Query("SELECT a FROM Address a WHERE a.customer.id = ?1 AND a.defaultAddress = true")
    public Address findDefaultByCustomer(Long customerId);
}

