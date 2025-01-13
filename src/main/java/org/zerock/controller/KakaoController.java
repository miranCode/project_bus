package org.zerock.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.dto.UserDTO;
import org.zerock.mapper.KakaoMapper;
import org.zerock.service.KakaoService;

@Controller
public class KakaoController {
    
    @Autowired
    private KakaoMapper mapper;
    
    @Autowired
    private KakaoService service;

    @GetMapping(value="/auth/kakao/callback")
    public String kakaoLogin(UserDTO mdto, @RequestParam(value = "code", required = false) String code, HttpSession session) throws Exception {

        int idCheck = 0;
        String access_Token = service.getAccessToken(code);
        HashMap<String, Object> userInfo = service.getUserInfo(access_Token);

        // 카카오 정보가 유효한 경우
        if (access_Token != null) {
            // DTO에 값을 설정
            mdto.setId(userInfo.get("id").toString());
            mdto.setName(userInfo.get("nickname").toString());

            idCheck = mapper.klogin(mdto);
            UserDTO login = mapper.kloginGo(mdto);

            // 아이디가 저장된 경우 
            if (idCheck > 0) {
                System.out.println("카카오 아이디 있음");
            } else {
                // Mapper를 통해 DB에 회원 등록
                int result = mapper.kjoin(mdto);
                System.out.println("카카오 회원등록");
            }

            // 로그인 후 세션에 사용자 정보 저장
            if (login != null) {
                session.setAttribute("name", login.getName());  // 카카오에서 가져온 nickname을 uname에 저장
                session.setAttribute("email", login.getId());
                // session.setAttribute("phone_num", login.getPhone_number());
            } else {
                // 로그인 실패 메시지를 추가
                throw new NullPointerException("로그인 정보가 없습니다. 회원가입이 제대로 이루어지지 않았습니다.");
            }
            // 리디렉션 경로 수정
            return "redirect:/";  // 상대 경로 대신 redirect:/index 사용
        }

        // 로그인 실패 시 리디렉션
        return "redirect:/";  
    }
}
