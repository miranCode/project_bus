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
	
}
