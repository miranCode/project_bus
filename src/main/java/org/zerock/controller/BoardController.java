package org.zerock.controller;

import java.util.List;

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
	                   @RequestParam(value = "sort", required = false) String sort, // sort는 required=false로 변경
	                   @RequestParam(value = "order", required = false) String order, // order도 required=false로 변경
	                   Model model) {

	    // 기본 게시판 목록 불러오기 (정렬하지 않음)
	    List<BoardDTO> boardList = boardService.getList(page, PAGE_SIZE);
	    int totalCount = boardService.getTotalCount();
	    PageDTO pageDTO = boardService.getPageDTO(page, PAGE_SIZE);

	    // 사용자가 정렬을 클릭했을 때만 정렬된 목록을 가져옴
	    if (sort != null && order != null) {
	        // 정렬된 게시판 목록 불러오기
	        List<BoardDTO> boardList1 = boardService.getBoardList(sort, order);
	        System.out.println("실행: 정렬된 목록" + boardList1); // 로그 추가

	        // boardList를 정렬된 boardList1으로 덮어쓰기
	        if (boardList1 != null) {
	            boardList = boardList1;
	        }
	    }
	    if (sort != null && order != null) {
	        // 정렬된 게시판 목록 불러오기
	        List<BoardDTO> boardList2 = boardService.getBoardList2(sort, order);
	        System.out.println("실행: 정렬된 목록" + boardList2); // 로그 추가

	        // boardList를 정렬된 boardList1으로 덮어쓰기
	        if (boardList2 != null) {
	            boardList = boardList2;
	        }
	    }

	    // 로그 추가: sort, order 값 확인
	    System.out.println("Received sort: " + sort + ", order: " + order); 

	    // 모델에 데이터 추가
	    model.addAttribute("boardList", boardList);
	    model.addAttribute("order", order); // 현재 정렬 방향
	    model.addAttribute("pageDTO", pageDTO);

	    return "admin/board/list"; // JSP로 결과 반환
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
    // 게시글 작성 처리
//    @PostMapping("/write")
//    public String write(@ModelAttribute BoardDTO boardDTO) {
//        boardService.register(boardDTO);
//        System.out.println("BoardDTO: " + boardDTO);
//        return "redirect:/qna/list";
//    }
//    


    

    @PostMapping("/updateStatus/{bno}")
    public String updateStatus(@PathVariable Long bno, @RequestParam String status, @RequestParam String memo) {
        boardService.updateStatusAndMemo(bno, status, memo);
        return "redirect:/qna/list#board-" + bno;

    }


//        private final BoardService boardService;
        private final EmailService emailService;

        public BoardController(BoardService boardService, EmailService emailService) {
            this.boardService = boardService;
            this.emailService = emailService;
        }

//        @PostMapping("/write")
//        public String write(@ModelAttribute BoardDTO boardDTO, Model model) {
//            try {
//                // 게시글 등록
//                boardService.register(boardDTO);
//
//                // 이메일 발송
//                String emailContent = "안녕하세요 " + boardDTO.getName() + "님,\n\n"
//                                    + "다음과 같은 건의사항이 접수되었습니다:\n\n"
//                                    + "제목: " + boardDTO.getTitle() + "\n"
//                                    + "내용: " + boardDTO.getContent() + "\n\n"
//                                    + "감사합니다.";
//                emailService.sendEmail(boardDTO.getEmail(), "건의사항 접수 확인", emailContent);
//
//                // 성공 메시지 추가 (선택 사항)
//                model.addAttribute("message", "건의사항이 성공적으로 접수되었습니다.");
//            } catch (Exception e) {
//                e.printStackTrace();
//                model.addAttribute("errorMessage", "건의사항 접수 중 문제가 발생했습니다.");
//            }
//
//            return "redirect:/"; // 목록 페이지로 이동
//        }
        @PostMapping("/write")
        public String write(@ModelAttribute BoardDTO boardDTO, RedirectAttributes redirectAttributes) {
            try {
                // 게시글 등록
                boardService.register(boardDTO);

                // 이메일 발송
                String emailContent = "안녕하세요 " + boardDTO.getName() + "님,\n\n"
                                    + "다음과 같은 건의사항이 접수되었습니다:\n\n"
                                    + "제목: " + boardDTO.getTitle() + "\n"
                                    + "내용: " + boardDTO.getContent() + "\n\n"
                                    + "감사합니다.";
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

    


