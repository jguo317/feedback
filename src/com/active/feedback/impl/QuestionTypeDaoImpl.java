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
import com.active.feedback.dao.QuestionTypeDao;
import com.active.feedback.entities.QuestionType;
import com.active.feedback.util.HibernateUtil;

public class QuestionTypeDaoImpl implements QuestionTypeDao {

	private Session session = null;
	private Transaction ts = null;

	public List<QuestionType> getAllQuestionType() {
		List<QuestionType> qtList = new ArrayList<QuestionType>();
		QuestionType qt = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from question_types with(nolock)";
				
		try{
			Class.forName(JDBCConstant.dbDriverName).newInstance();
			conn = DriverManager.getConnection(JDBCConstant.dbUrl, JDBCConstant.dbUserName, JDBCConstant.dbPassword);
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				qt = new QuestionType();
				qt.setId(rs.getInt(1));
				qt.setName(rs.getString(2));
				qtList.add(qt);
			}
          }catch(Exception e){
             System.out.println("Exception occurs in SurveyDaoImpl.add()");
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
         return qtList;
		
	}

	public QuestionType getQuestionTypeById(int qt_id) {
		QuestionType qt = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from question_types with(nolock) where qt_id = " + String.valueOf(qt_id);
				
		try{
			Class.forName(JDBCConstant.dbDriverName).newInstance();
			conn = DriverManager.getConnection(JDBCConstant.dbUrl, JDBCConstant.dbUserName, JDBCConstant.dbPassword);
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				qt = new QuestionType();
				qt.setId(rs.getInt(1));
				qt.setName(rs.getString(2));
			}
          }catch(Exception e){
             System.out.println("Exception occurs in SurveyDaoImpl.add()");
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
		return qt;
	}

}
