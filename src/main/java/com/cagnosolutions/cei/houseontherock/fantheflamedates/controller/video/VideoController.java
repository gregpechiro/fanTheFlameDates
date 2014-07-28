package com.cagnosolutions.cei.houseontherock.fantheflamedates.controller.video;

import com.cagnosolutions.cei.houseontherock.fantheflamedates.domain.VimeoAPI;
import com.cagnosolutions.cei.houseontherock.fantheflamedates.service.QuestionService;
import com.cagnosolutions.cei.houseontherock.fantheflamedates.service.UserService;
import com.cagnosolutions.cei.houseontherock.fantheflamedates.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

/**
 * Created by greg on 7/3/14.
 */

@Controller(value="userVideoController")
public class VideoController {

	@Autowired
	private VideoService videoService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private UserService userservice;

	// list get
	@RequestMapping(value = "/list/video", method = RequestMethod.GET)
	public String list(Model model, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "order", required = false) String order, Principal principal) {
		model.addAttribute("auth", (principal == null));
		try {
			VimeoAPI vimeo = new VimeoAPI();
			model.addAttribute("videos", vimeo.getInfo("https://api.vimeo.com/me/videos"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "video/list";
	}

	// view get
	@RequestMapping(value = "/videos/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") String id, Model model, Principal principal) {
		model.addAttribute("auth", (principal == null));
		try {
			VimeoAPI vimeo = new VimeoAPI();
			model.addAttribute("video", vimeo.getInfo("https://api.vimeo.com/videos/" + id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (principal == null) {
			return "video/view_loggedout";
		}
		model.addAttribute("questions", questionService.findByVideoId("/videos/" + id));
		model.addAttribute("username", principal.getName());
		return "video/view";

	}

}
