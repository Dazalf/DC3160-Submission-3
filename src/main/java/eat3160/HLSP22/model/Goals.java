package eat3160.HLSP22.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Goals {

	private Connection connection = null;
	String connectionURL = "jdbc:mysql://localhost:3306/hlsp";
	
	public Goals() {
		init();
	}
	
	/**
	 * Get the goals of a user based on a specific user id, store them within a java bean GoalsBean, and return this bean. 
	 * @param userID
	 * @return a goals object. Returns null if no row found for specified user. 
	 * 
	 * MOVED TO GOALSSERVICE
	 */
	 
	public GoalsBean getUserGoals(int userID) {
		
		GoalsBean gb = null;
		
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM goals WHERE userid LIKE ?");
			pstmt.setInt(1, userID);
			System.out.println(pstmt);
			ResultSet rs1 = pstmt.executeQuery();
			
			if (rs1.next()) { 
				gb = new GoalsBean();
				gb.setWeeklyStrengthGoal(rs1.getInt(3));
				gb.setWeeklyAerobicGoal(rs1.getInt(4));
				
			}
			
		} catch (SQLException e) {
			System.out.println("Exception is ;" + e + ": message is " + e.getMessage());
		}
		
		return gb;
	}
	
	/**
	 * Update the goals of a user based on a specific user id. 
	 * @param userID
	 * @param strengthGoal
	 * @param aerobicGoal
	 * @return
	 * 
	 * MOVED TO GOALSSERVICE
	 */
	 
	private void update(int userID, GoalsBean goals) {
				
		try {
			PreparedStatement pstmt = connection.prepareStatement("UPDATE goals SET weekly_strength_exercise = ?, weekly_aerobic_exercise = ? WHERE (userid = ?)");
			pstmt.setInt(1, goals.getWeeklyStrengthGoal());
			pstmt.setInt(2, goals.getWeeklyAerobicGoal());
			pstmt.setInt(3, userID);
			System.out.println(pstmt);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Exception is ;" + e + ": message is " + e.getMessage());
		}
	}
	
	/**
	 * Create a new row in the Goals table for a specified user. 
	 * @param userID
	 * @param strengthGoal
	 * @param aerobicGoal
	 * @return
	 */
	 
	private void create(int userID, GoalsBean goals) {
		
		try {
			PreparedStatement pstmt = connection.prepareStatement("INSERT INTO goals (userid, weekly_strength_exercise, weekly_aerobic_exercise) VALUES (?, ?, ?);");
			pstmt.setInt(1, userID);
			pstmt.setInt(2, goals.getWeeklyStrengthGoal());
			pstmt.setInt(3, goals.getWeeklyAerobicGoal());	
			System.out.println(pstmt);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Exception is ;" + e + ": message is " + e.getMessage());
		}
	}
	
	/**
	 * This method will either update, or add, the goals of a user. 
	 * If the user already had a goals row in the table, then simply update the row. 
	 * If the user doesn't already a goals row in the table, create a new row for them with their goal values. 
	 * @param userID
	 * @param strengthGoal
	 * @param aerobicGoal 
	 */
	public void updateUserGoals(int userID, GoalsBean goals) {

		/**
		 * Test if the get users goals method returns a bean. If null returned, call the create method. If bean returned, call the update method. 
		 */
		if(getUserGoals(userID) == null) {
			create(userID, goals);
		}else {
			update(userID, goals);
		}

	}
	
	
	/**
	 * Method used to intialise the connection to the database. 
	 */
	private void init() {
		if (connection == null) {
			try {

				// Load the database driver
				//Class.forName("com.mysql.jdbc.Driver");
				Class.forName("com.mysql.cj.jdbc.Driver");

				// Get a Connection to the database
				connection = DriverManager.getConnection(connectionURL, "root", "M87jmP%csd9RmuYk18");
		
				
			} catch (Exception e) {
				System.out.println("Exception is ;" + e + ": message is " + e.getMessage());
			}
		}
	}
	
	/**
	 * Method used to destroy the connection to the database. 
	 */
	public void destroy() {
		
		try {
			connection.close();
			connection = null;
		} catch (Exception e) {
			System.out.println("Exception is ;" + e + ": message is " + e.getMessage());
		}
		
	}
	
}
