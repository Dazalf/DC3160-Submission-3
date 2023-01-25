package eat3160.HLSP22.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This is simply the controller for handling requests related to analytics resources, i.e., recommendations and reports. 
 * @author alf-s
 *
 */

@Controller
public class AnalyticsController {
	
	@RequestMapping("/recommendations")
	public String viewRecommendations(HttpSession session, HttpServletResponse response)
			throws Exception {
		
		if(session.getAttribute("loggedIn") == null || (boolean)session.getAttribute("loggedIn") == false) {
			//If the user isn't logged in, send an 403 error and return null as the 'view'. This ensures the user cannot access
			//the view if they are already logged in. 
			response.sendError(403);
			return null;
		}else {		
			return "recommendations";
		}	
	}
	
	@RequestMapping("/reports")
	public String viewReports(HttpSession session, HttpServletResponse response)
			throws Exception {
		
		if(session.getAttribute("loggedIn") == null || (boolean)session.getAttribute("loggedIn") == false) {
			//If the user isn't logged in, send an 403 error and return null as the 'view'. This ensures the user cannot access
			//the view if they are already logged in. 
			response.sendError(403);
			return null;
		}else {		
			return "reports";
		}	
	}
}
