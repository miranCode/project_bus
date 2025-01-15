package org.zerock.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.dto.UserDTO;
import org.zerock.mapper.MemberMapper;
import org.zerock.service.MailSender;
@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired
	MemberMapper mapper;
	
	@Autowired
	private MailSender mailSender;
	
	//로그인
	@GetMapping(value = "login")
	public String login() {
		return "user/member/login";
	}
	
	@PostMapping(value = "login")
	public String loginPro(UserDTO mdto, HttpSession session, Model model) {
	    System.out.println("사용자 입력 정보: " + mdto);

	    // DB에서 사용자 정보 조회
	    UserDTO dbUser = mapper.login(mdto); // id로 사용자 정보 가져오기

	    if (dbUser != null) {
	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	        // 암호화된 비밀번호 비교
	        if (encoder.matches(mdto.getPass(), dbUser.getPass())) {
	            System.out.println("로그인 성공: " + dbUser);

	            // 세션에 사용자 정보 저장
	            session.setAttribute("id", dbUser.getId());
	            session.setAttribute("uname", dbUser.getName());
	            session.setAttribute("phone_num", dbUser.getPhone_number());
	            session.setAttribute("email", dbUser.getEmail());
	            session.setAttribute("provider", dbUser.getProvider());
	            session.setAttribute("dob", dbUser.getDob());
	            
	            return "redirect:/"; // 로그인 성공 시 메인 페이지로 리다이렉트
	        } else {
	            System.out.println("비밀번호가 일치하지 않음");
	            model.addAttribute("loginError", "아이디 또는 비밀번호가 올바르지 않습니다.");
	            return "user/member/login"; // 로그인 실패 시 로그인 페이지로
	        }
	    } else {
	        System.out.println("DB에 사용자 정보 없음");
	        model.addAttribute("loginError", "아이디 또는 비밀번호가 올바르지 않습니다.");
	        return "user/member/login"; // 로그인 실패 시 로그인 페이지로
	    }
	}

	//회원가입
	@GetMapping("/join")
	public String join() {
		return "user/member/join";
	}
	
	@PostMapping("/join")
	public String joinPro(UserDTO udto) { 
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    String encryptedPassword = encoder.encode(udto.getPass());  // 비밀번호 암호화
	    udto.setPass(encryptedPassword);  // 암호화된 비밀번호를 DTO에 저장
	    
	    int result = mapper.join(udto);
	    if(result > 0) {
	        return "success";  // 성공 메시지 반환
	    } else {
	        return "fail";  // 실패 메시지 반환
	    }
	}
	
	// 이메일 중복 확인 API1
    @PostMapping("/checkDuplicateEmail")
    @ResponseBody
    public String checkDuplicateEmail(@RequestParam("email") String id) {
        // 이메일 중복 검사
        if(mapper.getUserById(id) != null) {
            return "duplicate"; // 중복 이메일
        } else {
            return "available"; // 사용 가능한 이메일
        }
    }
    
    // 이메일 인증 요청
    @PostMapping("/sendVerification")
    @ResponseBody
    public String sendVerification(@RequestParam("email") String id) {
        // 중복 이메일 체크
        if (mapper.getUserById(id) != null) {
            return "duplicate"; // 이미 존재하는 이메일
        }
        
        // 이메일 인증 로직 처리 (이메일 발송 등)
        // 이메일 인증 코드 발송 처리
        try {
            mailSender.sendVerificationEmail(id);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }
    
    @GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate();
	    return "redirect:/";
	}
    
	@GetMapping(value = "myinfo")
	public String myinfo() {
		return "user/member/myinfo";
	}
	
	@GetMapping(value = "myinfo-edit")
	public String myinfoedit() {
		return "user/member/myinfo-edit";
	}
	
	@PostMapping("/update")
	public String myInfoUpdate(UserDTO userDTO, HttpSession session) {
		String sessionId = (String) session.getAttribute("id");

	    if (sessionId == null) {
	        System.out.println("세션에 ID가 존재하지 않습니다.");
	        return "redirect:/member/login";  // 로그인 페이지로 리다이렉트
	    }

	    // DTO에 세션 ID 설정
	    userDTO.setId(sessionId);

	    // 빈 값 처리
	    if (userDTO.getEmail() == null || userDTO.getEmail().isEmpty()) userDTO.setEmail(null);
	    if (userDTO.getDob() == null || userDTO.getDob().isEmpty()) userDTO.setDob(null);
	    if (userDTO.getPhone_number() == null || userDTO.getPhone_number().isEmpty()) userDTO.setPhone_number(null);

	    int result = mapper.updateMyInfo(userDTO);
	    if (result > 0) {
	        System.out.println("업데이트 성공!");
	        
	        // ✅ 세션 정보 갱신 추가
	        session.setAttribute("email", userDTO.getEmail());
	        session.setAttribute("dob", userDTO.getDob());
	        session.setAttribute("phone_number", userDTO.getPhone_number());

	        return "redirect:/member/myinfo";  // 업데이트 후 리다이렉트
	    } else {
	        System.out.println("업데이트 실패!");
	        return "redirect:/member/myinfo-edit";
	    }
	}
}
