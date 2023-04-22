package in.samar.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.samar.entity.StudentEnqEntity;
import in.samar.entity.UsersDetailsEntity;
import in.samar.repo.StudentEnqRepo;
import in.samar.repo.UserDetailsRepo;

@Component
public class DataLoader implements ApplicationRunner {

/*
	@Autowired
	private StudentEnqRepo srepo;
*/
	@Override
	public void run(ApplicationArguments args) throws Exception {
/*
		StudentEnqEntity see = new StudentEnqEntity();
		see.setStudentName("Pramod");
		see.setStudentPhone(91001l);
		see.setClassMode("offline");
		see.setCourseName("JAVA");

		srepo.save(see);
*/
	}

}
