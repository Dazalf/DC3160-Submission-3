package eat3160.HLSP22.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import eat3160.HLSP22.model.StrengthExerciseEntity;

public interface StrengthExerciseRepository extends CrudRepository<StrengthExerciseEntity, Integer>{
	
	@Query("SELECT s FROM StrengthExerciseEntity s WHERE s.userID LIKE ?1 AND date_of_exercise LIKE ?2")
	Iterable<StrengthExerciseEntity> findAllByUserIdAndDate(int userID, String date);
	
	@Query("SELECT s FROM StrengthExerciseEntity s WHERE s.id LIKE ?1 AND s.userID LIKE ?2")
	StrengthExerciseEntity findByUserIdAndId(int id, int userID);

	@Query("SELECT s FROM StrengthExerciseEntity s WHERE s.userID LIKE ?1 AND s.favourite = true ORDER BY s.id DESC")
	Iterable<StrengthExerciseEntity> findFavouritesByUserId(int userID);
	
	@Query("SELECT s FROM StrengthExerciseEntity s WHERE s.userID LIKE ?1 ORDER BY s.dateOfExercise DESC")
	Iterable<StrengthExerciseEntity> findRecentExercisesByUserId(int userID);
	
}


