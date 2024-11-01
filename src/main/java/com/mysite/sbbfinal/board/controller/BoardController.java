package com.mysite.sbbfinal.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysite.sbbfinal.board.dto.Board;
import com.mysite.sbbfinal.board.dto.PageDTO;
import com.mysite.sbbfinal.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	
	// /boards?page=2&size=10
		@GetMapping
		public String getBoard(@RequestParam(name="page", defaultValue="1") int page,
				@RequestParam(name="size", defaultValue="10") int size,
				Model model) {
			PageDTO pageDTO = boardService.getBoardList(page, size);
			model.addAttribute("pageDTO", pageDTO);
			return "/board/board-list";
		}
		
		@GetMapping("/{id}")
		@ResponseBody
		public Board getBoard(@PathVariable("id") Integer id) {
			return boardService.getBoardById(id);
		}

}
