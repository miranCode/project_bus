package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.dto.BoardDTO;
import org.zerock.dto.BusDTO;
import org.zerock.dto.PageDTO;
import org.zerock.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Override
    public void register(BoardDTO boardDTO) {
        boardMapper.insert(boardDTO);
    }

    @Override
    public List<BoardDTO> getList(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return boardMapper.selectList(offset, pageSize);
    }

    @Override
    public BoardDTO getDetail(Long bno) {
        return boardMapper.selectByBno(bno);
       
    }

    @Override
    public int getTotalCount() {
        return boardMapper.selectTotalCount();
    }

    @Override
    public PageDTO getPageDTO(int page, int pageSize) {
        int totalCount = getTotalCount();
        return new PageDTO(page, pageSize, totalCount);
    }

    @Override
    public void updateStatusAndMemo(Long bno, String status, String memo) {
        boardMapper.updateStatusAndMemo(bno, status, memo);
    }

    
    @Override
    public List<BoardDTO> getAllBusnum() {
        return boardMapper.selectAllBusnum();  // 오류 발생시 확인: 메서드 이름, 반환 타입
    }
    

    @Autowired
    public BoardServiceImpl(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    @Override
    public BoardDTO getBoard(Long bno) {
        return boardMapper.findByBno(bno);
    }
    
    @Override
    public List<BoardDTO> getBoardsByUserId(String userId) {
        // 해당 userId로 작성한 게시글만 반환하는 쿼리 호출
        return boardMapper.getBoardsByUserId(userId);
    }
}
