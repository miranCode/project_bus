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
	
	// 踰꾩뒪 �끂�꽑
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
	
	// 踰꾩뒪 �끂�꽑 api 濡� 諛쏆븘�삩 �뜲�씠�꽣媛� �엳�쑝硫� ���옣�븯吏� �븡怨� �뾾�쑝硫� ���옣�븳�떎. 
	public void saveBusLine() {
		List<BusDTO> busLine = busLineApi();
		for(BusDTO bdto : busLine) {
			int result = seBusLine(bdto);
			if(result == 0) {
				int inR = aMapper.inBusLine(bdto);
				System.out.println("���옣" + inR);
			}else {
				System.out.println("���옣�븞�븿, �씠誘� 議댁옱�븯�뒗 �뜲�씠�꽣");
			}
		}
	}
	
	// 踰꾩뒪 �끂�꽑 api �뿰寃고븳�떎. 
	public List<BusDTO> busLineApi(){
		// api 二쇱냼 
		String apiUrl = "http://openapi.seoul.go.kr:8088/7a4a7047727468663533544a6c4747/json/busRoute/1/683/";
		// API �샇異�
		String response = restTemplate.getForObject(apiUrl, String.class); // getForObject() 硫붿꽌�뱶�뒗 �꽌踰꾩쓽 �쓳�떟 蹂몃Ц�쓣 吏��젙�맂 responseType �겢�옒�뒪濡� 蹂��솚�븯�뿬 諛섑솚
		
		List<BusDTO> busLineList = new ArrayList<>();
		
		try {
			// JSON �쓳�떟�쓣 ObjectMapper瑜� �궗�슜�븯�뿬 List<BusDTO>濡� 蹂��솚
            ObjectMapper objectMapper = new ObjectMapper();  // JSON �쓳�떟�쓣 JsonNode濡� 蹂��솚
            JsonNode rootNode = objectMapper.readTree(response); // JSON�뿉�꽌 "busRoute" -> "row" 諛곗뿴�뿉 �젒洹�
            JsonNode rowNode = rootNode.path("busRoute").path("row"); // rowNode�뒗 諛곗뿴�씠誘�濡�, �씠瑜� List<BusDTO>濡� 蹂��솚
            
            // ObjectMapper瑜� �궗�슜�븯�뿬 row 諛곗뿴�쓣 List<BusDTO>濡� 蹂��솚
            busLineList = objectMapper.convertValue(rowNode, objectMapper.getTypeFactory().constructCollectionType(List.class, BusDTO.class));
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("api �뿰寃� �삤瑜�");
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
	
	// 踰꾩뒪�끂�꽑蹂� �젙瑜섏옣蹂� �듅�븯李� �씤�썝
	public List<BusUseDTO> busUseList(BusUseDTO budto) {
		return aMapper.busUseList(budto);
	}
	
	// 踰꾩뒪 �끂�꽑 api 濡� 諛쏆븘�삩 �뜲�씠�꽣媛� �엳�쑝硫� ���옣�븯吏� �븡怨� �뾾�쑝硫� ���옣�븳�떎. 
	public void saveBusUse() {
		List<BusUseDTO> busUse = busUseApi();
		for(BusUseDTO bdto : busUse) {
			int result = seBusUse(bdto);
			if(result == 0) {
				int inR = aMapper.inBusUse(bdto);
				System.out.println("���옣" + inR);
			}else {
				System.out.println("���옣�븞�븿, �씠誘� 議댁옱�븯�뒗 �뜲�씠�꽣");
			}
		}
	}
	
	public List<BusUseDTO> busUseApi(){
		
		List<BusUseDTO> busUseList = new ArrayList<>();
		// �떆�옉�씪遺��꽣 醫낅즺�씪源뚯�
		// 20241201

		String startDate = "20250114";
		String endDate = "20250117";
		
		// �궇吏� �룷留룻꽣 �꽕�젙
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        // LocalDate濡� 蹂��솚
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        
        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
        	// �궇吏쒕�� 臾몄옄�뿴濡� 蹂��솚 (yyyyMMdd �삎�떇)
            String dateString = date.format(formatter);
            
            int startIndex = 1;
    		int endIndex = 1000;
    		Boolean rolling = true;
    		int forCount = 1;
            while (rolling) {
                // URL �깮�꽦
                String apiUrl = String.format("http://openapi.seoul.go.kr:8088/7a4a7047727468663533544a6c4747/json/CardBusStatisticsServiceNew/%d/%d/%s", startIndex, endIndex, dateString);
                String response = restTemplate.getForObject(apiUrl, String.class); // getForObject() 硫붿꽌�뱶�뒗 �꽌踰꾩쓽 �쓳�떟 蹂몃Ц�쓣 吏��젙�맂 responseType �겢�옒�뒪濡� 蹂��솚�븯�뿬 諛섑솚
                String codeInfo = null;
                System.out.println("Generated API URL up: " + apiUrl);
                
                try {
                    ObjectMapper objectMapper = new ObjectMapper();  
                    JsonNode rootNode = objectMapper.readTree(response); 
                    JsonNode rowNode = rootNode.path("CardBusStatisticsServiceNew").path("row"); 
                    // ObjectMapper瑜� �궗�슜�븯�뿬 row 諛곗뿴�쓣 List<BusDTO>濡� 蹂��솚
                    busUseList = objectMapper.convertValue(rowNode, objectMapper.getTypeFactory().constructCollectionType(List.class, BusUseDTO.class));
                    
                    System.out.println(">>>>>>>>>>>>>>>>>>> 1" + rootNode);
                    
                	int rowCount = rootNode.path("CardBusStatisticsServiceNew").path("list_total_count").asInt();
                	codeInfo = rootNode.path("CardBusStatisticsServiceNew").path("RESULT").path("CODE").asText();
                	
                	List<BusUseDTO> busUse = busUseList;
                	System.out.println(startIndex + " / " + endIndex + " / �궇吏� " + dateString + "(" + rowCount + ")" + ">>>>>>>>>>>>>>>>>>> 2" + busUse);
                	
            		for(BusUseDTO bdto : busUse) {
            			int result = seBusUse(bdto);
            			
            			if(result == 0) {
            				int inR = aMapper.inBusUse(bdto);
            				System.out.println("���옣" + forCount);
            			}else {
            				System.out.println("���옣�븞�븿, �씠誘� 議댁옱�븯�뒗 �뜲�씠�꽣" + forCount);
            			}
            			forCount += 1;
            		}
                	
                	
                	System.out.println("api �뿰寃� �꽦怨�");
                }catch (Exception e) {
                	e.printStackTrace();
                	System.out.println("api �뿰寃� �삤瑜�");
				}
                // �깮�꽦�맂 API URL 異쒕젰
                // startNum怨� endNum�쓣 1000�뵫 利앷�
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
