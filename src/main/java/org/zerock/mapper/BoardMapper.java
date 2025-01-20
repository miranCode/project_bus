package org.zerock.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.zerock.dto.BoardDTO;
import org.zerock.dto.BusDTO;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface BoardMapper {

	  

    List<BoardDTO> selectList(@Param("offset") int offset, @Param("limit") int limit);
    // 게시글 총 개수 조회
    int selectTotalCount();

    void insert(BoardDTO boardDTO);
    List<BoardDTO> selectList();    

    // 게시글 상세 조회
    BoardDTO selectByBno(Long bno);
    
    void updateStatusAndMemo(@Param("bno") Long bno, @Param("status") String status, @Param("memo") String memo);

//    List<BoardDTO> selectAllBusnum();

    List<BoardDTO> selectAllBusnum();

    BoardDTO findByBno(Long bno);
    
    List<BoardDTO> getBoardsByUserId(String userId);
    
    List<BoardDTO> mainList();
    
    List<Map<String, Object>> sCount();
    
    List<BoardDTO> selectBoards(int offset, int pageSize, String sort, String order);
	List<BoardDTO> selectBoardList(Map<String, Object> params);
	List<BoardDTO> getBoardList(String sort, String order);
	List<BoardDTO> getBoardList(Map<String, Object> params);

}


	


    



