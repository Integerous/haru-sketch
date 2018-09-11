package com.harusketch.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.harusketch.domain.member.Member;
import com.harusketch.domain.member.MemberRepository;

@Controller
@RequestMapping("/members")
public class MemberController {
	
	// JpaRepository를 상속받은 MemberRepository를 사용할 것이므로 이것은 필요 없다.
	//private List<Member> members = new ArrayList<Member>();
	
	@Autowired
	private MemberRepository memberRepository;
	
	//회원가입 폼
	@GetMapping("/form")
	public String form() {
		return "/member/form";
	}
	
	//회원가입
	@PostMapping("")
	public String join(Member member) {
		
		//members.add(member);
		memberRepository.save(member);
		return "redirect:/members";
	}
	//회원목록
	@GetMapping("")
	public String list(Model model) {
		
		model.addAttribute("members", memberRepository.findAll()); 
		return "/member/list";
	}
	
	//회원 정보수정
	@GetMapping("/{id}/info")
	public String memberInfo(@PathVariable Long id, Model model) {
		
		Member member = memberRepository.findById(id).get();
		model.addAttribute("member", member);
		
		return "/member/updateInfo";
	}
	
	@PostMapping("/{id}/updateInfo")
	public String updateInfo(@PathVariable Long id, Member modifiedMember) {
		
		Member member = memberRepository.findById(id).get();
		
		member.update(modifiedMember);
		memberRepository.save(member); 
		
		return "redirect:/members";
	}
	
	
}





