package com.jat.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class Users {
	@Id
	private String id;
	private String firstname;
	private String lastname;

	public Users() {
	}

	public Users(String firstName, String lastName) {
		this.firstname = firstName;
		this.lastname = lastName;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstname;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstname = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastname;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastname = lastName;
	}
}