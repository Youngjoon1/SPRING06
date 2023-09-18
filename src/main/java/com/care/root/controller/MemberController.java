package com.care.root.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
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
	public String loginChk(@RequestParam String id,@RequestParam String pw,HttpSession user,RedirectAttributes rs,
										@RequestParam(required = false,defaultValue = "off") String autoLogin) {
		if(ms.logChk(id,pw) == 0) {
			//login == loginSession.login 상속받아 가져온다
			//user.setAttribute(login, id);
			rs.addAttribute("id",id);
			rs.addAttribute("autoLogin", autoLogin);
			return "redirect:successLogin";
		}
		return "redirect:login";
	}
	
	@GetMapping("successLogin")
	public String successLogin(HttpSession session,@RequestParam String id,@RequestParam String autoLogin,HttpServletResponse res) {
		System.out.println("autoLogin : " + autoLogin);
		
		if(autoLogin.equals("on")) {
			int limitTime = 60*60*24*90; //90일 설정
			Cookie loginCookie = new Cookie("loginCookie", session.getId());
			loginCookie.setPath("/");
			loginCookie.setMaxAge(limitTime);
			res.addCookie(loginCookie);
			
			ms.keepLogin(session.getId(), id);
		}
		
		session.setAttribute(login, id);
		return "member/successLogin";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session,@CookieValue(required = false) Cookie loginCookie,HttpServletResponse res) {
		
		if(loginCookie != null) {
			loginCookie.setMaxAge(0);
			res.addCookie(loginCookie);
			ms.keepLogin("nan", (String)session.getAttribute(loginSession.login));
		}
		//return "redirect:login";
		session.invalidate();
		
		return "redirect:login";
	}
	
	@GetMapping("list")
	public String list(Model model) {
		
		System.out.println("컨트롤러 리스트 동작");
		
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
			
			ad += a+"/";
		}
		String [] addr02 = ad.split("/");
		
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
	
	@GetMapping("userDel")
	public String userDel(String id,HttpSession session) {
		ms.userDel(id);
		if(id.equals(session.getAttribute(login))) {
			session.invalidate();
			return "redirect:login";
		}
		return "redirect:list";
	}
	
	
}
