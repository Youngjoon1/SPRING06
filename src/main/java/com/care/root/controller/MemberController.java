package com.care.root.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.care.root.common.loginSession;
import com.care.root.dto.MemberDTO;
import com.care.root.service.MemberService;

@Controller
@RequestMapping("member")
public class MemberController implements loginSession{
	@Autowired
	MemberService ms;
	
	
	@GetMapping("login")
	public String login() {
		return "member/login";
	}
	
	@PostMapping("loginChk")
	public String loginChk(@RequestParam String id,@RequestParam String pw,HttpSession user,RedirectAttributes rs) {
		if(ms.logChk(id,pw) == 0) {
			//login == loginSession.login 상속받아 가져온다
			//user.setAttribute(login, id);
			rs.addAttribute("id",id);
			return "redirect:successLogin";
		}
		return "redirect:login";
	}
	
	@GetMapping("successLogin")
	public String successLogin(HttpSession session,@RequestParam String id) {
		session.setAttribute(login, id);
		return "member/successLogin";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession user) {
		user.invalidate();
		return "redirect:login";
	}
	
	@GetMapping("list")
	public String list(Model model) {
		model.addAttribute("list",ms.getList());
		return "member/list";
	}
	
	@GetMapping("register")
	public String register() {
		
		return "member/register";
	}
	
	@PostMapping("join")
	public String join(HttpServletRequest req,MemberDTO dto) {
		String[] addr = req.getParameterValues("addr");
		String ad = "";
		for(String a : addr) {
			System.out.println(a);
			ad += a+"/";
		}
		String [] addr02 = ad.split("/");
		for(String a1 : addr02) {
			System.out.println(a1);
		}
		ms.register(dto,req.getParameterValues("addr"));
		
		return "redirect:login";
	}
	
	@GetMapping("info")
	public String info(@RequestParam String id,Model model) {
		model.addAttribute("mem",ms.getMember(id));
		return "member/info";
	}
	
	@GetMapping("modify")
	public String modify(@RequestParam String id,Model model) {
		model.addAttribute("info",ms.getMember(id));
		return "member/modify";
	}
	
	@PostMapping("userModify")
	public String userModify(MemberDTO dto,HttpServletRequest req) {
		ms.userModify(dto,req.getParameterValues("addr"));
		return "redirect:list";
	}
	
	
	
}
