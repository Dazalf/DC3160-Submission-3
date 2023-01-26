package eat3160.HLSP22.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eat3160.HLSP22.model.AerobicExerciseEntity;
import eat3160.HLSP22.repository.AerobicExerciseRepository;

@Service
public class AerobicExerciseService {
	
	@Autowired
	private AerobicExerciseRepository AerobicExerciseRepository;
	
	/**
	 * Get all exercises that match the specified user id and date.
	 * 
	 * @param userID
	 * @param date
	 * @return
	 */
	public ArrayList<AerobicExerciseEntity> findAllByUserIdAndDate(int userID, String date) {
		
		Iterable<AerobicExerciseEntity> results = AerobicExerciseRepository.findAllByUserIdAndDate(userID, date);
		ArrayList<AerobicExerciseEntity> ae = new ArrayList<>();
		results.forEach(result -> ae.add(result));
		
		return ae;
	}
	
	/**
	 * Get a single exercise record that matches the specified record id and user id.
	 * 
	 * @param id
	 * @param userID
	 * @return
	 */
	public AerobicExerciseEntity findByUserIdAndId(int id, int userID) {
		return AerobicExerciseRepository.findByUserIdAndId(id, userID);
	}
	
	/**
	 * Save the entity in the parameter into the database.
	 * 
	 * 
	 * If a record already exists for the specified entity, update it.
	 * If a record doesn't exist for the specified entity, create one.
	 * 
	 * @param goals entity
	 */
	public void save(AerobicExerciseEntity AerobicExercise) {
		AerobicExerciseRepository.save(AerobicExercise);
	}
	
	/** 
	 * Delete a record specified by its record id.
	 * 
	 * @param id
	 */
	public void delete(int id) {
		AerobicExerciseRepository.deleteById(id);
	}
}
