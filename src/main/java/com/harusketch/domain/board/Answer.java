package com.harusketch.domain.board;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
public class Answer extends BaseTimeEntity{
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long answerId;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
	private Member writer;
	//private String writer;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_question"))
	private Question question;
	
	@Lob
	private String title;
	private String content;
	
	private LocalDateTime createdDate;
	
	
	
	public Answer(Member writer, Question question, String title, String content) {

		this.writer = writer;
		this.question = question;
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

	//답변 업데이트
	public void update(String title, String content) {
		this.title = title;
		this.content = content;
		
	}

	//답변 수정 작성자 검증 
	public boolean isSameWriter(Member loginMember) {
		return this.writer.equals(loginMember);
	}
	
	
}
