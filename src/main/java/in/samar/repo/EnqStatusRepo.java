package in.samar.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.samar.entity.EnqEntity;

public interface EnqStatusRepo extends JpaRepository<EnqEntity, Integer> {

}
