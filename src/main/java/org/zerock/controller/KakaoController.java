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

        String access_Token = service.getAccessToken(code);
        HashMap<String, Object> userInfo = service.getUserInfo(access_Token);

        if (access_Token != null) {
            mdto.setId(userInfo.get("id").toString());
            mdto.setName(userInfo.get("nickname").toString());
            mdto.setEmail(userInfo.get("email").toString());

            // 사용자가 DB에 존재하는지 체크
            int idCheck = mapper.klogin(mdto);

            // 사용자가 없을 경우 회원가입 진행
            if (idCheck == 0) {
                int result = mapper.kjoin(mdto);
                System.out.println("카카오 회원등록 완료");
            }

            // 회원가입 후 로그인 정보 재조회
            UserDTO login = mapper.kloginGo(mdto);

            if (login != null) {
            	session.setAttribute("uname", login.getName()); // 占쎄쉭占쎈�∽옙肉� 占쎄텢占쎌뒠占쎌쁽 占쎌젟癰귨옙 占쏙옙占쎌삢
                session.setAttribute("id", login.getId());
                session.setAttribute("email", login.getEmail());
                session.setAttribute("dob", login.getDob());
                session.setAttribute("phone_number", login.getPhone_number());
                session.setAttribute("provider", login.getProvider());
                System.out.println("로그인 성공: " + login.getName());
            } else {
                throw new NullPointerException("회원가입 후 로그인 정보 조회 실패");
            }

            // 로그인 성공 후 메인 페이지로 이동
            return "redirect:/";
        }

        // 로그인 실패 시 처리
        return "redirect:/login";
    }

}
