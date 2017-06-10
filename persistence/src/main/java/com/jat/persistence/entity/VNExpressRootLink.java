package com.jat.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "vnexpressrootlink")
public class VNExpressRootLink {
	@Id
	private String id;
	private String link;
	
	public VNExpressRootLink() {
		
	}
	public VNExpressRootLink(String link) {
		super();
		this.link = link;
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
}
