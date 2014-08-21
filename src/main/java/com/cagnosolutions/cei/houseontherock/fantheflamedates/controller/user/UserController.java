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

    // GET home
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public String home(Principal principal, Model model) {
        String username = principal.getName();
        if (username.equals("admin")) {
            return "redirect:/admin";
        }
		model.addAttribute("auth", (principal == null));
		model.addAttribute("user", userService.findById(username));
		return "user/home";
	}

    /*// GET account
    @RequestMapping(value="/user/account", method = RequestMethod.GET)
    public String accountForm(Principal principal, Model model) {
        String username = principal.getName();
        if(username != null) {
            model.addAttribute("user", userService.findById(principal.getName()));
            return "user/account";
        }
        return "user/home";
    }*/

    // POST update account
    @RequestMapping(value="/user", method = RequestMethod.POST)
    public String account(Principal principal, Model model, User user, RedirectAttributes attr) {
        String username = principal.getName();
        model.addAttribute("user", user);
        if(username != null) {
            userService.updateUser(username, user);
			//flashService.flash(attr, "update.success");
			attr.addFlashAttribute("alertSuccess", "Account was changed successfully");
            return "redirect:/user";
        }
		//flashService.flash(attr, "update.error");
		attr.addFlashAttribute("alertError", "Error updating account");
        return "redirect:/user";
    }

    // GET edit pass
    @RequestMapping(value="/user/password", method=RequestMethod.GET)
    public String editPassForm(Principal principal, Model model) {
        model.addAttribute("user", userService.findById(principal.getName()));
		model.addAttribute("auth", (principal == null));
        return "user/editpass";
    }

    // POST edit pass
    @RequestMapping(value="/user/password", method=RequestMethod.POST)
    public String editPass(Principal principal, @RequestParam("password") String password, @RequestParam("confirm") String confirm, RedirectAttributes attr) {
        if(password == null || confirm == null || !password.equals(confirm)) {
			//flashService.flash(attr, "password.error");
			attr.addFlashAttribute("alertError", "Error changing password");
            return "redirect:/user/password";
        }
        User user = userService.findById(principal.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        userService.update(user);
		//flashService.flash(attr, "password.success");
		attr.addFlashAttribute("alertSuccess", "Password was changed successfully");
        return "redirect:/user";
    }

    // GET edit username
    @RequestMapping(value="/user/username", method=RequestMethod.GET)
    public String editUsernameForm(Principal principal, Model model) {
        model.addAttribute("user", userService.findById(principal.getName()));
		model.addAttribute("auth", (principal == null));
        return "user/edituser";
    }

    // POST edit username
    @RequestMapping(value="/user/username", method=RequestMethod.POST)
    public String editUsername(Principal principal, @RequestParam("username") String username, RedirectAttributes attr) {
        if(username == null || username.equals(principal.getName()) || userService.exists(username)) {
			//flashService.flash(attr, "username.error");
			attr.addFlashAttribute("alertError", "Error changing username");
			return "redirect:/user/username";
		}
        if(userService.updateUsername(principal.getName(), username)) {
            User user = userService.findById(username);
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(user, user.getPassword()));
			//flashService.flash(attr, "username.success");
			attr.addFlashAttribute("alertSuccess", "Username was successfully changed");
            return "redirect:/user";
        }
		//flashService.flash(attr, "username.error");
		attr.addFlashAttribute("alertError", "Error changing username");
        return "redirect:/user/username";
    }
}
