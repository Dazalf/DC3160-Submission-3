<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html class="h-100">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Exercises</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="/viewExerciseScripts.js"></script>
  </head>
  <body class="d-flex flex-column h-100">
    
 
    <jsp:include page="navigation.jsp"/>
   
   	<main role="main" class="flex-shrink-0 my-auto">
	     <div class="container py-3 border">
	     
	     	<h3 class="pb-3"> Exercises </h3>
	    	
	    	<!-- This shows a message letting the user know their exercise has been updated or deleted successfully. The request attribute is created 
		    by the Exercise controller, which redirects the user here. -->
	    	<c:if test = "${!empty sessionScope.SuccessfulExerciseUpdate || !empty sessionScope.SuccessfulExerciseDeletion || !empty sessionScope.SuccessfulExerciseCreation}">
	    		<div class="alert alert-success alert-dismissible" role="alert"> 
	    			<span><c:out value="${sessionScope.SuccessfulExerciseUpdate}"/></span>
	    			<span><c:out value="${sessionScope.SuccessfulExerciseDeletion}"/></span>
	    			<span><c:out value="${sessionScope.SuccessfulExerciseCreation}"/></span>
	    			<!-- Remove from session scope so this alert won't always show.  -->
	    			<c:remove var="SuccessfulExerciseUpdate" scope="session"/>
	    			<c:remove var="SuccessfulExerciseDeletion" scope="session"/>
	    			<c:remove var="SuccessfulExerciseCreation" scope="session"/>
	    			<button class="btn-close" aria-label="close" data-bs-dismiss="alert"></button>
	    		</div>
	    	</c:if>
	     	
	     	<label for="datepicker">Filter by date:</label>
			<input type="date" id="datepicker" name="datepicker" value="${requestScope.todaysDate}" onchange="updateTables(this.value);"> 
	     	
	     	<div class="row"> 
		     	<div class="col-12 py-3">
			     	<div class="table-responsive-sm"> 	
						<table class="table table-hover table-striped caption-top">
						<caption>Aerobic exercises</caption>
						  <thead class="table-light">
						    <tr>
						      <th scope="col">Exercise name</th>
						      <th scope="col">Location</th>
						      <th scope="col">Step count</th>
						      <th scope="col">Favourited</th>
						      <th scope="col"></th>
						    </tr>
						  </thead>
						  <tbody id="aerobicDisplay">
						  	<c:forEach var="exercise" items="${requestScope.aerobicExercises}">
						  		<tr>
							  	  <td><c:out value="${exercise.exerciseName}"/></td>
							      <td><c:out value="${exercise.location}"/></td>
							      <td><c:out value="${exercise.steps}"/></td>	
							      <td><c:out value="${exercise.favourite}"/></td>	
							      <td><a class="btn btn-secondary btn-sm" href="/exercises/edit/aerobic_exercise?id=${exercise.id}" role="button">Edit</a></td>	
						  		</tr>
						  	</c:forEach>
						  </tbody>
						</table>
					</div>
				
					<div class="pt-1"> 
						<a class="btn btn-light" href="/exercises/form/aerobic_exercise"> Add aerobic exercise </a>
					</div> 
				</div>
			
			
				<div class="col-12 py-3">
			     	<div class="table-responsive-sm"> 
						<table class="table table-hover table-striped caption-top">
						 <caption>Strength training exercises</caption>	
						 <thead class="table-light">
					      <tr>
						     <th scope="col">Exercise name</th>
						     <th scope="col">Location</th>
							 <th scope="col">Muscle group</th>
							 <th scope="col">Sets</th>
							 <th scope="col">Reps</th>
							 <th scope="col">Favourited</th>
							 <th scope="col"></th>
						  </tr>
						  </thead>
						  <tbody id="strengthDisplay">
						  	<c:forEach var="exercise" items="${requestScope.strengthExercises}">
							    <tr>
							      <td><c:out value="${exercise.exerciseName}"/></td>
							      <td><c:out value="${exercise.location}"/></td>
							      <td><c:out value="${exercise.muscleGroup}"/></td>
							      <td><c:out value="${exercise.sets}"/></td>
							      <td><c:out value="${exercise.reps}"/></td>
							      <td><c:out value="${exercise.favourite}"/></td>
								  <td><a class="btn btn-secondary btn-sm" href="/exercises/edit/strength_exercise?id=${exercise.id}" role="button">Edit</a></td>	
							    </tr>
						    </c:forEach>
						  </tbody>
						</table>
					</div>
					<div class="pt-1">
						<a class="btn btn-light" href="/exercises/form/strength_exercise"> Add strength training exercise </a> 
					</div>
				</div>
			</div>
		</div>
	</main>
	
	<jsp:include page="footer.jsp"/>
    
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.min.js" integrity="sha384-IDwe1+LCz02ROU9k972gdyvl+AESN10+x7tBKgc9I5HFtuNz0wWnPclzo6p9vxnk" crossorigin="anonymous"></script>
    
  </body>
</html>