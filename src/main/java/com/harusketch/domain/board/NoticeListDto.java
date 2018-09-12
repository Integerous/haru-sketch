package com.harusketch.domain.board;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.harusketch.domain.posts.Posts;

import lombok.Getter;

@Getter
public class NoticeListDto {
	
	private Long noticeId;
	private String title;
	private String writer;
	private String createdDate;
	// String을 사용한 이유는 View 영역에서는 LocalDateTime 타입을 모르기 때문에 인식할 수 있도록
	// toStringDateTime을 통해 문자열로 날짜 형식을 변경해서 등록
	private String modifiedDate;
	
	public NoticeListDto(Posts entity) {
		noticeId = entity.getId();
		title = entity.getTitle();
		writer = entity.getAuthor();
		createdDate = toStringDateTime(entity.getCreatedDate());
		modifiedDate = toStringDateTime(entity.getModifiedDate());
	}
	
	private String toStringDateTime(LocalDateTime localDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		return Optional.ofNullable(localDateTime)
				.map(formatter::format)
				.orElse("");
	}
}
