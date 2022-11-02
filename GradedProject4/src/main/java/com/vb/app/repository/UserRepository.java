package com.vb.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vb.app.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	@Query("SELECT user FROM User user WHERE user.username = ?1")
	public User getUserByUsername(String username);
}
