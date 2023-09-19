package com.care.root.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.care.root.dto.BoardDTO;
import com.care.root.dto.BoardRepDTO;

public interface BoardService {
	public Map<String,Object> boardAllList(int num);
	
	public String writeSave(BoardDTO dto,MultipartFile imageFileName);

	
	
	public BoardDTO contentView(int writeNo);
	
	public BoardDTO getContent(int writeNo);
	
	public String modify(BoardDTO dto,MultipartFile file);
	
	public String delete(String fileName,int writeNo);
	
	public void addReply(BoardRepDTO dto);
	
	public List<BoardRepDTO> getRepList(int write_group);
}
