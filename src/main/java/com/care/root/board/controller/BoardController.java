package com.care.root.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.care.root.board.service.BoardFileService;
import com.care.root.board.service.BoardService;
import com.care.root.dto.BoardDTO;

import oracle.jdbc.proxy.annotation.Post;

@Controller
@RequestMapping("board")
public class BoardController {
	@Autowired BoardService bs; 
	
	@GetMapping("boardAllList")
	public String boardAllList(Model model) {
		model.addAttribute("list",bs.boardAllList());
		return "board/boardAllList";
	}
	
	@GetMapping("writeForm") 
	public String writeForm() {
		return "board/writeForm";
	}
	
	@PostMapping("writeSave")
	public void writeSave(BoardDTO dto,@RequestParam(required = false) MultipartFile imgFileName,
			HttpServletResponse res) throws IOException {
		
		String msg = bs.writeSave(dto,imgFileName);
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print(msg);
	}
	
	@GetMapping("contentView")
	public String contentView(@RequestParam int writeNo,Model model) {
		model.addAttribute("user",bs.contentView(writeNo));
		
		return "board/contentView";
	}
	
	@GetMapping("download")
	public void download(@RequestParam String name,HttpServletResponse res) throws Exception {
		res.addHeader("content-disposition", "attachment; fileName=" +name);
		File file = new File(BoardFileService.IMAGE_REPO2+"/"+name);
	    FileInputStream in = new FileInputStream(file);
	    FileCopyUtils.copy(in, res.getOutputStream());
	    in.close();
	  }
	
	@GetMapping("modifyForm")
	public String modifyForm(@RequestParam int writeNo,Model model) {
		model.addAttribute("dto",bs.getContent(writeNo));
		return "board/modifyForm";
	}
	
	@PostMapping("modify")
	public void modify(BoardDTO dto,@RequestParam MultipartFile imgFileName,HttpServletResponse res) throws Exception {
		String msg = bs.modify(dto, imgFileName);
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print(msg);
	}
	
	@GetMapping("delete")
	public void delete(@RequestParam String fileName,@RequestParam int writeNo,HttpServletResponse res) throws Exception {
		String msg = bs.delete(fileName,writeNo);
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print(msg);
	}
	
	@GetMapping("replyForm")
	public String replyForm() {
		
		return "board/replyForm";
	}
	
}
