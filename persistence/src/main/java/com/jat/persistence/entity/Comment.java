package com.jat.persistence.entity;

public class Comment {

	private String message;
	private double senti;
	private double sentiSVM;
	private double sentiVNWord;
	
	
	public Comment(String message, double senti,  double sentiSVM,  double sentiVNWord) {
		super();
		this.message = message;
		this.senti = senti;
		this.sentiSVM = sentiSVM;
		this.sentiVNWord = sentiVNWord;
		
	}

	public Comment(String message) {
		super();
		this.message = message;
	}
	public Comment() {
		super();
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

	public double getSentiSVM() {
		return sentiSVM;
	}

	public void setSentiSVM(double sentiSVM) {
		this.sentiSVM = sentiSVM;
	}

	public double getSentiVNWord() {
		return sentiVNWord;
	}

	public void setSentiVNWord(double sentiVNWord) {
		this.sentiVNWord = sentiVNWord;
	}

}
