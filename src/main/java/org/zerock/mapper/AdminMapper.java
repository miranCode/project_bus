package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.dto.AdminDTO;

public interface AdminMapper {
	public AdminDTO login(AdminDTO mdto);
	
	public int join(AdminDTO mdto);
	
	public boolean idCheck(@Param("id") String id);
	
	public int updateLastLogin(AdminDTO mdto);
	
	// 관리자 목록을 페이징 처리하여 조회
	public List<AdminDTO> selectAdminList(@Param("startRow") int startRow, @Param("pageSize") int pageSize);
	
	// 전체 관리자 수를 반환하는 쿼리
	public int countAdmin();
	
	// 관리자 수정 쿼리
	public int updateAdmin(AdminDTO admin);
	
	public AdminDTO selectAdminById(String id);
}
