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
import com.active.feedback.dao.QuestionDao;
import com.active.feedback.entities.Question;
import com.active.feedback.entities.QuestionType;
import com.active.feedback.entities.Survey;
import com.active.feedback.entities.User;
import com.active.feedback.util.HibernateUtil;

public class QuestionDaoImpl implements QuestionDao{
	
	private Session session = null;
	private Transaction ts = null;
	
	public void add(Question question){
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "insert into questions (q_name, q_frn_qt_id) values ('"
					+ question.getName() + "'," + question.getQt().getId() + ") select @@IDENTITY as 'new_id'";
				
		try{
			Class.forName(JDBCConstant.dbDriverName).newInstance();
			conn = DriverManager.getConnection(JDBCConstant.dbUrl, JDBCConstant.dbUserName, JDBCConstant.dbPassword);
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				question.setId(rs.getInt("new_id"));
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
	}
	
	public void delete(int q_id){
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "delete from questions where q_id = " + String.valueOf(q_id);
				
		try{
			Class.forName(JDBCConstant.dbDriverName).newInstance();
			conn = DriverManager.getConnection(JDBCConstant.dbUrl, JDBCConstant.dbUserName, JDBCConstant.dbPassword);
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);
			ps.execute();
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
	}
	
	public void Update(Question question){
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "update questions set q_name = '" + question.getName() + "',q_frn_qt_id = " + question.getQt().getId() + " where q_id = " + String.valueOf(question.getId());
				
		try{
			Class.forName(JDBCConstant.dbDriverName).newInstance();
			conn = DriverManager.getConnection(JDBCConstant.dbUrl, JDBCConstant.dbUserName, JDBCConstant.dbPassword);
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);
			ps.execute();
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
	}
	
	public Question getQuestionById(int id){
		Question q = null;
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from questions with(nolock) join question_types with(nolock) on q_frn_qt_id = qt_id where q_id = ?";
				
		try{
			Class.forName(JDBCConstant.dbDriverName).newInstance();
			conn = DriverManager.getConnection(JDBCConstant.dbUrl, JDBCConstant.dbUserName, JDBCConstant.dbPassword);
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				q = new Question();
				q.setId(rs.getInt("q_id"));
				q.setName(rs.getString("q_name"));
				QuestionType qt = new QuestionType();
				qt.setId(rs.getInt("qt_id"));
				qt.setName(rs.getString("qt_name"));
				q.setQt(qt);
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
		
		return q;
	}
	
	public List<Question> getAllQuestion(){
		List<Question> qList = new ArrayList<Question>();
		
		Question q = null;
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from questions with(nolock) join question_types with(nolock) on q_frn_qt_id = qt_id ";
				
		try{
			Class.forName(JDBCConstant.dbDriverName).newInstance();
			conn = DriverManager.getConnection(JDBCConstant.dbUrl, JDBCConstant.dbUserName, JDBCConstant.dbPassword);
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				q = new Question();
				q.setId(rs.getInt("q_id"));
				q.setName(rs.getString("q_name"));
				QuestionType qt = new QuestionType();
				qt.setId(rs.getInt("qt_id"));
				qt.setName(rs.getString("qt_name"));
				q.setQt(qt);				
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
		
		return qList;
		
	}	
	
	public List<Question> getAllQuestionByUserId(int userId){
		List<Question> qList = new ArrayList<Question>();
		
		Question q = null;
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select distinct q.*, qt.* from questions q with(nolock) join question_types qt with(nolock) on qt_id = q_frn_qt_id join surveys_to_questions with(nolock) on stq_frn_q_id = q_id join surveys with(nolock) on survey_id = stq_frn_survey_id and survey_created_by = ?";
				
		try{
			Class.forName(JDBCConstant.dbDriverName).newInstance();
			conn = DriverManager.getConnection(JDBCConstant.dbUrl, JDBCConstant.dbUserName, JDBCConstant.dbPassword);
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				q = new Question();
				q.setId(rs.getInt("q_id"));
				q.setName(rs.getString("q_name"));
				QuestionType qt = new QuestionType();
				qt.setId(rs.getInt("qt_id"));
				qt.setName(rs.getString("qt_name"));
				q.setQt(qt);
				qList.add(q);
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
		
		return qList;
		
	}

}
