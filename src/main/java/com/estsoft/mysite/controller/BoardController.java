package com.estsoft.mysite.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.estsoft.mysite.annotation.Auth;
import com.estsoft.mysite.annotation.AuthUser;
import com.estsoft.mysite.service.BoardService;
import com.estsoft.mysite.vo.BoardVo;
import com.estsoft.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping("")
	public String list(Model model, @RequestParam(value = "kwd", required = true, defaultValue = "") String keyword,
			@RequestParam(value = "pNo", required = true, defaultValue = "1") Long pageNo) {

		long pageChanger = pageNo / 3;

		if (pageNo % 3 == 0) {
			pageChanger--;
		}

		List<BoardVo> list = boardService.getSearchList(pageNo, keyword);
		
		long count = boardService.countAll(keyword);
		long Tcount = (long) Math.ceil((double) count / 3);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keywd", keyword);
		map.put("list", list);
		map.put("pNo", pageNo);
		map.put("pCn", pageChanger);
		map.put("forIndex", count);
		map.put("Tcount", Tcount);

		model.addAttribute("map", map);

		return "board/list";
	}

	@RequestMapping("/view/{no}")
	public String view(Model model, @PathVariable("no") int no) {
		BoardVo vo = boardService.view(no);
		model.addAttribute("vo", vo);
		return "board/view";
	}

	@Auth
	@RequestMapping("/writeForm")
	public String writeForm() {
		return "board/write";
	}

	@Auth
	@RequestMapping("/write")
	public String write(@AuthUser UserVo authUser, @ModelAttribute BoardVo vo) {
		long no = boardService.write(vo);
		return "redirect:view/"+no;
	}
	
	@RequestMapping("/replyForm/{no}")
	public String replyForm(Model model, @PathVariable("no") int no){
		BoardVo vo = boardService.getVo(no);
		model.addAttribute("vo", vo);
		return "board/reply";
	}
	
	@RequestMapping("/reply")
	public String reply(@ModelAttribute BoardVo vo){
		long retNo = boardService.reply(vo);
		return "redirect:view/"+retNo;
	}
	
	@RequestMapping("/modifyForm/{no}")
	public String modifyForm(Model model, @PathVariable("no") int no){
		BoardVo vo = boardService.getVo(no);
		model.addAttribute("vo", vo);
		return "board/modify";
	}
	
	@RequestMapping("/modify")
	public String modify(@ModelAttribute BoardVo vo){
		boardService.modify(vo);
		return "redirect:view/"+vo.getNo();
	}
	
	@RequestMapping("/delete/{no}")
	public String delete(@PathVariable("no") int no){
		boardService.delete(no);
		return "redirect:/board";
	}
}