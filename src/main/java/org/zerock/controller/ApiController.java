package org.zerock.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.dto.BusDTO;
import org.zerock.dto.BusUseDTO;
import org.zerock.dto.RouteturnDTO;
import org.zerock.service.ApiService;



@Controller // Controller: �겢�씪�씠�뼵�듃 �슂泥��쓣 諛쏄퀬 �쓳�떟�쓣 諛섑솚.
@RequestMapping("bus")
public class ApiController {
	
	@Autowired
	ApiService aService;
	
	@GetMapping("busLine")
	public String busLine(BusDTO bdto, Model model, HttpSession session) {
		// 세션에서 로그인된 사용자 확인
	    if (session.getAttribute("id") == null) {
	        return "redirect:/admin/login";  // 로그인되지 않았다면 로그인 페이지로 리다이렉트
	    }
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
	        System.out.println("API �샇異쒖꽦怨�");
	        
	        // apiData null �씠 �븘�땲硫� ���옣 
	        
	        if(apiData != null && !apiData.isEmpty()){
	        	aService.saveBusLine();
	        	return ResponseEntity.ok(apiData);  // JSON �삎�떇�쑝濡� �쓳�떟 諛섑솚
	        } else {
	            return null;
	        }
	        
	    } catch (Exception e) {
	        System.out.println("API �샇異� 以� �삤瑜� 諛쒖깮: " + e.getMessage());
	        e.printStackTrace();
	        return null;
	    }
	};
	
	// 
	@GetMapping("busUse")
	public String busUse(BusUseDTO budto, Model model, HttpSession session) {
		// 세션에서 로그인된 사용자 확인
	    //if (session.getAttribute("id") == null) {
	    //    return "redirect:/admin/login";  // 로그인되지 않았다면 로그인 페이지로 리다이렉트
	    //}
		List<BusUseDTO> responses = aService.busUseList(budto);
		
		//model.addAttribute("busUseList", responses);
		model.addAttribute("roSize", responses.size());
		return "admin/bus/busUse";
	}
	
	@GetMapping("saveBusUse")
	public ResponseEntity<List<BusUseDTO>> svaBusLine(BusUseDTO budto) {
		List<BusUseDTO> apiData = new ArrayList<>();
		try {
			apiData = aService.busUseApi(); 
	        System.out.println("API 연결 성공");
		}catch (Exception e) {
			System.out.println("API 실패: " + e.getMessage());
	        e.printStackTrace();
	        return null;
		}
		
		
		return ResponseEntity.ok(apiData);  
		
	}
	@GetMapping("route")
	public String route(Model model, HttpSession session) {
		// 세션에서 로그인된 사용자 확인
	    if (session.getAttribute("id") == null) {
	        return "redirect:/admin/login";  // 로그인되지 않았다면 로그인 페이지로 리다이렉트
	    }
		List<Map<String, Object>> responses = aService.RouteList();
		
		model.addAttribute("routeList", responses);
		model.addAttribute("routeSize", responses.size());
		return "admin/bus/routeList";
	}
	
	@GetMapping("saveRoute")
	public ResponseEntity<List<RouteturnDTO>> saveRouteTurn(RouteturnDTO dto) {
		List<RouteturnDTO> apiData = new ArrayList<>();
		try {
			apiData = aService.routeApi(); 
	        System.out.println("API �샇異쒖꽦怨�");
		}catch (Exception e) {
			System.out.println("API �샇異� 以� �삤瑜� 諛쒖깮: " + e.getMessage());
	        e.printStackTrace();
	        return null;
		}
		
		
		return ResponseEntity.ok(apiData);  // JSON �삎�떇�쑝濡� �쓳�떟 諛섑솚
		
	}

}
