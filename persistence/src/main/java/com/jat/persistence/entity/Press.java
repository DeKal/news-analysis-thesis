package com.jat.persistence.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "presses")
public class Press {
	

	@Id
	private String id;
	private String link;
	private String content;
	private List<Comment> comment;

	public Press() {
	}
	public Press(String link, String content, List<Comment> comment) {
		super();
		this.link = link;
		this.content = content;
		this.comment = comment;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Comment> getComment() {
		return comment;
	}

	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}

	
}
