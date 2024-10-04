package com.gogo.model.common.data.jpa.repository;

import com.gogo.model.common.data.jpa.entity.locale.Currency;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Currency entity
 * **/
@Repository
@ConditionalOnProperty(name = "spring.application.name", havingValue = "shopping-service-setting")
public interface CurrencyRepository extends CrudRepository<Currency, Long> {

    public List<Currency> findAllByOrderByNameAsc();
}
