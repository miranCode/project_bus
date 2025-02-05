package org.zerock.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.zerock.dto.BusTimeDTO;

@Mapper
public interface MainMapper {
	
	public List<BusTimeDTO> seTime();

	// 날짜정보 시작일 ~ 종료일
	public List<Date> period();
	
	public List<Map<String, Object>> routeTurn ();
	
	// 250204
	// 버스별 12월 합산
	public List<Map<String, Object>> monthBusSum ();
	// 버스별 평일, 토요일, 공휴일 합산
	public List<Map<String, Object>> busDateSum ();
	
}
