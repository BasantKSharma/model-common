package com.gogo.model.common.data.jpa.repository;

import com.gogo.model.common.data.jpa.entity.locale.Country;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Country entity
 * **/
@Repository
@ConditionalOnProperty(name = "spring.application.name", havingValue = "shopping-service-setting")
public interface CountryRepository extends CrudRepository<Country, Long> {

    Optional<Country> findByName(String name);

    List<Country> findAllByOrderByNameAsc();

    void deleteByIdIn(List<Long> ids);
}
