package com.cagnosolutions.cei.houseontherock.fantheflamedates.controller.admin;

import com.cagnosolutions.cei.houseontherock.fantheflamedates.domain.VimeoAPI;
import com.cagnosolutions.cei.houseontherock.fantheflamedates.domain.VimeoVideo;
import com.cagnosolutions.cei.houseontherock.fantheflamedates.service.FlashService;
import com.cagnosolutions.cei.houseontherock.fantheflamedates.service.VideoService;
import com.cagnosolutions.cei.houseontherock.fantheflamedates.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class VideoController {

	@Autowired
	private VideoService videoService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private FlashService flashService;

	// list get
	@RequestMapping(value = "/video", method = RequestMethod.GET)
	public String list(Model model, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "order", required = false) String order) {
		try {
			VimeoAPI vimeo = new VimeoAPI();
			model.addAttribute("videos", vimeo.getInfo( "https://api.vimeo.com/me/videos"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin/video/list";
	}

	// add/edit get
	@RequestMapping(value = "/video/view", method = RequestMethod.GET)
	public String addForm(Model model, @RequestParam(value="video_uri") String videoUri) {
		try {
			VimeoAPI vimeo = new VimeoAPI();
			model.addAttribute("video", vimeo.getInfo( "https://api.vimeo.com" + videoUri));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin/video/view";
	}

	// add post
	@RequestMapping(value = "/video", method = RequestMethod.POST)
	public Object add(VimeoVideo video, RedirectAttributes attr) {
		try {
			VimeoAPI vimeo = new VimeoAPI();
			vimeo.editVideo(video);
			vimeo.addTags(video.getTags(), video.getVideoUri());
			vimeo.addEmbedPreset("451008", video.getVideoUri());
		} catch (Exception e) {
			e.printStackTrace();
		}
		flashService.flash(attr, "update.success");
		return "redirect:/admin/video";
	}

	@RequestMapping(value="/video/upload", method = RequestMethod.GET)
	public String upload(Model model) {
		try {
			VimeoAPI vimeo = new VimeoAPI();
			model.addAttribute("upload", vimeo.postInfo("https://api.vimeo.com/me/videos", "redirect_url=localhost:8080/admin/video/view"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin/video/upload";
	}

	@RequestMapping(value="/del/video")
	public String delete(@RequestParam(value="videoUri") String videoUri, RedirectAttributes attr) {
		try {
			VimeoAPI vimeo = new VimeoAPI();
			vimeo.deleteVideo(videoUri);
		} catch (Exception e) {
			e.printStackTrace();
		}
		flashService.flash(attr, "delete.success");
		return "redirect:/admin/video";
	}
}