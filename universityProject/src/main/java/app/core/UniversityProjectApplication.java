package app.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import app.core.entities.Address;
import app.core.entities.University;
import app.core.repositories.UniversityRepository;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableScheduling
@EnableSwagger2
public class UniversityProjectApplication {

	public static void main(String[] args) {
		
		ApplicationContext ctx =  SpringApplication.run(UniversityProjectApplication.class, args);
		
//		UniversityRepository universityRepository = ctx.getBean(UniversityRepository.class);
//		
//		universityRepository.save(new University("HIT", "hit@mail.com", "1234", new Address("Holon", "Israel")));
	}

}
