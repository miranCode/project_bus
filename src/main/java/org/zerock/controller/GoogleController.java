package org.zerock.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.dto.GoogleOAuthDTO;
import org.zerock.dto.UserDTO;
import org.zerock.mapper.MemberMapper;
import org.zerock.service.GoogleService;

@Controller
public class GoogleController {
    
    @Autowired
    private GoogleService service;
    @Autowired
	private MemberMapper mapper;
    
    private final String googleAuthUrl = "https://accounts.google.com/o/oauth2/auth";

    // Google Login Redirect
    @GetMapping("/google/login")
    public String googleLoginRedirect(HttpServletRequest request) {
    	
    	GoogleOAuthDTO credentials = mapper.getGoogleCredentials();
    	String clientId = credentials.getApiname();
		/* String redirectUri = credentials.getRedirectUri(); */
        String loginUrl = googleAuthUrl + 
            "?client_id=" + clientId + 
            "&redirect_uri=http://localhost:8080/google/userinfo"+ 
            "&response_type=code" + 
            "&scope=profile email";
        System.out.println("################" + loginUrl);
        return "redirect:" + loginUrl;
    }
    
    @RequestMapping(value = "/google/userinfo", method = RequestMethod.GET)
    public String googleLogin(@RequestParam(value = "code", required = false) String code, HttpServletRequest request, Model model) {
        try {
            System.out.println("######### Authorization Code: " + code);
            
            // 1. Access Token 발급
            String accessToken = service.getAccessToken(code);
            System.out.println("### Access Token ####: " + accessToken);

            // 2. 사용자 정보 가져오기
            HashMap<String, Object> userInfo = service.getUserInfo(accessToken);
            System.out.println("### User Info ####: " + userInfo);

            // 3. 사용자 정보 DB 저장 및 로그인 처리
            boolean userExists = service.saveUser(userInfo);
            
            if (!userExists) {
                // 계정 비활성화 시 알림 후 로그인 페이지로 리다이렉트
                model.addAttribute("alertMessage", "이 계정은 비활성화 상태입니다. 관리자에게 문의하세요.");
                return "user/member/login";
            }

            // 4. 사용자 정보 세션에 저장
            String googleId = (String) userInfo.get("id");
            UserDTO user = service.findUserByGoogleId(googleId);

            HttpSession session = request.getSession();
            session.setAttribute("uname", user.getName());
            session.setAttribute("id", user.getId());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("dob", user.getDob());
            session.setAttribute("phone_number", user.getPhone_number());
            session.setAttribute("provider", user.getProvider());
            session.setAttribute("created_at", user.getFormattedCreatedAt());
            
            System.out.println("### User logged in: " + user.getName());
            return "redirect:/"; // 로그인 성공 시 메인 페이지 이동
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("alertMessage", "로그인 중 오류가 발생했습니다.");
            return "user/member/login"; // 오류 발생 시 로그인 페이지로 이동
        }
    }
}