package eat3160.HLSP22.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * A data access object for the 'Strength_exercises' table from the HLSP database, providing CRUD functionality. 
 * @author alf-s
 *
 */
public class StrengthExercises {
	
	private Connection connection = null;
	String connectionURL = "jdbc:mysql://localhost:3306/hlsp";

	public StrengthExercises() {
		init();
	}
	
	/**
	 * Get an arraylist of strength exercises for a specific user for a specific date. 
	 * @param userid
	 * @param date
	 * @return
	 */
	public ArrayList<StrengthExerciseBean> getExercises(int userid, String date) {
		ArrayList<StrengthExerciseBean> list = new ArrayList<>();
		
		try {
			PreparedStatement pstmt = connection.prepareStatement
					("SELECT * FROM strength_exercises WHERE date_of_exercise LIKE ? AND userid = ?;");
			
			pstmt.setString(1, date);	
			pstmt.setInt(2, userid);
			System.out.println(pstmt);
			ResultSet rs1 = pstmt.executeQuery();

			while (rs1.next()) { 
				StrengthExerciseBean se = new StrengthExerciseBean();
				se.setId(rs1.getInt(1));
				se.setExerciseName(rs1.getString(3));
				se.setLocation(rs1.getString(4));
				se.setMuscleGroup(rs1.getString(5));
				se.setSets(rs1.getInt(6));
				se.setReps(rs1.getInt(7));
				se.setDateOfExercise(rs1.getString(8));
				se.setFavourite(rs1.getBoolean(9));
				list.add(se);
			}
			
		} catch (SQLException e) {
			System.out.println("Exception is ;" + e + ": message is " + e.getMessage());
		}
		
		return list;
	}
	
	/**
	 * Get a single exercise record from the table. 
	 * @param id
	 * @param userid
	 * @return StrengthExerciseBean object
	 */
	public StrengthExerciseBean getExercise(int id, int userid) {
		StrengthExerciseBean se = null;
		
		try {
			PreparedStatement pstmt = connection.prepareStatement
					("SELECT * FROM strength_exercises WHERE id LIKE ? AND userid = ?;");
			
			pstmt.setInt(1, id);	
			pstmt.setInt(2, userid);
			System.out.println(pstmt);
			ResultSet rs1 = pstmt.executeQuery();

			if (rs1.next()) { 
				se = new StrengthExerciseBean();
				se.setId(rs1.getInt(1));
				se.setExerciseName(rs1.getString(3));
				se.setLocation(rs1.getString(4));
				se.setMuscleGroup(rs1.getString(5));
				se.setSets(rs1.getInt(6));
				se.setReps(rs1.getInt(7));
				se.setDateOfExercise(rs1.getString(8));
				se.setFavourite(rs1.getBoolean(9));
			}
			
		} catch (SQLException e) {
			System.out.println("Exception is ;" + e + ": message is " + e.getMessage());
		}
		
		return se;
	}
	
	/**
	 * Update the values of a specific row in the table. 
	 * @param id - the id of the row. 
	 */
	public void update(int id, String exerciseName, String location, String muscleGroup, int sets, int reps, String date, boolean favourite) {
		
		try {
			PreparedStatement pstmt = connection.prepareStatement
					("UPDATE hlsp.strength_exercises SET exercise_name = ?, location = ?, muscle_group = ?, "
							+ "sets = ?, reps = ?, date_of_exercise = ?, favourite = ? WHERE (id = ?);");
			
			pstmt.setString(1, exerciseName);
			pstmt.setString(2, location);	
			pstmt.setString(3, muscleGroup);
			pstmt.setInt(4, sets);
			pstmt.setInt(5, reps);
			pstmt.setString(6, date);
			pstmt.setBoolean(7, favourite);
			pstmt.setInt(8, id);
			System.out.println(pstmt);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Exception is ;" + e + ": message is " + e.getMessage());
		}
		
	}
	
	/**
	 * Create a new row within the table. 
	 * @param userID
	 * @param exerciseName
	 * @param location
	 * @param muscleGroup
	 * @param sets
	 * @param reps
	 * @param date
	 * @param favourite
	 */
	public void create(int userID, String exerciseName, String location, String muscleGroup, int sets, int reps, String date, boolean favourite) {
		
		try {
			PreparedStatement pstmt = connection.prepareStatement
					("INSERT INTO strength_exercises (userid, exercise_name, location, muscle_group, sets, reps, date_of_exercise, favourite) "
							+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
			pstmt.setInt(1, userID);
			pstmt.setString(2, exerciseName);
			pstmt.setString(3, location);	
			pstmt.setString(4, muscleGroup);
			pstmt.setInt(5, sets);
			pstmt.setInt(6, reps);
			pstmt.setString(7, date);
			pstmt.setBoolean(8, favourite);
			System.out.println(pstmt);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Exception is ;" + e + ": message is " + e.getMessage());
		}
		
	}
	
	/**
	 * Delete a specific row from the table. 
	 * @param id - the id of the row being deleted.
	 * @param userid
	 * @return true means successfuly deletion, false means an error was encountered.
	 */
	public boolean delete(int id, int userid) {
		boolean success = false;
		try {
			PreparedStatement pstmt = connection.prepareStatement("DELETE FROM strength_exercises WHERE (id = ?);");
			
			pstmt.setInt(1, id);
			System.out.println(pstmt);
			pstmt.executeUpdate();
			
			success = true;
		} catch (SQLException e) {
			success = false;
			System.out.println("Exception is ;" + e + ": message is " + e.getMessage());
		}
		return success;
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
