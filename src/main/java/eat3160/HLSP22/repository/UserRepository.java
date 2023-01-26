package eat3160.HLSP22.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import eat3160.HLSP22.model.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer>{
	
	@Query("SELECT u FROM UserEntity u WHERE u.email LIKE ?1 AND u.password LIKE ?2")
	UserEntity findByEmailAndPassword(String email, String password);
	
	@Query("SELECT u FROM UserEntity u WHERE u.email LIKE ?1")
	UserEntity doesEmailExistByEmail(String email);
	
	@Query("SELECT u FROM UserEntity u WHERE u.email LIKE ?1 AND NOT u.userID = ?2")
	UserEntity doesEmailExistByEmailAndId(String email, int id);
	
	@Query("SELECT u.userID FROM UserEntity u WHERE u.email LIKE ?1")
	Integer getUserIdByEmail(String email);
}
