package in.samar.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.samar.entity.CourseEntity;


public interface CourseRepo extends JpaRepository<CourseEntity, Integer>{

}
