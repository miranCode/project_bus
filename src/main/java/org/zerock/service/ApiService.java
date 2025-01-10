package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.dto.BusDTO;

@Service
public interface ApiService {
	int inBusLine(BusDTO bdto);
	int seBusLine(BusDTO bdto);
	List<BusDTO> busLineList(BusDTO bdto);
	List<BusDTO> busLineApi();
	void saveBusLine();

}
