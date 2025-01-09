package org.zerock.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class adminDTO {
	private String id;
	private String pw;
	private String name;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date regidate;
	
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
	
	@Override
	public String toString() {
		return "adminDTO [id=" + id + ", pw=" + pw + ", name=" + name + ", regidate=" + regidate + "]";
	}
}
