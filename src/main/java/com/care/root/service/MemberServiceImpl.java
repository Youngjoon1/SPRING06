package com.care.root.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.care.root.dto.MemberDTO;
import com.care.root.mybatis.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberMapper mapper;
	
	BCryptPasswordEncoder encoder;
	public MemberServiceImpl() {
		encoder = new BCryptPasswordEncoder();
	}
	
	
	@Override
	public int logChk(String id, String pw) {
		MemberDTO dto = null;
		try {
			dto = mapper.getMember(id);
			if(dto != null) {
				if(dto.getPw().equals(pw) || encoder.matches(pw,dto.getPw())) {
					return 0;
				}else {
					return 1;
				}
			}else {
				return 1;
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
		
	}

	public List<MemberDTO> getList() {
		List<MemberDTO> list = null;
		try {
			list = mapper.getList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public void register(MemberDTO dto, String[] addr) {
		String ad = "";
		for(String a : addr) {
			ad += a + "/";
		}
		dto.setAddr(ad);
		System.out.println("평문 : " + dto.getPw() );
		System.out.println("암호화 : " + encoder.encode(dto.getPw()));
		dto.setPw(encoder.encode(dto.getPw()));
		try {
			mapper.register(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public Map<String, Object> getMember(String id) {
		MemberDTO dto = mapper.getMember(id);
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("dto", dto);
		
		String [] addr = dto.getAddr().split("/");
		if(addr.length > 1) {
		map.put("addr1",addr[0]);
		map.put("addr2",addr[1]);
		map.put("addr3",addr[2]);
		}else {
			map.put("addr1", dto.getAddr());
		}
		return map;
	}
	
	public void userModify(MemberDTO dto,String[] addr) {
		String ad = "";
		for(String a : addr) {
			ad += a + "/";
		}
		dto.setAddr(ad);
		dto.setPw(encoder.encode(dto.getPw()));
		try {
			mapper.userModify(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void userDel(String id) {
		try {
			mapper.userDel(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
