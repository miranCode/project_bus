package org.zerock.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.dto.BoardDTO;
import org.zerock.dto.PageDTO;
import org.zerock.service.BoardService;
import org.zerock.service.EmailService;

@Controller
@RequestMapping("/qna")
public class BoardController {

	@Autowired
	private BoardService boardService;

	private static final int PAGE_SIZE = 10;

	@GetMapping("/list")
	public String list(@RequestParam(value = "page", defaultValue = "1") Integer page,
	                   @RequestParam(value = "sort", defaultValue = "bno") String sort,  // 기본값을 bno로 설정
	                   @RequestParam(value = "order", defaultValue = "desc") String order, // 기본값을 desc로 설정
	                   @RequestParam(value = "rteNm", required = false) String rteNm,
	                   @RequestParam(value = "status", required = false) String status,
	                   Model model) {

	    List<BoardDTO> boardList;
	    int totalCount = boardService.getTotalCount();
	    PageDTO pageDTO = boardService.getPageDTO(page, PAGE_SIZE);

	    // 상태 필터링을 status 파라미터에 따라 조건적으로 적용
	    if (status != null) {
	        switch (status) {
	            case "미처리":
	                boardList = boardService.getBoardsByStatus("미처리");
	                break;
	            case "처리중":
	                boardList = boardService.getBoardsByStatus("처리중");
	                break;
	            case "완료":
	                boardList = boardService.getBoardsByStatus("완료");
	                break;
	            default:
	                boardList = boardService.getBoardList(sort, order, page, PAGE_SIZE);
	                break;
	        }
	    } else {
	        boardList = boardService.getBoardList(sort, order, page, PAGE_SIZE);
	    }

	    // 상태별 게시글 수
	    model.addAttribute("boardList", boardList);
	    model.addAttribute("unprocessedCount", boardService.getCountByStatus("미처리"));
	    model.addAttribute("processedCount", boardService.getCountByStatus("처리중"));
	    model.addAttribute("completedCount", boardService.getCountByStatus("완료"));
	    model.addAttribute("order", order);
	    model.addAttribute("pageDTO", pageDTO);

	    // 상태별 게시글 수 (count1, count2, count3)
	    model.addAttribute("count1", boardService.getStatusCount1(status));
	    model.addAttribute("count2", boardService.getStatusCount2(status));
	    model.addAttribute("count3", boardService.getStatusCount3(status));

	    // 모든 버스 노선 리스트 가져오기
	    List<BoardDTO> busnumList = boardService.getAllBusnum();
	    model.addAttribute("busnumList", busnumList);
	    model.addAttribute("rteNm", rteNm);

	    // rteNm에 따른 게시글 수
	    if (rteNm != null && !rteNm.isEmpty()) {
	        int count = boardService.getRteNmCount(rteNm);
	        model.addAttribute("count", count);
	    } else {
	        model.addAttribute("showModal", true);
	    }

	    return "admin/board/list";
	}


	@GetMapping("/list1")
	@ResponseBody
	public Map<String, Object> getBoardCount(@RequestParam String rteNm) {
		System.out.println("Received rteNm: " + rteNm); // 디버깅용 로그
		int count = boardService.getRteNmCount(rteNm);
		Map<String, Object> response = new HashMap<>();
		response.put("count", count);
		System.out.println("count count: " + count); // 디버깅용 로그
		return response;
	}

	// 게시글 상세보기
	@GetMapping("/view/{bno}")
	public String view(@PathVariable Long bno, Model model) {
		BoardDTO boardDTO = boardService.getDetail(bno);
		System.out.println("BoardDTO: " + boardDTO);
		model.addAttribute("board", boardDTO);
		return "admin/board/view";
	}

	// 게시글 작성 폼
	@GetMapping("/write")
	public String writeForm(HttpSession session, Model model) {
		// 로그인 여부 확인
		if (session.getAttribute("id") == null) {
			model.addAttribute("alertMessage", "로그인 후 이용 가능합니다.");
			return "user/member/alertRedirect"; // 알림 후 리다이렉트할 JSP 페이지로 이동
		}

		// 로그인 상태일 경우 게시판 작성 페이지로 이동
		List<BoardDTO> busnumList = boardService.getAllBusnum();
		model.addAttribute("busnumList", busnumList);
		return "user/board/write";
	}

	@PostMapping("/updateStatus/{bno}")
	public String updateStatus(@PathVariable Long bno, @RequestParam String status, @RequestParam String memo) {
		boardService.updateStatusAndMemo(bno, status, memo);
		return "redirect:/qna/list#board-" + bno;

	}

	private final EmailService emailService;

	public BoardController(BoardService boardService, EmailService emailService) {
		this.boardService = boardService;
		this.emailService = emailService;
	}

	@PostMapping("/write")
	public String write(@ModelAttribute BoardDTO boardDTO, RedirectAttributes redirectAttributes) {
		try {
			// 게시글 등록
			boardService.register(boardDTO);

			// 이메일 발송
			String emailContent = "안녕하세요 " + boardDTO.getName() + "님,\n\n" + "다음과 같은 건의사항이 접수되었습니다:\n\n" + "제목: "
					+ boardDTO.getTitle() + "\n" + "내용: " + boardDTO.getContent() + "\n\n" + "감사합니다.";
			emailService.sendEmail(boardDTO.getEmail(), "건의사항 접수 확인", emailContent);

			// 성공 메시지
			redirectAttributes.addFlashAttribute("message", "건의사항이 성공적으로 접수되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMessage", "건의사항 접수 중 문제가 발생했습니다.");
		}
		return "redirect:/"; // 목록 페이지로 이동
	}

}
