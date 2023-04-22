package in.samar.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.samar.entity.UsersDetailsEntity;


public interface UserDetailsRepo extends JpaRepository<UsersDetailsEntity, Integer> {
	
	public UsersDetailsEntity findByUserEmail(String userEmail);
	
	
	//public UsersDetailsEntity findByUserEmailandUserPassword(String userEmail,String userPassword);
	
}
