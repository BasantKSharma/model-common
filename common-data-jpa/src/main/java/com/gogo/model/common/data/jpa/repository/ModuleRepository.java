package com.gogo.model.common.data.jpa.repository;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnProperty(name = "spring.application.name", havingValue = "shopping-service-user")
public interface ModuleRepository extends PagingAndSortingRepository<Module, Long> {
}
