package com.gogo.model.common.domain.dto.user;

import com.gogo.model.common.domain.dto.AbstractDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ModuleDto extends AbstractDto {

    private String name;

    private String description;

    private String linkUrl;

    private Set<String> roles;

    @Override
    public String getIdentifier() {
        return name;
    }
}
