package org.zerock.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.zerock.mapper.ApiMapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.zerock.dto.BusDTO;

@Service
public class ApiServiceImpl implements ApiService {
	
	@Autowired
    private RestTemplate restTemplate;  // RestTemplate 
	
	@Autowired
	ApiMapper aMapper;
	
	// 버스 노선
	@Override
	public int inBusLine(BusDTO bdto) {
		return aMapper.inBusLine(bdto);
	}
	
	@Override
	public int seBusLine(BusDTO bdto) {
		return aMapper.seBusLine(bdto);
	}
	
	@Override
	public List<BusDTO> busLineList(BusDTO bdto) {
		return aMapper.busLineList(bdto);
	}
	
	// 버스 노선 api 로 받아온 데이터가 있으면 저장하지 않고 없으면 저장한다. 
	public void saveBusLine() {
		List<BusDTO> busLine = busLineApi();
		for(BusDTO bdto : busLine) {
			int result = seBusLine(bdto);
			if(result == 0) {
				int inR = aMapper.inBusLine(bdto);
				System.out.println("저장" + inR);
			}else {
				System.out.println("저장안함, 이미 존재하는 데이터");
			}
		}
	}
	
	// 버스 노선 api 연결한다. 
	public List<BusDTO> busLineApi(){
		// api 주소 
		String apiUrl = "http://openapi.seoul.go.kr:8088/7a4a7047727468663533544a6c4747/json/busRoute/1/683/";
		// API 호출
		String response = restTemplate.getForObject(apiUrl, String.class); // getForObject() 메서드는 서버의 응답 본문을 지정된 responseType 클래스로 변환하여 반환
		
		List<BusDTO> busLineList = new ArrayList<>();
		
		try {
			// JSON 응답을 ObjectMapper를 사용하여 List<BusDTO>로 변환
            ObjectMapper objectMapper = new ObjectMapper();  // JSON 응답을 JsonNode로 변환
            JsonNode rootNode = objectMapper.readTree(response); // JSON에서 "busRoute" -> "row" 배열에 접근
            JsonNode rowNode = rootNode.path("busRoute").path("row"); // rowNode는 배열이므로, 이를 List<BusDTO>로 변환
            
            // ObjectMapper를 사용하여 row 배열을 List<BusDTO>로 변환
            busLineList = objectMapper.convertValue(rowNode, objectMapper.getTypeFactory().constructCollectionType(List.class, BusDTO.class));
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("api 연결 오류");
		}
		return busLineList;
	}
	
	// 버스노선별 정류장별 승하차 인원

}
