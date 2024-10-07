package com.gogo.model.common.domain.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gogo.model.common.domain.dto.EditableDto;
import com.gogo.model.common.domain.dto.AbstractDto;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
public class AddressDto extends AbstractDto implements EditableDto {

    public AddressDto() {
        // No-argument constructor
    }

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private Long stateId;

    private String stateName;

    private String postalCode;

    private boolean defaultAddress;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date createdAt;

    private Long countryId;

    private String countryName;

    private Long customerId;

    @Override
    public String getIdentifier() {
        return firstName + "#" + lastName;
    }

    @Transient
    @JsonIgnore
    @IgnoreForBinding
    public String getFullAddress() {
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
        if (StringUtils.isNotBlank(stateName)) {
            address.append(", ").append(stateName);
        }
        if (StringUtils.isNotBlank(countryName)) {
            address.append(", ").append(countryName);
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
