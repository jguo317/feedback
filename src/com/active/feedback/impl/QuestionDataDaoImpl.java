package com.active.feedback.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.active.feedback.JDBC.JDBCConstant;
import com.active.feedback.dao.QuestionDataDao;
import com.active.feedback.entities.Question;
import com.active.feedback.entities.QuestionData;
import com.active.feedback.util.HibernateUtil;


public class QuestionDataDaoImpl implements QuestionDataDao {

	public List<QuestionData> getAllQuestionDataByQId(int q_id) {
		
		List<QuestionData> qdList = new ArrayList<QuestionData>();		
		QuestionData qd = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;		
		String sql = "select * from question_data as qd where qd_frn_q_id= ? ";
		
		try{
			Class.forName(JDBCConstant.dbDriverName).newInstance();
			conn = DriverManager.getConnection(JDBCConstant.dbUrl, JDBCConstant.dbUserName, JDBCConstant.dbPassword);
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, q_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				qd = new QuestionData();
				qd.setId(rs.getInt("qd_id"));
				qd.setQ_id(rs.getInt("qd_frn_q_id"));
				qd.setValue(rs.getString("qd_value"));
				qdList.add(qd);
			}
          }catch(Exception e){
             e.printStackTrace();
          }finally{
        	  if (conn != null) {
  				try {
  					conn.close();
  				} catch (SQLException e) {
  					// TODO Auto-generated catch block
  					e.printStackTrace();
  				}
  			}
        }
		
		return qdList;
		
	}
	
	public void save(QuestionData qd) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "INSERT INTO [dbo].[question_data] ([qd_value],[qd_frn_q_id]) VALUES (?, ?) select @@identity as 'new_id'";

		try{
			Class.forName(JDBCConstant.dbDriverName).newInstance();
			conn = DriverManager.getConnection(JDBCConstant.dbUrl, JDBCConstant.dbUserName, JDBCConstant.dbPassword);
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);
			ps.setString(1, qd.getValue());
			ps.setInt(2, qd.getQ_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				qd.setId(rs.getInt(1));
			}
          }catch(Exception e){
             e.printStackTrace();
          }finally{
        	  if (conn != null) {
  				try {
  					conn.close();
  				} catch (SQLException e) {
  					// TODO Auto-generated catch block
  					e.printStackTrace();
  				}
  			}
        }
	}
	
	public void delete(QuestionData qd) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "DELETE FROM [dbo].[question_data]  WHERE qd_id = ?";

		try{
			Class.forName(JDBCConstant.dbDriverName).newInstance();
			conn = DriverManager.getConnection(JDBCConstant.dbUrl, JDBCConstant.dbUserName, JDBCConstant.dbPassword);
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, qd.getId());
			ps.execute();
          }catch(Exception e){
             e.printStackTrace();
          }finally{
        	  if (conn != null) {
  				try {
  					conn.close();
  				} catch (SQLException e) {
  					// TODO Auto-generated catch block
  					e.printStackTrace();
  				}
  			}
        }	
	}
	
	public void update(QuestionData qd) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "UPDATE [dbo].[question_data] SET [qd_value] = ?, [qd_frn_q_id] = ? WHERE qd_id = ?";

		try{
			Class.forName(JDBCConstant.dbDriverName).newInstance();
			conn = DriverManager.getConnection(JDBCConstant.dbUrl, JDBCConstant.dbUserName, JDBCConstant.dbPassword);
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);
			ps.setString(1, qd.getValue());
			ps.setInt(2, qd.getQ_id());
			ps.setInt(3, qd.getId());
			ps.execute();
          }catch(Exception e){
             e.printStackTrace();
          }finally{
        	  if (conn != null) {
  				try {
  					conn.close();
  				} catch (SQLException e) {
  					// TODO Auto-generated catch block
  					e.printStackTrace();
  				}
  			}
        }	
	}
	
	public QuestionData getQuestionDataById(int id) {
		QuestionData qd = new QuestionData();
		String sql = "SELECT * FROM [dbo].[question_data] WITH(NOLOCK) WHERE qd_id = ?";		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try{
			Class.forName(JDBCConstant.dbDriverName).newInstance();
			conn = DriverManager.getConnection(JDBCConstant.dbUrl, JDBCConstant.dbUserName, JDBCConstant.dbPassword);
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				qd.setId(rs.getInt("qd_id"));
				qd.setQ_id(rs.getInt("qd_frn_q_id"));
				qd.setValue(rs.getString("qd_value"));
			}
          }catch(Exception e){
             e.printStackTrace();
          }finally{
        	  if (conn != null) {
  				try {
  					conn.close();
  				} catch (SQLException e) {
  					// TODO Auto-generated catch block
  					e.printStackTrace();
  				}
  			}
        }	
        return qd;
	}

}
