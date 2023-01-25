/**
 * This javascripts file contains the AJAX script(s) for updating the aerobic and strength exercising tables for when the date control is changed. 
 */

function updateTables(date){
	updateAerobicTable(date);
	updateStrengthTable(date);
}

function updateAerobicTable(date) {
	
	console.log(date);
	
	jQuery.post("/exercises/updateAerobicTable", {"date": date}, processAerobicTableResponse);
}


function processAerobicTableResponse(data) {
      
      //Clear the table of current data, i.e., rows appended from a previous AJAX request.
      jQuery('#aerobicDisplay').html(" ");
      
      //Loop through the JSON array sent by the server, and add a row for each into the table. 
      data.forEach(function(i){
			jQuery('#aerobicDisplay').append
			(	
			    '<tr>'
				+ '<td>' + i.exerciseName + '</td>'
				+ '<td>' + i.location + '</td>'
				+ '<td>' + i.steps + '</td>'
				+ '<td>' + i.favourite + '</td>'
				+ '<td><a class="btn btn-secondary btn-sm" href="/exercises/edit/aerobic_exercise?id=' + i.id + '" role="button">Edit</a></td>'
			 + "</tr>"				
			);
		});				 	
}

function updateStrengthTable(date) {
	
	console.log(date);
	
	jQuery.post("/exercises/updateStrengthTable", {"date": date}, processStrengthTableResponse);
}


function processStrengthTableResponse(data) {
      
      //Clear the table of current data, i.e., rows appended from a previous AJAX request, or data that exists upon loading of the page.
      jQuery('#strengthDisplay').html(" ");
      
      //Loop through the JSON array sent by the server, and add a row for each into the table. 
      data.forEach(function(i){
			jQuery('#strengthDisplay').append
			(	
			    "<tr>"
				+ "<td>" + i.exerciseName + "</td>"
				+ "<td>" + i.location + "</td>"
				+ "<td>" + i.muscleGroup + "</td>"
				+ "<td>" + i.sets + "</td>"
				+ "<td>" + i.reps + "</td>"
				+ "<td>" + i.favourite + "</td>"
				+ '<td><a class="btn btn-secondary btn-sm" href="/exercises/edit/strength_exercise?id=' + i.id + '" role="button">Edit</a></td>'
			 + "</tr>"				
			);
		});				 	
}
