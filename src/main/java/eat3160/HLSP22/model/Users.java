package eat3160.HLSP22.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class models the 'Users' table in the database being used for this application.
 * It enables the verification of email and password input, taken from the client-side, and
 * the getting of a users ID via email address.. 
 * @author alf-s
 */

public class Users {
	
	private Connection connection = null;
	String connectionURL = "jdbc:mysql://localhost:3306/hlsp";
	
	public Users() {
		init();
	}
	
	/**
	 * Checks if a user exists using an email and password. 
	 * This function is used to validate the input from the client-side and determine whether to log in a user. 
	 * @param email
	 * @param password
	 * @return false = invalid user, true = valid user. 
	 */
	
	public boolean validateUser(String email, String password) {
		boolean isValid = false;
			
		try {

			PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM users WHERE email LIKE ? AND password LIKE ?");
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			System.out.println(pstmt);
			ResultSet rs1 = pstmt.executeQuery();
			

			if (rs1.next()) { 
				isValid = true;
			}
			
		} catch (SQLException e) {
			System.out.println("Exception is ;" + e + ": message is " + e.getMessage());
		}
		return isValid;
	}
	
	/**
	 * This function checks if a specified email is already associated with an existing account in the 'Users' table.
	 * @param email
	 * @return boolean: false = email doesn't exist, true = email exists. 
	 */
	
	public boolean doesEmailExist(String email) {
		boolean exists = false;
			
		try {
	
			PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM users WHERE email LIKE ?");
			pstmt.setString(1, email);
			System.out.println(pstmt);
			ResultSet rs1 = pstmt.executeQuery();

			if (rs1.next()) { 
				exists = true;
			}
			
		} catch (SQLException e) {
			System.out.println("Exception is ;" + e + ": message is " + e.getMessage());
		}
		return exists;
	}
	
	/**
	 * This function checks if a specified email is already associated with an existing account in the 'Users' table.
	 * However, it will ignore the specified userID in the parameters e.g., the current logged in user, in it queries
	 * to the Users table.
	 * @param email
	 * @param userID
	 * @return boolean: false = email doesn't exist, true = email exists. 
	 */
	
	public boolean doesEmailExist(String email, int userID) {
		boolean exists = false;
			
		try {
	
			PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM users WHERE email LIKE ? AND NOT userid = ?");
			pstmt.setString(1, email);
			pstmt.setInt(2, userID);
			System.out.println(pstmt);
			ResultSet rs1 = pstmt.executeQuery();

			if (rs1.next()) { 
				exists = true;
			}
			
		} catch (SQLException e) {
			System.out.println("Exception is ;" + e + ": message is " + e.getMessage());
		}
		return exists;
	}
	
	/**
	 * This method is used to register a new user to the table, using the provided parameters.
	 * It performs a series of validation checks against the data prior to updating the table, and if any
	 * errors are found, the update isn't performed and instead a list of errors are returned.  
	 * @param fn
	 * @param sn
	 * @param dob
	 * @param email
	 * @param password
	 * @param experience
	 * @param disability
	 * @return a string array list that, if any found, contains errors. Otherwise empty.
	 */
	public ArrayList<String> registerUser(UserBean user) {
		
		ArrayList<String> errors = validateFormInput(user);
		
		//Checking if this email address is already in the database. If yes, add an error to the error string. 
		if(doesEmailExist(user.getEmail()) == true) {
			errors.add("\n The inputted email address is not available. Please use a different email address and try again.");
		}
		
		//If the errors array is empty, that means no validation errors were identified. Proceed with creating the user account. 
		if(errors.isEmpty()){
			
			try {
				
				PreparedStatement pstmt = connection.prepareStatement("INSERT INTO users (FirstName, Surname, DateOfBirth, Email, Password, Exercise_experience, Disability) VALUES (?, ?, ?, ?, ?, ?, ?);");
				pstmt.setString(1, user.getFirstName());
				pstmt.setString(2, user.getSurname());
				pstmt.setString(3, user.getDateOfBirth());
				pstmt.setString(4, user.getEmail());
				pstmt.setString(5, user.getPassword());
				pstmt.setString(6, user.getExerciseExperience());
				pstmt.setString(7, user.getDisability());
				System.out.println(pstmt);
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("Exception is ;" + e + ": message is " + e.getMessage());
				
				//If error occurs during the creation of a record, add to the errors array.
				errors.add("\n An unexpected error occured trying to create your account. Please try again.");
			}	
		}
		
		return errors;
	}
	
	/**
	 * This method updates a user record in the Users table based on the parameters provided.
	 * It performs a series of validation checks against the data prior to updating the table, and if any
	 * errors are found, the update isn't performed and instead a list of errors are returned. 
	 * @param userID
	 * @param fn
	 * @param sn
	 * @param dob
	 * @param email
	 * @param password
	 * @param experience
	 * @param disability
	 * @return a string array list that, if any found, contains errors. Otherwise empty.
	 */
	public ArrayList<String> updateUser(UserBean user, int userID){
		
		ArrayList<String> errors = validateFormInput(user);
		
		//Checking if this email address is already in the database, excluding a specified user from the table query. 
		//If yes, add an error to the error string. 
		if(doesEmailExist(user.getEmail(), userID) == true) {
			errors.add("\n The inputted email address is not available. Please use a different email address and try again.");
		}
		
		//If the errors array is empty, that means no validation errors were identified. Proceed with creating the user account. 
		if(errors.isEmpty()){
			try {
				
				PreparedStatement pstmt = connection.prepareStatement(
						"UPDATE users SET FirstName = ?, Surname = ?, DateOfBirth = ?, Email = ?, Password = ?, Exercise_experience = ?, Disability = ? WHERE (UserID = ?);");
//				pstmt.setString(1, fn);
//				pstmt.setString(2, sn);
//				pstmt.setString(3, dob);
//				pstmt.setString(4, email);
//				pstmt.setString(5, password);
//				pstmt.setString(6, experience);
//				pstmt.setString(7, disability);
//				pstmt.setInt(8, userID);
				pstmt.setString(1, user.getFirstName());
				pstmt.setString(2, user.getSurname());
				pstmt.setString(3, user.getDateOfBirth());
				pstmt.setString(4, user.getEmail());
				pstmt.setString(5, user.getPassword());
				pstmt.setString(6, user.getExerciseExperience());
				pstmt.setString(7, user.getDisability());
				pstmt.setInt(8, userID);
				System.out.println(pstmt);
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("Exception is ;" + e + ": message is " + e.getMessage());
				
				//If error occurs during the creation of a record, add to the errors array.
				errors.add("\n An unexpected error occured trying to create your account. Please try again.");
			}
		}
		
		return errors;
	}
	
	/**
	 * Performs a series of validation checks against the provided parameters to ensure they confirm to specifications
	 * prior to being added to the Users table. Any issues found are returned in array list.
	 * @param fn
	 * @param sn
	 * @param dob
	 * @param email
	 * @param password
	 * @return a string array list that, if any found, contains errors. Otherwise empty.
	 */
	private ArrayList<String> validateFormInput(UserBean user){
		
		String fn = user.getFirstName();
		String sn = user.getSurname();
		String dob = user.getDateOfBirth();
		String email = user.getEmail();
		String experience = user.getExerciseExperience();
		String password = user.getPassword();
		String disability = user.getDisability();
		
		ArrayList<String> errors = new ArrayList<>();
		
		//SERVER-SIDE VALIDATION OF INPUT
		
		//Checking if the inputted string parameters exceeds the limit of 45 characters. If yes, add an error to the error array. 
		if(fn.length() > 45) {
			errors.add("The first name exceeds the limit of 45 characters.");
		}
		
		if(fn.length() == 0) {
			errors.add("The first name cannot be empty.");
		}
		
		if(sn.length() > 45) {
			errors.add("\n The surname exceeds the limit of 45 characters.");
		}
		
		if(sn.length() == 0) {
			errors.add("\n The surname cannot be empty.");
		}
		
		if(dob.length() > 45) {
			errors.add("\n The date exceeds the limit of 45 characters.");
		}
		
		if(dob.length() == 0) {
			errors.add("\n The date cannot be empty.");
		}else {
			
			//Checking if the inputted date exceeds todays date. If yes, add an error message to the array. 	
			try {
				
				//Create a simple date formatter to only have the date.
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				//Format a new date object into a string, and then parse the string to return a date object. 
				//Store this formatted date object into a date variable. 
				Date td = formatter.parse(formatter.format(new Date()));		
			    
				//Convert the inputed date of birth string into a date object. 
				Date dobInput = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
				
				//Check if inputed date is after todays date. If yes, add error. 
				if(dobInput.after(td)) {
					errors.add("\n The inputted date of birth exceeds todays date. Please input a valid date.");
				}
				
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				errors.add("\n An unexpected error occured when processing the inputted date of birth. Please doublecheck and please try again.");
			}
		}
		
		if(email.length() > 45) {
			errors.add("\n The email exceeds the limit of 45 characters.");
		}
		
		if(email.length() == 0) {
			errors.add("\n The email cannot be empty.");
		}
		
		if(password.length() > 45) {
			errors.add("\n The password exceeds the limit of 45 characters.");
		}
		
		if(experience.length() > 45) {
			errors.add("\n The experience input exceeds the limit of 45 characters.");
		}
		
		if(experience.length() == 0) {
			errors.add("\n The exercise experience input cannot be empty.");
		}
		
		if(disability.length() > 45) {
			errors.add("\n The disability input exceeds the limit of 45 characters.");
		}
		
		if(disability.length() == 0) {
			errors.add("\n The disability input cannot be empty.");
		}
		
		//Checking if the password doesn't match the regex. If it doesn't, add an error. 
		
		Pattern p = Pattern.compile("^(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])(?=.*[a-z]).{8,}$");
		Matcher m = p.matcher(password);
		if(!m.matches()) {
			errors.add("\n The password must have 1 upper-case letter, 1 lower-case letter, 1 symbol, 1 number, and a length of 8.");
		}
	
		return errors;
	}

	/**
	 * Return the User ID attribute of the record associated with the provided email address in the parameter.
	 * If the returned User ID is equal to -1, then a record associated with this email address wasn't found. 
	 * @param email
	 * @return userID
	 */
	public int getUserID(String email) {
		
		int userID = -1;
		
		try {

			PreparedStatement pstmt = connection.prepareStatement("SELECT userid FROM users WHERE email LIKE ?");
			pstmt.setString(1, email);
			System.out.println(pstmt);
			ResultSet rs1 = pstmt.executeQuery();
			
			if (rs1.next()) { 
				userID = rs1.getInt(1);
				System.out.println(userID);
			}
			
		} catch (SQLException e) {
			System.out.println("Exception is ;" + e + ": message is " + e.getMessage());
		}
		return userID;
	}
	
	/**
	 * This method will delete the user specified user.
	 * @param userID - user to be deleted.
	 */
	public void deleteUser(int userID) {
		
		try {

			PreparedStatement pstmt = connection.prepareStatement("DELETE FROM users WHERE (UserID = ?);");
			pstmt.setInt(1, userID);
			System.out.println(pstmt);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Exception is ;" + e + ": message is " + e.getMessage());
		}
		
	}
	
	/**
	 * Get the details of a user, store them within a java bean 'User', and return this bean. 
	 * @param userID
	 * @return
	 */
	public UserBean getUserDetails(int userID) {
		
		UserBean user = new UserBean();
		
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM users WHERE userid LIKE ?");
			pstmt.setInt(1, userID);
			System.out.println(pstmt);
			ResultSet rs1 = pstmt.executeQuery();
			
			if (rs1.next()) { 
				user.setFirstName(rs1.getString(2));
				user.setSurname(rs1.getString(3));
				user.setDateOfBirth(rs1.getString(4));
				user.setEmail(rs1.getString(5));
				user.setPassword(rs1.getString(6));
				user.setExerciseExperience(rs1.getString(7));
				user.setDisability(rs1.getString(8));
			}
			
		} catch (SQLException e) {
			System.out.println("Exception is ;" + e + ": message is " + e.getMessage());
		}
		
		return user;
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
