package com.care.root.board.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.care.root.dto.BoardDTO;

public interface BoardService {
	public List<BoardDTO> boardAllList();
	
	public String writeSave(BoardDTO dto,MultipartFile imageFileName);

	String IMAGE_REPO = "C:\\spring\\image_repo";
	
	public BoardDTO contentView(int writeNo);
	
	public BoardDTO getContent(int writeNo);
	
	public String modify(BoardDTO dto,MultipartFile file);
	
	public String delete(String fileName,int writeNo);
}
