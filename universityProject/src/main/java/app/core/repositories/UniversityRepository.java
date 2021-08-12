package app.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import app.core.entities.University;

public interface UniversityRepository extends JpaRepository<University, Integer> {

	boolean existsByNameAndEmail(String name, String email);
	
	University findByEmailAndPassword(String email, String password);

}