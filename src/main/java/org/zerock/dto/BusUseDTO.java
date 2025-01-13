package org.zerock.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BusUseDTO {
	
	@JsonProperty("USE_YMD")
	private Date useYmd; // 이용일 
	
	@JsonProperty("RTE_ID")
	private String rteId; // 노선 ID
	
	@JsonProperty("RTE_NO")
	private String rteNo; // 노선 번호 
	
	@JsonProperty("RTE_NM")
	private String rteNm; // 노선 명
	
	@JsonProperty("STOPS_ID")
	private String stopsId; // 정류장 id
	
	@JsonProperty("STOPS_ARS_NO")
	private String stopsArsNo; // 정류장 ars no
	
	@JsonProperty("SBWY_STNS_NM")
	private String sbwyStnsNm; // 정류장 명
	
	@JsonProperty("GTON_TNOPE")
	private int gtonTnope; // 탑승객
	
	@JsonProperty("GTOFF_TNOPE")
	private int gtoffTnope; // 하차객 
	
	@JsonProperty("REG_YMD")
	private Date regYmd; // 등록일 

	public Date getUseYmd() {
		return useYmd;
	}

	public void setUseYmd(Date useYmd) {
		this.useYmd = useYmd;
	}

	public String getRteId() {
		return rteId;
	}

	public void setRteId(String rteId) {
		this.rteId = rteId;
	}

	public String getRteNo() {
		return rteNo;
	}

	public void setRteNo(String rteNo) {
		this.rteNo = rteNo;
	}

	public String getRteNm() {
		return rteNm;
	}

	public void setRteNm(String rteNm) {
		this.rteNm = rteNm;
	}

	public String getStopsId() {
		return stopsId;
	}

	public void setStopsId(String stopsId) {
		this.stopsId = stopsId;
	}

	public String getStopsArsNo() {
		return stopsArsNo;
	}

	public void setStopsArsNo(String stopsArsNo) {
		this.stopsArsNo = stopsArsNo;
	}

	public String getSbwyStnsNm() {
		return sbwyStnsNm;
	}

	public void setSbwyStnsNm(String sbwyStnsNm) {
		this.sbwyStnsNm = sbwyStnsNm;
	}

	public int getGtonTnope() {
		return gtonTnope;
	}

	public void setGtonTnope(int gtonTnope) {
		this.gtonTnope = gtonTnope;
	}

	public int getGtoffTnope() {
		return gtoffTnope;
	}

	public void setGtoffTnope(int gtoffTnope) {
		this.gtoffTnope = gtoffTnope;
	}

	public Date getRegYmd() {
		return regYmd;
	}

	public void setRegYmd(Date regYmd) {
		this.regYmd = regYmd;
	}

	@Override
	public String toString() {
		return "BusUseDTO [useYmd=" + useYmd + ", rteId=" + rteId + ", rteNo=" + rteNo + ", rteNm=" + rteNm
				+ ", stopsId=" + stopsId + ", stopsArsNo=" + stopsArsNo + ", sbwyStnsNm=" + sbwyStnsNm + ", gtonTnope="
				+ gtonTnope + ", gtoffTnope=" + gtoffTnope + ", regYmd=" + regYmd + "]";
	}
	
	
}
