
//Get the relevant elements from the signupForm
 let password = document.getElementById("pwd"); 
 let passwordCheck = document.getElementById("pwdCheck"); 
 let pwdMatch = document.getElementById("pwdMatchError");
 let pwdRegEx = document.getElementById("pwdRegExError");
 let form = document.getElementById("form");
 
 //Counts form errors
 let errorCounter = 0;
 
 //Add an event listener for when a submit occurs.
 form.addEventListener("submit", validatePassword, false);
 
 //This will check if the password is correct, and if not, don't allow a submission.
 //It will also show errors on the screen. 
 function validatePassword(event){
	
	//This regex ensures the password has 1 upper-case letter, 1 lower-case letter, 1 symbol, 1 number, and a length of 8.
	let regEx = new RegExp("^(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])(?=.*[a-z]).{8,}$");

	console.log(regEx.test(password.value));
	if(!regEx.test(password.value)){
		pwdRegEx.innerText = "Passwords must have 1 upper-case letter, 1 lower-case letter, 1 symbol, 1 number, and a length of 8"
		errorCounter++;
	}else{
		pwdRegEx.innerText = "";
	}
	
	if(password.value !== passwordCheck.value){
		pwdMatch.innerText = "Passwords must match!"
		errorCounter++;
	} else{
		pwdMatch.innerText = ""
	}
	
	if(errorCounter > 0){
		event.preventDefault();
		errorCounter = 0;
	}	
}

//The code below handles the AJAX request, for the sign-up page, to check whether the inputted email address already exists in the database. 

//This method will make an AJAX call to the Account Controller to identify whether the email that's been inputted by the user is valid. 
//The expected response from the server is simply a boolean value. 
function isEmailAvailable(email){
	jQuery.post("/isEmailAvailable", {"email":email}, processEmailResponse);
	console.log(email);
}

//The code below handles the AJAX request, for the edit user profile page, to check whether the inputted email already exists in the database.

//This method will make an AJAX call to the Account Controller to identify whether the email that's been inputted by the user is valid. 
//The expected response from the server is simply a boolean value. 
function isEmailAvailableExcludingLoggedInUser(email){
	jQuery.post("/account/isEmailAvailable", {"email":email}, processEmailResponse);
}

/**
	This method processes the response from the server. 
	If the response is true, then show an error and stop the form from being submitted.
	Else, don't show any error on the screen, as the email isn't already being used in the server. 
 */
function processEmailResponse(serverResponse){
	console.log(serverResponse);
	if(serverResponse == true){
		jQuery("#emailAlert").html("<div class='text-danger'> This email is already in use. Please use a different email. </div>");
	}else{
		jQuery("#emailAlert").html("");
	}
}
