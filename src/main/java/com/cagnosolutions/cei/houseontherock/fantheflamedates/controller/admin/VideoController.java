package com.cagnosolutions.cei.houseontherock.fantheflamedates.controller.admin;

import com.cagnosolutions.cei.houseontherock.fantheflamedates.domain.VimeoAPI;
import com.cagnosolutions.cei.houseontherock.fantheflamedates.domain.VimeoVideo;
import com.cagnosolutions.cei.houseontherock.fantheflamedates.service.VideoService;
import com.cagnosolutions.cei.houseontherock.fantheflamedates.service.WorksheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class VideoController {

	@Autowired
	private VideoService videoService;

	@Autowired
	private WorksheetService worksheetService;

	// list get
	@RequestMapping(value = "/video", method = RequestMethod.GET)
	public String list(Model model, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "order", required = false) String order) {

		try {
			VimeoAPI vimeo = new VimeoAPI("42d31cdffcfb6f820c33687faedda08f");
			model.addAttribute("videos", vimeo.getInfo( "https://api.vimeo.com/me/videos"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin/video/list";
	}

	// add/edit get
	@RequestMapping(value = "/video/temp", method = RequestMethod.GET)
	public String addForm(Model model, @RequestParam(value="video_uri") String videoUri) {
		try {
			VimeoAPI vimeo = new VimeoAPI("42d31cdffcfb6f820c33687faedda08f");
			model.addAttribute("video", vimeo.getInfo( "https://api.vimeo.com" + videoUri));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin/video/view";
	}

	// add post
	@RequestMapping(value = "/video", method = RequestMethod.POST)
	public Object add(VimeoVideo video) {
		try {
			VimeoAPI vimeo = new VimeoAPI("42d31cdffcfb6f820c33687faedda08f");
			vimeo.editVideo(video.getVideoUrl(), video);
			vimeo.addTags(video.getTags(), video.getVideoUrl());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/video";
		//return video;
	}

	@RequestMapping(value="/video/upload", method = RequestMethod.GET)
	public String upload(Model model) {
		try {
			VimeoAPI vimeo = new VimeoAPI("42d31cdffcfb6f820c33687faedda08f");
			model.addAttribute("upload", vimeo.postInfo("https://api.vimeo.com/me/videos", "redirect_url=localhost:8080/admin/video/temp"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin/video/upload";
	}
}