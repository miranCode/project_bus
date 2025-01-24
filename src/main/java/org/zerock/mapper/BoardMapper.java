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

	List<BoardDTO> selectAllBusnum();

	BoardDTO findByBno(Long bno);

	List<BoardDTO> getBoardsByUserId(String userId);

	List<BoardDTO> mainList();

	List<Map<String, Object>> sCount();

	List<BoardDTO> selectBoards(int offset, int pageSize, String sort, String order);

	List<BoardDTO> selectBoardList(Map<String, Object> params);

	List<BoardDTO> getBoardList(String sort, String order);

	List<BoardDTO> getBoardList(Map<String, Object> params);

	List<BoardDTO> getBoardList2(String sort, String order);

	List<BoardDTO> getBoardList2(Map<String, Object> params);

	int getBusRouteCount(String rteNm);

	List<Map<String, Object>> getAllRouteCounts();

	Map<String, Long> countByRteNm();

	List<BoardDTO> getAllBusnum(); // 전체 버스 노선 조회

	List<BoardDTO> getBoardsByRteNm(String rteNm);

	int getRteNmCount(String rteNm);

	int countByStatus1(String status);

	int countByStatus2(String status);

	int countByStatus3(String status);

	List<BoardDTO> findAllBoards(); // 전체 게시글 가져오기

	List<BoardDTO> findBoardsByStatus(String status); // 특정 상태의 게시글 가져오기

	int countBoardsByStatus(String status);
}
