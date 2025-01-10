package org.zerock.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserDTO {
	private Long userId;
	private String name;
	private String id;
	private String pass;
	private LocalDate dob;
	private LocalDateTime createdAt;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", name=" + name + ", id=" + id + ", pass=" + pass + ", dob="
				+ dob + ", createdAt=" + createdAt + "]";
	}


}
