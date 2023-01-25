package eat3160.HLSP22.model;

public class GoalsBean {
	
	private int weeklyStrengthGoal;
	private int weeklyAerobicGoal;
	
	public GoalsBean() {
		weeklyStrengthGoal = -1;
		weeklyAerobicGoal = -1;
	}

	public int getWeeklyStrengthGoal() {
		return weeklyStrengthGoal;
	}

	public void setWeeklyStrengthGoal(int weeklyStrengthGoal) {
		this.weeklyStrengthGoal = weeklyStrengthGoal;
	}

	public int getWeeklyAerobicGoal() {
		return weeklyAerobicGoal;
	}

	public void setWeeklyAerobicGoal(int weeklyAerobicGoal) {
		this.weeklyAerobicGoal = weeklyAerobicGoal;
	}
}
