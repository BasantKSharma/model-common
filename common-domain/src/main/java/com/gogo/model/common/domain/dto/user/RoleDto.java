package com.gogo.model.common.domain.dto.user;


import com.gogo.model.common.domain.dto.AbstractDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto extends AbstractDto {

    private String name;

    private String description;

    @Override
    public String getIdentifier() {
        return name;
    }
}
