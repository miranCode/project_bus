package org.zerock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.dto.BoardDTO;
import org.zerock.service.BoardService;

@Controller
@RequestMapping("/qna")
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 게시글 작성 폼
    @GetMapping("/write")
    public String writeForm() {
        return "user/board/write"; // JSP 경로
    }

    // 게시글 작성 처리
    @PostMapping("/write")
    public String write(@ModelAttribute BoardDTO boardDTO) {
        boardService.register(boardDTO);  // 게시글 등록
        return "redirect:/qna/list"; // 목록 페이지로 리다이렉트
    }

    // 게시글 목록
    @GetMapping("/list")
    public String list(Model model) {
        List<BoardDTO> boardList = boardService.getList();
        model.addAttribute("boardList", boardList);
        return "admin/board/list"; // 게시글 목록을 표시할 JSP 페이지
    }

    // 게시글 상세보기
    @GetMapping("/view/{bno}")
    public String view(@PathVariable Long bno, Model model) {
        BoardDTO boardDTO = boardService.getDetail(bno);
        model.addAttribute("board", boardDTO);
        return "admin/board/view"; // 상세보기 JSP 페이지
    }
}
