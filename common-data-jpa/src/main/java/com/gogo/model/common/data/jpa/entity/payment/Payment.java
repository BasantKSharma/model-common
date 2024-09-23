package com.gogo.model.common.data.jpa.entity.payment;

import com.gogo.model.common.data.jpa.entity.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "payment")
@ConditionalOnProperty(name = "spring.shopping.table", havingValue = "true")
public class Payment extends AbstractEntity {

    @Column(name = "gateway", nullable = false)
    private String gateway;

    @Column(name = "txn_id", nullable = false)
    private String txnId;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "product_info", nullable = false)
    private String productInfo;

    @Column(name = "surl")
    private String surl;

    @Column(name = "furl")
    private String furl;

    @Column(name = "hash")
    private String hash;

    @Column(name = "status")
    private String status;

    @Column(name = "response_code")
    private String responseCode;

    @Column(name = "response_message")
    private String responseMessage;
}
