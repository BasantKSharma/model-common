package com.gogo.model.common.data.jpa.repository;

import com.gogo.model.common.data.jpa.entity.order.Order;
import com.gogo.model.common.data.jpa.pagination.SearchRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Search repository for Order entity
 **/
@Repository
@ConditionalOnProperty(name = "spring.application.name", havingValue = "shopping-service-order")
public interface OrderRepository extends SearchRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.firstName LIKE %?1% OR"
            + " o.lastName LIKE %?1% OR o.phoneNumber LIKE %?1% OR"
            + " o.addressLine1 LIKE %?1% OR o.addressLine2 LIKE %?1% OR"
            + " o.postalCode LIKE %?1% OR o.city LIKE %?1% OR"
            + " o.state LIKE %?1% OR o.country LIKE %?1% OR"
            + " o.paymentMethod LIKE %?1% OR o.status LIKE %?1% OR"
            + " o.firstName LIKE %?1% OR"
            + " o.lastName LIKE %?1%")
    Page<Order> findAll(String keyword, Pageable pageable);
}
