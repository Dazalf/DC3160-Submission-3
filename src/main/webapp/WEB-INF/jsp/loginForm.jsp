<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html class="h-100">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Log in</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
  </head>
  <body class="d-flex flex-column h-100">
    
    
    <jsp:include page="navigation.jsp"/>
    
    <main role="main" class="flex-shrink-0 w-50 mx-auto my-auto">
	    <div class="container pt-3 border">
	    
		    <h3> Log in form </h3>
		    
	<!-- 	This div will show an error message. The '${error}' accesses the request scope and checks if an error attribute has been set. 
			If yes, it'll be displayed here. If not, nothing will show as it'll be null. -->
		    <c:if test="${!empty requestScope.error}">
		    	<div class="alert alert-danger alert-dismissible" role="alert"> 
		    		${error} 
		    		<button class="btn-close" aria-label="close" data-bs-dismiss="alert"></button>
		    	</div>
		    </c:if>
	    	
	<!--    This shows a message letting the user know their account has been created successfully. The session attribute is created 
		    by the Registration controller, who redirected the user here after a successful account creation. -->
	    	<c:if test = "${sessionScope.SuccessfulRegistration eq true}">
	    		<div class="alert alert-success alert-dismissible" role="alert"> 
	    			Account creation has been successful! Please log in. 
	    			<button class="btn-close" aria-label="close" data-bs-dismiss="alert"></button>
	    		</div>  		
	<!-- 		The session attribute is no longer required, so remove from the session just to ensure there are no unused attributes sitting around. -->
	    		<c:remove var="SuccessfulRegistration" scope="session"/>
	    	</c:if>
	    	
	    	<form action="/login" method="POST">
	    		
	    		<div class="row pt-3">
		    		<div class="form-group col">
		    			<label for="email">Email address</label>
		   				<input type="email" class="form-control" name="email" id="email" placeholder="Email" maxlength="45" required>
			 		</div>
			 		
			  		<div class="form-group col">
			  			<label for="pwd">Password</label>
			    		<input type="password" class="form-control" name="password" id="pwd" placeholder="Password" maxlength="45" required>
			  		</div>
		  		</div>
		  		<div class="pt-3">
		  			<button type="submit" value="Submit" class="btn btn-primary">Submit</button>
		  		</div>
		  		
		  		<div class="py-3">  
			  		<p> Don't have an account? </p>
			  		<a class="btn btn-primary" href="/signupform"> Sign up </a>  
		  		</div>
	    	</form>    	
	    </div>
	   </main>
    
    <jsp:include page="footer.jsp"/>
    
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.min.js" integrity="sha384-IDwe1+LCz02ROU9k972gdyvl+AESN10+x7tBKgc9I5HFtuNz0wWnPclzo6p9vxnk" crossorigin="anonymous"></script>
    
  </body>
</html>