package com.it.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.it.repository.entity.SsnEntity;

@Repository		// to translate DB exception into Spring
public interface SsnRepository extends JpaRepository<SsnEntity, Integer>
//JpaRepository uses SsnEntity entity class, and data type of primary key/ ID>
{
	public Optional<SsnEntity> findBySsn(String ssn);
	// optional is a immutable class acting as a container
	// which may or may not contain non-null value (something or nothing)
	// it returns data (user-name & password) if it exists, else returns "optional"
}
