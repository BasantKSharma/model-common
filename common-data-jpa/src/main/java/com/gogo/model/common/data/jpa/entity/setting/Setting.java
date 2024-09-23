package com.gogo.model.common.data.jpa.entity.setting;


import com.gogo.model.common.data.jpa.entity.AbstractEntity;
import com.gogo.model.common.domain.constants.SettingCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "setting")
@ConditionalOnProperty(name = "spring.shopping.table", havingValue = "true")
public class Setting extends AbstractEntity {

    @Column(nullable = false, length = 128)
    private String name;

    @Column(nullable = false, length = 1024, columnDefinition = "TEXT")
    private String value;

    @Column(name = "input_type", nullable = false)
    private String inputType;

    @Column(name = "input_source")
    private String inputSource;

    @Enumerated(EnumType.STRING)
    @Column(length = 45, nullable = false)
    private SettingCategory category;

    public Setting(String name) {
        this.name = name;
    }
}
