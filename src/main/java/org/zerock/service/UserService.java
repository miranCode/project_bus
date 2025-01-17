package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.dto.UserDTO;
import org.zerock.mapper.UserMapper;

@Service
public class UserService {
	
	private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<UserDTO> getAllUsersWithPostCount() {
    	List<UserDTO> users = userMapper.findAllUsersWithPostCount();
        
        // 데이터 확인용 로그 추가
    	 users.forEach(user -> {
             System.out.println("User Email: " + user.getEmail() + 
                                ", Active: " + user.getIsActive() + 
                                ", Post Count: " + user.getPostCount());
         });

        return users;
    }

    public UserDTO getUserById(Long userId) {
        return userMapper.findUserById(userId);
    }

 // 사용자 정지
    @Transactional
    public void banUser(String email) {
        userMapper.updateUserStatus(email, false);  // email로 활성 상태를 false로 업데이트
    }

    // 사용자 정지 해제
    @Transactional
    public void unbanUser(String email) {
        userMapper.updateUserStatus(email, true);  // email로 활성 상태를 true로 업데이트
    }
}

