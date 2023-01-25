package eat3160.HLSP22.model;

import java.util.ArrayList;
import java.util.HashMap;

public class GoalsQuizBean {
	
	//Question one
	private String questionOne; 
	int answerOne;
	
	//Question two
	private String questionTwo;
	ArrayList<String> answersTwo;
	String chosenQuestionTwoAnswer;
	
	public GoalsQuizBean() {
		//Question one
		questionOne = "How many exercises do you ideally want to complete in a week?";
		answerOne = -1;
		
		//Question two
		questionTwo = "What type of exercises do you want to focus on?";
		answersTwo = new ArrayList<>();
		answersTwo.add("Aerobic exercises only");
		answersTwo.add("Strength exercises only");
		answersTwo.add("Mix of both aerobic exercises and strength exercises");
		answersTwo.add("Focus on aerobic exercise, with some strength exercises.");
		answersTwo.add("Focus on strength exercise, with some aerobic exercises.");
		chosenQuestionTwoAnswer = null;
	}

	public String getQuestionOne() {
		return questionOne;
	}

	public void setQuestionOne(String questionOne) {
		this.questionOne = questionOne;
	}

	public int getAnswerOne() {
		return answerOne;
	}

	public void setAnswerOne(int answerOne) {
		this.answerOne = answerOne;
	}

	public String getQuestionTwo() {
		return questionTwo;
	}

	public void setQuestionTwo(String questionTwo) {
		this.questionTwo = questionTwo;
	}

	public ArrayList<String> getAnswersTwo() {
		return answersTwo;
	}

	public void setAnswersTwo(ArrayList<String> answersTwo) {
		this.answersTwo = answersTwo;
	}

	public String getChosenQuestionTwoAnswer() {
		return chosenQuestionTwoAnswer;
	}

	public void setChosenQuestionTwoAnswer(String chosenQuestionTwoAnswer) {
		this.chosenQuestionTwoAnswer = chosenQuestionTwoAnswer;
	}
	
	/**
	 * This method processes the answers to the quiz questions and provides, based on the answers, a HashMap of
	 * the suggested aerobic and strength goals the user should use. 
	 * 
	 * This method includes rounding to ensure the goals are only whole numbers, so the returned exercise days may not match
	 * the inputted number of days for question 1. 
	 * 
	 * @return HashMap<String, Integer> containing the goals.
	 */
	public HashMap<String, Integer> getResponse() {
		HashMap<String, Integer> goals = new HashMap<>();
		
		if(answerOne != -1 && chosenQuestionTwoAnswer != null) {
			
			if(chosenQuestionTwoAnswer.contentEquals("Aerobic exercises only")) {
				
				//If aerobic exercise only, input all exercise days as aerobic exercise. 
				
				//Input goal for the aerobic goal based on user input
				goals.put("Aerobic goal", answerOne);
				//Input goal for the strength goal based on user input
				goals.put("Strength goal", 0);
				
			}
			else if(chosenQuestionTwoAnswer.contentEquals("Strength exercises only")){
				
				//If strength exercise only, input all exercise days as strength exercise. 
				
				//Input goal for the aerobic goal based on user input
				goals.put("Aerobic goal", 0);
				//Input goal for the strength goal based on user input
				goals.put("Strength goal", answerOne);
				
			}
			else if(chosenQuestionTwoAnswer.contentEquals("Mix of both aerobic exercises and strength exercises")){
				
				//If a mix of both, split the provided exercise days 50/50, and then round them to the lowest whole number. 
				
				float pha = (float) (answerOne * 0.50);
				int aerobicDays = Math.round(pha);
				
				float phs = (float) (answerOne * 0.50);
				int strengthDays = Math.round(phs);
				
				//Input goal for the aerobic goal based on user input
				goals.put("Aerobic goal", aerobicDays);
				//Input goal for the strength goal based on user input
				goals.put("Strength goal", strengthDays);
				
			}
			else if(chosenQuestionTwoAnswer.contentEquals("Focus on aerobic exercise, with some strength exercises.")){
				
				//If a focus on aerobic, make it 75% of the inputted days. The rest (25%) will be strength exercises.
				
				float pha = (float) (answerOne * 0.75);
				int aerobicDays = Math.round(pha);
				
				float phs = (float) (answerOne * 0.25);
				int strengthDays = Math.round(phs);
				
				//Input goal for the aerobic goal based on user input
				goals.put("Aerobic goal", aerobicDays);
				//Input goal for the strength goal based on user input
				goals.put("Strength goal", strengthDays);
				
			}		
			else if(chosenQuestionTwoAnswer.contentEquals("Focus on strength exercise, with some aerobic exercises.")){
				
				//If a focus on aerobic, make it 75% of the inputted days. The rest (25%) will be strength exercises.
				
				float pha = (float) (answerOne * 0.25);
				int aerobicDays = Math.round(pha);
				
				float phs = (float) (answerOne * 0.75);
				int strengthDays = Math.round(phs);
				
				//Input goal for the aerobic goal based on user input
				goals.put("Aerobic goal", aerobicDays);
				//Input goal for the strength goal based on user input
				goals.put("Strength goal", strengthDays);
				
			}		
		}else {
			return null;
		}
		
		return goals;
	}
	
}
