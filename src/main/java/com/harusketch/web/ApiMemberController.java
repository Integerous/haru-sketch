package com.harusketch.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harusketch.domain.member.Member;
import com.harusketch.domain.member.MemberRepository;

@RestController
@RequestMapping("api/members")
public class ApiMemberController { //사용자의 정보를 JSON으로 받기 위한 컨트롤러

	@Autowired
	private MemberRepository memberRepository;
	
	@GetMapping("/{memberId}")
	public Member show(@PathVariable Long memberId) {
		
		return memberRepository.findById(memberId).get();
	}
}
