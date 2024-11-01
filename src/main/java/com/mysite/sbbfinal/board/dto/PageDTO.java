package com.mysite.sbbfinal.board.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PageDTO {
	
	private int page;
	private int size; //limit
	private int totalPages;
	private long totalElements;
	private List<Board> content;

}
