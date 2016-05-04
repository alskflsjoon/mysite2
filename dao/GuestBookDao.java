package com.estsoft.mysite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.estsoft.db.DBConnection;
import com.estsoft.mysite.vo.GuestBookVo;

public class GuestBookDao {
	DBConnection dbConnection = null;

	public GuestBookDao(DBConnection dbConnection){
		this.dbConnection = dbConnection;
	}

	public GuestBookVo get(long no){
		GuestBookVo vo = null;	
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs= null;
		try{
			conn = dbConnection.getConnection();
			stmt = conn.createStatement();

			String sql = ("SELECT no, name, date_format(reg_date, '%Y-%m-%d %h:%i:%s'),"
					+ " message from guestbook WHERE no="+no);
			rs = stmt.executeQuery(sql);

			while(rs.next()){
				long no2 = rs.getLong(1);
				String name = rs.getString(2);
				String regDate = rs.getString(3);
				String message = rs.getString(4);

				vo = new GuestBookVo();
				vo.setNo(no2);
				vo.setName(name);
				vo.setRegDate(regDate);
				vo.setMessage(message);
			}
			return vo;
		} catch(SQLException ex){
			System.out.println("SQL error: "+ex);
			return null;
		} finally{
			try{
				if(rs!=null){
					rs.close();
				}
				if(stmt!=null){
					stmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
	}



	public long insert(GuestBookVo vo){
		long no = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			conn = dbConnection.getConnection();

			String sql = "INSERT INTO guestbook VALUES(null, ?, now(), ?, password(?))";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getMessage());
			pstmt.setString(3, vo.getPasswd());
			pstmt.executeUpdate();

			stmt=conn.createStatement();
			rs = stmt.executeQuery("SELECT last_insert_id()");
			if(rs.next()){
				no = rs.getLong(1);
			}

			return no;

		}catch(SQLException ex){
			System.out.println("SQL error: "+ex);
			return 0L;
		} finally{
			try{
				if(rs!=null){
					rs.close();
				}
				if(stmt!=null){
					stmt.close();
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

	public void delete(GuestBookVo vo){
		Connection conn = null;
		PreparedStatement pstmt = null;

		try{
			conn = dbConnection.getConnection();

			String sql = "delete from guestbook where no=? and passwd=password(?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getPasswd());

			pstmt.executeUpdate();

		}catch(SQLException ex){
			System.out.println("SQL error: "+ex);
		} finally{
			try{

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


	public List<GuestBookVo> getList(){
		List<GuestBookVo> list = new ArrayList<GuestBookVo>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs= null;
		try{
			conn = dbConnection.getConnection();
			stmt = conn.createStatement();

			String sql = "SELECT no, name, date_format(reg_date, '%Y-%m-%d %h:%i:%s'), message from guestbook ORDER BY no desc";
			rs = stmt.executeQuery(sql);

			while(rs.next()){
				long no = rs.getLong(1);
				String name = rs.getString(2);
				String regDate = rs.getString(3);
				String message = rs.getString(4);


				GuestBookVo vo = new GuestBookVo();

				vo.setNo(no);
				vo.setName(name);
				vo.setRegDate(regDate);
				vo.setMessage(message);


				list.add(vo);

			}
		} catch(SQLException ex){
			System.out.println("SQL error: "+ex);
		} finally{
			try{
				if(rs!=null){
					rs.close();
				}
				if(stmt!=null){
					stmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}


		return list;
	}

	public List<GuestBookVo> getList(int page){
		List<GuestBookVo> list = new ArrayList<GuestBookVo>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs= null;
		try{
			conn = dbConnection.getConnection();
			stmt = conn.createStatement();

			String sql = "SELECT no, name, date_format(reg_date, '%Y-%m-%d %h:%i:%s'), message"
					+ " FROM guestbook ORDER BY no desc LIMIT " + (page-1)*3 + ",3";
			rs = stmt.executeQuery(sql);

			while(rs.next()){
				long no = rs.getLong(1);
				String name = rs.getString(2);
				String regDate = rs.getString(3);
				String message = rs.getString(4);


				GuestBookVo vo = new GuestBookVo();

				vo.setNo(no);
				vo.setName(name);
				vo.setRegDate(regDate);
				vo.setMessage(message);


				list.add(vo);

			}
		} catch(SQLException ex){
			System.out.println("SQL error: "+ex);
		} finally{
			try{
				if(rs!=null){
					rs.close();
				}
				if(stmt!=null){
					stmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}


		return list;
	}
}
