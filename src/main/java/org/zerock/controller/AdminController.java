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
	
	// 로그인 여부를 체크하는 메서드 추가
	private boolean isLoggedIn(HttpSession session) {
	    return session.getAttribute("id") != null;
	}

	
	// admin main
	@GetMapping(value="/")
	public String home(HttpSession session, BusTimeDTO BTdto, Model model) {
		System.out.println("aaaa");
		
		 // 세션에서 로그인 여부 확인
//	    if (session.getAttribute("id") == null) {
//	        return "redirect:/admin/login";  // 로그인되지 않았다면 로그인 페이지로 리다이렉트
//	    }
		
		try {
			List<BusTimeDTO> BTList = mService.seTime();
			List<BoardDTO> mList = bMapper.mainList();
			
			List<Map<String, Object>> sCount = bMapper.sCount();
			List<Map<String, Object>> period = aMapper.period();
			List<Map<String, Object>> mBUList = aMapper.mBUList();
			List<Map<String, Object>> routeTurn = mMapper.routeTurn();
			
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
			model.addAttribute("routeTurn", routeTurn);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("controller 臾몄젣");
		}
		
		return "admin/index";
	}
	
	// 占싸깍옙占쏙옙
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
	        // 占쌉뤄옙占쏙옙 占쏙옙橘占싫ｏ옙占� DB占쏙옙 占쏙옙占쏙옙占� 占쏙옙橘占싫� 占쏙옙
	        if (encoder.matches(mdto.getPw(), login.getPw())) {
	        	// 占싸깍옙占쏙옙 占쏙옙占쏙옙 占쏙옙, 占쏙옙占쏙옙 占시곤옙占쏙옙占쏙옙 lastLogin 占쏙옙占쏙옙
                login.setLastLogin(new Date());  // 占쏙옙占쏙옙 占시곤옙占쏙옙占쏙옙 占쏙옙占쏙옙
                mapper.updateLastLogin(login);  // DB占쏙옙 lastLogin 占쏙옙占쏙옙占쏙옙트
                
                // 占쏙옙占쏙옙占쏙옙 占쏙옙占싼곤옙 占싸깍옙占쏙옙 占쏙옙占쏙옙 처占쏙옙
                if ("BLOCKED".equals(login.getAccess())) {
                    model.addAttribute("loginError", "占싸깍옙占쏙옙 占쏙옙占쌤듸옙 占쏙옙占쏙옙占쌉니댐옙.");
                    return "admin/member/login";  // 占쏙옙占쌤듸옙 占쏙옙占쏙옙占쏙옙 占쏙옙占� 占싸깍옙占쏙옙 占쏙옙占쏙옙 처占쏙옙
                }
	            // 占쏙옙橘占싫ｏ옙占� 占쏙옙치占싹몌옙 占싸깍옙占쏙옙 占쏙옙占쏙옙
	            session.setAttribute("id", login.getId());
	            session.setAttribute("name", login.getName());
	            session.setAttribute("level", login.getLevel());
	            session.setMaxInactiveInterval(10 * 60);  // 10占쏙옙(600占쏙옙)
	            return "redirect:/admin/";  // 占싸깍옙占쏙옙 占쏙옙 占쏙옙첬占쏙옙占쏙옙 占쏙옙占쏙옙占싱뤄옙트
	        } else {
	            // 占쏙옙橘占싫� 占쏙옙占쏙옙치 占쏙옙 占싸깍옙占쏙옙 占쏙옙占쏙옙 처占쏙옙
	            model.addAttribute("loginError", "占쏙옙橘占싫ｏ옙占� 占쏙옙치占쏙옙占쏙옙 占십쏙옙占싹댐옙.");
	            return "admin/member/login";  // 占싸깍옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
	        }
	    } else {
	        // 占쏙옙占싱듸옙 占쏙옙치占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占싸깍옙占쏙옙 占쏙옙占쏙옙 처占쏙옙
	        model.addAttribute("loginError", "占쏙옙치占싹댐옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占싹댐옙.");
	        return "admin/member/login";  // 占싸깍옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
	    }
		
	}
	
	@GetMapping(value="logout")
	public String logout(HttpSession session) {
	    session.invalidate();  
	    System.out.println("로그아웃 작동");
	    return "redirect:/admin/login";  
	}
	
	
	// 占쏙옙占쏙옙占쏙옙 占쌩곤옙 (GET 占쏙옙占�, 占쏙옙 화占썽만 처占쏙옙)
	@GetMapping(value="join")
	public String join(@RequestParam(value = "id", required = false) String id, Model model, HttpSession session) {
		if (session.getAttribute("id") == null) {
	        return "redirect:/admin/login";  // 로그인되지 않았다면 로그인 페이지로 리다이렉트
	    }
	    if (id != null) {
	        // id占쏙옙 占쏙옙占쏙옙 占쏙옙占� 占쌔댐옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙회
	        AdminDTO admin = mapper.selectAdminById(id);
	        model.addAttribute("admin", admin); // 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏜델울옙 占쌩곤옙
	    }
	    return "admin/member/addAdmin";  // 占쏙옙占쏙옙占쏙옙 占쌩곤옙 화占쏙옙
	}
	
	@PostMapping(value="join")
	public String join(@ModelAttribute AdminDTO mdto, Model model, HttpSession session) {
		if (session.getAttribute("id") == null) {
	        return "redirect:/admin/login";  // 로그인되지 않았다면 로그인 페이지로 리다이렉트
	    }
	    if (mdto.getId() != null && !mdto.getId().isEmpty()) {
	        // 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占싹댐옙 占쏙옙占쏙옙
	        int result = mapper.updateAdmin(mdto);
	        
	        if (result > 0) {
	            return "redirect:/admin/manageAccount";  // 占쏙옙占쏙옙 占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占� 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占싱뤄옙트
	        }
	    }

	    // 占쏙옙 占쏙옙占쏙옙占쌘몌옙 占쌩곤옙占싹댐옙 占쏙옙占쏙옙 (占쏙옙占쏙옙 ID 占쌩븝옙 占싯삼옙 占쏙옙 占쌩곤옙)
	    if (mapper.idCheck(mdto.getId())) {
	        model.addAttribute("idError", "占싱뱄옙 占쏙옙占쏙옙占싹댐옙 ID占쌉니댐옙.");
	        return "admin/member/addAdmin";  // 占쌩븝옙 ID占쏙옙 占쏙옙占쏙옙 占쏙옙占� 占쏙옙占쏙옙占쏙옙 占쌩곤옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쌕쏙옙 占쏙옙占쏙옙占싱뤄옙트
	    }

	    // 占쏙옙橘占싫� 占쏙옙호화 처占쏙옙
	    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    if (mdto.getPw() != null && !mdto.getPw().isEmpty()) {
	        String hashedPassword = encoder.encode(mdto.getPw());
	        mdto.setPw(hashedPassword);  // 占쌔시듸옙 占쏙옙橘占싫ｏ옙占� 占쏙옙占쏙옙
	    }

	    // 占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쌩곤옙
	    int result = mapper.join(mdto);
	    if (result > 0) {
	        return "redirect:/admin/manageAccount";  // 占쏙옙占쏙옙占쏙옙 占쏙옙占� 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占싱뤄옙트
	    }
	    
	    // 占쏙옙占쏙옙 占쏙옙, 占쏙옙占쏙옙占쏙옙 占쌩곤옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占싱뤄옙트
	    return "redirect:/admin/join";
	}


	// 占쏙옙占쏙옙占쏙옙 占쌩곤옙 처占쏙옙 (POST 占쏙옙占�)
	@PostMapping("/admin/joinPro")
	public String joinPro(@ModelAttribute AdminDTO mdto, Model model, HttpSession session) {
		if (session.getAttribute("id") == null) {
	        return "redirect:/admin/login";  // 로그인되지 않았다면 로그인 페이지로 리다이렉트
	    }
		
	    // ID 占쌩븝옙 체크
	    if (mapper.idCheck(mdto.getId())) {
	        model.addAttribute("idError", "占싱뱄옙 占쏙옙占쏙옙占싹댐옙 ID占쌉니댐옙.");
	        return "admin/member/addAdmin";  // 占쌩븝옙 ID占쏙옙 占쏙옙占쏙옙 占쏙옙占� 占쏙옙占쏙옙占쏙옙 占쌩곤옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쌕쏙옙 占쏙옙占쏙옙占싱뤄옙트
	    }

	    // 占쏙옙橘占싫� 占쏙옙호화 처占쏙옙
	    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    if (mdto.getPw() != null && !mdto.getPw().isEmpty()) {
	        String hashedPassword = encoder.encode(mdto.getPw());
	        mdto.setPw(hashedPassword);  // 占쌔시듸옙 占쏙옙橘占싫ｏ옙占� 占쏙옙占쏙옙
	    }

	    // 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占싶쇽옙 lastLogin 占쏙옙 占쏙옙占쏙옙
	    if (mdto.getId() != null && !mdto.getId().isEmpty()) {
	        AdminDTO existingAdmin = mapper.selectAdminById(mdto.getId());  // 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙회
	        if (existingAdmin != null) {
	            mdto.setLastLogin(existingAdmin.getLastLogin());  // 占쏙옙占쏙옙 lastLogin 占쏙옙占쏙옙 占쌓댐옙占� 占쏙옙占�
	        } else {
	            mdto.setLastLogin(null);  // 占쏙옙 占쏙옙占쏙옙占쌘띰옙占� lastLogin占쏙옙 null占쏙옙 占쏙옙占쏙옙 (占십울옙占� 占쌕몌옙 처占쏙옙)
	        }
	    }

	    // 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쌩곤옙
	    int result = mapper.join(mdto);
	    if (result > 0) {
	        return "redirect:/admin/manageAccount";  // 占쏙옙占쏙옙占쏙옙 占쏙옙占� 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占싱뤄옙트
	    }
	    
	    // 占쏙옙占쏙옙 占쏙옙, 占쏙옙占쏙옙占쏙옙 占쌩곤옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占싱뤄옙트
	    return "redirect:/admin/join";
	}
	
	//Id 占쌩븝옙 확占쏙옙
	@PostMapping("/idCheck")
	@ResponseBody
	public ResponseEntity<Boolean> confirmId(String id) {
	    boolean result = id != null && !id.trim().isEmpty() && !mapper.idCheck(id);
	    return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	// 占쏙옙占쏙옙占쏙옙 占쏙옙占� 占쏙옙회 占쏙옙 占쏙옙占쏙옙징 처占쏙옙
	@GetMapping("manageAccount")
	public String manageAccounts(@RequestParam(value = "page", defaultValue = "1") int currentPage, Model model, HttpSession session) {
		if (!isLoggedIn(session)) {
	        return "redirect:/admin/login";  // 로그인되지 않았다면 로그인 페이지로 리다이렉트
	    }
		
	    int pageSize = 10;  // 占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙
	    int startRow = (currentPage - 1) * pageSize;  // 占쏙옙占쏙옙 占쏙옙 占쏙옙호

	    List<AdminDTO> adminList = mapper.selectAdminList(startRow, pageSize);  // 占쏙옙占쏙옙占쏙옙 占쏙옙占� 占쏙옙회
	    int totalAdminCount = mapper.countAdmin();  // 占쏙옙체 占쏙옙占쏙옙占쏙옙 占쏙옙
	    int totalPage = (int) Math.ceil((double) totalAdminCount / pageSize);  // 占쏙옙체 占쏙옙占쏙옙占쏙옙 占쏙옙

	    model.addAttribute("adminList", adminList);
	    model.addAttribute("currentPage", currentPage);
	    model.addAttribute("totalPage", totalPage);
	    
	    return "admin/member/manageAccount";  // 占쏙옙占쏙옙占쏙옙 占쏙옙占� 占쏙옙占쏙옙占쏙옙占쏙옙 占싱듸옙
	}
	
	// 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 처占쏙옙
	@PostMapping("updateAccount")
	public String updateAccount(@RequestParam("selectedIds") List<String> selectedIds,
	                            @RequestParam Map<String, String> levels,
	                            @RequestParam Map<String, String> accesses,
	                            HttpSession session) {
		if (!isLoggedIn(session)) {
	        return "redirect:/admin/login";  // 로그인되지 않았다면 로그인 페이지로 리다이렉트
	    }
	    for (String id : selectedIds) {
	        AdminDTO admin = new AdminDTO();
	        admin.setId(id);
	        
	        // level占쏙옙 access 占쏙옙占쏙옙 占쏙옙 占쏙옙占쏙옙占쌘몌옙占쏙옙 占쌕몌옙占쏙옙 占쏙옙占쌨되므뤄옙, 占쌔댐옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占심니댐옙.
	        String level = levels.get("level_" + id);  // "level_${admin.id}"
	        String access = accesses.get("access_" + id);  // "access_${admin.id}"
	        
	        admin.setLevel(level);
	        admin.setAccess(access);
	        
	        // 占쏙옙橘占싫� 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占� 처占쏙옙 (占쏙옙橘占싫� 처占쏙옙 占쏙옙占쏙옙 占쌩곤옙)
	        if (admin.getPw() != null && !admin.getPw().isEmpty()) {
	            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	            String hashedPassword = encoder.encode(admin.getPw());
	            admin.setPw(hashedPassword);  // 占쏙옙橘占싫ｏ옙占� 占쌔시듸옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
	        }
	        
	        // 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙트
	        int result = mapper.updateAdmin(admin);
	        
	        // 占쏙옙占쏙옙占� 占쏙옙占쏙옙 처占쏙옙 (占쏙옙占쏙옙 占쏙옙 占쏙옙占쏙옙占싱뤄옙트 혹占쏙옙 占쌕몌옙 처占쏙옙占쏙옙 占쏙옙 占쏙옙 占쏙옙占쏙옙)
	        if (result == 0) {
	            // 占쏙옙占쏙옙占쏙옙트 占쏙옙占쏙옙 占쏙옙
	            return "redirect:/admin/manageAccount?error=true";
	        }
	    }
	    
	    return "redirect:/admin/manageAccount";  // 占쏙옙占쏙옙 占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占� 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占싱뤄옙트
	}
} 