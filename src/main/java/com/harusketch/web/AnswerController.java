package com.harusketch.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.harusketch.domain.board.Answer;
import com.harusketch.domain.board.AnswerRepository;
import com.harusketch.domain.board.Question;
import com.harusketch.domain.board.QuestionRepository;
import com.harusketch.domain.member.Member;

@Controller
@RequestMapping("/questions/{questionId}/answers")
public class AnswerController {

	@Autowired
	QuestionRepository questionRepositoy;
	
	@Autowired
	AnswerRepository answerRepository;
	
	@PostMapping("")
	public String answer(@PathVariable Long questionId
			, HttpSession session, String writer, String title, String content) {
		
		Member loginMember = (Member) session.getAttribute("memberSession");
		
		Question question = questionRepositoy.findById(questionId).get();
		
		Answer answer = new Answer(loginMember, question, title, content);
		
		answerRepository.save(answer);
		
		return String.format("redirect:/questions/%d", questionId);
	}
}
