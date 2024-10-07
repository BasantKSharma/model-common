package com.gogo.model.common.data.jpa.repository;

import com.gogo.model.common.data.jpa.entity.user.Customer;
import com.gogo.model.common.data.jpa.pagination.SearchRepository;
import com.gogo.model.common.domain.constants.AuthenticationType;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository for Customer entity
 * **/
@Repository
@ConditionalOnProperty(name = "spring.application.name", havingValue = "common-security")
public interface CustomerRepository extends SearchRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c WHERE c.email = ?1")
    public Customer findByEmail(String email);

    @Query("SELECT c FROM Customer c WHERE c.verificationCode = ?1")
    public Customer findByVerificationCode(String code);

    @Query("SELECT c FROM Customer c WHERE c.resetPasswordToken = ?1")
    public Customer findByResetPasswordToken(String resetToken);

    @Query("UPDATE Customer c SET c.enabled = true WHERE c.id = ?1")
    @Modifying
    public void enable(Long id);

    @Query("SELECT u FROM Customer u WHERE CONCAT(u.id, ' ', u.email, ' ', u.firstName, ' ',"
            + " u.lastName) LIKE %?1%")
    public Page<Customer> findAll(String keyword, Pageable pageable);

    @Query("UPDATE Customer c SET c.authenticationType = ?2 WHERE c.id = ?1")
    @Modifying
    public void updateAuthenticationType(Long customerId, AuthenticationType type);
}

