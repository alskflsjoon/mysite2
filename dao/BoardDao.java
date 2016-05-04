package com.estsoft.mysite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.estsoft.db.DBConnection;
import com.estsoft.mysite.vo.BoardVo;

public class BoardDao {
	DBConnection dbConnection = null; 
	
	public BoardDao(DBConnection dbConnection){
		this.dbConnection = dbConnection;
	}

	public BoardVo getVo(long no){
		BoardVo boardVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn=dbConnection.getConnection();
			String sql = "select title, content, user_no, depth from board where no = ?";

			pstmt=conn.prepareStatement(sql);
			pstmt.setLong(1, no);

			rs=pstmt.executeQuery();

			while(rs.next()){
				String title = rs.getString(1);
				String text = rs.getString(2);
				long userNo = rs.getLong(3);
				long depth = rs.getLong(4);

				boardVo = new BoardVo();
				boardVo.setTitle(title);
				boardVo.setText(text);
				boardVo.setUserNo(userNo);
				boardVo.setNo(no);
				boardVo.setDepth(depth);
			}

			return boardVo;

		} catch (SQLException e) {
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

	public BoardVo getVoForReply(long no){
		BoardVo boardVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn=dbConnection.getConnection();
			String sql = "select group_no, order_no+1, depth+1 from board where no = ?";

			pstmt=conn.prepareStatement(sql);
			pstmt.setLong(1, no);

			rs=pstmt.executeQuery();

			while(rs.next()){
				long groupNo = rs.getLong(1);
				long orderNo = rs.getLong(2);
				long depth = rs.getLong(3);

				boardVo = new BoardVo();
				boardVo.setGroupNo(groupNo);
				boardVo.setOrderNo(orderNo);
				boardVo.setDepth(depth);
			}

			return boardVo;

		} catch (SQLException e) {
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

	public void increaseViewCount(long no){
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dbConnection.getConnection();

			String sql = "update board set hits = hits+1 where no=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);

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

	public void insert(BoardVo vo){
		Connection conn = null;
		PreparedStatement pstmt = null;

		try{
			conn = dbConnection.getConnection();

			String sql = "insert into board values "
					+ " (null, ?, ?, ?, ?, now(), 0,"
					+ " (select ifnull(max(group_no),0)+1 from board as b),"
					+ "  1, 0)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, vo.getUserNo());
			pstmt.setString(2, vo.getUserName());
			pstmt.setString(3, vo.getTitle());
			pstmt.setString(4, vo.getText());

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

	public void reply(BoardVo vo){
		Connection conn = null;
		PreparedStatement pstmt = null;

		try{
			conn = dbConnection.getConnection();

			String sql = "insert into board values (null, ?, ?, ?, "
					+ " ?, now(), 0, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, vo.getUserNo());
			pstmt.setString(2, vo.getUserName());
			pstmt.setString(3, vo.getTitle());
			pstmt.setString(4, vo.getText());
			pstmt.setLong(5, vo.getGroupNo());
			pstmt.setLong(6, vo.getOrderNo());
			pstmt.setLong(7, vo.getDepth());

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

	public void IncreaseBeforereply(BoardVo vo){
		Connection conn = null;
		PreparedStatement pstmt = null;

		try{
			conn = dbConnection.getConnection();

			String sql = "update board set "
					+ "order_no = order_no +1 "
					+ "where group_no=? and order_no >= ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, vo.getGroupNo());
			pstmt.setLong(2, vo.getOrderNo());

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

	public void delete(long no){
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dbConnection.getConnection();

			String sql = "delete from board where no=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);

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

	public List<BoardVo> getList(){
		List<BoardVo> list = new ArrayList<BoardVo>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs= null;

		try{
			conn = dbConnection.getConnection();
			stmt = conn.createStatement();

			String sql = "SELECT no, user_no, author, title, "
					+ " content, date_format(reg_date, '%Y-%m-%d %h:%i:%s'), "
					+ " hits, group_no, order_no, depth "
					+ " from board ORDER BY group_no DESC, order_no ASC";
			rs = stmt.executeQuery(sql);

			while(rs.next()){
				long no = rs.getLong(1);
				long userNo = rs.getLong(2);
				String userName = rs.getString(3);
				String title = rs.getString(4);
				String text = rs.getString(5);
				String regDate = rs.getString(6);
				long hit = rs.getLong(7);
				long groupNo = rs.getLong(8);
				long orderNo = rs.getLong(9);
				long depth = rs.getLong(10);

				BoardVo vo = new BoardVo();

				vo.setNo(no);
				vo.setUserNo(userNo);
				vo.setUserName(userName);
				vo.setTitle(title);
				vo.setText(text);
				vo.setRegDate(regDate);
				vo.setHit(hit);
				vo.setGroupNo(groupNo);
				vo.setOrderNo(orderNo);
				vo.setDepth(depth);


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

	public List<BoardVo> getList(long pNo){
		List<BoardVo> list = new ArrayList<BoardVo>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs= null;

		try{
			conn = dbConnection.getConnection();
			stmt = conn.createStatement();
			long index = (pNo-1L)*5;
			String sql = "SELECT no, user_no, author, title, "
					+ " content, date_format(reg_date, '%Y-%m-%d %h:%i:%s'), "
					+ " hits, group_no, order_no, depth "
					+ " from board ORDER BY group_no DESC, order_no ASC LIMIT "
					+" "+index+", 5";
			rs = stmt.executeQuery(sql);

			while(rs.next()){
				long no = rs.getLong(1);
				long userNo = rs.getLong(2);
				String userName = rs.getString(3);
				String title = rs.getString(4);
				String text = rs.getString(5);
				String regDate = rs.getString(6);
				long hit = rs.getLong(7);
				long groupNo = rs.getLong(8);
				long orderNo = rs.getLong(9);
				long depth = rs.getLong(10);

				BoardVo vo = new BoardVo();

				vo.setNo(no);
				vo.setUserNo(userNo);
				vo.setUserName(userName);
				vo.setTitle(title);
				vo.setText(text);
				vo.setRegDate(regDate);
				vo.setHit(hit);
				vo.setGroupNo(groupNo);
				vo.setOrderNo(orderNo);
				vo.setDepth(depth);

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
	
	public List<BoardVo> getSearchList(long pNo, String search){
		List<BoardVo> list = new ArrayList<BoardVo>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs= null;
		
		if(search == null){
			search = "";
		}
		
		try{
			conn = dbConnection.getConnection();
			long index = (pNo-1L)*3;
			
			stmt=conn.createStatement();
			
			String sql = "SELECT no, user_no, author, title, "
					+ " content, date_format(reg_date, '%Y-%m-%d %h:%i:%s'), "
					+ " hits, group_no, order_no, depth "
					+ " from board where title like '%"+search+"%' or content like '%"+search+"%' "
					+ " ORDER BY group_no DESC, order_no ASC LIMIT"
					+" "+index+", 3";
			
			rs = stmt.executeQuery(sql);

			while(rs.next()){
				long no = rs.getLong(1);
				long userNo = rs.getLong(2);
				String userName = rs.getString(3);
				String title = rs.getString(4);
				String text = rs.getString(5);
				String regDate = rs.getString(6);
				long hit = rs.getLong(7);
				long groupNo = rs.getLong(8);
				long orderNo = rs.getLong(9);
				long depth = rs.getLong(10);

				BoardVo vo = new BoardVo();

				vo.setNo(no);
				vo.setUserNo(userNo);
				vo.setUserName(userName);
				vo.setTitle(title);
				vo.setText(text);
				vo.setRegDate(regDate);
				vo.setHit(hit);
				vo.setGroupNo(groupNo);
				vo.setOrderNo(orderNo);
				vo.setDepth(depth);


				list.add(vo);

			}
		} catch(SQLException ex){
			System.out.println("SQL error: "+ex);
			//ex.printStackTrace();
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

	public void update(BoardVo vo){
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dbConnection.getConnection();

			String sql = "update board set title=?, content=? "
					+ "	where no=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getText());
			pstmt.setLong(3, vo.getNo());

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
	
	public long countAll(String search){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		long count = 0;
		if(search == null){
			search = "";
		}
		try {
			conn=dbConnection.getConnection();
			String sql = "select count(no) from board where title like '%"+search+"%' or content like '%"+search+"%'";
			stmt = conn.createStatement();
			rs=stmt.executeQuery(sql);

			while(rs.next()){
				count = rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
				System.out.println(ex);
			}
		}
		return count;
	}

	public long getNewestNo(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		long count = 0;
		
		try {
			conn=dbConnection.getConnection();
			String sql = "select max(no) from board";
			stmt = conn.createStatement();
			rs=stmt.executeQuery(sql);

			while(rs.next()){
				count = rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
				System.out.println(ex);
			}
		}
		return count;
	}	
		
}



