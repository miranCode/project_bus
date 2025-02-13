package org.zerock.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		return boardMapper.selectAllBusnum(); // 오류 발생시 확인: 메서드 이름, 반환 타입
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

	public List<BoardDTO> statusList(int page, int pageSize, String sort, String order) {
		int offset = (page - 1) * pageSize;
		return boardMapper.selectBoards(offset, pageSize, sort, order);
	}

	@Override
	public List<BoardDTO> getBoardList(String sort, String order, int page, int page_size) {
		// sort와 order를 Map에 넣어서 전달
		int offset = (page - 1) * page_size;

		Map<String, Object> params = new HashMap<>();
		params.put("sort", sort);
		params.put("order", order);
		params.put("offset", offset);
		params.put("limit", page_size);

		return boardMapper.getBoardList(params); // 수정된 메서드 호출
	}

	@Override
	public List<BoardDTO> getBoardList2(String sort, String order) {
		// sort와 order를 Map에 넣어서 전달
		Map<String, Object> params = new HashMap<>();
		params.put("sort", sort);
		params.put("order", order);

		return boardMapper.getBoardList(params); // 수정된 메서드 호출
	}

	@Override
	public int getBusRouteCount(String rteNm) {
		return boardMapper.getBusRouteCount(rteNm);
	}

	@Override
	public List<Map<String, Object>> getAllRouteCounts() {
		return boardMapper.getAllRouteCounts();
	}

	@Override
	public int getRteNmCount(String rteNm) {
		return boardMapper.getRteNmCount(rteNm);
	}

	@Override
	public int getStatusCount1(String status) {
		return boardMapper.countByStatus1(status); // 상태별 게시글 수 카운트
	}

	@Override
	public int getStatusCount2(String status) {
		return boardMapper.countByStatus2(status); // 상태별 게시글 수 카운트
	}

	@Override
	public int getStatusCount3(String status) {
		return boardMapper.countByStatus3(status); // 상태별 게시글 수 카운트
	}

	@Override
	public List<BoardDTO> getAllBoards() {
		return boardMapper.findAllBoards();
	}

	@Override
	public List<BoardDTO> getBoardsByStatus(String status) {
		return boardMapper.findBoardsByStatus(status);
	}

	@Override
	public int getCountByStatus(String status) {
		return boardMapper.countBoardsByStatus(status);
	}
}
