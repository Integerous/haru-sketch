package com.harusketch.web;

import java.util.Arrays;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.harusketch.domain.posts.PostsRepository;
import com.harusketch.dto.posts.PostsSaveRequestDto;
import com.harusketch.service.PostsService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
//스프링 프레임워크에서 Bean을 주입받는 방식 3가지 (@Autowired, Setter, 생성자) 중
//생성자로 주입받는 것이 가장 권장되므로 Lombok의 @AllArgsConstructor로
//모든 필드를 인자값으로 하는 생성자를 생성
public class WebRestController {
	
	//private PostsRepository postsRepository;
	private PostsService postsService; //save 메소드를 service의 save로 교체
	
	private Environment env; 
	
	@GetMapping("/hello")
	public String hello() {
		return "HelloWorld";
	}
	
	@PostMapping("/posts")
	public Long savePosts(@RequestBody PostsSaveRequestDto dto) {
		//postsRepository.save(dto.toEntity());
		return postsService.save(dto);//save 메소드를 service의 save로 교체
	}
	
	//실행중인 프로젝트의 Profile을 확인할 수 있는 API
	//프로젝트의 환경설정 값을 다루는 Environment Bean을 DI받아 현재 활성화된 Profile을 반환하는 코드
	@GetMapping("/profile")
	public String getProfile() {
		return Arrays.stream(env.getActiveProfiles())
				.findFirst()
				.orElse("");
	}
	
}
