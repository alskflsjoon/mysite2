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
import org.springframework.web.bind.annotation.ResponseBody;

import com.estsoft.mysite.service.GuestbookService;
import com.estsoft.mysite.vo.GuestBookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	
	@Autowired
	GuestbookService guestbookService;
	
	@RequestMapping( "" )
	public String index( Model model ) {
		List<GuestBookVo> list = guestbookService.getMessageList();
		model.addAttribute( "list", list );
		return "guestbook/list";
	}
	
	@RequestMapping( "/deleteform/{no}" )
	public String deletefrom( @PathVariable( "no" ) Long no, Model model ) {
		model.addAttribute( "no", no );
		return "guestbook/deleteform";
	}

	@RequestMapping( "/delete" )
	public String delete( @ModelAttribute GuestBookVo vo ) {
		guestbookService.deleteMessage(vo);
		return "redirect:/guestbook";
	}

	@RequestMapping( "/insert" )
	public String insert( @ModelAttribute GuestBookVo vo ) {
		guestbookService.insertMessage(vo);
		return "redirect:/guestbook";
	}

	@RequestMapping( "/ajax" )
	public String ajaxIndex( Model model) {
		//List<GuestBookVo> list = guestbookService.getMessageList();
		//model.addAttribute( "list", list );
		return "guestbook/ajax-main";
	}
	
	@RequestMapping( "/ajax-insert" )
	@ResponseBody
	public Object ajaxInsert( @ModelAttribute GuestBookVo vo) {
		vo = guestbookService.insertAndGetMessage(vo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result","success");
		map.put("data",vo);
		return map;
	}
	
	@RequestMapping( "/ajax-list" )
	@ResponseBody
	public Object ajaxList( @RequestParam("p") int page ) {
		List<GuestBookVo> list = guestbookService.getMessageList((page-1)*3);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", list);
		return map;
	}
	
}
