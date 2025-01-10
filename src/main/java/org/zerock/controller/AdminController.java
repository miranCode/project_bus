package org.zerock.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.dto.AdminDTO;
import org.zerock.mapper.AdminMapper;

@Controller
@RequestMapping("/admin/*") 
public class AdminController {
	
	@Autowired
	AdminMapper mapper;
	
	// 로그인
		@GetMapping(value="/")
		public String home() {
			return "admin/index";
		}
	
	// 로그인
	@GetMapping(value="login")
	public String loginGo() {
		return "admin/member/login";
	}

	@PostMapping(value="login")
	public String loginPro(AdminDTO mdto, HttpSession session, Model model) {

		System.out.println(mdto);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		AdminDTO login = mapper.login(mdto);
		
		if (login != null) {
	        // 입력한 비밀번호와 DB에 저장된 비밀번호 비교
	        if (encoder.matches(mdto.getPw(), login.getPw())) {
	        	// 로그인 성공 시, 현재 시간으로 lastLogin 갱신
                login.setLastLogin(new Date());  // 현재 시간으로 설정
                mapper.updateLastLogin(login);  // DB에 lastLogin 업데이트
                
                // 관리자 권한과 로그인 차단 처리
                if ("BLOCKED".equals(login.getAccess())) {
                    model.addAttribute("loginError", "로그인 차단된 계정입니다.");
                    return "admin/member/login";  // 차단된 계정일 경우 로그인 실패 처리
                }
	            // 비밀번호가 일치하면 로그인 성공
	            session.setAttribute("id", login.getId());
	            session.setAttribute("name", login.getName());
	            session.setAttribute("level", login.getLevel());
	            return "redirect:/admin/";  // 로그인 후 대시보드로 리다이렉트
	        } else {
	            // 비밀번호 불일치 시 로그인 실패 처리
	            model.addAttribute("loginError", "비밀번호가 일치하지 않습니다.");
	            return "admin/member/login";  // 로그인 페이지로 리턴
	        }
	    } else {
	        // 아이디가 일치하지 않으면 로그인 실패 처리
	        model.addAttribute("loginError", "일치하는 정보가 없습니다.");
	        return "admin/member/login";  // 로그인 페이지로 리턴
	    }
		
	}
	
	// 관리자 추가 
	@GetMapping(value="join")
	public String join() {
		return "admin/member/addAdmin";
	}

	@PostMapping(value = "join")
	public String joinPro(AdminDTO mdto) {
	    System.out.println("회원가입 요청이 들어옴: " + mdto);
	    
	    // 비밀번호 해싱
	    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    String hashedPassword = encoder.encode(mdto.getPw());
	    mdto.setPw(hashedPassword);  // 해시된 비밀번호로 설정
	    
	    // id와 pw 빈 값 체크
	    if (mdto == null || mdto.getId().trim().isEmpty()) {
	        return "redirect:/admin/join";
	    }

	    if (mdto.getPw() == null || mdto.getPw().trim().isEmpty()) {
	        return "redirect:/admin/join";
	    }

	    // regidate가 null이면 현재 날짜로 설정 (db에서 기본값으로 처리될 수도 있음)
	    if (mdto.getRegidate() == null) {
	        mdto.setRegidate(new Date());  // 현재 날짜로 설정
	    }

	    // 관리자 추가
	    int result = mapper.join(mdto);
	    if (result > 0) {
	        return "redirect:/admin/login";  // 성공 시 로그인 페이지로 리다이렉트
	    }

	    // 실패 시 가입 페이지로 리다이렉트
	    return "redirect:/admin/join";
	}
	
	//Id 중복 확인
	@PostMapping("/idCheck")
	@ResponseBody
	public ResponseEntity<Boolean> confirmId(String id) {
		System.out.println(id);
		boolean result = true;
		if(id.trim().isEmpty()) {
			result = false;
		} else {
			if (mapper.idCheck(id)) {
				result = false;
			} else {
				result = true;
			}
		}
		System.out.println("중복" + result);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	// 관리자 목록 조회 및 페이징 처리
	@GetMapping("manageAccount")
	public String manageAccounts(@RequestParam(value = "page", defaultValue = "1") int currentPage, Model model) {
	    int pageSize = 10;  // 한 페이지에 보여줄 관리자 수
	    int startRow = (currentPage - 1) * pageSize;  // 시작 행 번호

	    List<AdminDTO> adminList = mapper.selectAdminList(startRow, pageSize);  // 관리자 목록 조회
	    int totalAdminCount = mapper.countAdmin();  // 전체 관리자 수
	    int totalPage = (int) Math.ceil((double) totalAdminCount / pageSize);  // 전체 페이지 수

	    model.addAttribute("adminList", adminList);
	    model.addAttribute("currentPage", currentPage);
	    model.addAttribute("totalPage", totalPage);
	    
	    return "admin/member/manageAccount";  // 관리자 목록 페이지로 이동
	}
	
	// 관리자 수정 처리
	@PostMapping("updateAccount")
	public String updateAccount(@RequestParam("selectedIds") List<String> selectedIds,
	                             @RequestParam("level") String level,
	                             @RequestParam("access") String access) {
	    for (String id : selectedIds) {
	        AdminDTO admin = new AdminDTO();
	        admin.setId(id);
	        admin.setLevel(level);
	        admin.setAccess(access);
	        mapper.updateAdmin(admin);  // DB에서 관리자의 정보 수정
	    }
	    return "redirect:/admin/manageAccount";  // 수정 후 다시 관리자 목록 페이지로 리다이렉트
	}
} 