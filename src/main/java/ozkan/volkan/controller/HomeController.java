package ozkan.volkan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	// Home Page
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {

		System.out.println("login");
		return "login";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("home");
		return model;
	}
}
