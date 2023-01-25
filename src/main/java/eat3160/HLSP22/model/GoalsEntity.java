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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="user_id", nullable=false, unique=true)
	private Integer user_id;
	
	@Column(name="weekly_strength_exercise", nullable=true, unique=false)
	private Integer weeklyStrengthExercise;
	
	@Column(name="weekly_aerobic_exercise", nullable=true, unique=false)
	private Integer weeklyAerobicExercise;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
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
