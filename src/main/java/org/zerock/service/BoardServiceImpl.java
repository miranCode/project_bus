package org.zerock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.dto.BoardDTO;
import org.zerock.mapper.BoardMapper;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Override
    public void register(BoardDTO boardDTO) {
        boardMapper.insert(boardDTO);
    }

    @Override
    public List<BoardDTO> getList() {
        return boardMapper.selectList();
    }

    @Override
    public BoardDTO getDetail(Long bno) {
        return boardMapper.selectByBno(bno);
    }
}
