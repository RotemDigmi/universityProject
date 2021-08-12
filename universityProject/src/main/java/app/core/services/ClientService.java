package app.core.services;

import app.core.repositories.AddressRepository;
import app.core.repositories.GradeRepository;
import app.core.repositories.StudentRepository;
import app.core.repositories.UniversityRepository;

public abstract class ClientService {

	protected UniversityRepository universityRepo;
	protected StudentRepository studentRepo;
	protected GradeRepository gradeRepo;
	protected AddressRepository addressRepo;
	
	
	public abstract boolean login(String email, String password) throws Exception;
	
}
