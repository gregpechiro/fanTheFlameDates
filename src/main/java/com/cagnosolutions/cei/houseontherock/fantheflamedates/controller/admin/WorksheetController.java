package com.cagnosolutions.cei.houseontherock.fantheflamedates.controller.admin;

import com.cagnosolutions.cei.houseontherock.fantheflamedates.domain.Worksheet;
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
public class WorksheetController {

	@Autowired
	private WorksheetService worksheetService;

	@Autowired
	private VideoService videoService;

	// list get
	@RequestMapping(value = "/list/worksheet", method = RequestMethod.GET)
	public String list(Model model, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "order", required = false) String order) {
		model.addAttribute("worksheet", worksheetService.findAllSorted(sort, order));
		return "admin/worksheet/list";
	}

	// add get
	@RequestMapping(value = "/add/worksheet", method = RequestMethod.GET)
	public String addForm(Model model) {
		model.addAttribute("worksheet", new Worksheet());
		model.addAttribute("videos", videoService.findAll());
		return "admin/worksheet/add";
	}

	// add post
	@RequestMapping(value = "/add/worksheet", method = RequestMethod.POST)
	public String add(Worksheet worksheet) {
		worksheetService.insert(worksheet);
		return "redirect:/admin/add/worksheet?added";
	}

	// view get
	@RequestMapping(value = "/view/worksheet/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		model.addAttribute("worksheet", worksheetService.findById(id));
		return "admin/worksheet/view";
	}

	// delete post
	@RequestMapping(value = "/del/worksheet/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable("id") Long id, Model model) {
		worksheetService.delete(worksheetService.findById(id));
		return "redirect:/admin/list/worksheet?removed";
	}

	// edit get
	@RequestMapping(value = "/edit/worksheet/{id}", method = RequestMethod.GET)
	public String editForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("worksheet", worksheetService.findById(id));
		model.addAttribute("videos", videoService.findAll());
		return "admin/worksheet/edit";
	}

	// edit post
	@RequestMapping(value = "/edit/worksheet/{id}", method = RequestMethod.POST)
	public String edit(@PathVariable("id") Long id, Worksheet worksheet) {
		worksheetService.update(worksheet);
		return "redirect:/admin/view/worksheet/" + id + "?status";
	}
}
