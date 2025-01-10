package org.zerock.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)  // JSON에서 알 수 없는 필드는 무시
public class BusDTO {
	
	@JsonProperty("RTE_ID")  // 이 필드는 이미 필드 이름이 일치
	private String RTE_ID;
	
	@JsonProperty("RTE_NM")  // JSON 응답의 "RTE_NM"을 이 필드에 매핑
	private String RTE_NM;
	
	public String getRTE_ID() {
		return RTE_ID;
	}
	public void setRTE_ID(String rTE_ID) {
		RTE_ID = rTE_ID;
	}
	public String getRTE_NM() {
		return RTE_NM;
	}
	public void setRTE_NM(String rTE_NM) {
		RTE_NM = rTE_NM;
	}
	
	@Override
	public String toString() {
		return "BusDTO [RTE_ID=" + RTE_ID + ", RTE_NM=" + RTE_NM + "]";
	}
}
