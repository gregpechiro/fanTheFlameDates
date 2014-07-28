package com.cagnosolutions.cei.houseontherock.fantheflamedates.controller.admin;

import com.cagnosolutions.cei.houseontherock.fantheflamedates.domain.User;
import com.cagnosolutions.cei.houseontherock.fantheflamedates.service.FlashService;
import com.cagnosolutions.cei.houseontherock.fantheflamedates.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller("adminUserController")
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService userService;

	@Autowired
	private FlashService flashService;

    // list all
    @RequestMapping(value="/list/user", method=RequestMethod.GET)
    public String list(Model model, @RequestParam(value="sort", required=false) String sort, @RequestParam(value="order", required=false) String order) {
        model.addAttribute("users", userService.findAllSorted(sort, order));
        return "admin/user/list";
    }

    // add new form
    @RequestMapping(value="/add/user", method=RequestMethod.GET)
    public String addForm(Model model) {
        model.addAttribute("user", new User());
        return "admin/user/add";
    }

    // add new
    @RequestMapping(value="/add/user", method=RequestMethod.POST)
    public String add(User user, RedirectAttributes attr) {
        if (!userService.exists(user.getUsername()) && userService.usernameIsValid(user.getUsername())) {
            user.setActive(true);
            user.setRole("ROLE_USER");
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            userService.insert(user);
			flashService.flash(attr, "add.success");
            return "redirect:/admin/list/user";
        }
		flashService.flash(attr, "add.error");
        return "redirect:/admin/add/user";
    }

    // display
    @RequestMapping(value="/view/user/{id}", method=RequestMethod.GET)
    public String view(@PathVariable("id") String id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "admin/user/view";
    }

    // delete
    @RequestMapping(value="/del/user/{id}", method=RequestMethod.POST)
    public String delete(@PathVariable("id") String id, RedirectAttributes attr) {
        userService.delete(userService.findById(id));
		flashService.flash(attr, "delete.success");
        return "redirect:/admin/list/user";
    }

    // edit form
    @RequestMapping(value="/edit/user/{id}", method=RequestMethod.GET)
    public String editForm(@PathVariable("id") String id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "admin/user/edit";
    }

    // edit
    @RequestMapping(value="/edit/user/{id}", method=RequestMethod.POST)
    public String edit(@PathVariable("id") String id, User user, RedirectAttributes attr) {
        userService.updateUser(id, user);
		flashService.flash(attr, "update.user.success");
        return "redirect:/admin/edit/user/" + id;
    }

    // edit pass form
    @RequestMapping(value="/edit/user/{id}/password", method=RequestMethod.GET)
    public String editPassForm(@PathVariable("id") String id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "admin/user/edit/pass";
    }

    // edit pass
    @RequestMapping(value="/edit/user/{id}/password", method=RequestMethod.POST)
    public String editPass(@PathVariable("id") String id, @RequestParam("password") String password, @RequestParam("confirm") String confirm, RedirectAttributes attr) {
        if(password == null || confirm == null || !password.equals(confirm)) {
			flashService.flash(attr, "password.error");
            return "redirect:/admin/edit/user/" + id + "/password";
        }
        User user = userService.findById(id);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        userService.update(user);
		flashService.flash(attr, "password.success");
        return "redirect:/admin/edit/user/" + id;
    }

    // edit user form
    @RequestMapping(value="/edit/user/{id}/username", method=RequestMethod.GET)
    public String editUserForm(@PathVariable("id") String id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "admin/user/edit/user";
    }

    // edit user
    @RequestMapping(value="/edit/user/{id}/username", method=RequestMethod.POST)
    public String editUser(@PathVariable("id") String id, @RequestParam("username") String username, RedirectAttributes attr) {
        if(username == null || username.equals(id) || userService.exists(username)) {
			flashService.flash(attr, "update.user.error");
			return "redirect:/admin/edit/user/" + id + "/username";
		}
        if(userService.updateUsername(id, username)) {
			flashService.flash(attr, "update.user.success");
			return "redirect:/admin/edit/user/" + username;
		}
        else {
			flashService.flash(attr, "update.user.error");
			return "redirect:/admin/edit/user/" + id + "/username";
		}
	}

}
