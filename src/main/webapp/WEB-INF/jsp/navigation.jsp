<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>


    
    <!-- IMPORTANT NOTE: The bootstrap scripts aren't linked in this file. This is because the below html will get its bootstrap 
    from the jsp pages they will be included in. This is necessary because if I leave the bootstrap in here this file, the dropdown 
    will not work on the pages this is included into. -->
    
   		<c:choose>
   			<c:when test="${sessionScope.loggedIn eq true}">
	   			<nav class="navbar navbar-expand-sm navbar-dark bg-dark px-3">
	   				<div class="container">
			    		<h1 class="navbar-brand"> HLSP </h1> 
			    		<button type="button" data-bs-toggle="collapse" data-bs-target="#navbarC" class="navbar-toggler" aria-controls="navbarC" aria-expanded="false" aria-label="Toggle navigation">
			    			<span class="navbar-toggler-icon"></span>
			    		</button>
				    	<div class="collapse navbar-collapse" id="navbarC"> 
				    		 <ul class="navbar-nav">
				    		 	<li class="nav-item">
			          				<a class="nav-link active" href="/homepage">About us</a>
			       				</li>
			       				<li class="nav-item">
			          				<a class="nav-link active" href="/exercises/view">Exercises</a>
			       				</li>
			       				<li class="nav-item">
			          				<a class="nav-link active" href="/goals/view">Goals</a>
			       				</li>
			       				<li class="nav-item">
			          				<a class="nav-link active" href="/recommendations">Recommendations</a>
			       				</li>
			       				<li class="nav-item">
			          				<a class="nav-link active" href="/reports">Reports</a>
			       				</li>
			       			</ul>
							<ul class="navbar-nav ms-auto">
			       				<li class="nav-item dropdown">
			          				<a class="nav-link dropdown-toggle" href="#" id="nbDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
			          					Profile
			          				</a>
			          				<ul class="dropdown-menu dropdown-menu-end" aria-labelledby="nbDropdown">
			          					<li> <a class="dropdown-item" href="/account/view">Details</a> </li>
			          					<li> <a class="dropdown-item" href="/logout">Log out</a> </li>
			          				</ul>
			       				</li>
			       			</ul>	
				    	</div>
			    	</div>
		    	</nav>
   			</c:when>
   			<c:otherwise>
	   			<nav class="navbar navbar-expand-sm navbar-dark bg-dark px-3">
	   				<div class="container">
				    		<h1 class="navbar-brand"> HLSP </h1> 
				    		<button type="button" data-bs-toggle="collapse" data-bs-target="#navbarC" class="navbar-toggler" aria-controls="navbarC" aria-expanded="false" aria-label="Toggle navigation">
				    			<span class="navbar-toggler-icon"></span>
				    		</button>
					    	<div class="collapse navbar-collapse" id="navbarC"> 
					    		 <ul class="navbar-nav">
					    		 	<li class="nav-item">
				          				<a class="nav-link active" href="/homepage">About us</a>
				       				</li>
				       			</ul>
				       			<ul class="navbar-nav ms-auto">
				       				<li class="nav-item">
				          				<a class="nav-link active" href="/loginform">Log in</a>
				       				</li>
				       			</ul>		
					    	</div>
				    	</div>
			    	</nav>
   			</c:otherwise>
   		</c:choose>
    