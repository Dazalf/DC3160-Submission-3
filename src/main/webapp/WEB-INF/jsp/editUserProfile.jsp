<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html class="h-100">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>User profile</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script defer src="/jsscripts.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
</head>
  
  <body class="d-flex flex-column h-100">
  
   	 <jsp:include page="navigation.jsp"/>
    
     <main role="main" class="flex-shrink-0 my-auto">
	     <div class ="container py-3 border" >
			<h3> Edit your details </h3>
			
			 <!-- 	The controller will add an 'error' attribute to the request in the instance any errors were encountered on the server-side that stopped the
	    		creation of the new account. As it is an array, we'll loop through the errors and display them on the screen. -->
		   		
		   		<c:if test="${!empty UserProfileErrors}">
			   		<div class="alert alert-danger alert-dismissible" role="alert" data-bs-dismiss="alert">  
			   			<span class="fw-bold">Errors</span>
			   			<br>
			   			<ul>
				   			<c:forEach var="error" items="${UserProfileErrors}">
				   				<li><c:out value="${error}"/></li>
				   			</c:forEach>
			   			</ul>
			   			<c:remove var="UserProfileErrors" scope="session"/>
			   			<button class="btn-close" aria-label="close" data-bs-dismiss="alert"></button>
			   		</div>
			   	</c:if>
			   	
			<!-- Store the java bean 'user' from the request scope as a variable named 'user'. --> 
			<c:set var="user" value="${sessionScope.user}" scope="page"/> 
	
			<form id="form" action="/account/update" method="POST">
				
				<div class="row">
					<div class="form-group pt-3 col">
						<label for="firstname">First name</label>
						<input type="text" class="form-control" name="firstName" id="firstname" value="${user.firstName}" maxlength="45" required>    
					</div> 		
						 	 		
		 			<div class="form-group pt-3 col">
		     			<label for="surname">Surname</label>
		    			<input type="text" class="form-control" name="surname" id="surname" value="${user.surname}" maxlength="45" required>    
		 	 		</div>
	 	 		</div>
	 	 		
	 	 		<div class="row">
		 	 		<div class="form-group pt-3 col">
		     			<label for="dob">Date of birth</label>
		    			<input type="date" class="form-control" name="dateOfBirth" id="dob" value="${user.dateOfBirth}" onfocus="this.max=new Date().toISOString().split('T')[0]" required>
		 	 		</div>
		 	 		
		     		<div class="form-group pt-3 col">
		     			<label for="email">Email address</label>
		    			<input type="email" class="form-control" name="email" id="email" value="${user.email}" maxlength="45" 
		    			oninput="isEmailAvailableExcludingLoggedInUser(this.value);" required>
		    			<div id="emailAlert"></div>
		 	 		</div>
	 	 		</div>
	 	 		
	 	 		<div class="row">
		 	  		<div class="form-group pt-3 col">
		 	    		<label for="pwd">Password</label>
		 	    		<input type="password" class="form-control" name="password" id="pwd" value="${user.password}" maxlength="45" required>
		 	    		<div class="text-danger" id="pwdRegExError">  </div>
		 	  		</div>
		 	  		
		 	  		<div class="form-group pt-3 col">
			    		<label for="pwdCheck">Retype password</label>
			    		<input type="password" class="form-control" name="pwdCheck" id="pwdCheck" value="${user.password}" maxlength="45" required>
			    		<div class="text-danger" id="pwdMatchError">  </div>
			  		</div>
		  		</div>
		  		
		  		<div class="row">
			  		<div class="form-group pt-3 col">
		    			<label for="exp">What is your exercise experience?</label>
		   				<select class="form-control" name="exerciseExperience" id="exp">
		   					
		  					<!--  Below statements check whether the user bean value of exercise experience is equal to the condition. If true, set the
		  						  option as selected. Else, just use non-selected option.  -->
		   					<c:choose>
		   						<c:when test="${user.exerciseExperience eq 'None'}">
		   							<option value="None" selected>None</option>
		   						</c:when>
		   						<c:otherwise>
		   							<option value="None">None</option>
		   						</c:otherwise>
		   					</c:choose>
		   					
		   					<c:choose>
		   						<c:when test="${user.exerciseExperience eq 'Novice'}">
		   							<option value="Novice" selected>Novice</option>
		   						</c:when>
		   						<c:otherwise>
		   							<option value="Novice">Novice</option>
		   						</c:otherwise>
		   					</c:choose>
		   					
		   					<c:choose>
		   						<c:when test="${user.exerciseExperience eq 'Intermediate'}">
		   							<option value="Intermediate" selected>Intermediate</option>
		   						</c:when>
		   						<c:otherwise>
		   							<option value="Intermediate">Intermediate</option>
		   						</c:otherwise>
		   					</c:choose>
		   					
		   					<c:choose>
		   						<c:when test="${user.exerciseExperience eq 'Experienced'}">
		   							<option value="Experienced" selected>Experienced</option>
		   						</c:when>
		   						<c:otherwise>
		   							<option value="Experienced">Experienced</option>
		   						</c:otherwise>
		   					</c:choose>
		      					
			    		</select>
			 		</div>
			  		
			  		<!-- Create a javabean of the 'disabilites' class, which represents a list of disabilites a user my have. -->
					<jsp:useBean class="eat3160.HLSP22.model.DisabilitiesBean" id="disa" scope="page"/>
			  		
			  		<div class="form-group pt-3 col">
			    		<label for="disab">Do you have a disability? If yes, please specify</label>
			    		<select class="form-select" aria-label="Default select example" name="disability" id="disabSelection">
						 
						 <!-- If the user disability value equals N/A, then have this as selected. If not, leave unselected. -->
						 <c:choose> 
						 	<c:when test="${user.disability eq 'N/A'}"> 
						  		<option value="N/A" selected>No</option>
						  	</c:when>
						  	<c:otherwise>
						  		<option value="N/A">No</option>
						  	</c:otherwise>
						  </c:choose>			
						 
						 <!-- List all of the entries stored in the disabilites beans array list. -->
						  <c:forEach var="entry" items="${disa.disabilitesList}">
						  	<c:choose>
							  	<c:when test="${entry eq user.disability}">
							  		<option value="${entry}" selected><c:out value="${entry}"/></option>
							  	</c:when>
				   				<c:otherwise>
				   					<option value="${entry}"><c:out value="${entry}"/></option>
				   				</c:otherwise>
			   				</c:choose>
			   			  </c:forEach>		
						
						<option value="Other">Other (disability not listed)</option>
						
						</select>
			  		</div>
		  		</div>
	 	  		
	 	  		<div class="pt-3">
	 	  			<button type="submit" value="Submit" class="btn btn-primary">Save profile changes</button>
	 	  		</div>
	 	  		
	 	  		<div class="pt-3">
	 	  			<a class="btn btn-primary" href="/account/view" role="button">Discard changes</a>
		     	</div>
		     </form>	
		     
		     
		     <form id="deleteUserForm" action="/account/delete" method="POST">
		     	
		     	<!-- Button to trigger the delete account modal -->
				<div class="pt-3">
					<button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteAccountModal">
		 				 Delete account
					</button>
				</div>
				
				<!-- Deleting account modal -->
				<div class="modal fade" id="deleteAccountModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog modal-dialog-centered">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="exampleModalLabel">Delete account</h5>
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				        Are you sure you want to delete your account?
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No!</button>
				        <button type="submit" value="Submit" class="btn btn-primary" >Yes, please delete my account.</button>
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