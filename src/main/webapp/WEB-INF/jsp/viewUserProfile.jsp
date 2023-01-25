<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html class="h-100">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>User profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
</head>
  
  <body class="d-flex flex-column h-100">
  
   	 <jsp:include page="navigation.jsp"/>
    
     <main role="main" class="flex-shrink-0 my-auto">
	     <div class ="container py-3 border" >
			<h3> View your details </h3>
			
	<!--    This shows a message letting the user know their account has been created successfully. The session attribute is created -->
	<!--    by the Account controller, who redirected the user here after a successful account creation. -->
	    	<c:if test = "${SuccessfulProfileUpdate eq true}">
	    		<div class="alert alert-success alert-dismissible" role="alert" data-bs-dismiss="alert"> 
	    			Profile update has been successful! 
	    			<c:remove var="SuccessfulProfileUpdate" scope="session"/>
	    			<button class="btn-close" aria-label="close" data-bs-dismiss="alert"></button>
	    		</div>
	    	</c:if>
			
			<!-- Store the java bean 'user' from the request scope as a variable named 'user'. --> 
			<c:set var="user" value="${sessionScope.user}" scope="page"/>
			 
			 <!-- Using the bean getter methods, display the users data within the view -->
			<div class="row">
				<div class="form-group pt-3 col">
					<label for="firstname">First name</label>
					<input type="text" class="form-control" name="fn" id="firstname" value="${user.firstName}" readonly>    
				</div> 		
					 	 		
				<div class="form-group pt-3 col">
		   			<label for="surname">Surname</label>
		  			<input type="text" class="form-control" name="sn" id="surname" value="${user.surname}" readonly>    
		 		</div>
	 		</div>
	 		
	 		<div class="row">
		 		<div class="form-group pt-3 col">
		   			<label for="dob">Date of birth</label>
		  			<input type="date" class="form-control" name="dob" id="dob" value="${user.dateOfBirth}" readonly>
		 		</div>
		 		
		   		<div class="form-group pt-3 col">
		   			<label for="email">Email address</label>
		  			<input type="email" class="form-control" name="email" id="email" value="${user.email}" readonly>
		 		</div>
	 		</div>
	 		
	 		<div class="row">
		  		<div class="form-group pt-3 col">
		   			<label for="exp">Exercise experience</label>	
		    		<input type="text" class="form-control" name="exp" id="exp" value="${user.exerciseExperience}" readonly> 
		 		</div>
		  		
		  		<div class="form-group pt-3 col">
		    		<label for="disab">Disability</label>
		    		<input type="text" class="form-control" name="disab" id="disabSelection" value="${user.disability}" readonly> 
		 		</div>
	 		</div>
	 		
	  		<div class="form-group pt-3">
	    		<label for="pwd">Password</label>
	    		<input type="password" class="form-control" name="pwd" id="pwd" value="${user.password}" readonly>
	  		</div>
	  			
	  		<div class="pt-3">
	  			<a class="btn btn-primary" href="/account/edit" role="button">Edit profile</a>
	     	</div>
		     	 
		</div>
	</main>
	
	<jsp:include page="footer.jsp"/>
    
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.min.js" integrity="sha384-IDwe1+LCz02ROU9k972gdyvl+AESN10+x7tBKgc9I5HFtuNz0wWnPclzo6p9vxnk" crossorigin="anonymous"></script>
    
  </body>
</html>