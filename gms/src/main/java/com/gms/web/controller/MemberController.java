package com.gms.web.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gms.web.domain.MemberDTO;
import com.gms.web.service.MemberService;


@Controller
@RequestMapping("/member") //root-context공간에 저장
@SessionAttributes("user") //user란 세션공간
public class MemberController {
	//static final Logger Logger = LoggerFactory.getLogger();
	@Autowired MemberDTO member;
	@Autowired MemberService memberService;
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(@ModelAttribute MemberDTO member) {
		memberService.add(member);
		System.out.println("member is "+ member);
		return "redirect:/move/auth/member/login";
	}
	@RequestMapping("/list")
	public void list() {}
	@RequestMapping("/search")
	public void serarch() {}
	@RequestMapping("/retrieve")
	public void retrieve(@ModelAttribute MemberDTO member, Model model) {
		System.out.println("member값"+member);
		model.addAttribute("member",memberService.retrieve(member));
	}
	@RequestMapping("/count")
	public void count() {}
	@RequestMapping(value="/modify/id", method = RequestMethod.POST)
	public void modify(@ModelAttribute("member") MemberDTO member,@ModelAttribute("user") MemberDTO user) {
		Map<String, Object> p = new HashMap<String, Object>();
		//member.setId(user);
		System.out.println(user);
		p.put("member",member);
		
		memberService.modify(p);
		System.out.println("modify member값"+member);
		
	}
	@RequestMapping(value="/remove", method = RequestMethod.POST)
	public void remove(@ModelAttribute MemberDTO member, Model model) {
		
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("member",member);
		memberService.remove(p);
		System.out.println("remove member값"+member);
		
	}
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login(@ModelAttribute MemberDTO member, Model model) {
		String login ="";
		if(memberService.login(member)) {
			model.addAttribute("member",memberService.retrieve(member));
			login =  "login__success";
		}else {
			 login =  "redirect:/move/auth/member/login";
		}
		System.out.println("login값~~"+login);
		return login;
	}
	@RequestMapping("/logout")
	public String logout() {
		return "redirect:/";
	}
	@RequestMapping("/fileupload")
	public void fileupload() {}

	
	
	
	
	
	
}