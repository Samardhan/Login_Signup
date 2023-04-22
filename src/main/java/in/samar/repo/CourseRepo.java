package in.samar.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.samar.entity.UsersDetailsEntity;


public interface CourseRepo extends JpaRepository<UsersDetailsEntity, Integer>{

}
