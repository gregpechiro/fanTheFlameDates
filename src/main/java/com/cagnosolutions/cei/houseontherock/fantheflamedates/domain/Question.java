package com.cagnosolutions.cei.houseontherock.fantheflamedates.domain;


import javax.persistence.*;
import java.util.ArrayList;

/**
 * Created by greg on 7/3/14.
 */
@Entity
@Table(name = "question")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String videoId;
	private String askedQuestion;
	private Short sheetOrder;
	private String inType;
	private ArrayList<String> options;

	public Question() {
	}

	public Question(String videoId, String askedQuestion, Short sheetOrder, String inType, ArrayList<String> options) {
		this.videoId = videoId;
		this.askedQuestion = askedQuestion;
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

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getAskedQuestion() {
		return askedQuestion;
	}

	public void setAskedQuestion(String askedQuestion) {
		this.askedQuestion = askedQuestion;
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