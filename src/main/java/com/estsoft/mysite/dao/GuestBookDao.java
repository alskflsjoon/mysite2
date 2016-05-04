package com.estsoft.mysite.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.mysite.vo.GuestBookVo;

@Repository
public class GuestBookDao {
		
//	@Autowired
//	private DataSource dataSource;
	
	@Autowired
	private SqlSession sqlSession;
	
	public GuestBookVo get(long no){
		
		GuestBookVo vo = sqlSession.selectOne("guestbook.selectByNo",no);
		return vo;
	}

	public Long insert(GuestBookVo vo){
		int count = sqlSession.insert("guestbook.insert",vo);
		System.out.println(count + ":" +vo.getNo());
		return vo.getNo();
	}

	public int delete(GuestBookVo vo){
		int countDeleted = sqlSession.delete("guestbook.delete",vo);
		return countDeleted;
		
	}
	
	
	public List<GuestBookVo> getList(){
		List<GuestBookVo> list = sqlSession.selectList("guestbook.selectList");
		return list;
	}	
	
	
	public List<GuestBookVo> getList(int page){
		List<GuestBookVo> list = sqlSession.selectList("guestbook.ajaxList",page);
		return list;
	}
	
}
