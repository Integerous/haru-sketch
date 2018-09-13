package com.harusketch.domain.board;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.harusketch.domain.BaseTimeEntity;
import com.harusketch.domain.member.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
//@ToString
@Entity
public class Question extends BaseTimeEntity{
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long questionId;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	private Member writer;
	//private String writer;
	private String title;
	private String content;
	
	private LocalDateTime createdDate;
	
	
	
	public Question(Member writer, String title, String content) {

		this.writer = writer;
		this.title = title;
		this.content = content;
		this.createdDate = LocalDateTime.now();
	}

	//날짜변환
	public String getFormattedCreatedDate() {
		if (createdDate == null) {
			return "";
		}
		return createdDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm"));
	}
	
	
}
