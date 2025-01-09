package org.zerock.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.dto.adminDTO;

@Controller
@RequestMapping("/member/*") 
public class AdminController {
	
	// 로그인
	@GetMapping(value="login")
	public String loginGo() {
		return "member/login";
	}
/*
	@PostMapping(value="login")
	public String loginPro(adminDTO mdto, HttpSession session, Model model) {

		System.out.println(mdto);
		adminDTO login = mapper.login(mdto);
		if(login != null) {
			session.setAttribute("id", login.getId());
			session.setAttribute("name", login.getName());

			return "redirect:/index";
			// return "index";
		}else {
			model.addAttribute("loginError", "일치하는 정보가 없습니다."); // 로그인 실패 시 에러 메시지 추가
			return "member/login";
		}
		
	}
*/
	
	// 관리자 추가 
	@GetMapping(value="join")
	public String join() {
		return "member/join";
	}
/*
	@PostMapping(value="join")
	public String joinPro(adminDTO mdto) {      
		System.out.println("회원가입 요청이 들어옴: " + mdto); 
		int result = 0;
		if(mdto != null) {
			result = mapper.join(mdto);
			return "redirect:/member/login";
		}
	    return "member/join"; // 실패 시 가입 페이지로 리다이렉트
	}
*/
	
	//Id 중복 확인
/*	@PostMapping("/idCheck")
	@ResponseBody
	public ResponseEntity<Boolean> confirmId(String id) {
		System.out.println(id);
		boolean result = true;
		if(id.trim().isEmpty()) {
			result = false;
		} else {
			if (mapper.selectId(id)) {
				result = false;
			} else {
				result = true;
			}
		}
		System.out.println("중복" + result);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}*/
} 