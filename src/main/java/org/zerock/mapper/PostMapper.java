package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.zerock.dto.BoardDTO;

@Mapper
public interface PostMapper {
	// 특정 사용자의 모든 게시글 조회
    @Select(" SELECT name, email, title, content, regdate, rte_nm, status FROM board WHERE email = #{email} ")
    List<BoardDTO> findPostsByUserId(Long userId);

    // 특정 게시글 상세 조회
    @Select(" SELECT bno, name, email, title, content, regdate, rte_nm FROM board WHERE bno = #{bno} ")
    BoardDTO findPostById(Long postId);
}
