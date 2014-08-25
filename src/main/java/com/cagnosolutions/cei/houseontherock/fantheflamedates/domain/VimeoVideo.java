package com.cagnosolutions.cei.houseontherock.fantheflamedates.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;

/**
 * Created by greg on 7/23/14.
 */

@Entity
@Table(name = "video")
public class VimeoVideo {

	@Id
	private String videoUri;

	private String name;

	@Column(length = 1000)
	private String description;

	@Column(length = 1000)
	private ArrayList<String> tags;

	private String thumb;

	public VimeoVideo() {
	}

	public VimeoVideo(String name, String description, String videoUri, ArrayList<String> tags, String thumb) {
		this.name = name;
		this.description = description;
		this.videoUri = videoUri;
		this.tags = tags;
		this.thumb = thumb;
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

	public ArrayList<String> getTags() {
		return tags;
	}

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
}
