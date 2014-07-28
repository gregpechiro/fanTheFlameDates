package com.cagnosolutions.cei.houseontherock.fantheflamedates.controller.user;

import com.cagnosolutions.cei.houseontherock.fantheflamedates.domain.User;
import com.cagnosolutions.cei.houseontherock.fantheflamedates.service.FlashService;
import com.cagnosolutions.cei.houseontherock.fantheflamedates.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller("userController")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private FlashService flashService;

    // home get
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public String home(Principal principal, Model model) {
        String username = principal.getName();
        if (username.equals("admin")) {
            return "redirect:/admin";
        }
		model.addAttribute("username", username);
		return "user/home";
	}

    // account get
    @RequestMapping(value="/user/account", method = RequestMethod.GET)
    public String accountForm(Principal principal, Model model) {
        String username = principal.getName();
        if(username != null) {
            model.addAttribute("user", userService.findById(principal.getName()));
            return "user/account";
        }
        return "user/home";
    }

    // account post
    @RequestMapping(value="/user/account", method = RequestMethod.POST)
    public String account(Principal principal, Model model, User user, RedirectAttributes attr) {
        String username = principal.getName();
        model.addAttribute("user", user);
        if(username != null) {
            userService.updateUser(username, user);
			flashService.flash(attr, "update.success");
            return "redirect:/user/account";
        }
		flashService.flash(attr, "update.error");
        return "redirect:/user/account";
    }

    // edit pass form
    @RequestMapping(value="/user/account/password", method=RequestMethod.GET)
    public String editPassForm(Principal principal, Model model) {
        model.addAttribute("user", userService.findById(principal.getName()));
        return "user/editpass";
    }

    // edit pass
    @RequestMapping(value="/user/account/password", method=RequestMethod.POST)
    public String editPass(Principal principal, @RequestParam("password") String password, @RequestParam("confirm") String confirm, RedirectAttributes attr) {
        if(password == null || confirm == null || !password.equals(confirm)) {
			flashService.flash(attr, "password.error");
            return "redirect:/user/account/password";
        }
        User user = userService.findById(principal.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        userService.update(user);
		flashService.flash(attr, "password.success");
        return "redirect:/user/account";
    }

    // edit username form
    @RequestMapping(value="/user/account/username", method=RequestMethod.GET)
    public String editUsernameForm(Principal principal, Model model) {
        model.addAttribute("user", userService.findById(principal.getName()));
        return "user/edituser";
    }

    // edit username
    @RequestMapping(value="/user/account/username", method=RequestMethod.POST)
    public String editUsername(Principal principal, @RequestParam("username") String username, RedirectAttributes attr) {
        if(username == null || username.equals(principal.getName()) || userService.exists(username)) {
			flashService.flash(attr, "username.error");
			return "redirect:/user/account/username";
		}
        if(userService.updateUsername(principal.getName(), username)) {
            User user = userService.findById(username);
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(user, user.getPassword()));
			flashService.flash(attr, "username.success");
            return "redirect:/user/account";
        }
		flashService.flash(attr, "username.error");
        return "redirect:/user/account/username";
    }
}
