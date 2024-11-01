package com.mysite.sbbfinal.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mysite.sbbfinal.board.dto.Board;

@Mapper
public interface BoardMapper {
	
	List<Board> getBoardList(@Param("offset") int offset, @Param("size") int size);
	Integer countTotal();
	Board getBoardById(Integer id);


}
