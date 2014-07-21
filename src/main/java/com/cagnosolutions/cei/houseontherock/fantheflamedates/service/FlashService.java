package com.cagnosolutions.cei.houseontherock.fantheflamedates.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service(value = "flashService")
@PropertySource("classpath:flash.properties")
public class FlashService {

	@Autowired
	private Environment env;

	public void flash(RedirectAttributes attr, String msg) {
		attr.addFlashAttribute("msg", env.getProperty(msg));
	}

	public void flashAlert(RedirectAttributes attr, String msg, String  style, boolean button) {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("<div class=\"text-center alert alert-%s\"> %s", style, msg));
		if (button) {
			sb.append("<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>");
		}
		sb.append("</div>");
		attr.addFlashAttribute("msg", sb.toString());
	}

	public void flashText(RedirectAttributes attr, String msg, String style) {
		String div = String.format("<div class=\"text-center text-%s\"> %s </div>", style, msg);
		attr.addFlashAttribute("msg", div);
	}

	public void flashLink(RedirectAttributes attr, String msg, String url) {
		attr.addFlashAttribute("msg", String.format("<a href=\"%s\">%s</a>", url, msg));
	}
}
