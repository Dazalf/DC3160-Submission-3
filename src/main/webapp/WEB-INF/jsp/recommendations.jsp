<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html class="h-100">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Recommendations</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
  </head>
  <body class="d-flex flex-column h-100">
    
 
    <jsp:include page="navigation.jsp"/>
    
    <main role="main" class="flex-shrink-0 my-auto">
	     <div class="container py-3 border">
	     	
	     	<h3> Recommendations table</h3>
	     	<div class="table-responsive-sm pt-3">  
				<table class="table table-hover table-striped caption-top">
				  <thead class="table-light">
				    <tr>
				      <th scope="col">ID</th>
				      <th scope="col">Title</th>
				      <th scope="col">Link</th>
				    </tr>
				  </thead>
				  <tbody>
				    <tr>
				      <th scope="row">1</th>
				      <td>Article title 1</td>
				      <td>Link 1</td>
		
				    </tr>
				    <tr>
				      <th scope="row">2</th>
				      <td>Article title 2</td>
				      <td>Link 2</td>
	
				    </tr>
				    <tr>
				      <th scope="row">3</th>
				      <td>Article title 3</td>
				      <td>Link 3</td>
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