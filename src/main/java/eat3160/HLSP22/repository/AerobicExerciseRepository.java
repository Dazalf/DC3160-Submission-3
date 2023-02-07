package eat3160.HLSP22.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import eat3160.HLSP22.model.AerobicExerciseEntity;
import eat3160.HLSP22.model.StrengthExerciseEntity;

public interface AerobicExerciseRepository extends CrudRepository<AerobicExerciseEntity, Integer>{
	
	@Query("SELECT a FROM AerobicExerciseEntity a WHERE a.userID LIKE ?1 AND a.dateOfExercise LIKE ?2")
	Iterable<AerobicExerciseEntity> findAllByUserIdAndDate(int userID, String date);
	
	@Query("SELECT a FROM AerobicExerciseEntity a WHERE a.id LIKE ?1 AND a.userID LIKE ?2")
	AerobicExerciseEntity findByUserIdAndId(int id, int userID);
	
	@Query("SELECT a FROM AerobicExerciseEntity a WHERE a.userID LIKE ?1 AND a.favourite = true ORDER BY a.id DESC")
	Iterable<AerobicExerciseEntity> findFavouritesByUserId(int userID);
	
	@Query(value="SELECT * FROM aerobic_exercises WHERE userid LIKE ?1 ORDER BY date_of_exercise DESC LIMIT 5", nativeQuery=true)
	Iterable<AerobicExerciseEntity> findRecentExercisesByUserId(int userID);
	
}
