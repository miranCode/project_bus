package org.zerock.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserDTO {
	private Long userId;
	private String name;
	private String id;
	private String pass;
	private String dob;
	private String phone_number;
	private String provider_id;
	private String email;
	private String provider;
	private boolean is_active;
	private int postCount;
	private LocalDate created_at;
	
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
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getProvider_id() {
		return provider_id;
	}
	public void setProvider_id(String provider_id) {
		this.provider_id = provider_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public boolean getIsActive() {
		return is_active;
	}
	public void setIsActive(boolean is_active) {
		this.is_active = is_active;
	}
	public int getPostCount() {
		return postCount;
	}
	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}
	
	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", name=" + name + ", id=" + id + ", pass=" + pass + ", dob=" + dob
				+ ", phone_number=" + phone_number + ", provider_id=" + provider_id + ", email=" + email + ", provider="
				+ provider + ", is_active=" + is_active + ", postCount=" + postCount + ", create_at=" + created_at + "]";
	}
	public LocalDate getCreated_at() {
		return created_at;
	}
	public void setCreated_at(LocalDate create_at) {
		this.created_at = create_at;
	}
	public String getFormattedCreatedAt() {
        if (created_at != null) {
            return created_at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        return null;
    }

	


}
