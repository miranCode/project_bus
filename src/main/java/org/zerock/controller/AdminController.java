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
import org.zerock.dto.BoardDTO;
import org.zerock.dto.BusTimeDTO;
import org.zerock.mapper.AdminMapper;
import org.zerock.mapper.ApiMapper;
import org.zerock.mapper.BoardMapper;
import org.zerock.mapper.MainMapper;
import org.zerock.service.BoardService;
import org.zerock.service.MainService;

@Controller
@RequestMapping("/admin/*") 
public class AdminController {
	
	@Autowired
	AdminMapper mapper;
	
	@Autowired
	MainService mService;
	
	@Autowired
	BoardMapper bMapper;
	
	@Autowired
	ApiMapper aMapper;
	
	@Autowired
	MainMapper mMapper;
	
	private boolean isLoggedIn(HttpSession session) {
	    return session.getAttribute("id") != null;
	}

	@GetMapping(value="/")
	public String home(HttpSession session, BusTimeDTO BTdto, Model model) {
		System.out.println("aaaa");
		
	    if (session.getAttribute("id") == null) {
	        return "redirect:/admin/login";
	    }
		
		try {
			List<BusTimeDTO> BTList = mService.seTime();
			List<BoardDTO> mList = bMapper.mainList();
			List<BoardDTO> busnumList = bMapper.selectAllBusnum();
			
			
			List<Map<String, Object>> sCount = bMapper.sCount();
			List<Map<String, Object>> period = aMapper.period();
			List<Map<String, Object>> mBUList = aMapper.mBUList();
			// List<Map<String, Object>> routeTurn = mMapper.routeTurn();
			
			List<Map<String, Object>> barList = aMapper.barList();
			
			int totalcount = bMapper.selectTotalCount();
			int dateCount = aMapper.dateCount();
			int useCount = aMapper.useCount();
			
			
			model.addAttribute("busTimeList", BTList);
			model.addAttribute("boardList", mList);
			model.addAttribute("boardS", sCount);
			model.addAttribute("totalcount", totalcount);
			model.addAttribute("period", period);
			model.addAttribute("dateCount", dateCount);
			model.addAttribute("useCount", useCount);
			model.addAttribute("mBUList", mBUList);
			model.addAttribute("busnumList", busnumList);
			
			//model.addAttribute("routeTurn", routeTurn);
			
			model.addAttribute("barList", barList);
			
			System.out.println(barList);
			System.out.println(busnumList);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("controller 오류");
		}
		
		return "admin/index";
	}
	
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
	        if (encoder.matches(mdto.getPw(), login.getPw())) {
                login.setLastLogin(new Date());
                mapper.updateLastLogin(login);
                
                if ("BLOCKED".equals(login.getAccess())) {
                    model.addAttribute("loginError", "사용이 차단된 계정입니다.");
                    return "admin/member/login";
                }
	            session.setAttribute("id", login.getId());
	            session.setAttribute("name", login.getName());
	            session.setAttribute("level", login.getLevel());
	            session.setMaxInactiveInterval(10 * 60);
	            return "redirect:/admin/";
	        } else {
	            model.addAttribute("loginError", "아이디나 비밀번호가 잘못되었습니다.");
	            return "admin/member/login";
	        }
	    } else {
	        model.addAttribute("loginError", "아이디가 존재하지 않습니다.");
	        return "admin/member/login";
	    }
		
	}
	
	@GetMapping(value="logout")
	public String logout(HttpSession session) {
	    session.invalidate();  
	    System.out.println("로그아웃 작동");
	    return "redirect:/admin/login";  
	}
	
	@GetMapping(value="join")
	public String join(@RequestParam(value = "id", required = false) String id, Model model, HttpSession session) {
		System.out.println("join() 메서드 호출됨");
		if (session.getAttribute("id") == null) {
	        return "redirect:/admin/login";
	    }
	    if (id != null) {
	        AdminDTO admin = mapper.selectAdminById(id);
	        model.addAttribute("admin", admin);
	    }
	    return "admin/member/addAdmin";
	}
	
	@PostMapping(value="join")
	public String join(@ModelAttribute AdminDTO mdto, Model model, HttpSession session) {
		System.out.println("POST join() 메서드 호출됨");
		if (session.getAttribute("id") == null) {
	        System.out.println("id Null");
			return "redirect:/admin/login";
	    }
	    if (mdto.getId() != null && !mdto.getId().isEmpty()) {
	        int result = mapper.updateAdmin(mdto);
	        
	        if (result > 0) {
	            return "redirect:/admin/manageAccount";
	        }
	    }

	    if (mapper.idCheck(mdto.getId())) {
	        model.addAttribute("idError", "이미 존재하는 ID입니다.");
	        return "admin/member/addAdmin";
	    }

	    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    if (mdto.getPw() != null && !mdto.getPw().isEmpty()) {
	        String hashedPassword = encoder.encode(mdto.getPw());
	        mdto.setPw(hashedPassword);
	    }
	    
	    // lastLogin 설정
	    mdto.setLastLogin(new Date());  // 현재 시간으로 설정
	    
	    // 로그: mdto 값 확인
	    System.out.println("Join - AdminDTO: " + mdto.toString());  // 로그 추가

	    int result = mapper.join(mdto);
	    System.out.println("Insert result: " + result);  // insert 결과 로그
	    if (result > 0) {
	        return "redirect:/admin/manageAccount";
	    }
	    
	    return "redirect:/admin/join";
	}

	@PostMapping("/admin/joinPro")
	public String joinPro(@ModelAttribute AdminDTO mdto, Model model, HttpSession session) {
		if (session.getAttribute("id") == null) {
	        return "redirect:/admin/login";
	    }
		
	    if (mapper.idCheck(mdto.getId())) {
	        model.addAttribute("idError", "이미 존재하는 ID입니다.");
	        return "admin/member/addAdmin";
	    }

	    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    if (mdto.getPw() != null && !mdto.getPw().isEmpty()) {
	        String hashedPassword = encoder.encode(mdto.getPw());
	        mdto.setPw(hashedPassword);
	    }

	    if (mdto.getId() != null && !mdto.getId().isEmpty()) {
	        AdminDTO existingAdmin = mapper.selectAdminById(mdto.getId());
	        if (existingAdmin != null) {
	            mdto.setLastLogin(existingAdmin.getLastLogin());
	        } else {
	            mdto.setLastLogin(null);
	        }
	    }

	    int result = mapper.join(mdto);
	    if (result > 0) {
	        return "redirect:/admin/manageAccount";
	    }
	    
	    return "redirect:/admin/join";
	}
	
	@PostMapping("/idCheck")
	@ResponseBody
	public ResponseEntity<Boolean> confirmId(String id) {
	    boolean result = id != null && !id.trim().isEmpty() && !mapper.idCheck(id);
	    return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("manageAccount")
	public String manageAccounts(@RequestParam(value = "page", defaultValue = "1") int currentPage, Model model, HttpSession session) {
		if (!isLoggedIn(session)) {
	        return "redirect:/admin/login";
	    }
		
	    int pageSize = 10;
	    int startRow = (currentPage - 1) * pageSize;

	    List<AdminDTO> adminList = mapper.selectAdminList(startRow, pageSize);
	    int totalAdminCount = mapper.countAdmin();
	    int totalPage = (int) Math.ceil((double) totalAdminCount / pageSize);

	    model.addAttribute("adminList", adminList);
	    model.addAttribute("currentPage", currentPage);
	    model.addAttribute("totalPage", totalPage);
	    
	    return "admin/member/manageAccount";
	}
	
	@PostMapping("updateAccount")
	public String updateAccount(@RequestParam("selectedIds") List<String> selectedIds,
	                            @RequestParam Map<String, String> levels,
	                            @RequestParam Map<String, String> accesses,
	                            HttpSession session) {
		if (!isLoggedIn(session)) {
	        return "redirect:/admin/login";
	    }
	    for (String id : selectedIds) {
	        AdminDTO admin = new AdminDTO();
	        admin.setId(id);
	        
	        String level = levels.get("level_" + id);
	        String access = accesses.get("access_" + id);
	        
	        admin.setLevel(level);
	        admin.setAccess(access);
	        
	        if (admin.getPw() != null && !admin.getPw().isEmpty()) {
	            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	            String hashedPassword = encoder.encode(admin.getPw());
	            admin.setPw(hashedPassword);
	        }
	        
	        int result = mapper.updateAdmin(admin);
	        
	        if (result == 0) {
	            return "redirect:/admin/manageAccount?error=true";
	        }
	    }
	    
	    return "redirect:/admin/manageAccount";
	}
} 