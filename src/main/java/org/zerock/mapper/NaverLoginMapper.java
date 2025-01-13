package org.zerock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.zerock.dto.UserDTO;

@Mapper
public interface NaverLoginMapper {

	public void insertNaverMember(UserDTO mdto);

	public UserDTO selectNaverMemberById(String id);

	public UserDTO updateNaverMember(UserDTO mdto);

	public void deleteNaverMember(String id);
}