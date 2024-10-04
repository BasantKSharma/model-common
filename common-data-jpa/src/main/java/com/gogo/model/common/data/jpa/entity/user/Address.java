package com.gogo.model.common.data.jpa.entity.user;

import com.gogo.model.common.data.jpa.entity.locale.Country;
import com.gogo.model.common.data.jpa.entity.AbstractEntity;
import com.gogo.model.common.data.jpa.entity.locale.State;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "address")
@ConditionalOnProperty(name = "spring.shopping.table", havingValue = "true")
public class Address extends AbstractEntity {

    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    @Column(name = "phone_number", nullable = false, length = 15)
    private String phoneNumber;

    @Column(name = "address_line1", nullable = false, length = 64)
    private String addressLine1;

    @Column(name = "address_line2", length = 64)
    private String addressLine2;

    @Column(nullable = false, length = 45)
    private String city;

    @Column(name = "postal_code", nullable = false, length = 10)
    private String postalCode;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "default_address")
    private boolean defaultAddress;

    @Override
    public String toString() {
        StringBuilder address = new StringBuilder(firstName);
        if (lastName != null && !lastName.isEmpty()) address.append(" ").append(lastName);

        if (StringUtils.isNotBlank(addressLine1)) {
            address.append(", ").append(addressLine1);
        }
        if (StringUtils.isNotBlank(addressLine2)) {
            address.append(", ").append(addressLine2);
        }
        if (StringUtils.isNotBlank(city)) {
            address.append(", ").append(city);
        }
        if (state != null && StringUtils.isNotBlank(state.getName())) {
            address.append(", ").append(state.getName());
        }
        if (country != null && StringUtils.isNotBlank(country.getName())) {
            address.append(", ").append(country.getName());
        }
        if (StringUtils.isNotBlank(postalCode)) {
            address.append(". Postal Code: ").append(postalCode);
        }
        if (StringUtils.isNotBlank(phoneNumber)) {
            address.append(". Phone Number: ").append(phoneNumber);
        }
        return address.toString();
    }
}
