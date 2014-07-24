package com.cagnosolutions.cei.houseontherock.fantheflamedates.domain;

import java.util.List;

/**
 * Created by greg on 7/23/14.
 */
public class VimeoVideo {

	private String name;
	private String description;
	private String videoUri;
	private List<String> tags;

	public VimeoVideo() {
	}

	public VimeoVideo(String name, String description, String videoUri, List<String> tags) {
		this.name = name;
		this.description = description;
		this.videoUri = videoUri;
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

	public String getVideoUri() {
		return videoUri;
	}

	public void setVideoUri(String videoUri) {
		this.videoUri = videoUri;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
}
