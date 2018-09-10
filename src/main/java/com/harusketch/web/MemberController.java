package com.harusketch.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.harusketch.domain.member.Member;

@Controller
public class MemberController {

	private List<Member> members = new ArrayList<Member>();
	
	@PostMapping("/join")
	public String join(Member member) {
		
		members.add(member);
		return "redirect:/list";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		
		model.addAttribute("members", members);
		return "list";
	}
}
