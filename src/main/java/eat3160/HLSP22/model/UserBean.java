package eat3160.HLSP22.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is a java bean that represents a user and their accompanying details from the Users table. 
 * It follows the simple bean design pattern i.e., only setters, getters, and non-parameterized constructor.
 * 
 * @author alf-s
 *
 */
public class UserBean implements Serializable {
	
	private String firstName;
	private String surname;
	private String dateOfBirth;
	private String email;
	private String password;
	private String exerciseExperience;
	private String disability;
	
	
	public UserBean() {
		this.firstName = null; 
		this.surname = null;
		this.dateOfBirth = null;
		this.email = null;
		this.password = null;
		this.exerciseExperience = null;
		this.disability = null;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFirstName() {
		return firstName;
	}

	public String getSurname() {
		return surname;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}


	public String getExerciseExperience() {
		return exerciseExperience;
	}


	public void setExerciseExperience(String exerciseExperience) {
		this.exerciseExperience = exerciseExperience;
	}


	public String getDisability() {
		return disability;
	}


	public void setDisability(String disability) {
		this.disability = disability;
	}
	
}


