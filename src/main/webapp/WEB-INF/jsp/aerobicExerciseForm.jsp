<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html class="h-100">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add aerobic exercise</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
  </head>
  <body class="d-flex flex-column h-100">
    
    <jsp:include page="navigation.jsp"/>
    
    <main role="main" class="flex-shrink-0 my-auto">
	    <div class ="container pt-3 border">
	    
		    <h3 class="py-3"> Add aerobic exercise </h3>
	    	
	    	<form action="/exercises/add/aerobic_exercise" method="POST">
	    	
	    		<div class="form-group">
	    			<label for="ExerciseN">Exercise name</label>
	   				<input type="text" class="form-control" name="exerciseName" id="exerciseName" placeholder="Input exercise name" required>
		 		</div>
		 		
		 		<div class="row py-3">
			  		<div class="form-group col">
			    		<label for="stepC">Amount of steps</label>
			    		<input type="number" class="form-control" name="steps" id="steps" min="0">
			  		</div>
			  		
			  		<div class="form-group col">
			    		<label for="date">Choose exercise date</label>
			    		<input type="date" class="form-control" name="dateOfExercise" id="date" value="${requestScope.todaysDate}" onfocus="this.max=new Date().toISOString().split('T')[0]" required>
			  		</div>
		  		</div>
		 		
		 		<div class="form-group py-3">
		    		<label>Where did you exercise?</label>
		    		<div class="form-check">
					  <input class="form-check-input" type="radio" value="Home" name="location" id="locationRadioHome" checked="checked">
					  <label class="form-check-label" for="locationRadioHome">
					    Home
					  </label>
					</div>
					<div class="form-check">
					  <input class="form-check-input" type="radio" value="Gym" name="location" id="locationRadioGym">
					  <label class="form-check-label" for="locationRadioGym">
					    Gym
					  </label>
					</div>
						<div class="form-check">
					  <input class="form-check-input" type="radio" value="Park" name="location" id="locationRadioPark">
					  <label class="form-check-label" for="checkboxPark">
					    Park
					  </label>
					</div>
		  		</div>
		  		
	  			<div class="form-check form-switch pt-3">
				  <input class="form-check-input" type="checkbox" name="favourite" id="flexSwitchCheckDefault">
				  <label class="form-check-label" for="flexSwitchCheckDefault">Favourite?</label>
				</div>
		  		
		  		<div class="pt-3">
		  			<button type="submit" value="Submit" class="btn btn-primary">Submit</button>
		  		</div>
	    	</form>
	    	
	    	<div class="table-responsive-sm pt-3">
				<table class="table table-hover table-striped caption-top">
				<caption>Recently added exercises</caption>
				  <thead class="table-light">
				    <tr>
				      <th scope="col">Exercise name</th>
				      <th scope="col">Location</th>
				      <th scope="col">Step count</th>
				      <th scope="col">Date of exercise</th>
				    </tr>
				  </thead>
				  <tbody>
				    <tr>
				      <td>Example exercise 1</td>
				      <td>Example location 1</td>
				      <td>Example count 1</td>
				      <td>Example date 1</td>
				    </tr>
				  </tbody>
				</table>
			</div>
			
			<div class="table-responsive-sm pt-3">
				<table class="table table-hover table-striped caption-top">
				<caption>Favourite exercises</caption>
				  <thead class="table-light">
				   <tr>
				      <th scope="col">Exercise name</th>
				      <th scope="col">Location</th>
				      <th scope="col">Step count</th>
				      <th scope="col">Date of exercise</th>
				   </tr>
				  </thead>
				  <tbody>
				    <tr>
				      <td>Example exercise 1</td>
				      <td>Example location 1</td>
				      <td>Example count 1</td>
				      <td>Example date 1</td>
				    </tr>
				  </tbody>
				</table>
			</div>	
	    </div>
    </main>
    
    <jsp:include page="footer.jsp"/>
    
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.min.js" integrity="sha384-IDwe1+LCz02ROU9k972gdyvl+AESN10+x7tBKgc9I5HFtuNz0wWnPclzo6p9vxnk" crossorigin="anonymous"></script>
    
  </body>
</html>