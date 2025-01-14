package org.zerock.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BusUseDTO {
	
	@JsonProperty("USE_YMD")
	private String USE_YMD; // 이용일 
	
	@JsonProperty("RTE_ID")
	private String RTE_ID; // 노선 ID
	
	@JsonProperty("RTE_NO")
	private String RTE_NO; // 노선 번호 
	
	@JsonProperty("RTE_NM")
	private String RTE_NM; // 노선 명
	
	@JsonProperty("STOPS_ID")
	private String STOPS_ID; // 정류장 id
	
	@JsonProperty("STOPS_ARS_NO")
	private String STOPS_ARS_NO; // 정류장 ars no
	
	@JsonProperty("SBWY_STNS_NM")
	private String SBWY_STNS_NM; // 정류장 명
	
	@JsonProperty("GTON_TNOPE")
	private int GTON_TNOPE; // 탑승객
	
	@JsonProperty("GTOFF_TNOPE")
	private int GTOFF_TNOPE; // 하차객 
	
	@JsonProperty("REG_YMD")
	private String REG_YMD; // 등록일 

	public String getUSE_YMD() {
		return USE_YMD;
	}

	public void setUSE_YMD(String uSE_YMD) {
		USE_YMD = uSE_YMD;
	}

	public String getRTE_ID() {
		return RTE_ID;
	}

	public void setRTE_ID(String rTE_ID) {
		RTE_ID = rTE_ID;
	}

	public String getRTE_NO() {
		return RTE_NO;
	}

	public void setRTE_NO(String rTE_NO) {
		RTE_NO = rTE_NO;
	}

	public String getRTE_NM() {
		return RTE_NM;
	}

	public void setRTE_NM(String rTE_NM) {
		RTE_NM = rTE_NM;
	}

	public String getSTOPS_ID() {
		return STOPS_ID;
	}

	public void setSTOPS_ID(String sTOPS_ID) {
		STOPS_ID = sTOPS_ID;
	}

	public String getSTOPS_ARS_NO() {
		return STOPS_ARS_NO;
	}

	public void setSTOPS_ARS_NO(String sTOPS_ARS_NO) {
		STOPS_ARS_NO = sTOPS_ARS_NO;
	}

	public String getSBWY_STNS_NM() {
		return SBWY_STNS_NM;
	}

	public void setSBWY_STNS_NM(String sBWY_STNS_NM) {
		SBWY_STNS_NM = sBWY_STNS_NM;
	}

	public int getGTON_TNOPE() {
		return GTON_TNOPE;
	}

	public void setGTON_TNOPE(int gTON_TNOPE) {
		GTON_TNOPE = gTON_TNOPE;
	}

	public int getGTOFF_TNOPE() {
		return GTOFF_TNOPE;
	}

	public void setGTOFF_TNOPE(int gTOFF_TNOPE) {
		GTOFF_TNOPE = gTOFF_TNOPE;
	}

	public String getREG_YMD() {
		return REG_YMD;
	}

	public void setREG_YMD(String rEG_YMD) {
		REG_YMD = rEG_YMD;
	}

	@Override
	public String toString() {
		return "BusUseDTO [USE_YMD=" + USE_YMD + ", RTE_ID=" + RTE_ID + ", RTE_NO=" + RTE_NO + ", RTE_NM=" + RTE_NM
				+ ", STOPS_ID=" + STOPS_ID + ", STOPS_ARS_NO=" + STOPS_ARS_NO + ", SBWY_STNS_NM=" + SBWY_STNS_NM
				+ ", GTON_TNOPE=" + GTON_TNOPE + ", GTOFF_TNOPE=" + GTOFF_TNOPE + ", REG_YMD=" + REG_YMD + "]";
	}


	
}
