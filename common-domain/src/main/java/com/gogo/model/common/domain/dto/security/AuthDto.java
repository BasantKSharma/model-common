package com.gogo.model.common.domain.dto.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gogo.model.common.domain.dto.AbstractDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthDto extends AbstractDto {

    private String userName;

    private String email;

    private String message;

    @JsonProperty("isValid")
    private Boolean valid = Boolean.FALSE;

    private List<String> roles;

    public Boolean isValid() {
        return valid;
    }

    @Override
    public String getIdentifier() {
        return userName;
    }
}
