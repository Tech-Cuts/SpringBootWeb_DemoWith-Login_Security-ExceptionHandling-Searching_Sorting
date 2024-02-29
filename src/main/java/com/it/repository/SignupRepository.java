package com.it.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.it.repository.entity.Signup;

public interface SignupRepository extends JpaRepository<Signup, String>
		// JpaRepository uses Sign-up entity class, and data type of primary key/ ID>
{
	public Optional<Signup> findByUsernameAndPassword(String username, String password);
	// optional is a immutable class acting as a container
	// which may or may not contain non-null value (something or nothing)
	// it returns data (user-name & password) if it exists, else returns "optional"
	
//	List<Signup> findByUsername(String username);
	
	@Query("Select c from Signup c where c.username LIKE %?1% or c.email LIKE %?2%")
	List<Signup> findByUsernameOrEmailLike(String username, String email);
	
	Optional<Signup> findByEmail(String email);
}
