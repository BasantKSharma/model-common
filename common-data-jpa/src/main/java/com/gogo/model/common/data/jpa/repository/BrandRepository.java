package com.gogo.model.common.data.jpa.repository;

import com.gogo.model.common.data.jpa.pagination.SearchRepository;
import com.gogo.model.common.data.jpa.entity.product.Brand;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Search repository for Brand entity
 * **/
@Repository
@ConditionalOnProperty(name = "spring.application.name", havingValue = "shopping-service-product-catalog")
public interface BrandRepository extends SearchRepository<Brand, Long> {

    public Long countById(Long id);

    public Brand findByName(String name);

    @Query("SELECT b FROM Brand b WHERE b.name LIKE %?1%")
    public Page<Brand> findAll(String keyword, Pageable pageable);
}
