package com.cagnosolutions.cei.houseontherock.fantheflamedates.controller.admin;

import com.cagnosolutions.cei.houseontherock.fantheflamedates.domain.VimeoAPI;
import com.cagnosolutions.cei.houseontherock.fantheflamedates.domain.Worksheet;
import com.cagnosolutions.cei.houseontherock.fantheflamedates.service.FlashService;
import com.cagnosolutions.cei.houseontherock.fantheflamedates.service.VideoService;
import com.cagnosolutions.cei.houseontherock.fantheflamedates.service.WorksheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	@Autowired
	private FlashService flashService;

	// list get
	@RequestMapping(value = "/list/worksheet", method = RequestMethod.GET)
	public String list(Model model, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "order", required = false) String order) {
		model.addAttribute("worksheets", worksheetService.findAllSorted(sort, order));
		return "admin/worksheet/list";
	}

	// add get
	@RequestMapping(value = "/add/worksheet", method = RequestMethod.GET)
	public String addForm(Model model) {
		try {
			VimeoAPI vimeo = new VimeoAPI();
			model.addAttribute("videos", vimeo.getInfo("https://api.vimeo.com/me/videos"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin/worksheet/add";
	}

	// add post
	@RequestMapping(value = "/add/worksheet", method = RequestMethod.POST)
	public String add(Worksheet worksheet, RedirectAttributes attr) {
		worksheetService.insert(worksheet);
		flashService.flash(attr, "update.success");
		return "redirect:/admin/list/worksheet";
	}

	// view get
	@RequestMapping(value = "/view/worksheet/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		model.addAttribute("worksheet", worksheetService.findById(id));
		try {
			VimeoAPI vimeo = new VimeoAPI();
			model.addAttribute("videos", vimeo.getInfo("https://api.vimeo.com/me/videos"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin/worksheet/view";
	}

	// delete post
	@RequestMapping(value = "/del/worksheet/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable("id") Long id, RedirectAttributes attr) {
		worksheetService.delete(worksheetService.findById(id));
		flashService.flash(attr, "delete.success");
		return "redirect:/admin/list/worksheet";
	}
}
