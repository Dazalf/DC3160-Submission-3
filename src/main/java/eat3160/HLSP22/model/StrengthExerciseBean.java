package eat3160.HLSP22.model;

public class StrengthExerciseBean {
	
	private int id;
	private String exerciseName;
	private String location;
	private String muscleGroup;
	private int sets;
	private int reps;
	private String dateOfExercise;
	private boolean favourite;
	
	public StrengthExerciseBean() {
		id = -1;
		exerciseName = null;
		location = null;
		muscleGroup = null;
		sets = -1;
		reps = -1;
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

	public String getMuscleGroup() {
		return muscleGroup;
	}

	public void setMuscleGroup(String muscleGroup) {
		this.muscleGroup = muscleGroup;
	}

	public int getSets() {
		return sets;
	}

	public void setSets(int sets) {
		this.sets = sets;
	}

	public int getReps() {
		return reps;
	}

	public void setReps(int reps) {
		this.reps = reps;
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
