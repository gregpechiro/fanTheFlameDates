package com.cagnosolutions.cei.houseontherock.fantheflamedates.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller("adminHomeController")
@RequestMapping("/admin")
public class HomeController {

	@RequestMapping(method=RequestMethod.GET)
	public String home(Principal principal, Model model) {
		model.addAttribute("username", principal.getName());
        return "admin/home";
	}
}
