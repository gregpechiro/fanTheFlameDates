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

	// GET list
	@RequestMapping(value = "/list/question", method = RequestMethod.GET)
	public String list(Model model, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "order", required = false) String order) {
		model.addAttribute("questions", questionService.findAllSorted(sort, order));
		return "admin/question/list";
	}

	// GET add
	@RequestMapping(value = "/add/question", method = RequestMethod.GET)
	public String addForm(@RequestParam(value="video_uri") String videoUri, Model model) {
		model.addAttribute("videoUri", videoUri);
		return "admin/question/add";
	}

	// POST add
	@RequestMapping(value = "/add/question", method = RequestMethod.POST)
	public String add(Question question, RedirectAttributes attr) {
		questionService.insert(question);
		//flashService.flash(attr, "update.success");
		attr.addFlashAttribute("alertSuccess", "Successfully updated question");
		return "redirect:/admin/edit/video?video_uri=" + question.getVideoId();
	}

	// GET edit
	@RequestMapping(value = "/edit/question/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		model.addAttribute("question", questionService.findById(id));
		try {
			VimeoAPI vimeo = new VimeoAPI();
			model.addAttribute("videos", vimeo.getInfo("https://api.vimeo.com/me/videos"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin/question/edit";
	}

	// POST delete
	@RequestMapping(value = "/del/question/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable("id") Long id, @RequestParam(value = "videoUri") String videoUri, RedirectAttributes attr) {
		questionService.delete(questionService.findById(id));
		//flashService.flash(attr, "delete.success");
		attr.addFlashAttribute("alertSuccess", "Successfully deleted question");
		return "redirect:/admin/edit/video?video_uri=" + videoUri;
	}
}
