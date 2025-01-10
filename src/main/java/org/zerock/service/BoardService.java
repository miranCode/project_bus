
package org.zerock.service;

import org.zerock.dto.BoardDTO;
import java.util.List;

public interface BoardService {
    void register(BoardDTO boardDTO);
    
    List<BoardDTO> getList(); 
    BoardDTO getDetail(Long bno);
}

