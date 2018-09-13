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
import com.harusketch.domain.member.Member;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	@Autowired
	private NoticeRepository noticeRepository;
	
	/**
	 * 공지사항 작성 폼
	 * @param session
	 * @return
	 */
	@GetMapping("/form")
	public String noticeForm(HttpSession session) {
		if(session == null) {
			return "members/loginForm";
		}
		return "board/noticeForm";
	}
	
	/**
	 * 공지사항 등록
	 * @param title
	 * @param content
	 * @param session
	 * @return
	 */
	@PostMapping("/form")
	public String noticeCreate(String title, String content, HttpSession session) {
		
		Member memberSession = (Member) session.getAttribute("memberSession");
		
		Notice newNotice = new Notice(memberSession.getName(), title, content);
		
		noticeRepository.save(newNotice);
		
		return "redirect:/notice/list";
	}
	
	/**
	 * 공지사항 리스트
	 * @param model
	 * @return
	 */
	@GetMapping("/list")
	public String noticeList(Model model) {
		
		model.addAttribute("notices", noticeRepository.findAll());
		
		return "board/noticeList";
	}
	
}
