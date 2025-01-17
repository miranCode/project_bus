package org.zerock.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.zerock.dto.ApiDTO;
import org.zerock.dto.BusDTO;
import org.zerock.dto.BusUseDTO;

@Mapper 
public interface ApiMapper {
	
	// apikey
	public List<ApiDTO> apiList();
	
	// 버스 노선 정보 
	public int inBusLine(BusDTO bdto); // 저장되면 1을 반환
	public int seBusLine(BusDTO bdto); // 이미 저장된 값이 있으면 1을 반환 
	public List<BusDTO> busLineList(BusDTO bdto); // 버스 노선 정보 리스트로 반환 
	
	// 버스 노선별 정류장별 정보 
	
	public int inBusUse(BusUseDTO budto); // 저장되면 1을 반환
	public int seBusUse(BusUseDTO budto); //
	public List<BusUseDTO> busUseList(BusUseDTO budto);
	
	// 날짜정보 시작일 ~ 종료일	
	public List<Map<String, Object>> period();
	
	// 날짜 카운터 
	public int dateCount();
	
	// 기간 전체 사용객 카운터 
	public int useCount();
	
	// 요일별 버스 노선별 평균
	public List<Map<String, Object>> mBUList();
	

}
