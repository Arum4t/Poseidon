package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * Query to fin user by their username.
 * This was needed for authentication
 *
 * @see com.nnk.springboot.services.CustomUserDetailsService
 *
 * @author Quentin
 *
 */
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    @Query("SELECT u FROM User u WHERE u.username = :username")
    User findByUsername(String username);

}
