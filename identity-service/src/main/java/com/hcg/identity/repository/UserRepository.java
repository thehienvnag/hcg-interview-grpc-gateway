package com.hcg.identity.repository;

import com.hcg.identity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created on July 2023
 *
 * @author Ivan
 */
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);

	boolean existsByEmail(String email);

	User findById(int id);

}
