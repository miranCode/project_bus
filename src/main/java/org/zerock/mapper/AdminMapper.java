package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.dto.AdminDTO;

public interface AdminMapper {
	public AdminDTO login(AdminDTO mdto);
	
	public int join(AdminDTO mdto);
	
	public boolean idCheck(@Param("id") String id);
	
	public int updateLastLogin(AdminDTO mdto);
	
	// ������ ����� ����¡ ó���Ͽ� ��ȸ
	public List<AdminDTO> selectAdminList(@Param("startRow") int startRow, @Param("pageSize") int pageSize);
	
	// ��ü ������ ���� ��ȯ�ϴ� ����
	public int countAdmin();
	
	// ������ ���� ����
	public int updateAdmin(AdminDTO admin);
	
	public AdminDTO selectAdminById(String id);
}
