package com.harusketch.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.harusketch.domain.member.Member;
import com.harusketch.domain.member.MemberRepository;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	// JpaRepository를 상속받은 MemberRepository를 사용할 것이므로 이것은 필요 없다.
	//private List<Member> members = new ArrayList<Member>();
	
	@Autowired
	private MemberRepository memberRepository;
	
	@GetMapping("/form")
	public String form() {
		return "/member/form";
	}
	
	@PostMapping("/join")
	public String join(Member member) {
		
		//members.add(member);
		memberRepository.save(member);
		return "redirect:/member/list";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		
		model.addAttribute("members", memberRepository.findAll()); 
		return "/member/list";
	}
}
