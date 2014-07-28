package com.cagnosolutions.cei.houseontherock.fantheflamedates.controller.admin;

import com.cagnosolutions.cei.houseontherock.fantheflamedates.domain.Question;
import com.cagnosolutions.cei.houseontherock.fantheflamedates.domain.VimeoAPI;
import com.cagnosolutions.cei.houseontherock.fantheflamedates.service.FlashService;
import com.cagnosolutions.cei.houseontherock.fantheflamedates.service.QuestionService;
import com.cagnosolutions.cei.houseontherock.fantheflamedates.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by greg on 7/3/14.
 */

@Controller
@RequestMapping("/admin")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private VideoService videoService;

	@Autowired
	private FlashService flashService;

	// list get
	@RequestMapping(value = "/list/question", method = RequestMethod.GET)
	public String list(Model model, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "order", required = false) String order) {
		model.addAttribute("questions", questionService.findAllSorted(sort, order));
		return "admin/question/list";
	}

	// add get
	@RequestMapping(value = "/add/question", method = RequestMethod.GET)
	public String addForm(Model model) {
		try {
			VimeoAPI vimeo = new VimeoAPI();
			model.addAttribute("videos", vimeo.getInfo("https://api.vimeo.com/me/videos"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin/question/add";
	}

	// add post
	@RequestMapping(value = "/add/question", method = RequestMethod.POST)
	public String add(Question question, RedirectAttributes attr) {
		questionService.insert(question);
		flashService.flash(attr, "update.success");
		return "redirect:/admin/list/question";
	}

	// view get
	@RequestMapping(value = "/view/question/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		model.addAttribute("question", questionService.findById(id));
		try {
			VimeoAPI vimeo = new VimeoAPI();
			model.addAttribute("videos", vimeo.getInfo("https://api.vimeo.com/me/videos"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin/question/view";
	}

	// delete post
	@RequestMapping(value = "/del/question/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable("id") Long id, RedirectAttributes attr) {
		questionService.delete(questionService.findById(id));
		flashService.flash(attr, "delete.success");
		return "redirect:/admin/list/question";
	}
}
