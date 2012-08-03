package com.active.feedback.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.active.feedback.JDBC.JDBCConstant;
import com.active.feedback.dao.SurveyDao;
import com.active.feedback.entities.Question;
import com.active.feedback.entities.QuestionData;
import com.active.feedback.entities.QuestionType;
import com.active.feedback.entities.Survey;
import com.active.feedback.entities.Survey2Question;
import com.active.feedback.entities.Survey2Team;
import com.active.feedback.entities.SurveyReport;
import com.active.feedback.entities.Team;
import com.active.feedback.util.HibernateUtil;

public class SurveyDaoImpl implements SurveyDao{
	
	private Session session = null;
	private Transaction ts = null;
	
	public void add(Survey survey){
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "insert into surveys (survey_title, survey_created_by, survey_start_date, survey_end_date) values ('"
					+ survey.getTitle() + "'," + survey.getCreateBy() + ",getdate(),getdate()) select @@IDENTITY as 'new_id'";
				
		try{
			System.out.println(sql);
			Class.forName(JDBCConstant.dbDriverName).newInstance();
			conn = DriverManager.getConnection(JDBCConstant.dbUrl, JDBCConstant.dbUserName, JDBCConstant.dbPassword);
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				survey.setId(rs.getInt("new_id"));
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
	
	public void update(Survey survey){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DateFormat df = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss "); 		
		String sql = "update surveys set survey_title = '" + survey.getTitle() + "',survey_for_member="+ survey.getMemberSurvey() +", survey_start_date = '"+df.format(survey.getStartDate())
					+"', survey_end_date = '" +df.format(survey.getEndDate())+ "' where survey_id = " + String.valueOf(survey.getId());
				
		try{
			System.out.println(sql);
			Class.forName(JDBCConstant.dbDriverName).newInstance();
			conn = DriverManager.getConnection(JDBCConstant.dbUrl, JDBCConstant.dbUserName, JDBCConstant.dbPassword);
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);
			ps.execute();
          }catch(Exception e){
             System.out.println("Exception occurs in SurveyDaoImpl.update()");
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
	
	public Survey getSurveyById (int id){
		Survey s = null;
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from surveys with(nolock) where survey_id = " + String.valueOf(id);
				
		try{
			Class.forName(JDBCConstant.dbDriverName).newInstance();
			conn = DriverManager.getConnection(JDBCConstant.dbUrl, JDBCConstant.dbUserName, JDBCConstant.dbPassword);
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				s = new Survey();
				s.setId(id);
				s.setTitle(rs.getString("survey_title"));
				s.setStartDate(rs.getTimestamp("survey_start_date"));
				s.setEndDate(rs.getTimestamp("survey_end_date"));
				s.setMemberSurvey(rs.getInt("survey_for_member"));
			}
          }catch(Exception e){
             System.out.println("Exception occurs in SurveyDaoImpl.update()");
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
		
		return s;
		
	}
	
	public List<Survey> getAllSurveys(){
		
		List<Survey> sList = null;
		
		Survey s = null;
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from surveys with(nolock)";
				
		try{
			Class.forName(JDBCConstant.dbDriverName).newInstance();
			conn = DriverManager.getConnection(JDBCConstant.dbUrl, JDBCConstant.dbUserName, JDBCConstant.dbPassword);
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				s = new Survey();
				s.setId(rs.getInt("survey_id"));
				s.setTitle(rs.getString("survey_titel"));
				s.setStartDate(rs.getDate("survey_start_date"));
				s.setStartDate(rs.getDate("survey_end_date"));
				s.setCreateBy(rs.getInt("survey_created_by"));
				sList.add(s);
			}
          }catch(Exception e){
             System.out.println("Exception occurs in SurveyDaoImpl.update()");
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
		
		return sList;
		
	}
	
	public List<Survey> getAllSurveysByUserId(int userId){
		
		List<Survey> sList = new ArrayList<Survey>();
		
		Survey s = null;
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from surveys with(nolock) where survey_created_by = " + String.valueOf(userId);
				
		try{
			Class.forName(JDBCConstant.dbDriverName).newInstance();
			conn = DriverManager.getConnection(JDBCConstant.dbUrl, JDBCConstant.dbUserName, JDBCConstant.dbPassword);
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				s = new Survey();
				s.setId(rs.getInt("survey_id"));
				s.setTitle(rs.getString("survey_title"));
				s.setStartDate(rs.getDate("survey_start_date"));
				s.setEndDate(rs.getDate("survey_end_date"));
				s.setCreateBy(rs.getInt("survey_created_by"));
				sList.add(s);
			}
          }catch(Exception e){
             System.out.println("Exception occurs in SurveyDaoImpl.update()");
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
		
		return sList;
		
	}
	
	public void addSurvey2Question (Survey2Question s2q){
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "INSERT INTO [dbo].[surveys_to_questions] ([stq_frn_survey_id],[stq_frn_q_id],[stq_order_id]) values ("
					+ s2q.getSurvey_id() + "," + s2q.getQ_id() + "," + s2q.getOrder_id() + ")";
				
		try{
			System.out.println(sql);
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
	
	public List<Question> getQuestionsBySurveyId (int id){
		List<Question> qList = new ArrayList<Question>();

		Question q = null;
		QuestionData qd = null;
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select distinct q.*, qt.* from questions q with(nolock) join question_types qt with(nolock) on qt_id = q_frn_qt_id join surveys_to_questions stq with(nolock) on stq_frn_q_id = q_id and stq_frn_survey_id = ?";				
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
				
				qList.add(q);
			}
			
			sql = "select distinct qd.* from question_Data qd with(nolock) join surveys_to_questions stq with(nolock) on stq_frn_q_id = qd_frn_q_id and stq_frn_survey_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			Map<Integer, List<QuestionData>> qdMap = new HashMap<Integer, List<QuestionData>>();
			while (rs.next()) {
				qd = new QuestionData();
				qd.setId(rs.getInt("qd_id"));
				qd.setQ_id(rs.getInt("qd_frn_q_id"));
				qd.setValue(rs.getString("qd_value"));
				
				if (qdMap.containsKey(qd.getQ_id())) {
					qdMap.get(qd.getQ_id()).add(qd);
				} else {
					List<QuestionData> qdList = new ArrayList<QuestionData>();
					qdList.add(qd);
					qdMap.put(qd.getQ_id(), qdList);
				}
			}
			
			for (int i = 0; i < qList.size(); i ++) {
				q = qList.get(i);
				if (qdMap.containsKey(q.getId())) {
					q.setQdList(qdMap.get(q.getId()));
				}
			}

          }catch(Exception e){
             System.out.println("Exception occurs in SurveyDaoImpl.update()");
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

	public void addSurvey2Team (Survey2Team s2t){
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "INSERT INTO [dbo].[surveys_to_teams] ([stt_frn_survey_id],[stt_frn_team_id]) VALUES ("
					+ s2t.getSurvey_id() + "," + s2t.getTeam_id() + ")";
				
		try{
			System.out.println(sql);
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

	public void delete(Survey survey) {
		// TODO Auto-generated method stub
		
	}

	public List<Team> getTeamsBySurveyId(int id) {
		List<Team> tList = new ArrayList<Team>();
		Team t = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select t.* from teams t with(nolock) join surveys_to_teams with(nolock) on stt_frn_team_id = team_id and stt_frn_survey_id = ? order by team_name";
				
		try{
			Class.forName(JDBCConstant.dbDriverName).newInstance();
			conn = DriverManager.getConnection(JDBCConstant.dbUrl, JDBCConstant.dbUserName, JDBCConstant.dbPassword);
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				t = new Team();
				t.setId(rs.getInt("team_id"));
				t.setName(rs.getString("team_name"));
				tList.add(t);
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

		return tList;
		
	}
	
	public void deleteTeamsBySurveyId (int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "DELETE FROM [dbo].[surveys_to_teams] WHERE [stt_frn_survey_id]=" + id;
		try{
			System.out.println(sql);
			Class.forName(JDBCConstant.dbDriverName).newInstance();
			conn = DriverManager.getConnection(JDBCConstant.dbUrl, JDBCConstant.dbUserName, JDBCConstant.dbPassword);
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);
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

	public Map<String, String> getSurveyInfoForEmailById(int id) {
		Map<String, String> emailMap = new Hashtable<String, String>();
		Team t = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select survey_title, owner.user_email as email_from, (select member.user_email + ';' from users member with(nolock) join teams_to_members on member.user_id = ttm_frn_user_id join surveys_to_teams on stt_frn_team_id = ttm_frn_team_id where stt_frn_survey_id = survey_id FOR XML PATH('')) as email_to from	surveys with(nolock) join	users owner with(nolock) on owner.user_id = survey_created_by where	survey_id = ?";				
		try{
			Class.forName(JDBCConstant.dbDriverName).newInstance();
			conn = DriverManager.getConnection(JDBCConstant.dbUrl, JDBCConstant.dbUserName, JDBCConstant.dbPassword);
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				emailMap.put("survey_title", rs.getString("survey_title"));
				emailMap.put("email_from", rs.getString("email_from"));
				emailMap.put("email_to", rs.getString("email_to"));
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
          
        return emailMap;
	}

	public void deleteQuestionBySurveyId(int survey_id, int q_id) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "delete from surveys_to_questions where stq_frn_survey_id=? and stq_frn_q_id = ?";
		try{
			Class.forName(JDBCConstant.dbDriverName).newInstance();
			conn = DriverManager.getConnection(JDBCConstant.dbUrl, JDBCConstant.dbUserName, JDBCConstant.dbPassword);
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, survey_id);
			ps.setInt(2, q_id);
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
	
	public List<SurveyReport> getSurveyReportByIds(int survey_id, int team_id, int user_id) {
		if (user_id == 0) {
			return getSurveyReportBySurveyId(survey_id);
		} else {
			return getSurveyReportByUserId(survey_id, user_id);
		}
	}
	
	private List<SurveyReport> getSurveyReportBySurveyId(int id) {
		List<SurveyReport> srList = new ArrayList<SurveyReport>();
		//Map<Integer, SurveyReport> srMap = new HashMap<Integer, SurveyReport>();
		SurveyReport sr = null;
		QuestionData qd = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select distinct q_id, q_name, qt_name from questions with(nolock) join question_types with(nolock) on q_frn_qt_id = qt_id join surveys_to_questions with(nolock) on stq_frn_q_id = q_id and stq_frn_survey_id = ?";
		try{
			Class.forName(JDBCConstant.dbDriverName).newInstance();
			conn = DriverManager.getConnection(JDBCConstant.dbUrl, JDBCConstant.dbUserName, JDBCConstant.dbPassword);
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				sr = new SurveyReport();
				sr.setQ_id(rs.getInt("q_id"));
				sr.setQ_name(rs.getString("q_name"));
				sr.setQ_type(rs.getString("qt_name"));
				sr.setAnswerList(new ArrayList<String>());
				sr.setQdList(new ArrayList<QuestionData>());
				srList.add(sr);
			}
			
			if (ps != null) ps.close();
			
			sql = "select q_id, qd_id, qd_value,count(distinct answer_id) as 'qd_count' from question_data with(nolock) join questions with(nolock) on qd_frn_q_id = q_id join question_types with(nolock) on q_frn_qt_id = qt_id and qt_name in ('radio','checkbox') join surveys_to_questions with(nolock) on stq_frn_q_id = q_id and stq_frn_survey_id = ? left join answers with(nolock) on answer_frn_qd_id = qd_id and answer_frn_survey_id = stq_frn_survey_id group by q_id, qd_id, qd_value";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				qd = new QuestionData();
				qd.setValue(rs.getString("qd_value"));
				qd.setId(rs.getInt("qd_id"));
				qd.setCount(rs.getInt("qd_count"));
				
				for (int i=0; i<srList.size(); i++) {
					if (srList.get(i).getQ_id() == rs.getInt("q_id")) {
						srList.get(i).getQdList().add(qd);
					}
				}
			}
			
			if (ps != null) ps.close();
			
			sql = "select q_id, answer_value from questions with(nolock) join question_types with(nolock) on q_frn_qt_id = qt_id and qt_name = 'text' join surveys_to_questions with(nolock) on stq_frn_q_id = q_id and stq_frn_survey_id = ? left join answers with(nolock) on answer_frn_q_id = q_id and answer_frn_survey_id = stq_frn_survey_id";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				for (int i=0; i<srList.size(); i++) {
					if (srList.get(i).getQ_id() == rs.getInt("q_id")) {
						System.out.println("Answer - " + rs.getString("answer_value"));
						srList.get(i).getAnswerList().add(rs.getString("answer_value"));
					}
				}
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
		
		return srList;		
	}
	
	private List<SurveyReport> getSurveyReportByUserId(int id, int user_id) {
		List<SurveyReport> srList = new ArrayList<SurveyReport>();
		//Map<Integer, SurveyReport> srMap = new HashMap<Integer, SurveyReport>();
		SurveyReport sr = null;
		QuestionData qd = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select distinct q_id, q_name, qt_name from questions with(nolock) join question_types with(nolock) on q_frn_qt_id = qt_id join surveys_to_questions with(nolock) on stq_frn_q_id = q_id and stq_frn_survey_id = ?";
		try{
			Class.forName(JDBCConstant.dbDriverName).newInstance();
			conn = DriverManager.getConnection(JDBCConstant.dbUrl, JDBCConstant.dbUserName, JDBCConstant.dbPassword);
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				sr = new SurveyReport();
				sr.setQ_id(rs.getInt("q_id"));
				sr.setQ_name(rs.getString("q_name"));
				sr.setQ_type(rs.getString("qt_name"));
				sr.setAnswerList(new ArrayList<String>());
				sr.setQdList(new ArrayList<QuestionData>());
				srList.add(sr);
			}
			
			if (ps != null) ps.close();
			
			sql = "select q_id, qd_id, qd_value,count(distinct answer_id) as 'qd_count' from question_data with(nolock) join questions with(nolock) on qd_frn_q_id = q_id join question_types with(nolock) on q_frn_qt_id = qt_id and qt_name in ('radio','checkbox') join surveys_to_questions with(nolock) on stq_frn_q_id = q_id and stq_frn_survey_id = ? left join answers with(nolock) on answer_frn_qd_id = qd_id and answer_frn_survey_id = stq_frn_survey_id and answer_frn_user_id = ? group by q_id, qd_id, qd_value";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2, user_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				qd = new QuestionData();
				qd.setValue(rs.getString("qd_value"));
				qd.setId(rs.getInt("qd_id"));
				qd.setCount(rs.getInt("qd_count"));
				
				for (int i=0; i<srList.size(); i++) {
					if (srList.get(i).getQ_id() == rs.getInt("q_id")) {
						srList.get(i).getQdList().add(qd);
					}
				}
			}
			
			if (ps != null) ps.close();
			
			sql = "select q_id, answer_value from questions with(nolock) join question_types with(nolock) on q_frn_qt_id = qt_id and qt_name = 'text' join surveys_to_questions with(nolock) on stq_frn_q_id = q_id and stq_frn_survey_id = ? left join answers with(nolock) on answer_frn_q_id = q_id and answer_frn_survey_id = stq_frn_survey_id  and answer_frn_user_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2, user_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				for (int i=0; i<srList.size(); i++) {
					if (srList.get(i).getQ_id() == rs.getInt("q_id")) {
						System.out.println("Answer - " + rs.getString("answer_value"));
						srList.get(i).getAnswerList().add(rs.getString("answer_value"));
					}
				}
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
		
		return srList;		
	}
}
