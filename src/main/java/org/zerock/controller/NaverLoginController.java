package org.zerock.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.dto.UserDTO;
import org.zerock.mapper.NaverLoginMapper;
import org.zerock.service.NaverLoginService;

@Controller
public class NaverLoginController {

    @Autowired
    private NaverLoginService naverLoginService;
    
    @Autowired
    private NaverLoginMapper mapper;

    @GetMapping("/naver/login")
    public String loginWithNaver() {
        return "redirect:" + naverLoginService.getAuthorizationUrl();
    }

    @GetMapping("/naver/callback")
    public String callback(@RequestParam String code, @RequestParam String state, HttpSession session, Model model) {
        try {
            String accessToken = naverLoginService.getAccessToken(code, state);
            UserDTO userInfo = naverLoginService.getUserInfo(accessToken);

            // 사용자 정보 DB 조회
            UserDTO login = mapper.selectNaverMemberById(userInfo.getId());

            if (login != null) {
                // 비활성화 계정 체크
                if (!login.getIsActive()) {
                    model.addAttribute("alertMessage", "이 계정은 비활성화 상태입니다. 관리자에게 문의하세요.");
                    return "user/member/login";
                }

                // 활성 사용자 로그인 처리
                session.setAttribute("uname", login.getName());
                session.setAttribute("id", login.getId());
                session.setAttribute("email", login.getEmail());
                session.setAttribute("dob", login.getDob());
                session.setAttribute("phone_number", login.getPhone_number());
                session.setAttribute("provider", login.getProvider());
                session.setAttribute("created_at", login.getFormattedCreatedAt());
                System.out.println("로그인 성공: " + login.getName());
                return "redirect:/";
            } else {
                model.addAttribute("alertMessage", "사용자 정보를 찾을 수 없습니다.");
                return "user/member/login";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("alertMessage", "로그인 중 오류가 발생했습니다.");
            return "user/member/login";
        }
    }

    @PostMapping("/naver/loginfo")
    public ResponseEntity<String> handleNaverLoginInfo(@RequestBody UserDTO loginfo, HttpSession session) {
        try {
            if (loginfo == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("loginfo is null.");
            }

            // DB에서 사용자 정보 가져오기
            UserDTO login = mapper.selectNaverMemberById(loginfo.getId());

            if (login != null) {
                if (!login.getIsActive()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이 계정은 비활성화 상태입니다. 관리자에게 문의하세요.");
                }
                
                session.setAttribute("uname", login.getName());
                session.setAttribute("id", login.getId());
                return ResponseEntity.status(HttpStatus.OK).body("Login successful");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Login failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }
}
