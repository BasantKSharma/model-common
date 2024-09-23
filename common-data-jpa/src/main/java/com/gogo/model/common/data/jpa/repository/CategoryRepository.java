package com.gogo.model.common.data.jpa.repository;

import com.gogo.model.common.data.jpa.entity.product.Category;
import com.gogo.model.common.data.jpa.pagination.SearchRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Search repository for Category entity
 * **/
@Repository
@ConditionalOnProperty(name = "spring.application.name", havingValue = "shopping-service-product-catalog")
public interface CategoryRepository extends SearchRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.name = :name")
    public Category findCategoryByName(@Param("name") String name);

    @Query("SELECT c FROM Category c WHERE c.name = :name")
    public List<Category> findParentCategories();

    @Query("SELECT c FROM Category c WHERE c.parent.id is NULL")
    public List<Category> findRootCategories();

    @Query("SELECT c FROM Category c WHERE CONCAT(c.id, ' ', c.name, ' ') LIKE %?1%")
    public Page<Category> findAll(String keyword, Pageable pageable);

    @Query("SELECT c FROM Category c WHERE c.enabled = true AND c.name = ?1")
    public Category findByNameEnabled(String name);
}
