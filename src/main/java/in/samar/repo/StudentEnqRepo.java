package in.samar.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.samar.entity.StudentEnqEntity;

public interface StudentEnqRepo extends JpaRepository<StudentEnqEntity, Integer>{

}
