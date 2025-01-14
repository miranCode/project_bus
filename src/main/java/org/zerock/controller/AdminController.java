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
import org.springframework.web.bind.annotation.ModelAttribute;
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
	            session.setMaxInactiveInterval(10 * 60);  // 10분(600초)
	            return "redirect:/admin/test";  // 로그인 후 대시보드로 리다이렉트
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
	
	@GetMapping(value="logout")
	public String logout(HttpSession session) {
	    session.invalidate();  // 세션 무효화
	    return "redirect:/admin/login";  // 로그아웃 후 로그인 페이지로 리다이렉트
	}
	
	
	// 관리자 추가 (GET 방식, 폼 화면만 처리)
	@GetMapping(value="join")
	public String join(@RequestParam(value = "id", required = false) String id, Model model) {
	    if (id != null) {
	        // id가 있을 경우 해당 관리자의 정보 조회
	        AdminDTO admin = mapper.selectAdminById(id);
	        model.addAttribute("admin", admin); // 관리자 정보를 모델에 추가
	    }
	    return "admin/member/addAdmin";  // 관리자 추가 화면
	}
	
	@PostMapping(value="join")
	public String join(@ModelAttribute AdminDTO mdto, Model model) {
	    if (mdto.getId() != null && !mdto.getId().isEmpty()) {
	        // 기존 관리자의 정보를 수정하는 로직
	        int result = mapper.updateAdmin(mdto);
	        
	        if (result > 0) {
	            return "redirect:/admin/manageAccount";  // 수정 후 관리자 목록 페이지로 리다이렉트
	        }
	    }

	    // 새 관리자를 추가하는 로직 (기존 ID 중복 검사 및 추가)
	    if (mapper.idCheck(mdto.getId())) {
	        model.addAttribute("idError", "이미 존재하는 ID입니다.");
	        return "admin/member/addAdmin";  // 중복 ID가 있을 경우 관리자 추가 페이지로 다시 리다이렉트
	    }

	    // 비밀번호 암호화 처리
	    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    if (mdto.getPw() != null && !mdto.getPw().isEmpty()) {
	        String hashedPassword = encoder.encode(mdto.getPw());
	        mdto.setPw(hashedPassword);  // 해시된 비밀번호로 설정
	    }

	    // 새 관리자 정보 추가
	    int result = mapper.join(mdto);
	    if (result > 0) {
	        return "redirect:/admin/manageAccount";  // 관리자 목록 페이지로 리다이렉트
	    }
	    
	    // 실패 시, 관리자 추가 페이지로 리다이렉트
	    return "redirect:/admin/join";
	}


	// 관리자 추가 처리 (POST 방식)
	@PostMapping("/admin/joinPro")
	public String joinPro(@ModelAttribute AdminDTO mdto, Model model) {
	    // ID 중복 체크
	    if (mapper.idCheck(mdto.getId())) {
	        model.addAttribute("idError", "이미 존재하는 ID입니다.");
	        return "admin/member/addAdmin";  // 중복 ID가 있을 경우 관리자 추가 페이지로 다시 리다이렉트
	    }

	    // 비밀번호 암호화 처리
	    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    if (mdto.getPw() != null && !mdto.getPw().isEmpty()) {
	        String hashedPassword = encoder.encode(mdto.getPw());
	        mdto.setPw(hashedPassword);  // 해시된 비밀번호로 설정
	    }

	    // 기존 관리자의 정보를 가져와서 lastLogin 값 유지
	    if (mdto.getId() != null && !mdto.getId().isEmpty()) {
	        AdminDTO existingAdmin = mapper.selectAdminById(mdto.getId());  // 기존 관리자 정보 조회
	        if (existingAdmin != null) {
	            mdto.setLastLogin(existingAdmin.getLastLogin());  // 기존 lastLogin 값을 그대로 사용
	        } else {
	            mdto.setLastLogin(null);  // 새 관리자라면 lastLogin을 null로 설정 (필요시 다른 처리)
	        }
	    }

	    // 관리자 정보 추가
	    int result = mapper.join(mdto);
	    if (result > 0) {
	        return "redirect:/admin/manageAccount";  // 관리자 목록 페이지로 리다이렉트
	    }
	    
	    // 실패 시, 관리자 추가 페이지로 리다이렉트
	    return "redirect:/admin/join";
	}
	
	//Id 중복 확인
	@PostMapping("/idCheck")
	@ResponseBody
	public ResponseEntity<Boolean> confirmId(String id) {
	    boolean result = id != null && !id.trim().isEmpty() && !mapper.idCheck(id);
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
	                            @RequestParam Map<String, String> levels,
	                            @RequestParam Map<String, String> accesses) {
	    for (String id : selectedIds) {
	        AdminDTO admin = new AdminDTO();
	        admin.setId(id);
	        
	        // level과 access 값은 각 관리자마다 다르게 전달되므로, 해당 관리자의 값을 가져옵니다.
	        String level = levels.get("level_" + id);  // "level_${admin.id}"
	        String access = accesses.get("access_" + id);  // "access_${admin.id}"
	        
	        admin.setLevel(level);
	        admin.setAccess(access);
	        
	        // 비밀번호 변경이 있을 경우 처리 (비밀번호 처리 로직 추가)
	        if (admin.getPw() != null && !admin.getPw().isEmpty()) {
	            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	            String hashedPassword = encoder.encode(admin.getPw());
	            admin.setPw(hashedPassword);  // 비밀번호를 해시된 값으로 설정
	        }
	        
	        // 관리자 정보 업데이트
	        int result = mapper.updateAdmin(admin);
	        
	        // 결과에 따른 처리 (실패 시 리다이렉트 혹은 다른 처리를 할 수 있음)
	        if (result == 0) {
	            // 업데이트 실패 시
	            return "redirect:/admin/manageAccount?error=true";
	        }
	    }
	    
	    return "redirect:/admin/manageAccount";  // 성공 시 관리자 목록 페이지로 리다이렉트
	}
	
	@GetMapping("test")
	public String test() {
	    return "admin/member/test";  // admin/test.jsp 파일을 반환
	}
} 