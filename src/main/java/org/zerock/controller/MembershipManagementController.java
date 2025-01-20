package org.zerock.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.dto.BoardDTO;
import org.zerock.dto.UserDTO;
import org.zerock.service.PostService;
import org.zerock.service.UserService;

@Controller
@RequestMapping("/admin/*")
public class MembershipManagementController {
	
	 	private final UserService userService;
	    private final PostService postService;

	    public MembershipManagementController(UserService userService, PostService postService) {
	        this.userService = userService;
	        this.postService = postService;
	    }

	    @GetMapping("/users")
	    public String getAllUsers(Model model) {
	    	List<UserDTO> users = userService.getAllUsersWithPostCount();
	    	System.out.println("users: " + users);
	        model.addAttribute("users", users);
	        return "admin/management/users";  // users.jsp와 연결
	    }

	    @GetMapping("/users/{userId}/posts")
	    public String getUserPosts(@PathVariable Long userId, Model model) {
	        System.out.println("Fetching user data for userId: " + userId);
	        UserDTO user = userService.getUserById(userId);
	        List<BoardDTO> posts = postService.getPostsByUserId(userId);

	        // 로그로 데이터 확인
	        System.out.println("Fetched user: " + user);
	        System.out.println("Fetched posts: " + posts);

	        model.addAttribute("user", user);
	        model.addAttribute("posts", posts);
	        
	        return "admin/management/userposts";  // userPosts.jsp와 연결
	    }

	    @GetMapping("/users/{userId}/posts/{postId}")
	    public String getPostDetail(@PathVariable Long userId, @PathVariable Long postId, Model model) {
	        model.addAttribute("user", userService.getUserById(userId));
	        model.addAttribute("board", postService.getPostById(postId));
	        return "admin/board/view";  // postDetail.jsp와 연결
	    }

	    @PostMapping("/users/{email}/ban")
	    public String banUser(@PathVariable String email) {
	        // email은 URL 디코딩된 상태로 처리됩니다.
	        userService.banUser(email);
	        return "redirect:/admin/users";
	    }

	    @PostMapping("/users/{email}/unban")
	    public String unbanUser(@PathVariable String email) {
	        // email은 URL 디코딩된 상태로 처리됩니다.
	        userService.unbanUser(email);
	        return "redirect:/admin/users";
	    }

	
}
