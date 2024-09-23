package com.gogo.model.common.data.jpa.repository;

import com.gogo.model.common.data.jpa.entity.product.CartItem;
import com.gogo.model.common.data.jpa.entity.product.Product;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for CartItem entity
 **/
@Repository
@ConditionalOnProperty(name = "spring.application.name", havingValue = "shopping-service-product-catalog")
public interface CartItemRepository extends CrudRepository<CartItem, Long> {

    public List<CartItem> findByCustomerId(Long customerId);

    public CartItem findByCustomerIdAndProduct(Long customerId, Product product);

    @Modifying
    @Query("UPDATE CartItem c SET c.quantity = ?3 WHERE c.customerId = ?1 AND c.product.id = ?2")
    public void updateQuantity(Long customerId, Long productId, Integer quantity);

    @Modifying
    @Query("DELETE FROM CartItem c WHERE c.customerId = ?1 AND c.product.id = ?2")
    public void deleteByCustomerAndProduct(Long customerId, Long productId);

    @Modifying
    @Query("DELETE FROM CartItem c WHERE c.customerId = ?1 AND c.product.id IN ?2")
    public void deleteByCustomerAndProductIds(Long customerId, List<Long> productIds);

    @Modifying
    @Query("DELETE CartItem c WHERE c.customerId = ?1")
    public void deleteCartItemsByCustomerId(Long customerId);
}

