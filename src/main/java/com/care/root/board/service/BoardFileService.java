package com.care.root.board.service;

import org.springframework.web.multipart.MultipartFile;

public interface BoardFileService {
	public String getMessage(String msg,String url);
	
	public String saveFile(MultipartFile imageFileName);
	
	String IMAGE_REPO2 = "C:\\spring\\image_repo2";
}
