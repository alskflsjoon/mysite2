package com.estsoft.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.mysite.dao.BoardDao;
import com.estsoft.mysite.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	BoardDao boardDao;
	
	public List<BoardVo> getSearchList(long pageNo, String kwd){
		return boardDao.getSearchList(pageNo, kwd);
	}
	
	public long countAll(String kwd){
		return boardDao.countAll(kwd);
	}
	
	public BoardVo view(long no){
		boardDao.increaseViewCount(no);
		return boardDao.getVo(no);
	}
	
	public long write(BoardVo vo){
		long no = boardDao.insert(vo);
		System.out.println("svc: "+no);
		return no;
	}
	
	public BoardVo getVo(long no){
		return boardDao.getVo(no);
	}
	
	public long reply(BoardVo vo){
		BoardVo fVo = boardDao.getVoForReply(vo.getNo());
		fVo.setUserName(vo.getUserName());
		fVo.setUserNo(vo.getUserNo());
		fVo.setText(vo.getText());
		fVo.setTitle(vo.getTitle());
		
		boardDao.IncreaseBeforereply(fVo);
		
		long no = boardDao.reply(fVo);
		
		return no;
	}
	
	public void modify(BoardVo vo){
		boardDao.update(vo);	
	}
	
	public void delete(long no){
		boardDao.delete(no);
	}
}
