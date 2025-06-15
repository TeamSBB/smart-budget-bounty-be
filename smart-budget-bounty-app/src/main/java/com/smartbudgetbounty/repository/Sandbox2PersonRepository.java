package com.smartbudgetbounty.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smartbudgetbounty.entity.Sandbox2_OneToOne_Person;

public interface Sandbox2PersonRepository extends JpaRepository<Sandbox2_OneToOne_Person, Long> {
	// Custom query using JPQL
	@Query("SELECT p FROM Sandbox2_OneToOne_Person p WHERE p.passport.passportNumber = :passportNumber")
	List<Sandbox2_OneToOne_Person> findByPassportNumber(String passportNumber);
}
