package com.cagnosolutions.cei.houseontherock.fantheflamedates.domain;


import javax.persistence.*;
import java.util.ArrayList;

/**
 * Created by greg on 7/3/14.
 */
@Entity
@Table(name = "worksheet")
public class Worksheet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long videoId;
	private String question;
	private Short sheetOrder;
	private String inType;
	private ArrayList<String> options;

	public Worksheet() {
	}

	public Worksheet(Long videoId, String question, Short sheetOrder, String inType, ArrayList<String> options) {
		this.videoId = videoId;
		this.question = question;
		this.sheetOrder = sheetOrder;
		this.inType = inType;
		this.options = options;
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

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Short getSheetOrder() {
		return sheetOrder;
	}

	public void setSheetOrder(Short sheetOrder) {
		this.sheetOrder = sheetOrder;
	}

	public String getInType() {
		return inType;
	}

	public void setInType(String inType) {
		this.inType = inType;
	}

	public ArrayList<String> getOptions() {
		return options;
	}

	public void setOptions(ArrayList<String> options) {
		this.options = options;
	}
}