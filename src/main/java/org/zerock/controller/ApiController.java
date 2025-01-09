package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller // Controller: 클라이언트 요청을 받고 응답을 반환.
@RequestMapping("bus")
public class ApiController {
	
	@GetMapping("busLine")
	public String busLine() {
		return "admin/bus/busLine";
	}

}
