package com.cagnosolutions.cei.houseontherock.fantheflamedates.controller.video;

import com.cagnosolutions.cei.houseontherock.fantheflamedates.service.VideoService;
import com.cagnosolutions.cei.houseontherock.fantheflamedates.service.WorksheetService;
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
	private WorksheetService worksheetService;

	// list get
	@RequestMapping(value = "/list/video", method = RequestMethod.GET)
	public String list(Model model, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "order", required = false) String order, Principal principal) {
		model.addAttribute("auth", (principal == null));
		model.addAttribute("video", videoService.findAllSorted(sort, order));
		return "video/list";
	}

	// view get
	@RequestMapping(value = "/view/video/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model, Principal principal) {
		model.addAttribute("auth", (principal == null));
		model.addAttribute("video", videoService.findById(id));
		if (principal == null) {
			return "video/view_loggedout";
		}
		model.addAttribute("worksheet", worksheetService.findByVideoId(id));
		return "video/view";

	}

}