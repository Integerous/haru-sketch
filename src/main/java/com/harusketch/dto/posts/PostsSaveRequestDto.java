package com.harusketch.dto.posts;

import com.harusketch.domain.Posts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



// DTO 클래스가 Entity 클래스와 유사해도 만든 이유는
// Entity 클래스를 Request/Response 클래스로 사용하면 안되기 때문!
// Controller에서 결과값으로 여러 테이블을 조인해서 줘야할 경우가 빈번하기 때문에,
// Entity 클래스만으로 표현하기 어려운 경우가 많다. 따라서 Entity 클래스와 Controller에서 쓸 DTO를 분리한다.
@Getter
@Setter
//Controller에서 @RequestBody로 외부에서 데이터를 받는 경우에는
//기본생성자+set메소드를 통해서만 값이 할당되므로 Setter 사용
@NoArgsConstructor
public class PostsSaveRequestDto {

	private String title;
	private String content;
	private String author;
	
	public Posts toEntity() {
		return Posts.builder()
				.title(title)
				.content(content)
				.author(author)
				.build();
	}
}
