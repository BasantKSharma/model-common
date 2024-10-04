package com.gogo.model.common.data.jpa.repository;

import com.gogo.model.common.data.jpa.entity.locale.Country;
import com.gogo.model.common.data.jpa.entity.locale.State;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for State entity
 * **/
@Repository
@ConditionalOnProperty(name = "spring.application.name", havingValue = "shopping-service-setting")
public interface StateRepository extends CrudRepository<State, Long> {

    public List<State> findByCountryOrderByNameAsc(Country country);

    public List<State> findByOrderByCountryNameAsc();

    void deleteByCountryId(Long countryId);
}
