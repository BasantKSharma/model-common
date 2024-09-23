package com.gogo.model.common.data.jpa.repository;

import com.gogo.model.common.data.jpa.entity.shipping.ShippingRate;
import com.gogo.model.common.data.jpa.pagination.SearchRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Search repository for Shipping Rate entity
 * **/
@Repository
@ConditionalOnProperty(name = "spring.application.name", havingValue = "shopping-service-shipping")
public interface ShippingRateRepository extends SearchRepository<ShippingRate, Long> {

    @Query("SELECT sr FROM ShippingRate sr WHERE sr.country.id = ?1 AND sr.state.id = ?2")
    public ShippingRate findByCountryAndState(Long countryId, Long stateId);

    @Query("SELECT sr FROM ShippingRate sr WHERE sr.country.id = ?1 AND sr.state.id = ?2 AND sr.city = ?3")
    public ShippingRate findByCountryStateAndCity(Long countryId, Long stateId, String city);

    @Query("UPDATE ShippingRate sr SET sr.codSupported = ?2 WHERE sr.id = ?1")
    @Modifying
    public void updateCODSupport(Long id, boolean enabled);

    @Query("SELECT sr FROM ShippingRate sr WHERE sr.country.name LIKE %?1% OR sr.state.name LIKE %?1%")
    public Page<ShippingRate> findAll(String keyword, Pageable pageable);

    @Query(value = "SELECT * FROM ShippingRate WHERE "
            + "MATCH(sr.country.id, sr.state.id) AGAINST (?1)",
            nativeQuery = true)
    public Page<ShippingRate> search(String keyword, Pageable pageable);

    public Long countById(Long id);
}
