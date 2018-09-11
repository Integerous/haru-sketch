package com.harusketch.web;

import javax.servlet.http.HttpSession;

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
	
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "member/login";
	}
	
	@PostMapping("/login")
	public String login(String email, String pwd, HttpSession session) {
		
		Member member = memberRepository.findByEmail(email);
		
		if(member == null) {
			return "redirect:/members/loginForm";
		}
		if(!pwd.equals(member.getPwd())) {
			return "redirect:/members/loginForm";
		}
		session.setAttribute("memberSession", member);
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("memberSession");
		return "redirect:/";
	}
	
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
	public String memberInfo(@PathVariable Long id, Model model, HttpSession session) {
	
		Object memberSession = session.getAttribute("memberSession");
		if(memberSession == null) {
			return "redirect:/members/loginForm";
		}
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





