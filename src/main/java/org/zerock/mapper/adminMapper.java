package org.zerock.mapper;

import org.apache.ibatis.annotations.Param;
import org.zerock.dto.adminDTO;

public interface adminMapper {

		public adminDTO login(adminDTO mdto);
		
		public int join(adminDTO mdto);
		
		public int idCheck(@Param("id") String id);
		
		public boolean selectId(String id);
}
