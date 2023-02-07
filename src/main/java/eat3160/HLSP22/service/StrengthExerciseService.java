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
	 * Get all exercises that match the specified user id and date.
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
	 * Get a single exercise record that matches the specified record id and user id.
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
	 * Delete a record specified by its record id.
	 * 
	 * @param id
	 */
	public void delete(int id) {
		strengthExerciseRepository.deleteById(id);
	}
	
	public ArrayList<StrengthExerciseEntity> findFavouritesByUserId(int userID){
		
		Iterable<StrengthExerciseEntity> results = strengthExerciseRepository.findFavouritesByUserId(userID);
		ArrayList<StrengthExerciseEntity> se = new ArrayList<>();
		results.forEach(result -> se.add(result));
		
		return se;
	}
	
	public ArrayList<StrengthExerciseEntity> findRecentExercisesByUserId(int userID){
		
		Iterable<StrengthExerciseEntity> results = strengthExerciseRepository.findRecentExercisesByUserId(userID);
		ArrayList<StrengthExerciseEntity> se = new ArrayList<>();
		results.forEach(result -> se.add(result));
		
		return se;
	}
}
