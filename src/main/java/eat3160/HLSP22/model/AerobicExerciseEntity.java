package eat3160.HLSP22.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="aerobic_exercises")
public class AerobicExerciseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="userid", nullable=false, unique=false)
	private Integer userID;
	
	@Column(name="exercise_name", nullable=false, unique=false, length=45)
	private String exerciseName;

	@Column(name="location", nullable=false, unique=false, length=45)
	private String location;
	
	@Column(name="steps", nullable=true, unique=false)
	private Integer steps;
	
	@Column(name="date_of_exercise", nullable=false, unique=false)
	private String dateOfExercise;
	
	@Column(name="favourite", nullable=false, unique=false)
	private Boolean favourite;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getExerciseName() {
		return exerciseName;
	}

	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getSteps() {
		return steps;
	}

	public void setSteps(Integer steps) {
		this.steps = steps;
	}

	public String getDateOfExercise() {
		return dateOfExercise;
	}

	public void setDateOfExercise(String dateOfExercise) {
		this.dateOfExercise = dateOfExercise;
	}

	public Boolean getFavourite() {
		return favourite;
	}

	public void setFavourite(Boolean favourite) {
		this.favourite = favourite;
	}
	
}
