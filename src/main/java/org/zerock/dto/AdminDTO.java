package org.zerock.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class AdminDTO {
	private String id;
	private String pw;
	private String name;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date regidate;
	private Date lastLogin;
	private String level;
	private String access;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getRegidate() {
		return regidate;
	}
	public void setRegidate(Date regidate) {
		this.regidate = regidate;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getAccess() {
		return access;
	}
	public void setAccess(String access) {
		this.access = access;
	}
	
	@Override
	public String toString() {
		return "AdminDTO [id=" + id + ", pw=" + pw + ", name=" + name + ", regidate=" + regidate + ", lastLogin="
				+ lastLogin + ", level=" + level + ", access=" + access + "]";
	}
}
