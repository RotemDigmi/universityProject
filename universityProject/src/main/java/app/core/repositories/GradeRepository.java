package app.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import app.core.entities.Grade;

public interface GradeRepository extends JpaRepository<Grade, Integer>{

}
