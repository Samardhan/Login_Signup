package in.samar.runner;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.samar.entity.EnqEntity;
import in.samar.repo.CourseRepo;
import in.samar.repo.EnqStatusRepo;
import in.samar.repo.StudentEnqRepo;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	private CourseRepo crepo;

	@Autowired
	private EnqStatusRepo erepo;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		/*
		  CourseEntity c1 = new CourseEntity(); c1.setCourseName("Java");
		 
		  CourseEntity c2 = new CourseEntity(); c2.setCourseName("python");
		  
		  CourseEntity c3 = new CourseEntity(); c3.setCourseName("Aws");
		  
		  CourseEntity c4 = new CourseEntity(); c4.setCourseName(".Net");
		  
		  List<CourseEntity> all = crepo.saveAll(Arrays.asList(c1, c2, c3, c4));
		  
		  System.out.println(all);
		 
		
		
		
		EnqEntity e1=new EnqEntity();
		e1.setEnqStatus("New");
		
		EnqEntity e2=new EnqEntity();
		e2.setEnqStatus("Enrolled");
		
		EnqEntity e3=new EnqEntity();
		e3.setEnqStatus("Lost");
		
		erepo.saveAll(Arrays.asList(e1,e2));
		*/
		
	}

}
