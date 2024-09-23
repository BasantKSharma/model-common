package com.gogo.model.common.data.jpa.entity.shipping;

import com.gogo.model.common.data.jpa.entity.AbstractEntity;
import com.gogo.model.common.data.jpa.entity.product.Country;
import com.gogo.model.common.data.jpa.entity.setting.State;
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
@Table(name = "shipping_rate")
@ConditionalOnProperty(name = "spring.shopping.table", havingValue = "true")
@Setter
public class ShippingRate extends AbstractEntity {

    private BigDecimal rate;

    private Integer days;

    @Column(name = "cod_supported")
    private Boolean codSupported;

    @Column(name = "city")
    private String city;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
}
