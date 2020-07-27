package com.mobileapp.api.users.ui.models;

import java.util.List;

public class UserResponseModel {
	private String firatname;
	private String lastname;
	private String email;
	private List<PostResposeModel> posts;
	public String getFiratname() {
		return firatname;
	}
	public void setFiratname(String firatname) {
		this.firatname = firatname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<PostResposeModel> getPosts() {
		return posts;
	}
	public void setPosts(List<PostResposeModel> posts) {
		this.posts = posts;
	}
	
	
}
