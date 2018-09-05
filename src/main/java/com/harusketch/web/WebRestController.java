package com.harusketch.web;

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
	
	@GetMapping("/hello")
	public String hello() {
		return "HelloWorld";
	}
	
	@PostMapping("/posts")
	public Long savePosts(@RequestBody PostsSaveRequestDto dto) {
		//postsRepository.save(dto.toEntity());
		return postsService.save(dto);//save 메소드를 service의 save로 교체
	}
	
}
