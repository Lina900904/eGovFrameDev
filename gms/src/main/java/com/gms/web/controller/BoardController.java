package com.gms.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gms.web.domain.ArticleDTO;
import com.gms.web.service.BoardService;

public class BoardController {
	@Autowired ArticleDTO member;
	@Autowired BoardService articleService;
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(@ModelAttribute ArticleDTO member, Model model) {
		articleService.add(member);
		System.out.println("member is "+ member);
		return "redirect:/move/auth/member/login";
	}
	
	@RequestMapping("/list")
	public void list() {}
	@RequestMapping("/search")
	public void serarch() {}
	@RequestMapping("/retrieve")
	public void retrieve(@ModelAttribute ArticleDTO member, Model model) {
		System.out.println("member값"+member);
		model.addAttribute("member",articleService.retrieve(member));
	}
	@RequestMapping("/count")
	public void count() {}
	@RequestMapping(value="/modify/id", method = RequestMethod.POST)
	public void modify(@ModelAttribute ArticleDTO member, @PathVariable String id ) {
		Map<String, Object> p = new HashMap<String, Object>();
	//	member.setId(id);
		System.out.println(id);
		p.put("member",member);
		
		articleService.modify(p);
		System.out.println("modify member값"+member);
		
	}
	@RequestMapping(value="/remove", method = RequestMethod.POST)
	public void remove(@ModelAttribute ArticleDTO member, Model model) {
		
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("member",member);
		articleService.remove(p);
		System.out.println("remove member값"+member);
		
	}
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login(@ModelAttribute ArticleDTO member, Model model) {
		String login ="";
		if(articleService.login(member)) {
			model.addAttribute("member",articleService.retrieve(member));
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
