package eat3160.HLSP22.model;

public class AerobicExerciseBean {
	
	private int id;
	private String exerciseName;
	private String location;
	private int steps;
	private String dateOfExercise;
	private boolean favourite;
	
	public AerobicExerciseBean() {
		id = 0;
		exerciseName = null;
		location = null;
		steps = 0;
		dateOfExercise = null;
		favourite = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public String getDateOfExercise() {
		return dateOfExercise;
	}

	public void setDateOfExercise(String dateOfExercise) {
		this.dateOfExercise = dateOfExercise;
	}

	public boolean isFavourite() {
		return favourite;
	}

	public void setFavourite(boolean favourite) {
		this.favourite = favourite;
	}
	
}
