package com.harusketch.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>{

	//MemberController login에 활용
	Member findByEmail(String email);
}
