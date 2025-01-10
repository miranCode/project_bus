package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
	//12
	@GetMapping("/login")
	public String login() {
		return "user/member/login";
	}
	
	@GetMapping("/join")
	public String join() {
		return "user/member/join";
	}
}
