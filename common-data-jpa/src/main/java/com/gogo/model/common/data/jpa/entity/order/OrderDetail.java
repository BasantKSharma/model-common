package com.gogo.model.common.data.jpa.entity.order;

import com.gogo.model.common.data.jpa.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.math.BigDecimal;


@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
@Entity
@Table(name = "orders_detail")
@ConditionalOnProperty(name = "spring.shopping.table", havingValue = "true")
@Setter
public class OrderDetail extends AbstractEntity {

    private Integer quantity;

    private BigDecimal productCost;

    private BigDecimal shippingCost;

    private BigDecimal unitPrice;

    private BigDecimal subtotal;

    private BigDecimal tax;

    private BigDecimal total;

    @Column(name = "product_id", nullable = false)
    private Long productId;

//    @OneToOne
//    @JoinTable(
//            name = "order_detail_product",
//            joinColumns = @JoinColumn(name = "order_detail_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id")
//    )
//    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}

