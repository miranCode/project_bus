package org.zerock.dto;

// DTO: 데이터 전송 객체. 클라이언트와 서버 간에 데이터를 주고받기 위한 객체
public class ApiDTO {
	// api key 
	private String codename;
	private int num;
	private String apiname;
	private String apikey;
	public String getCodename() {
		return codename;
	}
	public void setCodename(String codename) {
		this.codename = codename;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getApiname() {
		return apiname;
	}
	public void setApiname(String apiname) {
		this.apiname = apiname;
	}
	public String getApikey() {
		return apikey;
	}
	public void setApikey(String apikey) {
		this.apikey = apikey;
	}
	
	@Override
	public String toString() {
		return "ApiDTO [codename=" + codename + ", num=" + num + ", apiname=" + apiname + ", apikey=" + apikey + "]";
	}
}
