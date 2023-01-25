<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
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
    
    <main role="main" class="flex-shrink-0 w-50 mx-auto my-auto">
	     <div class ="container py-3 border" >
			<h3> Exercise goals </h3>
			
			<!-- Store the java bean 'goals' from the request scope as a variable named 'goals'. --> 
			<c:set var="goals" value="${requestScope.goals}" scope="page"/> 
			
			<div class="row">
				<div class="form-group pt-3 col">
		 					<label for="Sbepw">Strength based exercises per week:</label>
							<input type="text" class="form-control" name="Sbepw" id="Sbepw" value="${goals.weeklyStrengthGoal}" readonly>     
					</div>
			
		 		<div class="form-group pt-3 col">
		   			<label for="Abepw">Aerobic exercises per week:</label>
		  			<input type="text" class="form-control" name="Abepwl" id="Abepwl" value="${goals.weeklyAerobicGoal}" readonly>    
		 		</div>
	 		</div>
	  			
	  		<div class="pt-3">
	  			<a class="btn btn-primary" href="/goals/edit" role="button">Edit goals</a>
	     	</div>
				    	 
		</div>
	</main>
	
	<jsp:include page="footer.jsp"/>
    
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.min.js" integrity="sha384-IDwe1+LCz02ROU9k972gdyvl+AESN10+x7tBKgc9I5HFtuNz0wWnPclzo6p9vxnk" crossorigin="anonymous"></script>
    
  </body>
</html>