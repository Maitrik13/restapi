package com.service.pet.dto;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.security.core.GrantedAuthority;

public class User extends org.springframework.security.core.userdetails.User {

	private String UUID;

	private String firstName;

	private String lastName;

	private String email;

	private String username;

	private String password;

	public User(String userName, String password) {
		super(userName, password, true, true, true, true, new ArrayList<GrantedAuthority>());
		this.username = userName;
		this.password = password;
	}

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [UUID=" + UUID + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", username=" + username + ", password=" + password + "]";
	}

}
