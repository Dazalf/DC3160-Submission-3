<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html class="h-100">
   <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sign up</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script defer src="/jsscripts.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
  </head>
  
  <body class="d-flex flex-column h-100"> 
 
   <jsp:include page="navigation.jsp"/>
    
    <main role="main" class="flex-shrink-0 w-50 mx-auto my-auto">
	    <div class ="container py-3 border">
	    	
	    	<form id="form" action="/registration" method="POST">
	    	
	    		 <h3> Sign up form </h3>
	    		
	    <!-- 	The controller will add an 'error' attribute to the request in the instance any errors were encountered on the server-side that stopped the
	    		creation of the new account. As it is an array, we'll loop through the errors and display them on the screen. -->
		   		
		   		<c:if test="${!empty errors}"> 
			   		<div class="alert alert-danger alert-dismissible" role="alert" data-bs-dismiss="alert">  
			   			<span class="fw-bold">Errors</span>
			   			<br>
			   			<ul>
				   			<c:forEach var="error" items="${errors}">
				   				<li><c:out value="${error}"/></li>
				   			</c:forEach>
			   			</ul>
			   			<button class="btn-close" aria-label="close" data-bs-dismiss="alert"></button>
			   		</div>
			   	</c:if>
		 		<div class="row">
			 		<div class="form-group pt-3 col">
		    			<label for="firstname">First name</label>
		   				<input type="text" class="form-control" name="firstName" id="firstname" placeholder="Input first name" maxlength="45" required>
			 		</div>
			 		
			 		<div class="form-group pt-3 col">
		    			<label for="surname">Surname</label>
		   				<input type="text" class="form-control" name="surname" id="surname" placeholder="Input surname" maxlength="45" required>
			 		</div>
		 		</div>
		 		
		 		<div class="form-group pt-3">
	    			<label for="dob">Date of birth</label>
	    		
	<!--     		The onfocus, and accompanying code, is used to ensure that a user cannot input a date in the future. 
					The inspiration for this code is taken from the following stackoverflow article: https://stackoverflow.com/questions/32378590/set-date-input-fields-max-date-to-today -->
	   				<input type="date" class="form-control" name="dateOfBirth" id="dob" placeholder="Input date of birth" onfocus="this.max=new Date().toISOString().split('T')[0]" required>
		 		
		 	</div>
		 		
	    		<div class="form-group pt-3">
	    			<label for="email">Email address</label>
	   				<input type="email" class="form-control" name="email" id="email" placeholder="Input email" maxlength="45" 
	   				oninput="isEmailAvailable(this.value);" required>
	   				<div id="emailAlert"></div>
		 		</div>
		 		
		 		<div class="row">
			  		<div class="form-group pt-3 col">
			    		<label for="pwd">Password</label>
			    		<input type="password" class="form-control" name="password" id="pwd" placeholder="Password" maxlength="45" required>
			    		<div class="text-danger" id="pwdRegExError">  </div>
			  		</div>
			  		
			  		<div class="form-group pt-3 col">
			    		<label for="pwdCheck">Retype password</label>
			    		<input type="password" class="form-control" name="pwdCheck" id="pwdCheck" placeholder="Password" maxlength="45" required>
			    		<div class="text-danger" id="pwdMatchError">  </div>
			  		</div>
		  		</div>
		  		
		  		<div class="form-group pt-3">
	    			<label for="exp">What is your exercise experience?</label>
	   				<select class="form-control" name="exerciseExperience" id="exp">
	   					<option value="None">None</option>
	   					<option value="Novice">Novice</option>
	      				<option value="Intermediate">Intermediate</option>
	      				<option value="Experienced">Experienced</option>	
		    		</select>
		 		</div>
		  		
		  		<!-- Create a javabean of the 'disabilites' class, which represents a list of disabilites a user my have. -->
				<jsp:useBean class="eat3160.HLSP22.model.DisabilitiesBean" id="disa" scope="page"/>
		  		
		  		<div class="form-group pt-3">
		    		<label for="disab">Do you have a disability? If yes, please specify</label>
		    		<select class="form-select" aria-label="Default select example" name="disability" id="disabSelection">
					  
					  <option value="N/A">No</option>
	 			  
	<!--			List all of the entries stored in the disabilites beans array list. -->
					  <c:forEach var="entry" items="${disa.disabilitesList}">
		   				<option value="${entry}"><c:out value="${entry}"/></option>
		   			  </c:forEach>
					
					<option value="Other"> Other (disability not listed)</option>
					
					</select>
		  		</div>
		  		
		  		<div class="pt-3">
		  			<button type="submit" value="Submit" class="btn btn-primary">Submit</button>
		  		</div>
		  		
		  		<div class="pt-3">
			  		<p> Already have an account? </p>
			  		<a class="btn btn-primary" href="/loginform"> Log in </a> 
		  		</div>
	    	</form>
	    	
	    </div>
	   </main>
    
    <jsp:include page="footer.jsp"/>
    
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.min.js" integrity="sha384-IDwe1+LCz02ROU9k972gdyvl+AESN10+x7tBKgc9I5HFtuNz0wWnPclzo6p9vxnk" crossorigin="anonymous"></script>
    
  </body>
</html>