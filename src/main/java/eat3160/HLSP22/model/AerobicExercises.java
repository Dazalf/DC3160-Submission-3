package eat3160.HLSP22.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * A data access object for the 'Aerobic_exercises' table from the HLSP database, providing CRUD functionality. 
 * @author alf-s
 *
 */
public class AerobicExercises {
	
	private Connection connection = null;
	String connectionURL = "jdbc:mysql://localhost:3306/hlsp";

	public AerobicExercises() {
		init();
	}
	
	/**
	 * Get an arraylist of aerobic exercises for a specific user for a specific date. 
	 * @param userid
	 * @param date
	 * @return
	 */
	public ArrayList<AerobicExerciseBean> getExercises(int userid, String date) {
		ArrayList<AerobicExerciseBean> list = new ArrayList<>();
		
		try {
			PreparedStatement pstmt = connection.prepareStatement
					("SELECT * FROM aerobic_exercises WHERE date_of_exercise LIKE ? AND userid = ?;");
			
			pstmt.setString(1, date);	
			pstmt.setInt(2, userid);
			System.out.println(pstmt);
			ResultSet rs1 = pstmt.executeQuery();

			while (rs1.next()) { 
				AerobicExerciseBean ae = new AerobicExerciseBean();
				ae.setId(rs1.getInt(1));
				ae.setExerciseName(rs1.getString(3));
				ae.setLocation(rs1.getString(4));
				ae.setSteps(rs1.getInt(5));
				ae.setDateOfExercise(rs1.getString(6));
				ae.setFavourite(rs1.getBoolean(7));
				list.add(ae);
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
	 * @return an AerobicExerciseeBean object. 
	 */
	public AerobicExerciseBean getExercise(int id, int userid) {
		AerobicExerciseBean ae = null;
		
		try {
			PreparedStatement pstmt = connection.prepareStatement
					("SELECT * FROM aerobic_exercises WHERE id LIKE ? AND userid = ?;");
			
			pstmt.setInt(1, id);	
			pstmt.setInt(2, userid);
			System.out.println(pstmt);
			ResultSet rs1 = pstmt.executeQuery();

			if (rs1.next()) { 
				ae = new AerobicExerciseBean();
				ae.setId(rs1.getInt(1));
				ae.setExerciseName(rs1.getString(3));
				ae.setLocation(rs1.getString(4));
				ae.setSteps(rs1.getInt(5));
				ae.setDateOfExercise(rs1.getString(6));
				ae.setFavourite(rs1.getBoolean(7));
			}
			
		} catch (SQLException e) {
			System.out.println("Exception is ;" + e + ": message is " + e.getMessage());
		}
		
		return ae;
	}
	
	/**
	 * Update the values of a specific row in the table. 
	 * @param id
	 * @param exerciseName
	 * @param location
	 * @param steps
	 * @param date
	 * @param favourite
	 */
	public void update(int id, String exerciseName, String location, int steps, String date, boolean favourite) {
		
		try {
			PreparedStatement pstmt = connection.prepareStatement
					("UPDATE aerobic_exercises SET exercise_name = ?, location = ?, "
							+ "steps = ?, date_of_exercise = ?, favourite = ? WHERE (id = ?);");
			
			pstmt.setString(1, exerciseName);
			pstmt.setString(2, location);
			pstmt.setInt(3, steps);
			pstmt.setString(4, date);
			pstmt.setBoolean(5, favourite);
			pstmt.setInt(6, id);
			System.out.println(pstmt);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Exception is ;" + e + ": message is " + e.getMessage());
		}
	}
	
	/**
	 * Create a new row within the table.
	 * @param int userID
	 * @param String exerciseName
	 * @param String location
	 * @param int steps
	 * @param String date
	 * @param boolean favourite
	 */
	public void create(int userID, String exerciseName, String location, int steps, String date, boolean favourite) {
		
		try {
			PreparedStatement pstmt = connection.prepareStatement
					("INSERT INTO aerobic_exercises (userid, exercise_name, location, steps, date_of_exercise, favourite) "
							+ "VALUES (?, ?, ?, ?, ?, ?);");
			pstmt.setInt(1, userID);
			pstmt.setString(2, exerciseName);
			pstmt.setString(3, location);
			pstmt.setInt(4, steps);
			pstmt.setString(5, date);
			pstmt.setBoolean(6, favourite);
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
			PreparedStatement pstmt = connection.prepareStatement("DELETE FROM aerobic_exercises WHERE (id = ?);");
			
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
