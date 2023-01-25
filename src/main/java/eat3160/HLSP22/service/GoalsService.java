package eat3160.HLSP22.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eat3160.HLSP22.model.GoalsEntity;
import eat3160.HLSP22.repository.GoalsRepository;

@Service
public class GoalsService {
	
	@Autowired
	private GoalsRepository goalsRepository;
	
	/**
	 * Get the Goals record of a specific user by their user ID.
	 * 
	 * @param userID
	 * @return GoalsEntity if record found, null if nothing found
	 */
	public GoalsEntity findByUserId(int userID) {
		return goalsRepository.findByUserId(userID);
	}
	
	/**
	 * Save the entity in the parameter into the database.
	 * 
	 * If a record already exists for the specified entity, update it.
	 * If a record doesn't exist for the specified entity, create one.
	 * 
	 * @param goals entity
	 */
	public void save(GoalsEntity goals) {
		goalsRepository.save(goals);
	}
}
