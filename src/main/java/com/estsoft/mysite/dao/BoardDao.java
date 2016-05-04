package com.estsoft.mysite.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.db.DBConnection;
import com.estsoft.mysite.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	DBConnection dbConnection = null; 

	public BoardVo getVo(long no){
		BoardVo vo = sqlSession.selectOne("board.getVo", no);
		return vo;
	}

	public BoardVo getVoForReply(long no){
		BoardVo vo = sqlSession.selectOne("board.getVoForReply", no);
		return vo;
	}

	public void increaseViewCount(long no){
		sqlSession.update("board.increaseViewCount",no);
	}

	public long insert(BoardVo vo){
		sqlSession.insert("board.insert",vo);
		return vo.getNo();
	}

	public long reply(BoardVo vo){
		sqlSession.insert("board.reply", vo);
		return vo.getNo();
	}

	public void IncreaseBeforereply(BoardVo vo){
		sqlSession.update("board.increaseBeforeReply", vo);
	}

	public void delete(long no){
		sqlSession.delete("board.delete",no);
	}

	public List<BoardVo> getSearchList(long pNo, String search){
		long index = (pNo-1L)*3;
		Map<String, Object> map = new HashMap<String, Object>();
		search = "%"+search+"%";
		map.put("kwd", search);
		map.put("index", index);
		List<BoardVo> list = sqlSession.selectList("board.getList",map);
		return list;
	}

	public void update(BoardVo vo){
		sqlSession.update("board.modify",vo);
	}
	
	public long countAll(String search){
		search="%"+search+"%";
		return sqlSession.selectOne("board.countAll",search);
	}
}



