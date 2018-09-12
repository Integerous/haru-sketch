package com.harusketch.domain.board;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.harusketch.domain.BaseTimeEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
//@ToString
@Entity
public class Notice extends BaseTimeEntity{
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long noticeId;
	private String writer;
	private String title;
	private String content;
	
	
	
	public Notice(String writer, String title, String content) {
		super();
		this.writer = writer;
		this.title = title;
		this.content = content;
	}

	
	
}
