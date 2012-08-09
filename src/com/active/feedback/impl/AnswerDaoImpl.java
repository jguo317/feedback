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
import com.active.feedback.bean.ResultBean;
import com.active.feedback.dao.AnswerDao;
import com.active.feedback.entities.Answer;
import com.active.feedback.util.HibernateUtil;

public class AnswerDaoImpl implements AnswerDao{
	
	private Session session = null;
	private Transaction ts = null;
	
	
	public List<Answer> getAnswersBySIdUId(int surveyId, int userId){
	
		List<Answer> answerList = new ArrayList<Answer>();
		Answer answer = null;
		String sql = "select * from answers as a where a.answer_frn_survey_id = " + surveyId+ 
					    " and a.answer_frn_user_id = " + userId+ 
					    " order by a.answer_frn_q_id";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
				
		try{
			Class.forName(JDBCConstant.dbDriverName).newInstance();
			conn = DriverManager.getConnection(JDBCConstant.dbUrl, JDBCConstant.dbUserName, JDBCConstant.dbPassword);
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				answer = new Answer();
				answer.setId(rs.getInt("answer_id"));
				answer.setQ_id(rs.getInt("answer_frn_q_id"));
				answer.setQd_id(rs.getInt("answer_frn_qd_id"));
				answer.setSurvey_id(rs.getInt("answer_frn_survey_id"));
				answer.setUser_id(rs.getInt("answer_frn_user_id"));
				answer.setValue(rs.getString("answer_value"));
				answerList.add(answer);
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
	
	      return answerList;		
		
	}
	
	public List<Answer> getAnswersBySIdTId(int surveyId, int teamId){
		
		List<Answer> answers = null;
		

					
			//String qStr = "select * from answers as a where a.answer_frn_survey_id = ? and a.answer_frn_qd_id = ? order by a.answer_frn_q_id";			
			//Query query = session.createSQLQuery("qStr").addEntity(Answer.class);
			
			String qStr = "select * from Answer as a where a.answer_frn_survey_id = ? and a.answer_frn_qd_id = ? order by a.answer_frn_q_id";			
			Query query = session.createQuery("qStr");
		
	   
		
		return answers;		
			
		}
	
	
	public void addAnswers(int suerveyId, int userId, List<ResultBean> rList){
		
		if(rList != null && rList.size()>0){				
			for(ResultBean b:rList){
				Connection conn = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				
				try {
					String qq = "";
					Class.forName(JDBCConstant.dbDriverName).newInstance();
					conn = DriverManager.getConnection(JDBCConstant.dbUrl, JDBCConstant.dbUserName, JDBCConstant.dbPassword);
					conn.setAutoCommit(true);				
					if(b.getQuestionDataId() == null || b.getQuestionDataId().size()==0){					
							
							qq = "insert into answers " +
										"(answer_value, " +
										"answer_frn_q_id," +
										"answer_frn_survey_id, " +
										"answer_frn_user_id, " +
										"answer_timestamp) " +										
										" values ('"+b.getAnswerValue().replaceAll("'", "''")+"'," + b.getQuestionId()+
										"," + suerveyId +
										"," + userId +
										"," +
										"getdate())";
							
					
						
					}else{
						
						List<Integer> qdIds = b.getQuestionDataId();
						for(Integer a:qdIds){
							qq = qq + " insert into answers " +
							"(answer_value, " +
							"answer_frn_qd_id," +
							"answer_frn_q_id," +
							"answer_frn_survey_id, " +
							"answer_frn_user_id, " +
							"answer_timestamp) " +
							
							" values ('"+b.getAnswerValue()+"'," + a.intValue()+
							"," + b.getQuestionId()+
							"," + suerveyId +
							"," + userId +
							"," +
							"getdate())";
						}
					}
					System.out.println(qq);
					ps = conn.prepareStatement(qq);
					ps.execute();							
				} catch(Exception e){
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
		}
	}
	
	public void addAnswer(){
		
		try{
			session = HibernateUtil.getSession();
			ts = session.beginTransaction();
			
			Query q = session.createSQLQuery("select max(answer_id) from answers");
			
			List list = q.list();
			int maxid = 1;  
			
			if (list != null && list.size()>0){
				maxid = ((Integer)list.get(0)).intValue()+1;
			}
			
			String qq = "insert into answers " +
			"(answer_id, " +
			"answer_value, " +
			"answer_frn_qd_id," +
			"answer_frn_q_id," +
			"answer_frn_survey_id, " +
			"answer_frn_user_id, " +
			"answer_timestamp) " +
			
			" values ("+maxid+", " +
			"''," +
			"250," +
			"138," +
			"90," +
			"2," +
			"now())";
	
			session.createSQLQuery(qq).executeUpdate();
		
			
			ts.commit();
          }catch(Exception e){
             System.out.println("Exception occurs in AnswerDaoImpl.adddfdfs()");
             e.printStackTrace();
          }finally{
        	  HibernateUtil.closeSession(session);
        }
	}
	

}
