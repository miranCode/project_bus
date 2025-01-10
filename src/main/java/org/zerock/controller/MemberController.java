package org.zerock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.dto.UserDTO;
import org.zerock.mapper.MemberMapper;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired
	MemberMapper mapper;
	
	
	//로그인
	@GetMapping(value = "login")
	public String login() {
		return "user/member/login";
	}
	
	
	//회원가입
	@GetMapping(value = "join")
	public String join() {
		return "user/member/join";
	}
	
	@PostMapping(value = "join")
	public String joinPro(UserDTO udto) {      
		System.out.println("회원가입 요청이 들어옴: " + udto); 
		int result = 0;
		if(udto != null) {
			result = mapper.join(udto);
			return "redirect:/member/login";
		}
	    return "member/join"; // 실패 시 가입 페이지로 리다이렉트
	}
}
