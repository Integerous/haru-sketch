package com.harusketch.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harusketch.domain.board.Answer;
import com.harusketch.domain.board.AnswerRepository;
import com.harusketch.domain.board.Question;
import com.harusketch.domain.board.QuestionRepository;
import com.harusketch.domain.member.Member;

//@Controller //메소드가 자동으로 JSON으로 변환하게끔 하도록 @Controller > @RestController 바꿈
@RestController
@RequestMapping("api/questions/{questionId}/answers")
public class ApiAnswerController {

	@Autowired
	QuestionRepository questionRepositoy;
	
	@Autowired
	AnswerRepository answerRepository;
	
	/**
	 * 문의에 답변하기
	 * @param questionId
	 * @param session
	 * @param writer
	 * @param title
	 * @param content
	 * @return
	 */
	@PostMapping("")
	//Ajax 사용을 위해 반환타입 String -> Answer 바꾸고 Answer타입인 save메소드를 반환
	public Answer answer(@PathVariable Long questionId 
			, HttpSession session, String writer, String title, String content) {
		
		Member loginMember = (Member) session.getAttribute("memberSession");
		
		Question question = questionRepositoy.findById(questionId).get();
		
		Answer answer = new Answer(loginMember, question, title, content);
		
		//return String.format("redirect:/questions/%d", questionId);
		Answer result = answerRepository.save(answer);
		return result;
//		return answerRepository.save(answer);
		
	}
}
