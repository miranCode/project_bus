package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.dto.BoardDTO;
import org.zerock.mapper.PostMapper;

@Service
public class PostService {
	
	 private final PostMapper postMapper;

	    public PostService(PostMapper postMapper) {
	        this.postMapper = postMapper;
	    }

	    public List<BoardDTO> getPostsByUserId(Long userId) {
	        return postMapper.findPostsByUserId(userId);
	    }

	    public BoardDTO getPostById(Long postId) {
	        return postMapper.findPostById(postId);
	    }
}
