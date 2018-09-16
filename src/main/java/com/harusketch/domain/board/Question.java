package com.harusketch.domain.board;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.harusketch.domain.BaseTimeEntity;
import com.harusketch.domain.member.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Question extends BaseTimeEntity{
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long questionId;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	private Member writer;
	//private String writer;
	

	@OneToMany(mappedBy="question") //mappedBy 값은 @ManyToOne으로 매핑할때의 Question 이름 
	@OrderBy(" answerId DESC") // answer의 아이디를 기준으로 오름차순 정렬
	private List<Answer> answers;
	
	@Lob
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

	//질문 업데이트
	public void update(String title, String content) {
		this.title = title;
		this.content = content;
		
	}

	//질문 수정 작성자 검증 
	public boolean isSameWriter(Member loginMember) {
		return this.writer.equals(loginMember);
	}
	
	
}
