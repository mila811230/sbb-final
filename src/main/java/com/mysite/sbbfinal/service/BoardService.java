package com.mysite.sbbfinal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysite.sbbfinal.board.dto.Board;
import com.mysite.sbbfinal.board.dto.PageDTO;
import com.mysite.sbbfinal.board.mapper.BoardMapper;

@Service
public class BoardService {
	
	@Autowired
	private BoardMapper boardMapper;
	
	public PageDTO getBoardList(int page, int size) {
		int offset = (page - 1) * size;
		// select * from table limit x offset y;
		int totalElements = boardMapper.countTotal();
		int totalPages = (int) Math.ceil((double) totalElements / size);
		
		List<Board> content = boardMapper.getBoardList(offset, size);
		
		PageDTO pageDTO = new PageDTO(page, size, totalPages, totalElements, content);
		return pageDTO;
	}
	
	public Board getBoardById(Integer id) {
		return boardMapper.getBoardById(id);
	}

}
