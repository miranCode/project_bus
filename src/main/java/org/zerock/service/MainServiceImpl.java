package org.zerock.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.dto.BusTimeDTO;
import org.zerock.mapper.MainMapper;

@Service
public class MainServiceImpl implements MainService{
	
	@Autowired
	MainMapper mapper;
	
	public List<BusTimeDTO> seTime(){
		List<BusTimeDTO> BTList = new ArrayList<>();
		try {
			BTList = mapper.seTime();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("service impl 문제");
		}
		
		return BTList;
	}


}
