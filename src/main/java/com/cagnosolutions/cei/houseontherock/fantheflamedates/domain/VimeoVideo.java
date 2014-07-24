package com.cagnosolutions.cei.houseontherock.fantheflamedates.domain;

import java.util.List;

/**
 * Created by greg on 7/23/14.
 */
public class VimeoVideo {

	private String name;
	private String description;
	private String videoUrl;
	private List<String> tags;

	public VimeoVideo() {
	}

	public VimeoVideo(String name, String description, String videoUrl, List<String> tags) {
		this.name = name;
		this.description = description;
		this.videoUrl = videoUrl;
		this.tags = tags;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
}
