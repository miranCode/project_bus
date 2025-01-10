package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.zerock.dto.BoardDTO;

@Mapper
public interface BoardMapper {
    void insert(BoardDTO boardDTO);
    List<BoardDTO> selectList();
    
    


    // 게시글 상세 조회
    BoardDTO selectByBno(Long bno);
}



