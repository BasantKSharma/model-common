package com.gogo.model.common.data.jpa.entity.product;

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
@Table(name = "cart_item")
@ConditionalOnProperty(name = "spring.shopping.table", havingValue = "true")
@Setter
public class CartItem extends AbstractEntity {

    private Long customerId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    @Transient
    public BigDecimal getSubtotal() {
        return product.getDiscountPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
