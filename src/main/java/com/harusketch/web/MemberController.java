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
	
	/**
	 * 로그인 폼
	 * @return
	 */
	@GetMapping("/loginForm")
	public String loginForm() {
		return "member/login";
	}
	
	/**
	 * 로그인 
	 * @param email
	 * @param pwd
	 * @param session
	 * @return
	 */
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
	
	/**
	 * 로그아웃
	 * @param session
	 * @return
	 */
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("memberSession");
		return "redirect:/";
	}
	
	/**
	 * 회원가입 폼
	 * @return
	 */
	@GetMapping("/form")
	public String form() {
		return "/member/form";
	}
	
	/**
	 * 회원가입
	 * @param member
	 * @return
	 */
	@PostMapping("")
	public String join(Member member) {
		
		//members.add(member);
		memberRepository.save(member);
		return "redirect:/members";
	}
	
	/**
	 * 회원 목록
	 * @param model
	 * @return
	 */
	@GetMapping("")
	public String list(Model model) {
		
		model.addAttribute("members", memberRepository.findAll()); 
		return "/member/list";
	}
	
	/**
	 * 회원 정보 수정
	 * @param memberId
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/{memberId}/info")
	public String memberInfo(@PathVariable Long memberId, Model model, HttpSession session) {
	
		Member memberSession = (Member) session.getAttribute("memberSession");
		
		if(memberSession == null) {
			return "redirect:/members/loginForm";
		}
		
		if(!memberId.equals(memberSession.getMemberId())) {
			throw new IllegalStateException("You can only update your info");
		}
		
		Member member = memberRepository.findById(memberId).get();
		model.addAttribute("member", member);
		
		return "/member/updateInfo";
	}
	/**
	 * 회원 정보 수정
	 * @param memberId
	 * @param modifiedMember
	 * @param session
	 * @return
	 */
	@PostMapping("/{memberId}/updateInfo")
	public String updateInfo(@PathVariable Long memberId
			, Member modifiedMember, HttpSession session) {
		
		Object memberSession = session.getAttribute("memberSession");
		
		if(memberSession == null) {
			return "redirect:/members/loginForm";
		}
		
		Member sessionCheck = (Member) memberSession;
		if(!memberId.equals(sessionCheck.getMemberId())) {
			throw new IllegalStateException("You can update your own info only.");
		}
		
		Member member = memberRepository.findById(memberId).get();
		
		member.update(modifiedMember);
		memberRepository.save(member); 
		
		return "redirect:/members";
	}
	
	
}





