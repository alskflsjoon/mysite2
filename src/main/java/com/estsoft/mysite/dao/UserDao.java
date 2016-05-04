package com.estsoft.mysite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.db.DBConnection;
import com.estsoft.mysite.vo.UserVo;



@Repository
public class UserDao {
	
	@Autowired
	private DBConnection dbConnection;
	
	
//	public UserDao(DBConnection dbConnection){
//		this.dbConnection = dbConnection;
//	}
	
	
	
	public UserVo get(String email){
		UserVo vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = dbConnection.getConnection();
			String sql = "select no, email from user where email = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				vo=new UserVo();
				vo.setNo(rs.getLong(1));
				vo.setEmail(rs.getString(2));
			}
			
			return vo;
			
		}catch(SQLException ex){
			System.out.println("error:"+ex);
			return null;
		}finally{
			try{
			if(rs!=null){
				rs.close();
			}
			if(pstmt!=null){
				pstmt.close();
			}
			if(conn!=null){
				conn.close();
			}
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		
	}
	
	
	
	
	public UserVo get(UserVo vo){
		UserVo userVo = null;		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn=dbConnection.getConnection();
			String sql = "select no, name, email from user where email = ? and passwd = password(?)";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, vo.getEmail());
			pstmt.setString(2, vo.getPassword());
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				long no = rs.getLong(1);
				String email = rs.getString(3);
				String name = rs.getString(2);
				
				userVo = new UserVo();
				userVo.setNo(no);
				userVo.setEmail(email);
				userVo.setName(name);
			}
			
			return userVo;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally{
			try{
				if(rs!=null){
					rs.close();
				}
				if(pstmt!=null){
					pstmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			}catch(SQLException ex){
				System.out.println(ex);
			}
		}
		
	}
	
	public UserVo get(Long no){
		UserVo userVo = null;		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn=dbConnection.getConnection();
			String sql = "select name,gender from user where no = ?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				String name = rs.getString(1);
				String gender = rs.getString(2);
				
				userVo = new UserVo();
				userVo.setNo(no);
				userVo.setName(name);
				userVo.setGender(gender);
			}
			
			return userVo;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally{
			try{
				if(rs!=null){
					rs.close();
				}
				if(pstmt!=null){
					pstmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			}catch(SQLException ex){
				System.out.println(ex);
			}
		}
	}
	
	public void insert(UserVo vo){
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbConnection.getConnection();
			
			String sql = "insert into user "
					+ "	values(null,?,?,password(?),?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());
			
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try{
				if(pstmt!=null){
					pstmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	public void update(UserVo vo){
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dbConnection.getConnection();
			
			String sql = "update user set name=?, passwd=password(?), gender=? "
					+ "	where no=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getGender());
			pstmt.setLong(4, vo.getNo());
			
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try{
				if(pstmt!=null){
					pstmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	public long idCheck(String name){
		Long num=0L;		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn=dbConnection.getConnection();
			String sql = "select count(*) from user where email=?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, name);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				num=rs.getLong(1);
			}
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally{
			try{
				if(rs!=null){
					rs.close();
				}
				if(pstmt!=null){
					pstmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			}catch(SQLException ex){
				System.out.println(ex);
			}
		}
		return num;
	}
}
