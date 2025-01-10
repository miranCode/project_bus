package org.zerock.mapper;

import org.zerock.dto.UserDTO;

public interface MemberMapper {
	// 회원가입 메소드
	public int join(UserDTO udto);
	
	// 사용자의 ID로 회원 정보 조회 (로그인 검증 시 사용)
    public UserDTO getUserById(String id);
	
    // 이메일 중복 검사 메소드
    public int checkEmailDuplicate(String id);
}
