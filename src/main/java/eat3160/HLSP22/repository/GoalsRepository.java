package eat3160.HLSP22.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import eat3160.HLSP22.model.GoalsEntity;

public interface GoalsRepository extends CrudRepository<GoalsEntity, Integer>{
	
	@Query("SELECT g FROM GoalsEntity g WHERE g.userID LIKE ?1")
	GoalsEntity findByUserId(int userID);
	
}
