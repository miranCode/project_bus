package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.dto.BusTimeDTO;
import org.zerock.mapper.MainMapper;

@Service
public interface MainService {
	
	public List<BusTimeDTO> seTime();

}
