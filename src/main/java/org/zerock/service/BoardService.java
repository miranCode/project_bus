package org.zerock.service;

import java.util.List;
import java.util.Map;

import org.zerock.dto.BoardDTO;

import org.zerock.dto.PageDTO;

public interface BoardService {
	void register(BoardDTO boardDTO);

	List<BoardDTO> getList(int page, int pageSize);

	BoardDTO getDetail(Long bno);

	int getTotalCount();

	PageDTO getPageDTO(int page, int pageSize);

	void updateStatusAndMemo(Long bno, String status, String memo);

	List<BoardDTO> getAllBusnum(); // 수정된 메서드

	BoardDTO getBoard(Long bno);

	List<BoardDTO> getBoardsByUserId(String userId);

	List<BoardDTO> statusList(int page, int pageSize, String sort, String order);

	List<BoardDTO> getBoardList(String sort, String order, int page, int page_size);

	List<BoardDTO> getBoardList2(String sort, String order);

	int getBusRouteCount(String rteNm);

	List<Map<String, Object>> getAllRouteCounts();

	int getRteNmCount(String rteNm);

	int getStatusCount1(String status);

	int getStatusCount2(String status);

	int getStatusCount3(String status);

	List<BoardDTO> getAllBoards(); // 전체 게시글 가져오기

	List<BoardDTO> getBoardsByStatus(String status); // 특정 상태의 게시글 가져오기

	int getCountByStatus(String status); // 특

}
