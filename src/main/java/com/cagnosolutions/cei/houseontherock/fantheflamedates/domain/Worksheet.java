package com.cagnosolutions.cei.houseontherock.fantheflamedates.domain;
/**
 * Created by greg on 7/29/14.
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "worksheet")
public class Worksheet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String userId;
	private String videoId;
	private String answers;
	private boolean save;
	private boolean email;
	private boolean send;

	public Worksheet() {
	}

	public Worksheet(String userId, String videoId, String answers, boolean save, boolean email, boolean send) {
		this.userId = userId;
		this.videoId = videoId;
		this.answers = answers;
		this.save = save;
		this.email = email;
		this.send = send;
	}

	public String toString() {
		return "Id: " + id + ", Entity: Worksheet";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getAnswers() {
		return answers;
	}

	public void setAnswers(String answers) {
		this.answers = answers;
	}

	public boolean isSave() {
		return save;
	}

	public void setSave(boolean save) {
		this.save = save;
	}

	public boolean isEmail() {
		return email;
	}

	public void setEmail(boolean email) {
		this.email = email;
	}

	public boolean isSend() {
		return send;
	}

	public void setSend(boolean send) {
		this.send = send;
	}
}
