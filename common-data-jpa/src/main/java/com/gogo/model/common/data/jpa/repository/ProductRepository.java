package com.gogo.model.common.data.jpa.repository;

import com.gogo.model.common.data.jpa.entity.product.Product;
import com.gogo.model.common.data.jpa.entity.product.ProductImage;
import com.gogo.model.common.data.jpa.pagination.SearchRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Search repository for Product entity
 * **/
@Repository
//@ConditionalOnProperty(name = "spring.application.name", havingValue = "shopping-service-product-catalog")
public interface ProductRepository extends SearchRepository<Product, Long> {

    List<Product> findByIdIn(List<Long> productIds);

    @Query("SELECT c FROM Product c WHERE c.name = :name")
    public Product findByName(@Param("name") String name);

    @Query("UPDATE Product p SET p.enabled = ?2 WHERE p.id = ?1")
    @Modifying
    public void updateEnabledStatus(Long id, boolean enabled);

    public Long countById(Long id);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1% "
            + "OR p.shortDescription LIKE %?1% "
            + "OR p.fullDescription LIKE %?1% "
            + "OR p.brand.name LIKE %?1% "
            + "OR p.category.name LIKE %?1%")
    public Page<Product> findAll(String keyword, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.category.id = ?1 "
            + "OR p.category.allParentIDs LIKE %?2%")
    public Page<Product> findAllInCategory(Long categoryId, String categoryIdMatch,
                                           Pageable pageable);

    @Query("SELECT p FROM Product p WHERE (p.category.id = ?1 "
            + "OR p.category.allParentIDs LIKE %?2%) AND "
            + "(p.name LIKE %?3% "
            + "OR p.shortDescription LIKE %?3% "
            + "OR p.fullDescription LIKE %?3% "
            + "OR p.brand.name LIKE %?3% "
            + "OR p.category.name LIKE %?3%)")
    public Page<Product> searchInCategory(Long categoryId, String categoryIdMatch,
                                          String keyword, Pageable pageable);

    @Query("SELECT p FROM ProductImage p WHERE p.product.id = :productId")
    @Modifying
    public List<ProductImage> findProductImageByProductId(@Param("productId") Long productId);

    @Query("SELECT p FROM Product p WHERE p.enabled = true "
            + "AND (p.category.id = ?1 OR p.category.allParentIDs LIKE %?2%)"
            + " ORDER BY p.name ASC")
    public Page<Product> listByCategory(Long categoryId, String categoryIDMatch, Pageable pageable);

    @Query("DELETE FROM ProductImage WHERE id =:id")
    @Modifying
    @Transactional
    void deleteProductImageById(@Param("id") Long id);

    @Query(value = "SELECT * FROM Product WHERE enabled = true AND "
            + "MATCH(name, short_description, full_description) AGAINST (?1)",
            nativeQuery = true)
    public Page<Product> search(String keyword, Pageable pageable);
}
