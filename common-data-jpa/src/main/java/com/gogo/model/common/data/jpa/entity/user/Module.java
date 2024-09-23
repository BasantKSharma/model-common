package com.gogo.model.common.data.jpa.entity.user;

import com.gogo.model.common.data.jpa.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "module")
@ConditionalOnProperty(name = "spring.shopping.table", havingValue = "true")
public class Module extends AbstractEntity {

    @Column(length = 40, nullable = false, unique = true)
    private String name;

    @Column(length = 150, nullable = false)
    private String description;

    @Column(name = "link_url", length = 150, nullable = false)
    private String linkUrl;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "module_role",
            joinColumns = @JoinColumn(name = "module_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role) {
        this.roles.add(role);
    }

}
