package com.harusketch.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.harusketch.domain.board.Notice;
import com.harusketch.domain.board.NoticeRepository;
import com.harusketch.domain.board.Question;
import com.harusketch.domain.board.QuestionRepository;
import com.harusketch.domain.member.Member;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	/**
	 * 질문 작성 폼
	 * @param session
	 * @return
	 */
	@GetMapping("/form")
	public String questionForm(HttpSession session) {
		if(session == null) {
			return "members/loginForm";
		}
		return "board/questionForm";
	}
	
	/**
	 * 질문 등록
	 * @param title
	 * @param content
	 * @param session
	 * @return
	 */
	@PostMapping("/form")
	public String questionCreate(String title, String content, HttpSession session) {
		
		Member memberSession = (Member) session.getAttribute("memberSession");
		
		Question newQuestion = new Question(memberSession, title, content);
		
		questionRepository.save(newQuestion);
		
		return "redirect:/questions/list";
	}
	
	/**
	 * 질문 리스트
	 * @param model
	 * @return
	 */
	@GetMapping("/list")
	public String questionList(Model model) {
		
		model.addAttribute("questions", questionRepository.findAll());
		
		return "board/questionList";
	}
	
}
