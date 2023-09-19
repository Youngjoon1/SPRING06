package com.care.root.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.care.root.dto.BoardDTO;
import com.care.root.dto.BoardRepDTO;
import com.care.root.mybatis.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired BoardMapper mapper;
	@Autowired BoardFileService bfs;
	
	public Map<String,Object> boardAllList(int num) {
		int pageLetter = 3; // 몇개씩 표현할건지
		int allCount = mapper.selectBoardCount(); // 총 글의 개수
		int repeat = allCount/pageLetter;
		if(allCount % pageLetter != 0) {
			repeat++;
		}
		
		int end = num * pageLetter;
		int start = end + 1 - pageLetter;
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("repeat", repeat);
		map.put("list",  mapper.boardAllList(start,end));
		
		return map;
		
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
	
	public void addReply(BoardRepDTO dto) {
		try {
			mapper.addReply(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<BoardRepDTO> getRepList(int write_group) {
		return mapper.getRepList(write_group);
	}
	

}
