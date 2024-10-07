package com.gogo.model.common.domain.dto.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gogo.model.common.domain.dto.ImageDto;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class UserDto extends ImageDto {

    private String email;

    private String firstName;

    private String lastName;

    private String password;

    private Set<String> roles;

    private Boolean enabled;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date createdAt;

    @Transient
    @JsonIgnore
    @IgnoreForBinding
    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String getIdentifier() {
        return email;
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

    @Transient
    @JsonIgnore
    @IgnoreForBinding
    public String getRolesList() {
        if (roles == null) {
            return "[]";
        }
        return roles.toString();
    }
}
