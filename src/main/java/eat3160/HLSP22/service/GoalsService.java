package eat3160.HLSP22.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import eat3160.HLSP22.model.GoalsEntity;
import eat3160.HLSP22.repository.GoalsRepository;

@Service
public class GoalsService {
	
	@Autowired
	private GoalsRepository goalsRepository;
	
	/**
	 * Get the Goals record of a specific user by ID.
	 * 
	 *  NOT TESTED IF SUCCESSFUL
	 * 
	 * @param userID
	 * @return GoalsEntity if record found, null if nothing found
	 */
	public Optional<GoalsEntity> findById(int userID) {
		return goalsRepository.findById(userID);
	}
	
	/**
	 * Save the entity in the parameter into the database.
	 * 
	 * It looks if the entity id doesn't already exist in the database, add it, 
	 * if the id does already exist, update that existin record.
	 * 
	 * NOT TESTED IF SUCCESSFUL
	 * 
	 * @param userID
	 * @param goals
	 */
	public void save(int userID, GoalsEntity goals) {
		goalsRepository.save(goals);
	}
	
}
