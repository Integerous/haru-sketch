package com.harusketch.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.harusketch.domain.board.Question;
import com.harusketch.domain.board.QuestionRepository;
import com.harusketch.domain.member.Member;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	/**
	 * 문의 작성 폼
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
	 * 문의 리스트
	 * @param model
	 * @return
	 */
	@GetMapping("/list")
	public String questionList(Model model) {
		
		model.addAttribute("questions", questionRepository.findAll());
		
		return "board/questionList";
	}
	
	/**
	 * 문의 상세내용
	 * @param questionId
	 * @param model
	 * @return
	 */
	@GetMapping("/{questionId}")
	public String questionDetail(@PathVariable Long questionId, Model model) {
		
		Question question = questionRepository.findById(questionId).get();
		
		model.addAttribute("question", question);
		
		return "board/questionDetail";
	}
	
	/**
	 * 문의 수정 폼
	 * @param questionId
	 * @param model
	 * @return
	 */
	@GetMapping("/{questionId}/form")
	public String questionEdit(@PathVariable Long questionId
			, Model model, HttpSession session) {
		
		Member loginMember = (Member) session.getAttribute("memberSession");
		Question question = questionRepository.findById(questionId).get();
		
		if(!question.isSameWriter(loginMember)) {
			throw new IllegalStateException("You can update your own question only.");
		}
		model.addAttribute("question", question);
		
		return "board/questionEdit";
	}
	
	/**
	 * 문의 업데이트
	 * @param questionId
	 * @param title
	 * @param content
	 * @return
	 */
	@PutMapping("/{questionId}")
	public String questionUpdate(@PathVariable Long questionId
			, String title, String content, HttpSession session) {
	
		Member loginMember = (Member) session.getAttribute("memberSession");
		Question question = questionRepository.findById(questionId).get();
		
		if(!question.isSameWriter(loginMember)) {
			throw new IllegalStateException("You can update your own question only.");
		}
		
		question.update(title, content);
		
		questionRepository.save(question);
		
		return String.format("redirect:/questions/%d", questionId);
	}
	
	/**
	 * 질문 삭제
	 * @param questionId
	 * @return
	 */
	@DeleteMapping("/{questionId}")
	public String questionDelete(@PathVariable Long questionId
			, HttpSession session) {
		
		Member loginMember = (Member) session.getAttribute("memberSession");
		Question question = questionRepository.findById(questionId).get();
		
		if(!question.isSameWriter(loginMember)) {
			throw new IllegalStateException("You can update your own question only.");
		}
		
		questionRepository.deleteById(questionId);
		return "redirect:/questions/list";
	}
	
}
