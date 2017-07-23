package com.jat.persistence.entity;

import org.springframework.data.annotation.Id;

public class Comment {

	private String message;
	private double senti;
	public Comment(String message, double senti) {
		super();
		this.message = message;
		this.senti = senti;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public double getSenti() {
		return senti;
	}
	public void setSenti(double senti) {
		this.senti = senti;
	}
}
