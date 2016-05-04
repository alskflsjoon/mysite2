package com.estsoft.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.mysite.dao.UserDao;
import com.estsoft.mysite.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;

	public void join(UserVo vo){
		userDao.insert(vo);
	}
	
	public UserVo login(UserVo vo){
		UserVo authUser = userDao.get(vo);
		return authUser;
	}
	
	public UserVo getUser(String email){
		UserVo vo = userDao.get(email);
		return vo;
	}
	
	public boolean modify(UserVo vo){
		if(vo.getName().equals("")||vo.getPassword().equals("")){
			return false;
		}
		userDao.update(vo);
		return true;
	}
	
}
