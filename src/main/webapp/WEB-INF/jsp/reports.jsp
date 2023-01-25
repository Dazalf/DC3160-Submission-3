<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html class="h-100">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Reports</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
  </head>
  <body class="d-flex flex-column h-100">

    <jsp:include page="navigation.jsp"/>
    
    <main role="main" class="flex-shrink-0 my-auto">
	    <div class ="container pt-3 border">
	     
		  	<form action="#">
			  	<label for="selectGraph"> Choose a graph: </label>
			  	<select name="selectGraph" id="selectGraph" class="form-select" aria-label="Select graph type"> 
			  		<option value="strength">Number of strength-training exercises completed</option>
			  		<option value="aerobic">Number of aerobic exercises completed</option>
			  		<option value="exercises">Number of exercises completed</option>
			  		<option value="exercisegoal">Number of exercises completed against the set goal over the most recent week</option>
			  	</select>
			  	
				<label class="pt-3" for="selectPeriod"> Choose time period: </label>
			  	<select name="selectPeriod" id="selectPeriod" class="form-select" aria-label="Select graph period"> 
			  		<option value="7days">Last 7 days</option>
			  		<option value="30days">Last 30 days</option>
			  	</select>
		  	</form>
		     
		    <p class="pt-3"> WIP: Visualizations of inputted data, i.e., charts, will be here. </p> 	
	    	
	    </div>
	</main>
    
    <jsp:include page="footer.jsp"/>
    
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.min.js" integrity="sha384-IDwe1+LCz02ROU9k972gdyvl+AESN10+x7tBKgc9I5HFtuNz0wWnPclzo6p9vxnk" crossorigin="anonymous"></script>
    
  </body>
</html>