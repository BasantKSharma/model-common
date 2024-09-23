package com.gogo.model.common.data.jpa.repository;

import com.gogo.model.common.data.jpa.entity.payment.Payment;
import com.gogo.model.common.data.jpa.pagination.SearchRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnProperty(name = "spring.application.name", havingValue = "shopping-common-payment")
public interface PaymentRepository extends SearchRepository<Payment, Long> {

    @Query("SELECT p FROM Payment p WHERE"
            + " p.gateway LIKE %?1% OR p.txnId LIKE %?1% OR"
            + " p.firstName LIKE %?1% OR CAST(p.amount AS STRING) LIKE %?1% OR"
            + " p.email LIKE %?1% OR p.phoneNumber LIKE %?1% OR"
            + " p.productInfo LIKE %?1% OR p.status LIKE %?1% OR"
            + " p.responseMessage LIKE %?1% OR p.responseCode LIKE %?1%")
    Page<Payment> findAll(String keyword, Pageable pageable);

    @Query("SELECT p FROM Payment p WHERE p.gateway = :gateway")
    public Payment findByGateway(@Param("gateway") String gateway);

    @Query("SELECT p FROM Payment p WHERE p.txnId = :txnId")
    public Payment findByTxnId(@Param("txnId") String txnId);
}
