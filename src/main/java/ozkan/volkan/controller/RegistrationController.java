package ozkan.volkan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ozkan.volkan.model.Authorities;
import ozkan.volkan.model.Users;
import ozkan.volkan.service.UserService;

@Controller
public class RegistrationController {

	@Autowired
	UserService userService;

	// Redirect To Register Page
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView registrationJSP() {
		ModelAndView model = new ModelAndView();
		model.setViewName("registration");
		return model;
	}

	// Add New User
	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
	public String addUser(@ModelAttribute(value = "users") Users user, BindingResult result) {

		Authorities authorities = new Authorities(user.getUsername(), "USER");
		user.setAuthorities(authorities);
		authorities.setUsers(user);
		user.setEnabled(true);
		
		userService.addUser(user);
		return "redirect:/login";
	}

	// Check Form Values
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	@ResponseBody
	public JsonController checkValues(@ModelAttribute(value = "users") Users user, BindingResult result) {

		JsonController jsonController = new JsonController();

		if (userService.emailAlreadyExists(user.getEmail())) {
			jsonController.setStatus("fail-email");
		} else if (userService.usernameAlreadyExists(user.getUsername())) {
			jsonController.setStatus("fail-username");
		} else {
			jsonController.setStatus("success");
		}
		return jsonController;
	}
}
