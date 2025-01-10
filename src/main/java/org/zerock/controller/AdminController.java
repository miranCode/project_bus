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
	//123
	@Autowired
	AdminMapper mapper;
	
	// �α���
		@GetMapping(value="/")
		public String home() {
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
	            return "redirect:/admin/";  // �α��� �� ��ú���� �����̷�Ʈ
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
	
	// ������ �߰� 
	@GetMapping(value="join")
	public String join() {
		return "admin/member/addAdmin";
	}

	@PostMapping(value = "join")
	public String joinPro(AdminDTO mdto) {
	    System.out.println("ȸ������ ��û�� ����: " + mdto);
	    
	    // ��й�ȣ �ؽ�
	    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    String hashedPassword = encoder.encode(mdto.getPw());
	    mdto.setPw(hashedPassword);  // �ؽõ� ��й�ȣ�� ����
	    
	    // id�� pw �� �� üũ
	    if (mdto == null || mdto.getId().trim().isEmpty()) {
	        return "redirect:/admin/join";
	    }

	    if (mdto.getPw() == null || mdto.getPw().trim().isEmpty()) {
	        return "redirect:/admin/join";
	    }

	    // regidate�� null�̸� ���� ��¥�� ���� (db���� �⺻������ ó���� ���� ����)
	    if (mdto.getRegidate() == null) {
	        mdto.setRegidate(new Date());  // ���� ��¥�� ����
	    }

	    // ������ �߰�
	    int result = mapper.join(mdto);
	    if (result > 0) {
	        return "redirect:/admin/login";  // ���� �� �α��� �������� �����̷�Ʈ
	    }

	    // ���� �� ���� �������� �����̷�Ʈ
	    return "redirect:/admin/join";
	}
	
	//Id �ߺ� Ȯ��
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
		System.out.println("�ߺ�" + result);
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
	                             @RequestParam("level") String level,
	                             @RequestParam("access") String access) {
	    for (String id : selectedIds) {
	        AdminDTO admin = new AdminDTO();
	        admin.setId(id);
	        admin.setLevel(level);
	        admin.setAccess(access);
	        mapper.updateAdmin(admin);  // DB���� �������� ���� ����
	    }
	    return "redirect:/admin/manageAccount";  // ���� �� �ٽ� ������ ��� �������� �����̷�Ʈ
	}
} 