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
import org.zerock.dto.BusTimeDTO;
import org.zerock.mapper.AdminMapper;
import org.zerock.service.MainService;

@Controller
@RequestMapping("/admin/*") 
public class AdminController {
	
	@Autowired
	AdminMapper mapper;
	
	@Autowired
	MainService mService;
	
	
	// admin main
	@GetMapping(value="/")
	public String home(BusTimeDTO BTdto, Model model) {
		try {
			List<BusTimeDTO> BTList = mService.seTime();
			System.out.println(BTdto);
			model.addAttribute("busTimeList", BTList);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("controller 문제");
		}
		
		return "admin/index";
	}
	
	// �α���
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
	        // �Է��� ��й�ȣ�� DB�� ����� ��й�ȣ ��
	        if (encoder.matches(mdto.getPw(), login.getPw())) {
	        	// �α��� ���� ��, ���� �ð����� lastLogin ����
                login.setLastLogin(new Date());  // ���� �ð����� ����
                mapper.updateLastLogin(login);  // DB�� lastLogin ������Ʈ
                
                // ������ ���Ѱ� �α��� ���� ó��
                if ("BLOCKED".equals(login.getAccess())) {
                    model.addAttribute("loginError", "�α��� ���ܵ� �����Դϴ�.");
                    return "admin/member/login";  // ���ܵ� ������ ��� �α��� ���� ó��
                }
	            // ��й�ȣ�� ��ġ�ϸ� �α��� ����
	            session.setAttribute("id", login.getId());
	            session.setAttribute("name", login.getName());
	            session.setAttribute("level", login.getLevel());
	            session.setMaxInactiveInterval(10 * 60);  // 10��(600��)
	            return "redirect:/admin/test";  // �α��� �� ��ú���� �����̷�Ʈ
	        } else {
	            // ��й�ȣ ����ġ �� �α��� ���� ó��
	            model.addAttribute("loginError", "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
	            return "admin/member/login";  // �α��� �������� ����
	        }
	    } else {
	        // ���̵� ��ġ���� ������ �α��� ���� ó��
	        model.addAttribute("loginError", "��ġ�ϴ� ������ �����ϴ�.");
	        return "admin/member/login";  // �α��� �������� ����
	    }
		
	}
	
	@GetMapping(value="logout")
	public String logout(HttpSession session) {
	    session.invalidate();  // ���� ��ȿȭ
	    return "redirect:/admin/login";  // �α׾ƿ� �� �α��� �������� �����̷�Ʈ
	}
	
	
	// ������ �߰� (GET ���, �� ȭ�鸸 ó��)
	@GetMapping(value="join")
	public String join(@RequestParam(value = "id", required = false) String id, Model model) {
	    if (id != null) {
	        // id�� ���� ��� �ش� �������� ���� ��ȸ
	        AdminDTO admin = mapper.selectAdminById(id);
	        model.addAttribute("admin", admin); // ������ ������ �𵨿� �߰�
	    }
	    return "admin/member/addAdmin";  // ������ �߰� ȭ��
	}
	
	@PostMapping(value="join")
	public String join(@ModelAttribute AdminDTO mdto, Model model) {
	    if (mdto.getId() != null && !mdto.getId().isEmpty()) {
	        // ���� �������� ������ �����ϴ� ����
	        int result = mapper.updateAdmin(mdto);
	        
	        if (result > 0) {
	            return "redirect:/admin/manageAccount";  // ���� �� ������ ��� �������� �����̷�Ʈ
	        }
	    }

	    // �� �����ڸ� �߰��ϴ� ���� (���� ID �ߺ� �˻� �� �߰�)
	    if (mapper.idCheck(mdto.getId())) {
	        model.addAttribute("idError", "�̹� �����ϴ� ID�Դϴ�.");
	        return "admin/member/addAdmin";  // �ߺ� ID�� ���� ��� ������ �߰� �������� �ٽ� �����̷�Ʈ
	    }

	    // ��й�ȣ ��ȣȭ ó��
	    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    if (mdto.getPw() != null && !mdto.getPw().isEmpty()) {
	        String hashedPassword = encoder.encode(mdto.getPw());
	        mdto.setPw(hashedPassword);  // �ؽõ� ��й�ȣ�� ����
	    }

	    // �� ������ ���� �߰�
	    int result = mapper.join(mdto);
	    if (result > 0) {
	        return "redirect:/admin/manageAccount";  // ������ ��� �������� �����̷�Ʈ
	    }
	    
	    // ���� ��, ������ �߰� �������� �����̷�Ʈ
	    return "redirect:/admin/join";
	}


	// ������ �߰� ó�� (POST ���)
	@PostMapping("/admin/joinPro")
	public String joinPro(@ModelAttribute AdminDTO mdto, Model model) {
	    // ID �ߺ� üũ
	    if (mapper.idCheck(mdto.getId())) {
	        model.addAttribute("idError", "�̹� �����ϴ� ID�Դϴ�.");
	        return "admin/member/addAdmin";  // �ߺ� ID�� ���� ��� ������ �߰� �������� �ٽ� �����̷�Ʈ
	    }

	    // ��й�ȣ ��ȣȭ ó��
	    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    if (mdto.getPw() != null && !mdto.getPw().isEmpty()) {
	        String hashedPassword = encoder.encode(mdto.getPw());
	        mdto.setPw(hashedPassword);  // �ؽõ� ��й�ȣ�� ����
	    }

	    // ���� �������� ������ �����ͼ� lastLogin �� ����
	    if (mdto.getId() != null && !mdto.getId().isEmpty()) {
	        AdminDTO existingAdmin = mapper.selectAdminById(mdto.getId());  // ���� ������ ���� ��ȸ
	        if (existingAdmin != null) {
	            mdto.setLastLogin(existingAdmin.getLastLogin());  // ���� lastLogin ���� �״�� ���
	        } else {
	            mdto.setLastLogin(null);  // �� �����ڶ�� lastLogin�� null�� ���� (�ʿ�� �ٸ� ó��)
	        }
	    }

	    // ������ ���� �߰�
	    int result = mapper.join(mdto);
	    if (result > 0) {
	        return "redirect:/admin/manageAccount";  // ������ ��� �������� �����̷�Ʈ
	    }
	    
	    // ���� ��, ������ �߰� �������� �����̷�Ʈ
	    return "redirect:/admin/join";
	}
	
	//Id �ߺ� Ȯ��
	@PostMapping("/idCheck")
	@ResponseBody
	public ResponseEntity<Boolean> confirmId(String id) {
	    boolean result = id != null && !id.trim().isEmpty() && !mapper.idCheck(id);
	    return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	// ������ ��� ��ȸ �� ����¡ ó��
	@GetMapping("manageAccount")
	public String manageAccounts(@RequestParam(value = "page", defaultValue = "1") int currentPage, Model model) {
	    int pageSize = 10;  // �� �������� ������ ������ ��
	    int startRow = (currentPage - 1) * pageSize;  // ���� �� ��ȣ

	    List<AdminDTO> adminList = mapper.selectAdminList(startRow, pageSize);  // ������ ��� ��ȸ
	    int totalAdminCount = mapper.countAdmin();  // ��ü ������ ��
	    int totalPage = (int) Math.ceil((double) totalAdminCount / pageSize);  // ��ü ������ ��

	    model.addAttribute("adminList", adminList);
	    model.addAttribute("currentPage", currentPage);
	    model.addAttribute("totalPage", totalPage);
	    
	    return "admin/member/manageAccount";  // ������ ��� �������� �̵�
	}
	
	// ������ ���� ó��
	@PostMapping("updateAccount")
	public String updateAccount(@RequestParam("selectedIds") List<String> selectedIds,
	                            @RequestParam Map<String, String> levels,
	                            @RequestParam Map<String, String> accesses) {
	    for (String id : selectedIds) {
	        AdminDTO admin = new AdminDTO();
	        admin.setId(id);
	        
	        // level�� access ���� �� �����ڸ��� �ٸ��� ���޵ǹǷ�, �ش� �������� ���� �����ɴϴ�.
	        String level = levels.get("level_" + id);  // "level_${admin.id}"
	        String access = accesses.get("access_" + id);  // "access_${admin.id}"
	        
	        admin.setLevel(level);
	        admin.setAccess(access);
	        
	        // ��й�ȣ ������ ���� ��� ó�� (��й�ȣ ó�� ���� �߰�)
	        if (admin.getPw() != null && !admin.getPw().isEmpty()) {
	            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	            String hashedPassword = encoder.encode(admin.getPw());
	            admin.setPw(hashedPassword);  // ��й�ȣ�� �ؽõ� ������ ����
	        }
	        
	        // ������ ���� ������Ʈ
	        int result = mapper.updateAdmin(admin);
	        
	        // ����� ���� ó�� (���� �� �����̷�Ʈ Ȥ�� �ٸ� ó���� �� �� ����)
	        if (result == 0) {
	            // ������Ʈ ���� ��
	            return "redirect:/admin/manageAccount?error=true";
	        }
	    }
	    
	    return "redirect:/admin/manageAccount";  // ���� �� ������ ��� �������� �����̷�Ʈ
	}
} 