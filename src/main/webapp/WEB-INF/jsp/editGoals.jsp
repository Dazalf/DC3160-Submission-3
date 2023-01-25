<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html class="h-100">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Exercise goals</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
</head>
  
  <body class="d-flex flex-column h-100">
  
   	 <jsp:include page="navigation.jsp"/>
    
     <main role="main" class="flex-shrink-0 my-auto"> 
	     
	     <div class="row"> 
	     
	     	<div class="col-12 py-3">
			     <div class="container py-3 border">
			     	
				    <h3> Edit exercise goals </h3>
				   	
					<!-- Store the java bean 'goals' from the request scope as a variable named 'goals'. --> 
					<c:set var="goals" value="${requestScope.goals}" scope="page"/> 
			
					<form id="form" action="/goals/update" method="POST">		
						
						<div class="row"> 	 		
							<div class="form-group pt-3 col">
					   			<label for="Sbepw">Strength based exercises per week:</label>
					  			<input type="number" class="form-control" name="weeklyStrengthExercise" id="Sbepw" min="0" value="${goals.weeklyStrengthExercise}" required />     
					 		</div>
					 		
					 		<div class="form-group pt-3 col">
					   			<label for="Abepw">Aerobic exercises per week:</label>
					  			<input type="number" class="form-control" name="weeklyAerobicExercise" id="Abepw" min="0" value="${goals.weeklyAerobicExercise}" required />    
					 		</div>
				 		</div>
			 	  		
			 	  		<div class="pt-3">
			 	  			<button type="submit" value="Submit" class="btn btn-primary">Save changes</button>
			 	  		</div>
			 	  		
			 	  		<div class="pt-3">
			 	  			<a class="btn btn-primary" href="/goals/view" role="button">Discard changes</a>
				     	</div>
				    
				     </form>	 	
				    </div>
				   </div>
		    
		    <!-- The below HTML/EL/JSTL is for the Goals quiz. -->
		    <div class="col-12">
			    <div class="container py-3 border">
				    <h3> Goals recommendation quiz</h3>
					
					<form method="POST">
						<jsp:useBean class="eat3160.HLSP22.model.GoalsQuizBean" id="quiz" scope="page"/>
					
						<div class="form-group pt-3">
				   			<label for="questionOne"><c:out value="${quiz.questionOne}"/></label>
				  			<input type="number" class="form-control" name="questionOne" id="questionOne" min="0" required />     
				 		</div>
				 		
				 		<!-- Set this variable to act as a counter to be used to make the ID of each radio unique. -->
				 		<c:set var="count" value="0" scope="page"/>
				 		
				 		<div class="form-group pt-3">
				    		<label><c:out value="${quiz.questionTwo}"/></label>
				    		<c:forEach var="answer" items="${quiz.answersTwo}">
				    		
				    			<c:set var="count" value="${count + 1 }" scope="page"/>
					    		
					    		<div class="form-check">
					    		
					    		<!-- Check the first radio of the loop, based on whether the counter is equal to 1 (i.e., the first loop) -->
					    		<c:choose>
						    		<c:when test="${count eq 1}">
									  <input class="form-check-input" type="radio" value="${answer}" name="questionTwoRadio" id="question2Radio${count}" checked>
									</c:when>
									<c:otherwise>
									  <input class="form-check-input" type="radio" value="${answer}" name="questionTwoRadio" id="question2Radio${count}">
									</c:otherwise>
								</c:choose>
								  <label class="form-check-label" for="question2Radio${count}">
								    <c:out value="${answer}"/>
								  </label>
								</div>
							
							</c:forEach>
				  		</div>
				  		
				  		<div class="pt-3">
				  			<button type="submit" value="Submit" class="btn btn-primary">Submit</button>
				  		</div>
				  		
				  		<c:if test="${pageContext.request.method == 'POST'}">
				  			<c:set target="${quiz}" property="answerOne" value="${param.questionOne}"/>
				  			<c:set target="${quiz}" property="chosenQuestionTwoAnswer" value="${param.questionTwoRadio}"/>
				  			
				  			<!-- From the quiz response, set two variables - the aerobic goal, and the strength goal, for use -->
				  			<c:forEach var="entry" items="${quiz.response}">
				  						
				  				<c:if test="${entry.key eq 'Aerobic goal'}">
				  					<c:set var="aerobicGoal" value="${entry.value}" scope="page"/>
				  				</c:if>
				  				
				  				<c:if test="${entry.key eq 'Strength goal'}">
				  					<c:set var="strengthGoal" value="${entry.value}" scope="page"/>
				  				</c:if>
				  			
				  			</c:forEach>
				  		
					  		<c:choose>
						  		<c:when test="${empty quiz.response}">
						  			<p> All questions need to be answered for your goal recommendations! </p>
						  		</c:when>
						  		<c:otherwise>
						  		<div class="pt-3">
						  			<p class="fw-bold">Recommended goals:</p>
						  			<ul>					  			
							  			<li>Strength exercises per week: <c:out value="${strengthGoal}"/></li>
							  			<li>Aerobic exercise per week: <c:out value="${aerobicGoal}"/></li>
						  			</ul>
						  		</div>
						  		</c:otherwise>	  		
					  		</c:choose>	  		
				  		</c:if>
					</form>	
			     </div>
		  		</div>
		  	</div>
	</main>
		
	<jsp:include page="footer.jsp"/>
    
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.min.js" integrity="sha384-IDwe1+LCz02ROU9k972gdyvl+AESN10+x7tBKgc9I5HFtuNz0wWnPclzo6p9vxnk" crossorigin="anonymous"></script>
    
  </body>
</html>