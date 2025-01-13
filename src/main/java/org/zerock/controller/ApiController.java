package org.zerock.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.dto.BusDTO;
import org.zerock.dto.BusUseDTO;
import org.zerock.service.ApiService;



@Controller // Controller: 클라이언트 요청을 받고 응답을 반환.
@RequestMapping("bus")
public class ApiController {
	
	@Autowired
	ApiService aService;
	
	@GetMapping("busLine")
	public String busLine(BusDTO bdto, Model model) {
		List<BusDTO> responses = aService.busLineList(bdto);
		model.addAttribute("budLineListSize", responses.size());
		model.addAttribute("budLineList", responses);
		return "admin/bus/busLine";
	}
	
	@GetMapping("saveBusLine")
	public ResponseEntity<List<BusDTO>> svaBusLine(BusDTO bdto) {
		List<BusDTO> apiData = new ArrayList<>();
	    try {
	        apiData = aService.busLineApi(); 
	        System.out.println("API 호출성공");
	        
	        // apiData null 이 아니면 저장 
	        if(apiData != null && !apiData.isEmpty()){
	        	aService.saveBusLine();
	        	return ResponseEntity.ok(apiData);  // JSON 형식으로 응답 반환
	        } else {
	            return null;
	        }
	    } catch (Exception e) {
	        System.out.println("API 호출 중 오류 발생: " + e.getMessage());
	        e.printStackTrace();
	        return null;
	    }
	};
	
	// 
	@GetMapping("busUse")
	public String busUse(BusUseDTO budto, Model model) {
		List<BusUseDTO> responses = aService.busUseList(budto);
		
		model.addAttribute("busUseList", responses);
		
		return "admin/bus/busUse";
	}
	
	@GetMapping("saveBusUse")
	public ResponseEntity<List<BusUseDTO>> svaBusLine(BusUseDTO budto) {
		List<BusUseDTO> apiData = new ArrayList<>();
		try {
			apiData = aService.busUseApi(); 
	        System.out.println("API 호출성공" + apiData );
		}catch (Exception e) {
			System.out.println("API 호출 중 오류 발생: " + e.getMessage());
	        e.printStackTrace();
	        return null;
		}
		return ResponseEntity.ok(apiData);  // JSON 형식으로 응답 반환
	}

}
