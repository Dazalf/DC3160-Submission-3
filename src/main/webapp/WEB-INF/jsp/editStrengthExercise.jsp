<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html class="h-100">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Edit strength exercise</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
  </head>
  <body class="d-flex flex-column h-100">
    
	<jsp:include page="navigation.jsp"/>
	
	<main role="main" class="flex-shrink-0 my-auto">
	    <div class ="container py-2 border">
	    
		    <h3> Edit strength exercise </h3>
		    
		     <!-- Store the java bean 'strengthExerciseBean' from the request scope as a variable named 'exercise'. --> 
			<c:set var="exercise" value="${requestScope.strengthExerciseBean}" scope="page"/> 
	    	
	    	<form action="/exercises/update/strength_exercise" method="POST">
	    	
				<div class="form-group">
	    			<label for="ExerciseN">Exercise name</label>
	   				<input type="text" class="form-control" name="exerciseName" id="exerciseName" value="${exercise.exerciseName}" required>
		 		</div>
		 			
		 		<div class="row"> 
			  		<div class="form-group pt-3 col">
			    		<label for="sets">Amount of sets</label>
			    		<input type="number" class="form-control" name="sets" id="sets" min="0" value="${exercise.sets}" required>
			  		</div>
			  		
			  		<div class="form-group pt-3 col">
			    		<label for="reps">Amount of reps</label>
			    		<input type="number" class="form-control" name="reps" id="reps" min="0" required value="${exercise.reps}">
			  		</div>
		  		</div>
		 		
		 		<div class="form-group pt-3">
		    		<label>Where did you exercise?</label>
		    		<div class="form-check">
		    		<!-- The below choose statements are used to determine what radio button should be set as checked, dependent on whether the
		    		radio value equals the corresponding value of the exercise bean -->
		    		 	 <c:choose>
	   						<c:when test="${exercise.location eq 'Home'}">
	   							<input class="form-check-input" type="radio" value="Home" name="location" id="locationRadioHome" checked="checked"/>
								<label class="form-check-label" for="locationRadioHome">
								   Home
					 			</label>
	   						</c:when>
	   						<c:otherwise>
	   							<input class="form-check-input" type="radio" value="Home" name="location" id="locationRadioHome"/>
								<label class="form-check-label" for="locationRadioHome">
								   Home
					 			</label>
	   						</c:otherwise>
	   					</c:choose>  
					</div>
					
					<div class="form-check">
		    		 	 <c:choose>
	   						<c:when test="${exercise.location eq 'Gym'}">
							  <input class="form-check-input" type="radio" value="Gym" name="location" id="locationRadioGym" checked="checked">
							  <label class="form-check-label" for="locationRadioGym">
							    Gym
							  </label>
	   						</c:when>
	   						<c:otherwise>
							  <input class="form-check-input" type="radio" value="Gym" name="location" id="locationRadioGym">
							  <label class="form-check-label" for="locationRadioGym">
							    Gym
							  </label>
	   						</c:otherwise>
	   					</c:choose>				
					</div>
					
					<div class="form-check">
		    		 	 <c:choose>
	   						<c:when test="${exercise.location eq 'Park'}">
							  <input class="form-check-input" type="radio" value="Park" name="location" id="locationRadioPark" checked="checked">
							  <label class="form-check-label" for="checkboxPark">
							    Park
							  </label>
	   						</c:when>
	   						<c:otherwise>
							  <input class="form-check-input" type="radio" value="Park" name="location" id="locationRadioPark">
							  <label class="form-check-label" for="checkboxPark">
							    Park
							  </label>
	   						</c:otherwise>
	   					</c:choose>					
					</div>
		  		
		  		</div>
		  		
		  		<div class="form-group pt-3">
		    		<label>What muscle group was involved?</label>
		    		<div class="form-check">
		    		 	 <c:choose>
	   						<c:when test="${exercise.muscleGroup eq 'Chest'}">
							  <input class="form-check-input" type="radio" value="Chest" name="muscleGroup" id="muscleGroupRadioChest" checked>
							  <label class="form-check-label" for="muscleGroupRadioChest">
							    Chest
							  </label>
	   						</c:when>
	   						<c:otherwise>
							  <input class="form-check-input" type="radio" value="Chest" name="muscleGroup" id="muscleGroupRadioChest" checked>
							  <label class="form-check-label" for="muscleGroupRadioChest">
							    Chest
							  </label>
	   						</c:otherwise>
	   					</c:choose>	    		
					</div>
					
					<div class="form-check">
		    		 	 <c:choose>
	   						<c:when test="${exercise.muscleGroup eq 'Back'}">
							  <input class="form-check-input" type="radio" value="Back" name="muscleGroup" id="muscleGroupRadioBack" checked>
							  <label class="form-check-label" for="muscleGroupRadioBack">
							    Back
							  </label>
	   						</c:when>
	   						<c:otherwise>
							  <input class="form-check-input" type="radio" value="Back" name="muscleGroup" id="muscleGroupRadioBack">
							  <label class="form-check-label" for="muscleGroupRadioBack">
							    Back
							  </label>
	   						</c:otherwise>
	   					</c:choose>				
					</div>
					
					<div class="form-check">
		    		 	 <c:choose>
	   						<c:when test="${exercise.muscleGroup eq 'Arms'}">
							  <input class="form-check-input" type="radio" value="Arms" name="muscleGroup" id="muscleGroupRadioArms" checked>
							  <label class="form-check-label" for="muscleGroupRadioArms">
							    Arms
							  </label>
	   						</c:when>
	   						<c:otherwise>
							  <input class="form-check-input" type="radio" value="Arms" name="muscleGroup" id="muscleGroupRadioArms">
							  <label class="form-check-label" for="muscleGroupRadioArms">
							    Arms
							  </label>
	   						</c:otherwise>
	   					</c:choose>							  
					</div>
					
					<div class="form-check">
		    		 	 <c:choose>
	   						<c:when test="${exercise.muscleGroup eq 'Abdominals'}">
							  <input class="form-check-input" type="radio" value="Abdominals" name="muscleGroup" id="muscleGroupRadioAbdominals" checked>
							  <label class="form-check-label" for="checkboxAbdominals">
							    Abdominals
							  </label>
	   						</c:when>
	   						<c:otherwise>
							  <input class="form-check-input" type="radio" value="Abdominals" name="muscleGroup" id="muscleGroupRadioAbdominals">
							  <label class="form-check-label" for="checkboxAbdominals">
							    Abdominals
							  </label>
	   						</c:otherwise>
	   					</c:choose>	
					</div>
					
					<div class="form-check">
		    		 	 <c:choose>
	   						<c:when test="${exercise.muscleGroup eq 'Legs'}">
							  <input class="form-check-input" type="radio" value="Legs" name="muscleGroup" id="muscleGroupRadioLegs" checked>
							  <label class="form-check-label" for="muscleGroupRadioLegs">
							    Legs
							  </label>
	   						</c:when>
	   						<c:otherwise>
							  <input class="form-check-input" type="radio" value="Legs" name="muscleGroup" id="muscleGroupRadioLegs">
							  <label class="form-check-label" for="muscleGroupRadioLegs">
							    Legs
							  </label>
	   						</c:otherwise>
	   					</c:choose>				
					</div>
					
					<div class="form-check">
		    		 	 <c:choose>
	   						<c:when test="${exercise.muscleGroup eq 'Shoulders'}">
							  <input class="form-check-input" type="radio" value="Shoulders" name="muscleGroup" id="muscleGroupRadioShoulders" checked>
							  <label class="form-check-label" for="muscleGroupRadioShoulders">
							    Shoulders
							  </label>
	   						</c:when>
	   						<c:otherwise>
							  <input class="form-check-input" type="radio" value="Shoulders" name="muscleGroup" id="muscleGroupRadioShoulders">
							  <label class="form-check-label" for="muscleGroupRadioShoulders">
							    Shoulders
							  </label>	
	   						</c:otherwise>
	   					</c:choose>
					</div>
		  		
		  		</div>
		  		
		  		<div class="form-group pt-3">
		    		<label for="date">Choose exercise date</label>
		    		<input type="date" class="form-control" name="dateOfExercise" id="date" value="${exercise.dateOfExercise}" onfocus="this.max=new Date().toISOString().split('T')[0]" required>
		  		</div>
	  			
	  			<div class="form-check form-switch pt-3">
	  				<c:choose>
	  					<c:when test="${exercise.favourite eq 'true'}">
						  <input class="form-check-input" type="checkbox" name="favourite" id="favouriteSwitch" checked>
						  <label class="form-check-label" for="favouriteSwitch">Favourite?</label>  						
	  					</c:when>
	  					<c:otherwise>
	  					  <input class="form-check-input" type="checkbox" name="favourite" id="favouriteSwitch">
						  <label class="form-check-label" for="favouriteSwitch">Favourite?</label> 
	  					</c:otherwise>
	  				</c:choose>
				</div>
				
				<input type="hidden" id="exerciseID" name="exerciseID" value="${exercise.id}">
		  		
		  		<div class="pt-3">
		  			<button type="submit" value="Submit" class="btn btn-primary">Submit</button>
		  		</div>
		  		
		  		<div class="pt-3">
	 	  			<a class="btn btn-primary" href="/exercises/view" role="button">Discard changes</a>
		     	</div>	
	
	    	</form>
	    	
	    	<form id="deleteUserForm" action="/exercises/delete/strength_exercise" method="POST">
	    	
	    	    <input type="hidden" id="exerciseID" name="exerciseID" value="${exercise.id}">
		     	
		     	<!-- Button to trigger the delete strength exercise modal -->
				<div class="pt-3">
					<button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteExerciseModal">
		 				 Delete exercise
					</button>
				</div>
				
				<!-- Delete exercise modal -->
				<div class="modal fade" id="deleteExerciseModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog modal-dialog-centered">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="exampleModalLabel">Delete exercise</h5>
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				        Are you sure you want to delete this exercise?
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No!</button>
				        <button type="submit" value="Submit" class="btn btn-primary" >Yes!</button>
				      </div>
				    </div>
				  </div>
				</div>
		     </form>  
		     
	    </div>
    </main>
    
    <jsp:include page="footer.jsp"/>
    
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.min.js" integrity="sha384-IDwe1+LCz02ROU9k972gdyvl+AESN10+x7tBKgc9I5HFtuNz0wWnPclzo6p9vxnk" crossorigin="anonymous"></script>
    
  </body>
</html>