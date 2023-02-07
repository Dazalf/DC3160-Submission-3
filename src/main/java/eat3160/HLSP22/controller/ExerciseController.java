package eat3160.HLSP22.controller;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.gson.Gson;

import eat3160.HLSP22.model.AerobicExerciseBean;
import eat3160.HLSP22.model.AerobicExerciseEntity;
import eat3160.HLSP22.model.AerobicExercises;
import eat3160.HLSP22.model.StrengthExerciseBean;
import eat3160.HLSP22.model.StrengthExerciseEntity;
import eat3160.HLSP22.model.StrengthExercises;
import eat3160.HLSP22.service.AerobicExerciseService;
import eat3160.HLSP22.service.GoalsService;
import eat3160.HLSP22.service.StrengthExerciseService;


/**
 * This controller handles requests related to the CRUD functionality of exercises. 
 * @author alf-s
 */

@RequestMapping("/exercises*")
@Controller
@SessionAttributes("todaysDate")
public class ExerciseController {
	
	@Autowired
	private StrengthExerciseService strengthExerciseService;
	
	@Autowired
	private AerobicExerciseService aerobicExerciseService;
	
	@ModelAttribute("todaysDate")
	public LocalDate createDateObject() {
		LocalDate todaysDate = LocalDate.now();
		return todaysDate;
	}
	
	@RequestMapping("/view")
	public String viewExercises(HttpSession session, HttpServletResponse response, HttpServletRequest request, Model model, 
			@ModelAttribute("todaysDate") LocalDate todaysDate) 
			throws Exception {
		
		if(session.getAttribute("loggedIn") == null || (boolean)session.getAttribute("loggedIn") == false) {
			//If the user isn't logged in, send an 403 error and return null as the 'view'. This ensures the user cannot access
			//the view if they are already logged in. 
			response.sendError(403);
			return null;
		}else {
			
			int userid = (Integer)session.getAttribute("userID");
			
			//UPDATED -> WORKING
			model.addAttribute("strengthExercises", strengthExerciseService.findAllByUserIdAndDate(userid, todaysDate.toString()));
			
			//UPDATED -> working

			model.addAttribute("aerobicExercises", aerobicExerciseService.findAllByUserIdAndDate(userid, todaysDate.toString()));
			
			return "viewExercises";
		}	
	}
	
	@RequestMapping("/form/aerobic_exercise")
	public String viewAerobicExerciseForm(HttpSession session, HttpServletResponse response, HttpServletRequest request,
			@ModelAttribute("todaysDate") LocalDate todaysDate, Model model) 
			throws Exception {
		
		if(session.getAttribute("loggedIn") == null || (boolean)session.getAttribute("loggedIn") == false) {
			//If the user isn't logged in, send an 403 error and return null as the 'view'. This ensures the user cannot access
			//the view if they are already logged in. 
			response.sendError(403);
			return null;
		}else {
			int userid = (Integer)session.getAttribute("userID");
			model.addAttribute("favouriteExercises", aerobicExerciseService.findFavouritesByUserId(userid));
			model.addAttribute("recentExercises", aerobicExerciseService.findRecentExercisesByUserId(userid));
			
			model.addAttribute("exercise", new AerobicExerciseEntity());
			
			return "aerobicExerciseForm";
		}	
	}
	
	@RequestMapping("/form/aerobic_exercise/load_exercise")
	public String viewAerobicExerciseFormWithLoadedExercise(HttpSession session, HttpServletResponse response, HttpServletRequest request,
			@ModelAttribute("todaysDate") LocalDate todaysDate, Model model, @RequestParam int id) 
			throws Exception {
		
		if(session.getAttribute("loggedIn") == null || (boolean)session.getAttribute("loggedIn") == false) {
			//If the user isn't logged in, send an 403 error and return null as the 'view'. This ensures the user cannot access
			//the view if they are already logged in. 
			response.sendError(403);
			return null;
		}else {
			int userid = (Integer)session.getAttribute("userID");
			model.addAttribute("favouriteExercises", aerobicExerciseService.findFavouritesByUserId(userid));
			model.addAttribute("recentExercises", aerobicExerciseService.findRecentExercisesByUserId(userid));
			
			model.addAttribute("exercise", aerobicExerciseService.findByUserIdAndId(id, userid));
			
			return "aerobicExerciseForm";
		}	
	}
	
	@RequestMapping("/form/strength_exercise")
	public String viewStrengthExerciseForm(HttpSession session, HttpServletResponse response, HttpServletRequest request,
			@ModelAttribute("todaysDate") LocalDate todaysDate, Model model) 
			throws Exception {
		
		if(session.getAttribute("loggedIn") == null || (boolean)session.getAttribute("loggedIn") == false) {
			//If the user isn't logged in, send an 403 error and return null as the 'view'. This ensures the user cannot access
			//the view if they are already logged in. 
			response.sendError(403);
			return null;
		}else {
			int userid = (Integer)session.getAttribute("userID");
			model.addAttribute("favouriteExercises", strengthExerciseService.findFavouritesByUserId(userid));
			model.addAttribute("recentExercises", strengthExerciseService.findRecentExercisesByUserId(userid));
			
			model.addAttribute("exercise", new StrengthExerciseEntity());
			
			return "strengthTrainingForm";
		}	
	}
	
	@RequestMapping("/form/strength_exercise/load_exercise")
	public String viewStrengthExerciseFormWithLoadedExercise(HttpSession session, HttpServletResponse response, HttpServletRequest request,
			@ModelAttribute("todaysDate") LocalDate todaysDate, Model model, @RequestParam int id) 
			throws Exception {
		
		if(session.getAttribute("loggedIn") == null || (boolean)session.getAttribute("loggedIn") == false) {
			//If the user isn't logged in, send an 403 error and return null as the 'view'. This ensures the user cannot access
			//the view if they are already logged in. 
			response.sendError(403);
			return null;
		}else {
			int userid = (Integer)session.getAttribute("userID");
			model.addAttribute("favouriteExercises", strengthExerciseService.findFavouritesByUserId(userid));
			model.addAttribute("recentExercises", strengthExerciseService.findRecentExercisesByUserId(userid));
			
			model.addAttribute("exercise", strengthExerciseService.findByUserIdAndId(id, userid));
			
			return "strengthTrainingForm";
		}	
	}
	
	
	//Add exercises
	
	@RequestMapping(value = "/add/aerobic_exercise", method = RequestMethod.POST)
	public String addAerobicExercise(HttpSession session, HttpServletResponse response, @RequestParam String exerciseName, @RequestParam String location,
			@RequestParam(defaultValue = "0") int steps, @RequestParam String dateOfExercise, @RequestParam(defaultValue = "0") String favourite) 
			throws Exception {
		
		if(session.getAttribute("loggedIn") == null || (boolean)session.getAttribute("loggedIn") == false) {
			//If the user isn't logged in, send an 403 error and return null as the 'view'. This ensures the user cannot access
			//the view if they are already logged in. 
			response.sendError(403);
			return null;
		}else {
			
			//To note: the @ModelAttribute approach of having the request parameters automatically assigned to a bean object wasn't used here
			//as there was a requirement to add default values and perform checks on the request parameters, which the @MA approach doesn't
			//enable, hence the use of @RequestParams in the method parameters. 
			
			boolean favouriteBoolean = false;
			//If favourite contains 0, the default value in the scenario of a null return, it means that this exercise wasn't favourited.
			//Hence, set as false. Otherwise, if not null, set as true, as it was favourited. 
			if(favourite.contains("0")) {
				favouriteBoolean = false;
			}else {
				favouriteBoolean = true;
			}
			
			//UPDATED -> working
			
			AerobicExerciseEntity s = new AerobicExerciseEntity();
			int userid = (Integer)session.getAttribute("userID");
			
			s.setUserID(userid);
			s.setExerciseName(exerciseName);
			s.setLocation(location);
			s.setSteps(steps);
			s.setDateOfExercise(dateOfExercise);
			s.setFavourite(favouriteBoolean);
    		
			aerobicExerciseService.save(s);
    		
    		//Add an attribute to the request to let them exercise page know there was a successful exercise creation. 
    		session.setAttribute("SuccessfulExerciseCreation", "Aerobic exercise successfully created");
			
			return "redirect:/exercises/view";
		}	
	}
	
	@RequestMapping(value = "/add/strength_exercise", method = RequestMethod.POST)
	public String addStrengthExercise(HttpSession session, HttpServletResponse response, @RequestParam String exerciseName, @RequestParam String location,
			@RequestParam String muscleGroup, @RequestParam(defaultValue = "0") int sets, @RequestParam(defaultValue = "0") int reps, 
			@RequestParam String dateOfExercise, @RequestParam(defaultValue = "0") String favourite) 
			throws Exception {
		
		if(session.getAttribute("loggedIn") == null || (boolean)session.getAttribute("loggedIn") == false) {
			//If the user isn't logged in, send an 403 error and return null as the 'view'. This ensures the user cannot access
			//the view if they are already logged in. 
			response.sendError(403);
			return null;
		}else {
			
			//To note: the @ModelAttribute approach of having the request parameters automatically assigned to a bean object wasn't used here
			//as there was a requirement to add default values and perform checks on the request parameters, which the @MA approach doesn't
			//enable, hence the use of @RequestParams in the method parameters. 
			
			boolean favouriteBoolean = false;
			//If favourite contains 0, the default value in the scenario of a null return, it means that this exercise wasn't favourited.
			//Hence, set as false. Otherwise, if not null, set as true, as it was favourited. 
			if(favourite.contains("0")) {
				favouriteBoolean = false;
			}else {
				favouriteBoolean = true;
			}
			
			//UPDATED -> Working
			
			StrengthExerciseEntity s = new StrengthExerciseEntity();
			int userid = (Integer)session.getAttribute("userID");
			
			s.setUserID(userid);
			s.setExerciseName(exerciseName);
			s.setLocation(location);
			s.setMuscleGroup(muscleGroup);
			s.setSets(sets);
			s.setReps(reps);
			s.setDateOfExercise(dateOfExercise);
			s.setFavourite(favouriteBoolean);
    		
			strengthExerciseService.save(s);
			
    		//Add an attribute to the request to let them exercise page know there was a successful exercise creation. 
    		session.setAttribute("SuccessfulExerciseCreation", "Strength exercise successfully created");
			
			return "redirect:/exercises/view";
		}	
	}
	
	//View exercise edit forms
	
	@RequestMapping("/edit/aerobic_exercise")
	public String editAerobicExercise(HttpSession session, HttpServletResponse response, Model model, @RequestParam int id) 
			throws Exception {
		
		if(session.getAttribute("loggedIn") == null || (boolean)session.getAttribute("loggedIn") == false) {
			//If the user isn't logged in, send an 403 error and return null as the 'view'. This ensures the user cannot access
			//the view if they are already logged in. 
			response.sendError(403);
			return null;
		}else {
			
			//UPDATED -> working
			
			int userid = (Integer)session.getAttribute("userID");
			//Get the entity of this record from the model. 
			AerobicExerciseEntity aeb = aerobicExerciseService.findByUserIdAndId(id, userid);;
    		
    		//Check if null. If null, redirect to the exercises page. If not null, respond to client with a view of the exercise. 
    		if(aeb != null) {
    			model.addAttribute("exercise", aeb);
	    		return "editAerobicExercise";
    		}else {
    			return "redirect:/exercises/view";
    		}
		}	
	}
	
	@RequestMapping("/edit/strength_exercise")
	public String editStrengthExercise(HttpSession session, HttpServletResponse response, Model model, @RequestParam int id) 
			throws Exception {
		
		if(session.getAttribute("loggedIn") == null || (boolean)session.getAttribute("loggedIn") == false) {
			//If the user isn't logged in, send an 403 error and return null as the 'view'. This ensures the user cannot access
			//the view if they are already logged in. 
			response.sendError(403);
			return null;
		}else {
			
			
			//UPDATED -> Working 
			
			int userid = (Integer)session.getAttribute("userID");
			//Get the entity of this record from the model. 
			StrengthExerciseEntity seb = strengthExerciseService.findByUserIdAndId(id, userid);

    		
    		//Check if null. If null, redirect to the exercises page. If not null, respond to client with a view of the exercise. 
    		if(seb != null) {
    			model.addAttribute("exercise", seb);
	    		return "editStrengthExercise";
    		}else {
    			return "redirect:/exercises/view";
    		}
		}	
	}
	
	//Update exercises
	
	@RequestMapping(value = "/update/aerobic_exercise", method = RequestMethod.POST)
	public String updateAerobicExercise(HttpSession session, HttpServletResponse response, @RequestParam String exerciseName, @RequestParam String location,
			@RequestParam(defaultValue = "0") int steps, @RequestParam String dateOfExercise, @RequestParam(defaultValue = "0") String favourite,
			@RequestParam int exerciseID) 
			throws Exception {
		
		if(session.getAttribute("loggedIn") == null || (boolean)session.getAttribute("loggedIn") == false) {
			//If the user isn't logged in, send an 403 error and return null as the 'view'. This ensures the user cannot access
			//the view if they are already logged in. 
			response.sendError(403);
			return null;
		}else {
			
			//To note: the @ModelAttribute approach of having the request parameters automatically assigned to a bean object wasn't used here
			//as there was a requirement to add default values and perform checks on the request parameters, which the @MA approach doesn't
			//enable, hence the use of @RequestParams in the method parameters. 
			
			boolean favouriteBoolean = false;
			//If favourite contains 0, the default value in the scenario of a null return, it means that this exercise wasn't favourited.
			//Hence, set as false. Otherwise, if not null, set as true, as it was favourited. 
			if(favourite.contains("0")) {
				favouriteBoolean = false;
			}else {
				favouriteBoolean = true;
			}
			
			//UPDATED -> working
			
			AerobicExerciseEntity s = new AerobicExerciseEntity();
			int userid = (Integer)session.getAttribute("userID");
			
			s.setId(exerciseID);
			s.setUserID(userid);
			s.setExerciseName(exerciseName);
			s.setLocation(location);
			s.setSteps(steps);
			s.setDateOfExercise(dateOfExercise);
			s.setFavourite(favouriteBoolean);
    		
			aerobicExerciseService.save(s);
    		
    		//Add an attribute to the request to let them exercise page know there was a successful exercise creation. 
    		session.setAttribute("SuccessfulExerciseUpdate", "Aerobic exercise updated successfully!");
			
			return "redirect:/exercises/view";
		}	
	}
	
	@RequestMapping(value = "/update/strength_exercise", method = RequestMethod.POST)
	public String updateStrengthExercise(HttpSession session, HttpServletResponse response, @RequestParam String exerciseName, @RequestParam String location,
			@RequestParam String muscleGroup, @RequestParam(defaultValue = "0") int sets, @RequestParam(defaultValue = "0") int reps, 
			@RequestParam String dateOfExercise, @RequestParam(defaultValue = "0") String favourite, @RequestParam int exerciseID) 
			throws Exception {
		
		if(session.getAttribute("loggedIn") == null || (boolean)session.getAttribute("loggedIn") == false) {
			//If the user isn't logged in, send an 403 error and return null as the 'view'. This ensures the user cannot access
			//the view if they are already logged in. 
			response.sendError(403);
			return null;
		}else {
			
			//To note: the @ModelAttribute approach of having the request parameters automatically assigned to a bean object wasn't used here
			//as there was a requirement to add default values and perform checks on the request parameters, which the @MA approach doesn't
			//enable, hence the use of @RequestParams in the method parameters. 
			
			boolean favouriteBoolean = false;
			//If favourite contains 0, the default value in the scenario of a null return, it means that this exercise wasn't favourited.
			//Hence, set as false. Otherwise, if not null, set as true, as it was favourited. 
			if(favourite.contains("0")) {
				favouriteBoolean = false;
			}else {
				favouriteBoolean = true;
			}
			
			//UPDATED -> Working
			
			StrengthExerciseEntity s = new StrengthExerciseEntity();
			int userid = (Integer)session.getAttribute("userID");
			
			s.setId(exerciseID);
			s.setUserID(userid);
			s.setExerciseName(exerciseName);
			s.setLocation(location);
			s.setMuscleGroup(muscleGroup);
			s.setSets(sets);
			s.setReps(reps);
			s.setDateOfExercise(dateOfExercise);
			s.setFavourite(favouriteBoolean);
    		
			strengthExerciseService.save(s);
    		
    		//Add an attribute to the request to let them exercise page know there was a successful exercise creation. 
    		session.setAttribute("SuccessfulExerciseUpdate", "Strength exercise updated successfully!");
			
			return "redirect:/exercises/view";
		}	
	}	
	
	//Delete exercise
	
	@RequestMapping(value = "/delete/aerobic_exercise", method = RequestMethod.POST)
	public String deleteAerobicExercise(HttpSession session, HttpServletResponse response, @RequestParam int exerciseID) 
			throws Exception {
		
		if(session.getAttribute("loggedIn") == null || (boolean)session.getAttribute("loggedIn") == false) {
			//If the user isn't logged in, send an 403 error and return null as the 'view'. This ensures the user cannot access
			//the view if they are already logged in. 
			response.sendError(403);
			return null;
		}else {
			
			//UPDATED -> working
			
			int userid = (Integer)session.getAttribute("userID");
			
			//Get the entity of this record from the model. 
			AerobicExerciseEntity aeb = aerobicExerciseService.findByUserIdAndId(exerciseID, userid);
			
			//Check if null. If null, redirect to the exercises page. If not null, delete the exercise and return view. 
			if(aeb != null) {
				
				aerobicExerciseService.delete(exerciseID);	
	    		//Add an attribute to the request to let them exercise page know there was a successful exercise creation. 
	    		session.setAttribute("SuccessfulExerciseDeletion", "Aerobic exercise deleted successfully!");
	    		
	    		return "redirect:/exercises/view";
	    		
			}else {
				return "redirect:/exercises/view";
			}

		}	
	}
	
	@RequestMapping(value = "/delete/strength_exercise", method = RequestMethod.POST)
	public String deleteStrengthExercise(HttpSession session, HttpServletResponse response, @RequestParam int exerciseID) 
			throws Exception {
		
		if(session.getAttribute("loggedIn") == null || (boolean)session.getAttribute("loggedIn") == false) {
			//If the user isn't logged in, send an 403 error and return null as the 'view'. This ensures the user cannot access
			//the view if they are already logged in. 
			response.sendError(403);
			return null;
		}else {
			
			//UPDATED -> Working
			
			int userid = (Integer)session.getAttribute("userID");
			//Get the entity of this record from the model. 
			StrengthExerciseEntity seb = strengthExerciseService.findByUserIdAndId(exerciseID, userid);

    		
    		//Check if null. If null, redirect to the exercises page. If not null, delete the exercise and return view. 
    		if(seb != null) {
    			strengthExerciseService.delete(exerciseID);
    			
        		//Add an attribute to the request to let them exercise page know there was a successful exercise creation. 
        		session.setAttribute("SuccessfulExerciseDeletion", "Strength exercise deleted successfully!");
    			
    			return "redirect:/exercises/view";
    		}else {
    			return "redirect:/exercises/view";
    		}
    		

		}	
	}	
	
	
	
	//AJAX REQUESTS
	
	@RequestMapping(value = "/updateAerobicTable", method = RequestMethod.POST)
	public void updateAerobicExercisesAJAX(HttpSession session, HttpServletResponse response, Model model, @RequestParam String date) 
			throws Exception {
		
		
		//Update the session attribute date with the selected date by the user. 
		model.addAttribute("todaysDate", LocalDate.parse(date));
		
		response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
       
        
		//UPDATED -> working
        
		int userid = (Integer)session.getAttribute("userID");
		
		ArrayList<AerobicExerciseEntity> list = aerobicExerciseService.findAllByUserIdAndDate(userid, date);
        
        try {
        	//GSON library, i.e., https://github.com/google/gson, enables me to convert my array list into a JSON array, which is a life saver :). 
        	out.println(new Gson().toJson(list));
        } finally {
            out.close();
        }
	}
	
	@RequestMapping(value = "/updateStrengthTable", method = RequestMethod.POST)
	public void updateStrengthExercisesAJAX(HttpSession session, HttpServletResponse response, Model model, @RequestParam String date) 
			throws Exception {
		
		//UPDATED -> working
		
		//Update the session attribute date with the selected date by the user. 	
		model.addAttribute("todaysDate", LocalDate.parse(date));
		
		response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
		int userid = (Integer)session.getAttribute("userID");
		
        ArrayList<StrengthExerciseEntity> list = strengthExerciseService.findAllByUserIdAndDate(userid, date);
        
        try {
        	//GSON library, i.e., https://github.com/google/gson, enables me to convert my array list into a JSON array, which is a life saver :). 
        	out.println(new Gson().toJson(list));
        } finally {
            out.close();
        }
	}
	
}
