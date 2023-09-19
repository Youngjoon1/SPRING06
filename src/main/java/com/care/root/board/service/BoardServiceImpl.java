package com.care.root.board.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.care.root.dto.BoardDTO;
import com.care.root.mybatis.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired BoardMapper mapper;
	@Autowired BoardFileService bfs;
	
	public List<BoardDTO> boardAllList() {
			List<BoardDTO> list = null;
		try {
			list = mapper.boardAllList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public String writeSave(BoardDTO dto,MultipartFile imageFileName) {
		
		if(!imageFileName.isEmpty()) {
//			SimpleDateFormat fo = new SimpleDateFormat("yyyyMMddHHmmss-");
//			String FileName = fo.format(new Date());
//			FileName += imageFileName.getOriginalFilename();
//			dto.setImageFileName(FileName);
//			File saveFile = new File(IMAGE_REPO + "/" + FileName);
//			try {
//				imageFileName.transferTo(saveFile);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			dto.setImageFileName(bfs.saveFile(imageFileName));
		}else {
			dto.setImageFileName("nan");
		}
		
		int result = mapper.writeSave(dto);
		String msg="",url = "";
		if(result == 1) {
			msg="새글이 추가되었습니다";
			url="/root/board/boardAllList"; 
		}else {
			msg="문제발생";
			url="/root/board/writeForm"; 
		}
		
		return bfs.getMessage(msg,url);
	}
	
	public BoardDTO contentView(int writeNo) {
		BoardDTO dto = null;
		upHit(writeNo);
		
		try {
			dto = mapper.contentView(writeNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	private void upHit(int writeNo) {
		mapper.upHit(writeNo);
		
	}
	
	public BoardDTO getContent(int writeNo) {
		BoardDTO dto = null;
		try {
			dto = mapper.contentView(writeNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public String modify(BoardDTO dto,MultipartFile file) {
			String FilName = "";
		if(!file.isEmpty()) {
			FilName =  dto.getImageFileName();
			dto.setImageFileName(bfs.saveFile(file));
			
		}
		int result = mapper.modify(dto);
		
		String msg = "",url="";
		if(result == 1) {
			bfs.deleteImage(FilName);
			msg="수정되었습니다";
			url="/root/board/contentView?writeNo=" + dto.getWriteNo();
		}else {
			bfs.deleteImage(dto.getImageFileName());
			msg="문제 발생!!!";
			url="/root/board/modifyForm?writeNo=" + dto.getWriteNo();
		}
		return bfs.getMessage(msg, url);
	}
	
	public String delete(String fileName,int writeNo) {
		String msg = "",url="";
		int result = mapper.delete(writeNo);
		
		if(result == 1) {
			bfs.deleteImage(fileName);
			msg="삭제되었습니다";
			url="/root/board/boardAllList";
		}else {
			msg="문제 발생!!!";
			url="/root/board/contentView?writeNo=" + writeNo;
		}
		
		return bfs.getMessage(msg, url);
	}
	

}
