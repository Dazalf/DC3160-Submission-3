package eat3160.HLSP22.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="goals")
public class GoalsEntity {
	
	
	@Id
	@Column(name="userid", nullable=false, unique=true)
	private Integer userID;
	
	@Column(name="weekly_strength_exercise", nullable=true, unique=false)
	private Integer weeklyStrengthExercise;
	
	@Column(name="weekly_aerobic_exercise", nullable=true, unique=false)
	private Integer weeklyAerobicExercise;

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer user_id) {
		this.userID = user_id;
	}

	public Integer getWeeklyStrengthExercise() {
		return weeklyStrengthExercise;
	}

	public void setWeeklyStrengthExercise(Integer weeklyStrengthExercise) {
		this.weeklyStrengthExercise = weeklyStrengthExercise;
	}

	public Integer getWeeklyAerobicExercise() {
		return weeklyAerobicExercise;
	}

	public void setWeeklyAerobicExercise(Integer weeklyAerobicExercise) {
		this.weeklyAerobicExercise = weeklyAerobicExercise;
	}

}
