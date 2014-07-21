package com.cagnosolutions.cei.houseontherock.fantheflamedates.controller.admin;

import com.cagnosolutions.cei.houseontherock.fantheflamedates.domain.Video;
import com.cagnosolutions.cei.houseontherock.fantheflamedates.service.VideoService;
import com.cagnosolutions.cei.houseontherock.fantheflamedates.service.WorksheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by greg on 7/3/14.
 */

@Controller
@RequestMapping("/admin")
public class VideoController {

	@Autowired
	private VideoService videoService;

	@Autowired
	private WorksheetService worksheetService;

	// list get
	@RequestMapping(value = "/list/video", method = RequestMethod.GET)
	public String list(Model model, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "order", required = false) String order) {
		model.addAttribute("video", videoService.findAllSorted(sort, order));
		return "admin/video/list";
	}

	// add get
	@RequestMapping(value = "/add/video", method = RequestMethod.GET)
	public String addForm(Model model) {
		model.addAttribute("video", new Video());
		return "admin/video/add";
	}

	// add post
	@RequestMapping(value = "/add/video", method = RequestMethod.POST)
	public String add(Video video) {
		videoService.insert(video);
		return "redirect:/admin/list/video?added";
	}

	// view get
	@RequestMapping(value = "/view/video/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		model.addAttribute("video", videoService.findById(id));
		return "admin/video/view";
	}

	// delete post
	@RequestMapping(value = "/del/video/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable("id") Long id, Model model) {
		videoService.delete(videoService.findById(id));
		return "redirect:/admin/list/video?removed";
	}

	// edit get
	@RequestMapping(value = "/edit/video/{id}", method = RequestMethod.GET)
	public String editForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("video", videoService.findById(id));
		return "admin/video/edit";
	}

	// edit post
	@RequestMapping(value = "/edit/video/{id}", method = RequestMethod.POST)
	public String edit(@PathVariable("id") Long id, Video video) {
		videoService.update(video);
		return "redirect:/admin/view/video/" + id + "?updated";
	}

	// worksheet get
	@RequestMapping(value="/list/worksheet/{videoId}")
	public String worksheet(Model model, @PathVariable(value="videoId") Long videoId) {
		model.addAttribute("worksheet", worksheetService.findByVideoId(videoId));
		return "/admin/worksheet/list";
	}
}