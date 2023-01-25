package eat3160.HLSP22.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This is simply the controller for handling requests to access the homepage view.
 * @author alf-s
 *
 */

@Controller
public class WelcomeController {
	
	@RequestMapping("/homepage")
	public String greeting() {
		return "index";
	}
}
