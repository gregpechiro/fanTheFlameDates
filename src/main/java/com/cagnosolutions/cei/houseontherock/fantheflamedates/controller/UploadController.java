package com.cagnosolutions.cei.houseontherock.fantheflamedates.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Controller
@RequestMapping("/upload")
public class UploadController {

	@RequestMapping(method=RequestMethod.GET)
	public String getUpload(Model model) {
		return "upload";
	}

	@RequestMapping("/test")
	@ResponseBody
	public String test(@RequestParam(value="video_uri") String videoUri) {
		return videoUri;
	}


	@RequestMapping(method= RequestMethod.POST)
	@ResponseBody
	public String postUpload(@RequestParam(value="file1") MultipartFile file1, @RequestParam(value="file2") MultipartFile file2) {
		if (!file1.isEmpty() && !file2.isEmpty()) {
			try {
				byte[] bytes = file1.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(file1.getName()+"-uploaded#1")));
				stream.write(bytes);
				stream.close();
				byte[] bytes2 = file2.getBytes();
				BufferedOutputStream stream2 = new BufferedOutputStream(new FileOutputStream(new File(file2.getName()+"-uploaded#2")));
				stream2.write(bytes2);
				stream2.close();
				return "files uploaded successfully";
			} catch (Exception ex) {
				return "failed to upload "+ file1.getName() + " or " + file2.getName() + " => " +ex.getMessage();
			}
		}
		return "failed to upload "+ file1.getName() + " or " + file2.getName() + " because file was empty ";
	}
}