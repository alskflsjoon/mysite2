package com.estsoft.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.mysite.dao.GuestBookDao;
import com.estsoft.mysite.vo.GuestBookVo;

@Service
public class GuestbookService {

	@Autowired
	GuestBookDao guestbookDao;

	public List<GuestBookVo> getMessageList() {
		return guestbookDao.getList();
	}
	
	public List<GuestBookVo> getMessageList(int page) {
		return guestbookDao.getList(page);
	}
	

	public boolean deleteMessage(GuestBookVo vo) {
		return guestbookDao.delete(vo) == 1;
	}

	public boolean insertMessage(GuestBookVo vo) {
		Long no = guestbookDao.insert(vo);
		return no != 0;
	}
	
	public GuestBookVo insertAndGetMessage(GuestBookVo vo) {
		Long no = guestbookDao.insert(vo);
		GuestBookVo ret = guestbookDao.get(no);
		return ret;
	}
}
