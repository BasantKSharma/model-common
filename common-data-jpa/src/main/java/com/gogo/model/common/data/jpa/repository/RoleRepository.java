package com.gogo.model.common.data.jpa.repository;

import com.gogo.model.common.data.jpa.entity.user.Role;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@ConditionalOnProperty(name = "spring.application.name", havingValue = "shopping-service-user")
public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByName(String roleName);
}
