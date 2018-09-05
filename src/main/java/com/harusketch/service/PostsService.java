package com.harusketch.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.harusketch.domain.posts.PostsRepository;
import com.harusketch.dto.posts.PostsMainResponseDto;
import com.harusketch.dto.posts.PostsSaveRequestDto;

import lombok.AllArgsConstructor;




@AllArgsConstructor
@Service
public class PostsService {

	private PostsRepository postsRepository;
	
	@Transactional //일반적으로 DB데이터를 등록/수정/삭제 하는 Service 메소드는 @Transactional을 필수로 가져감
	// @Transactional은 메소드 내에서 Exception이 발생하면 해당 메소드에서 이루어진 모든 DB작업을 초기화한다.
	public Long save(PostsSaveRequestDto dto) {
		
		return postsRepository.save(dto.toEntity()).getId();
		// Controller에서 Dto.toEntity를 통해서 바로 전달해도 되는데 굳이 Service에서 Dto를 받는 이유는
		// Controller와 Service의 역할을 분리하기 위해서.
		// 비즈니스 로직과 트랜잭션 관리는 모두 Service 에서.
		// View와 연동되는 부분은 Controller에서 담당.
	}
	
	@Transactional(readOnly = true) //트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도 개선. 등록/수정/삭제 기능이 없는 메소드에서 사용 추천
	public List<PostsMainResponseDto> findAllDesc(){
		return postsRepository.findAllDesc()
				.map(PostsMainResponseDto::new) // .map(posts -> new PostsMainResponseDto(posts)) 와 같다.
				.collect(Collectors.toList());
				// repository 결과로 넘어온 Posts의 Stream을 map을 통해 PostsMainResponseDto로 변환하고 List로 반환하는 메소드
	}
}
