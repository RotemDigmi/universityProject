package app.core.services;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import app.core.entities.Student;
import app.core.entities.University;
import app.core.repositories.StudentRepository;
import app.core.repositories.UniversityRepository;

@Service
@Scope("prototype")
@Transactional
public class UniversityService extends ClientService{

	private Integer univercityId;
	
	public UniversityService(UniversityRepository universityRepo, StudentRepository studentRepo) {
		super();
		this.universityRepo = universityRepo;
		this.studentRepo = studentRepo;
		
	}
		@Override
		public boolean login(String email, String password) throws Exception {
			University currUniversity = universityRepo.findByEmailAndPassword(email, password);
			if(currUniversity != null) {
				this.univercityId = currUniversity.getId();
				System.out.println("Welcom " + currUniversity.getName() + "!");
				return true;
			}else {
				throw new Exception("this login failed.");
			}
		}
	
	public void addUniversity(University university) throws Exception {
		if(!universityRepo.existsByNameAndEmail(university.getName(), university.getEmail())) {
			universityRepo.save(university);
		} else {
			throw new Exception("student with same parametes allredy exists.");
		}
	}
	
	public void addStudent(Student student) throws Exception {
		if(!studentRepo.existsByNameAndEmail(student.getName(), student.getEmail())) {
			studentRepo.save(student);
		} else {
			throw new Exception("student with same parametes allredy exists.");
		}
	}
	
	public void deleteStudent(Integer id) {
		studentRepo.deleteById(id);
	}
	
	public void updateStudent(Student student) throws Exception {
		Student s = studentRepo.getOne(student.getId());
		if(s != null) {
			s.setEmail(student.getEmail());
			s.setPassword(student.getPassword());
			studentRepo.flush();
		}else {
			throw new Exception("student not exists");
		}
	}
	
	public Student getStudent(Integer id) throws Exception {
		Student s = studentRepo.getOne(id);
		if(s != null) {
			return s;
		}else {
			throw new Exception("student not exists");
		}
	}

}
