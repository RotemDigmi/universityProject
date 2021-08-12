package app.core.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import app.core.entities.Grade;
import app.core.repositories.GradeRepository;
import app.core.repositories.StudentRepository;

@Service
@Scope("prototype")
@Transactional
public class StudentService extends ClientService{
	
	public StudentService(StudentRepository studentRepo, GradeRepository gradeRepository) {
		super();
		this.studentRepo = studentRepo;
	}

	@Override
	public boolean login(String email, String password) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void addGrade(Grade grade) {
		gradeRepo.save(grade);
	}
	
	public List<Grade> getAllGrade(){
		return gradeRepo.findAll();
	}
	
	
}
