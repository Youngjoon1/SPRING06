package com.care.root.mybatis;

import java.util.List;

import com.care.root.dto.BoardDTO;

public interface BoardMapper {
	public List<BoardDTO> boardAllList();
	
	public int writeSave(BoardDTO dto);
	
	public BoardDTO contentView(int writeNo);
	
	public void upHit(int writeNo);
	
	public int modify(BoardDTO dto);
	
	public int delete(int writeNo);
}
