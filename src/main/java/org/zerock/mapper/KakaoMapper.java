package org.zerock.mapper;

import org.zerock.dto.UserDTO;

public interface KakaoMapper {
	
	public int klogin(UserDTO mdto);
	public UserDTO kloginGo(UserDTO mdto);
	public int kjoin(UserDTO mdto);

}