package org.zerock.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.dto.UserDTO;

public interface MemberMapper {
	// 회원가입 메소드
	public int join(UserDTO udto);
	
	// 사용자의 ID로 회원 정보 조회 (로그인 검증 시 사용)
    public UserDTO getUserById(String id);
	
    // 이메일 중복 검사 메소드
    public int checkEmailDuplicate(String id);
    
    //메일 키 가져오기2
    @Select("SELECT encrypted_password FROM smtp_credentials WHERE username = #{username}")
    String getEncryptedPassword(@Param("username") String username);
}
