package com.cagnosolutions.cei.houseontherock.fantheflamedates.domain;

/**
 * Created by greg on 7/28/14.
 */
public class Email {
	private String first;
	private String last;
	private String email;
	private String confirm;
	private String phone;
	private String message;
	private boolean info;
	private boolean technical;
	private boolean speak;

	public Email() {
	}

	public Email(String first, String last, String email, String confirm, String phone, String message, boolean info, boolean technical, boolean speak) {
		this.first = first;
		this.last = last;
		this.email = email;
		this.confirm = confirm;
		this.phone = phone;
		this.message = message;
		this.info = info;
		this.technical = technical;
		this.speak = speak;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isInfo() {
		return info;
	}

	public void setInfo(boolean info) {
		this.info = info;
	}

	public boolean isTechnical() {
		return technical;
	}

	public void setTechnical(boolean technical) {
		this.technical = technical;
	}

	public boolean isSpeak() {
		return speak;
	}

	public void setSpeak(boolean speak) {
		this.speak = speak;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(message);
		sb.append("\n\n");
		sb.append("Contact Information:\n\n");
		sb.append("Name: ");
		sb.append(first);
		sb.append(" ");
		sb.append(last);
		sb.append("\n");
		sb.append("Email: ");
		sb.append(email);
		sb.append("\n");
		sb.append("Phone: ");
		sb.append(phone);
		sb.append("\n\n");
		return sb.toString();
	}

	public String subject() {
		StringBuilder sb = new StringBuilder();
		if (info) {
			sb.append("Please email me more information about Fan the Flame Dates ");
		}
		if (technical) {
			sb.append("I have a technical issue. Please contact me ");
		}
		if (speak) {
			sb.append("I’m interested in having House on the Rock speak at my church/event ");
		}
		return sb.toString();
	}

	public boolean emailCheck() {
		return email.equals(confirm);
	}
}
