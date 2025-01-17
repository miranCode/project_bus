package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.zerock.dto.UserDTO;

@Mapper
public interface UserMapper {
    
    // 모든 사용자와 게시글 수 조회
    List<UserDTO> findAllUsersWithPostCount();
    
    // 특정 사용자 정보 조회
    UserDTO findUserById(Long userId);

    // 사용자 상태 업데이트 (정지/해제)
    void updateUserStatus(@Param("email") String email, @Param("isActive") boolean isActive);
}
