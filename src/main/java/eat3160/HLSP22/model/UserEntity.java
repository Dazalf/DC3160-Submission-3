package eat3160.HLSP22.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="users")
public class UserEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="userid")
	private Integer userID;
	
	@Column(name="first_name", nullable=false, unique=false, length=45)
	private String firstName;
	
	@Column(name="surname", nullable=false, unique=false, length=45)
	private String surname;
	
	@Column(name="date_of_birth", nullable=false, unique=false)
	private String dateOfBirth;
	
	@Column(name="email", nullable=false, unique=true, length=45)
	private String email;
	
	@Column(name="password", nullable=false, unique=false, length=45)
	private String password;
	
	@Column(name="exercise_experience", nullable=false, unique=false, length=45)
	private String exerciseExperience;
	
	@Column(name="disability", nullable=false, unique=false, length=45)
	private String disability;

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
