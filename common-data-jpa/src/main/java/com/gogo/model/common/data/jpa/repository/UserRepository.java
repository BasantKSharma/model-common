package com.gogo.model.common.data.jpa.repository;

import com.gogo.model.common.data.jpa.entity.user.User;
import com.gogo.model.common.data.jpa.pagination.SearchRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnProperty(name = "spring.application.name", havingValue = "shopping-service-user")
public interface UserRepository extends SearchRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE u.email = :email")
	public User findByEmail(@Param("email") String email);

	@Query("SELECT u FROM User u WHERE u.firstName = :firstName")
	public User getUserByFirstName(@Param("firstName") String firstName);

	public Long countById(Long id);
	
	@Query("SELECT u FROM User u WHERE CONCAT(u.id, ' ', u.email, ' ', u.firstName, ' ',"
			+ " u.lastName) LIKE %?1%")
	public Page<User> findAll(String keyword, Pageable pageable);
	
	@Query("UPDATE User u SET u.enabled = ?2 WHERE u.id = ?1")
	@Modifying
	public void updateEnabledStatus(Long id, boolean enabled);
}
