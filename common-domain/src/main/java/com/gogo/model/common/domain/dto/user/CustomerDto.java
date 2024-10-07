package com.gogo.model.common.domain.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gogo.model.common.domain.constants.AuthenticationType;
import com.gogo.model.common.domain.dto.AbstractDto;
import com.gogo.model.common.domain.dto.EditableDto;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Setter
@Getter
public class CustomerDto extends AbstractDto implements EditableDto {

    private String email;

    private String password;

    private String originalPassword;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String verificationCode;

    private String resetPasswordToken;

    private boolean enabled;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date createdAt;

    private AuthenticationType authenticationType;

    private Set<String> roles;

    private List<AddressDto> addresses;

    @Override
    public String getIdentifier() {
        return email;
    }

    @Transient
    @JsonIgnore
    @IgnoreForBinding
    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Transient
    @JsonIgnore
    @IgnoreForBinding
    public AddressDto getDefaultAddress() {
        if (addresses != null && !addresses.isEmpty()) {
            for (AddressDto address : addresses) {
                if (address.isDefaultAddress()) {
                    return address;
                }
            }
            // If no default address is found, return the first address in the list
            return addresses.get(0);
        }
        return null;
    }

    @Transient
    @JsonIgnore
    @IgnoreForBinding
    public Integer getDefaultAddressIndex() {
        if (addresses != null && !addresses.isEmpty()) {
            for (int i = 0; i < addresses.size(); i++) {
                if (addresses.get(i).isDefaultAddress()) {
                    return i;
                }
            }
        }
        return 0;
    }

    @Transient
    @JsonIgnore
    @IgnoreForBinding
    public String getFullAddress() {
        StringBuilder address = new StringBuilder(firstName);
        if (lastName != null && !lastName.isEmpty()) address.append(" ").append(lastName);

        AddressDto defaultAddress = getDefaultAddress();

        if (defaultAddress == null) {
            return address.toString();
        }
        address = new StringBuilder(defaultAddress.getFullAddress());
        return address.toString();
    }

    @Transient
    @JsonIgnore
    @IgnoreForBinding
    public String getMaskedPassword() {
        int length = 8;
        if (password != null) {
            length = password.length();
        }
        return "*".repeat(length);
    }
}
