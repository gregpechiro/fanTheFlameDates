package com.cagnosolutions.cei.houseontherock.fantheflamedates.controller;

import com.cagnosolutions.cei.houseontherock.fantheflamedates.domain.Email;
import com.cagnosolutions.cei.houseontherock.fantheflamedates.domain.User;
import com.cagnosolutions.cei.houseontherock.fantheflamedates.service.FlashService;
import com.cagnosolutions.cei.houseontherock.fantheflamedates.service.MailService;
import com.cagnosolutions.cei.houseontherock.fantheflamedates.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class MainController {

	@Autowired
	private UserService userService;

	@Autowired
	private FlashService flashService;

	@Autowired
	private MailService mailService;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index() {
		return "redirect:/home";
	}

	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home(Principal principal, Model model) {
		model.addAttribute("auth", (principal == null));
		return "home";
	}

	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String registerForm(Model model) {
		return "redirect:/home?register";
	}

	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(User user, @RequestParam("confirm") String confirm, RedirectAttributes attr) {
		if (!userService.exists(user.getUsername()) &&
				userService.usernameIsValid(user.getUsername()) &&
				confirm.equals(user.getPassword())) {
			user.setActive(true);
			user.setRole("ROLE_USER");
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
			user.setRecentlyViewed(new ArrayList<String>());
			userService.insert(user);
			//flashService.flash(attr, "register.success");
			attr.addFlashAttribute("alertSuccess", "Successfully Registered. Please login");
			return "redirect:/home?login=true";
		}
		//flashService.flash(attr, "register.error");
		attr.addFlashAttribute("alertError", "Error registering");
		return "redirect:/home?register=true";
	}

	@RequestMapping(value="/login")
	public String login(@RequestParam(value="error", required=false) String error, RedirectAttributes attr) {
		if (error != null) {
			//flashService.flash(attr, "login.error");
			attr.addFlashAttribute("alertError", "Error logging you in");
		}
		return ("redirect:/home?login=true");
	}

	@RequestMapping(value="/about")
	public String about(Principal principal, Model model) {
		model.addAttribute("auth", (principal == null));
		return "about";
	}

	@RequestMapping(value="/contact", method = RequestMethod.GET)
	public String contact(Principal principal, Model model) {
		model.addAttribute("auth", (principal == null));
		return "contact";
	}

	@RequestMapping(value="/contact", method = RequestMethod.POST)
	public String contactSubmit(Email email, RedirectAttributes attr) {
		if (email.emailCheck()) {
			mailService.sendSimpleEmail("info@fantheflamedates.com", email.subject(), email.toString(), "gregpechiro@gmail.com");
			//flashService.flashAlert(attr, "Your message was successfully sent", "success", true);
			attr.addFlashAttribute("alertSuccess", "Your message was successfully sent");
			return "redirect:/contact";
		}
		//flashService.flashAlert(attr, "There was an error sending your message. Please try again", "danger", true);
		attr.addFlashAttribute("alertDanger", "There was an error sending your message. Please try again");
		return "redirect:/contact";
	}

	@RequestMapping(value="/donate")
	public String donate(Principal principal, Model model) {
		model.addAttribute("auth", (principal == null));
		return "donate";
	}

	@ExceptionHandler(value={Exception.class, RuntimeException.class})
	public ModelAndView errorHandler(Exception e) {
		ModelAndView view = new ModelAndView("main/exception");
		view.addObject("msg", e.getLocalizedMessage());
		StringBuilder sb = new StringBuilder();
		for(StackTraceElement frame : e.getStackTrace())
			sb.append(frame.toString()).append("\n");
		view.addObject("ex", sb.toString());
		return view;
	}
}
