package eat3160.HLSP22.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eat3160.HLSP22.model.StrengthExerciseEntity;
import eat3160.HLSP22.repository.StrengthExerciseRepository;

@Service
public class StrengthExerciseService {
	
	@Autowired
	private StrengthExerciseRepository strengthExerciseRepository;
	
	/**
	 * Not tested yet
	 * 
	 * @param userID
	 * @param date
	 * @return
	 */
	public ArrayList<StrengthExerciseEntity> findAllByUserIdAndDate(int userID, String date) {
		
		Iterable<StrengthExerciseEntity> results = strengthExerciseRepository.findAllByUserIdAndDate(userID, date);
		ArrayList<StrengthExerciseEntity> se = new ArrayList<>();
		results.forEach(result -> se.add(result));
		
		return se;
	}
	
	/**
	 * Not tested yet
	 * 
	 * @param id
	 * @param userID
	 * @return
	 */
	public StrengthExerciseEntity findByUserIdAndId(int id, int userID) {
		return strengthExerciseRepository.findByUserIdAndId(id, userID);
	}
	
	/**
	 * Save the entity in the parameter into the database.
	 * 
	 * Not tested yet
	 * 
	 * If a record already exists for the specified entity, update it.
	 * If a record doesn't exist for the specified entity, create one.
	 * 
	 * @param goals entity
	 */
	public void save(StrengthExerciseEntity strengthExercise) {
		strengthExerciseRepository.save(strengthExercise);
	}
	
	/**
	 * Not tested yet. 
	 * 
	 * @param id
	 */
	public void delete(int id) {
		strengthExerciseRepository.deleteById(id);
	}
}
