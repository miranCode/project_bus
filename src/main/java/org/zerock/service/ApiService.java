package org.zerock.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.zerock.dto.BusDTO;
import org.zerock.dto.BusUseDTO;
import org.zerock.dto.RouteturnDTO;

@Service
public interface ApiService {
	int inBusLine(BusDTO bdto);
	int seBusLine(BusDTO bdto);
	List<BusDTO> busLineList(BusDTO bdto);
	List<BusDTO> busLineApi();
	void saveBusLine();
	
	
	int inBusUse(BusUseDTO budto);
	int seBusUse(BusUseDTO budto);
	List<BusUseDTO> busUseList(BusUseDTO budto);
	List<BusUseDTO> busUseApi();
	void saveBusUse();
	
	int inRoute(RouteturnDTO dto);
	int seRoute(RouteturnDTO dto);
	List<Map<String, Object>> RouteList();
	List<RouteturnDTO> routeApi();
	void saveRouteTurn();

}
