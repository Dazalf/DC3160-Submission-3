package eat3160.HLSP22.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eat3160.HLSP22.model.UserEntity;
import eat3160.HLSP22.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * NOT TESTED
	 * Checks if a user exists using an email and password. 
	 * This function is used to validate the input from the client-side and determine whether to log in a user. 
	 * @param email
	 * @param password
	 * @return false = invalid user, true = valid user. 
	 */
	public boolean validateUser(String email, String password) {
		
		if(userRepository.findByEmailAndPassword(email, password) != null) {
			return true;
		}else {
			return false;
		}
		
	}
	
	/**
	 * Get the User record of a specific user by their user ID.
	 * 
	 * @param userID
	 * @return UserEntity if record found, null if nothing found
	 */
	public Optional<UserEntity> findById(int userID) {
		return userRepository.findById(userID);
	}
	
	/**
	 * TO BE TESTED
	 * Check if specified email exists in database.
	 * @param email
	 * @return true if yes, false if not
	 */
	public boolean doesEmailExist(String email) {
		if(userRepository.doesEmailExistByEmail(email) != null) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * TO BE TESTED
	 * Check if specified email exists in database, ignoring a specified user by id.
	 * @param email
	 * @param userid
	 * @return true if yes, false if not
	 */
	public boolean doesEmailExist(String email, int userid) {
		if(userRepository.doesEmailExistByEmailAndId(email, userid) != null) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * CREATE -> WORKING
	 * UPDATE -> NOT TESTED
	 * 
	 * Save the entity in the parameter into the database.
	 * 
	 * If a record already exists for the specified entity, and the email address doesn't already exist (except for its own), update it.
	 * If a record doesn't exist for the specified entity, and the email addres doesn't already exist, create one.
	 * 
	 * @param User entity
	 */
	public boolean save(UserEntity user) {
		
		//If the user entity doesn't have an ID, it means this is an attempt at creating a new user. 
		//Else, this is an attempt at updating an existing user
		if(user.getUserID() == null) {
			if(doesEmailExist(user.getEmail()) == false) {
				userRepository.save(user);
				return true;
			}else {
				return false;
			}
		}else {
			
			if(doesEmailExist(user.getEmail(), user.getUserID()) == false) {
				userRepository.save(user);
				return true;
			}else {
				return false;
			}
			
		}
	}
	
	/**
	 * Return the User ID attribute of the record associated with the provided email address in the parameter.
	 * 
	 * @param email
	 * @return
	 */
	public int getUserIDByEmail(String email) {
		return userRepository.getUserIdByEmail(email);
	}

	/**
	 * Delete a record specified by its record id.
	 * 
	 * @param id
	 */
	public void delete(int id) {
		userRepository.deleteById(id);
	}
	
	
}
