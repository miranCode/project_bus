package org.zerock.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.zerock.mapper.ApiMapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.zerock.dto.BusDTO;
import org.zerock.dto.BusUseDTO;
import org.zerock.dto.RouteturnDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	
	
	@Override
	public int inBusUse(BusUseDTO budto) {
		return aMapper.inBusUse(budto);
	}
	
	@Override
	public int seBusUse(BusUseDTO budto) {
		return aMapper.seBusUse(budto);
	}
	
	// 버스노선별 정류장별 승하차 인원
	public List<BusUseDTO> busUseList(BusUseDTO budto) {
		return aMapper.busUseList(budto);
	}
	
	// 버스 노선 api 로 받아온 데이터가 있으면 저장하지 않고 없으면 저장한다. 
	public void saveBusUse() {
		List<BusUseDTO> busUse = busUseApi();
		for(BusUseDTO bdto : busUse) {
			int result = seBusUse(bdto);
			if(result == 0) {
				int inR = aMapper.inBusUse(bdto);
				System.out.println("저장" + inR);
			}else {
				System.out.println("저장안함, 이미 존재하는 데이터");
			}
		}
	}
	
	public List<BusUseDTO> busUseApi(){
		
		List<BusUseDTO> busUseList = new ArrayList<>();
		// 시작일부터 종료일까지
		// 20241201
		String startDate = "20250114";
		String endDate = "20250117";
		
		// 날짜 포맷터 설정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        // LocalDate로 변환
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        
        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
        	// 날짜를 문자열로 변환 (yyyyMMdd 형식)
            String dateString = date.format(formatter);
            
            int startIndex = 1;
    		int endIndex = 1000;
    		Boolean rolling = true;
    		int forCount = 1;
            while (rolling) {
                // URL 생성
                String apiUrl = String.format("http://openapi.seoul.go.kr:8088/7a4a7047727468663533544a6c4747/json/CardBusStatisticsServiceNew/%d/%d/%s", startIndex, endIndex, dateString);
                String response = restTemplate.getForObject(apiUrl, String.class); // getForObject() 메서드는 서버의 응답 본문을 지정된 responseType 클래스로 변환하여 반환
                String codeInfo = null;
                System.out.println("Generated API URL up: " + apiUrl);
                
                try {
                    ObjectMapper objectMapper = new ObjectMapper();  
                    JsonNode rootNode = objectMapper.readTree(response); 
                    JsonNode rowNode = rootNode.path("CardBusStatisticsServiceNew").path("row"); 
                    // ObjectMapper를 사용하여 row 배열을 List<BusDTO>로 변환
                    busUseList = objectMapper.convertValue(rowNode, objectMapper.getTypeFactory().constructCollectionType(List.class, BusUseDTO.class));
                    
                    System.out.println(">>>>>>>>>>>>>>>>>>> 1" + rootNode);
                    
                	int rowCount = rootNode.path("CardBusStatisticsServiceNew").path("list_total_count").asInt();
                	codeInfo = rootNode.path("CardBusStatisticsServiceNew").path("RESULT").path("CODE").asText();
                	
                	List<BusUseDTO> busUse = busUseList;
                	System.out.println(startIndex + " / " + endIndex + " / 날짜 " + dateString + "(" + rowCount + ")" + ">>>>>>>>>>>>>>>>>>> 2" + busUse);
                	
            		for(BusUseDTO bdto : busUse) {
            			int result = seBusUse(bdto);
            			
            			if(result == 0) {
            				int inR = aMapper.inBusUse(bdto);
            				System.out.println("저장" + forCount);
            			}else {
            				System.out.println("저장안함, 이미 존재하는 데이터" + forCount);
            			}
            			forCount += 1;
            		}
                	
                	
                	System.out.println("api 연결 성공");
                }catch (Exception e) {
                	e.printStackTrace();
                	System.out.println("api 연결 오류");
				}
                // 생성된 API URL 출력
                // startNum과 endNum을 1000씩 증가
                startIndex += 1000;
                endIndex += 1000;
                
                if(!codeInfo.equals("INFO-000")) {
                 	rolling = false;
                }
                
            }
        }
		return busUseList;
		
	}
	
	@Override
	public int inRoute(RouteturnDTO dto) {
		return aMapper.inRoute(dto);
	};
	@Override
	public int seRoute(RouteturnDTO dto) {
		return aMapper.seRoute(dto);
	};
	@Override
	public List<Map<String, Object>> RouteList(){
		return aMapper.RouteList();
	};
	@Override
	public void saveRouteTurn() {
		List<RouteturnDTO> route = routeApi();
		for(RouteturnDTO dto : route) {
			int result = seRoute(dto);
			if(result == 0) {
				int inR = aMapper.inRoute(dto);
				System.out.println("저장" + inR);
			}else {
				System.out.println("저장안함, 이미 존재하는 데이터");
			}
		}
		
	};
	
	public List<RouteturnDTO> routeApi(){
	    List<RouteturnDTO> routeList = new ArrayList<>();
	    
	    int startIndex = 1;
	    int endIndex = 1000;
	    int forCount = 1;
	    Boolean rolling = true;
	    String codeInfo = null;
	    
	    while (rolling) {
	        // URL 생성
	        String apiUrl = String.format("http://openapi.seoul.go.kr:8088/7a4a7047727468663533544a6c4747/json/tpssStationRouteTurn/%d/%d/", startIndex, endIndex);
	        String response = restTemplate.getForObject(apiUrl, String.class); // getForObject() 메서드는 서버의 응답 본문을 지정된 responseType 클래스로 변환하여 반환
	        System.out.println("Generated API URL up: " + apiUrl);
	        
	        try {
	            ObjectMapper objectMapper = new ObjectMapper();  
	            JsonNode rootNode = objectMapper.readTree(response); 
	            JsonNode rowNode = rootNode.path("tpssStationRouteTurn").path("row"); 
	            
	            routeList = objectMapper.convertValue(rowNode, objectMapper.getTypeFactory().constructCollectionType(List.class, RouteturnDTO.class));
	            
	            System.out.println(">>>>>>>>>>>>>>>>>>> 1" + rootNode);
	            
	            int rowCount = rootNode.path("tpssStationRouteTurn").path("list_total_count").asInt();
	            codeInfo = rootNode.path("tpssStationRouteTurn").path("RESULT").path("CODE").asText();
	            
	            // routeList 저장
	            for (RouteturnDTO dto : routeList) {
	                int result = seRoute(dto);
	                dto.setSetNum(forCount);
	                if (result == 0) {
	                    int inR = aMapper.inRoute(dto);
	                    
	                    System.out.println("저장" + forCount);
	                } else {
	                    System.out.println("저장안함, 이미 존재하는 데이터" + forCount);
	                }
	                forCount += 1;
	            }

	            System.out.println("api 연결 성공");
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("api 연결 오류");
	        }

	        // startNum과 endNum을 1000씩 증가
	        startIndex += 1000;
	        endIndex += 1000;
	        
	        if (!"INFO-000".equals(codeInfo)) {
	            System.out.println("Error Code: " + codeInfo);  // 오류 코드 디버깅용
	            rolling = false;
	        }
	    }
	    return routeList;
	}

}
