package com.active.feedback.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.active.feedback.dao.QuestionDao;
import com.active.feedback.dao.SurveyDao;
import com.active.feedback.dao.TeamDao;
import com.active.feedback.entities.Question;
import com.active.feedback.entities.Team;
import com.active.feedback.entities.User;
import com.active.feedback.impl.QuestionDaoImpl;
import com.active.feedback.impl.SurveyDaoImpl;
import com.active.feedback.impl.TeamDaoImpl;
import com.active.feedback.util.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class TeamSelectedAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private int team_id;
	private List<Team> tList;	
	private List<Question> qList;
	private List<User> uList;
	private int survey_id;
	private int memberSurvey;
	
	private QuestionDao qDao = new QuestionDaoImpl();

	private SurveyDao sDao = new SurveyDaoImpl();

	@Override
	public String execute() throws Exception {

		SurveyDao sDao = new SurveyDaoImpl();
		tList = sDao.getTeamsBySurveyId(this.survey_id);
		memberSurvey = sDao.getSurveyById(survey_id).getMemberSurvey();
		System.out.println("memberSurvey==========================" + memberSurvey);
		if (team_id == 0 && tList.size() > 0) {
			team_id = tList.get(0).getId();
		}
		TeamDao tDao = new TeamDaoImpl();
		uList = tDao.getMembersByTeamId(team_id);
		qList = sDao.getQuestionsBySurveyId(survey_id);

		return SUCCESS;

	}

	public void generateQuestionList(int survey_id){
		
		List<Question> questionList = sDao.getQuestionsBySurveyId(survey_id);
		qList = new ArrayList<Question>();
		int count = questionList.size();

		for (int i = 0; i < count; i++) {
			
			Question q = questionList.get(i);
			int q_id = q.getId();
			q = qDao.getQuestionById(q_id);
			qList.add(q);
		}
	}

	public int getTeam_id() {
		return team_id;
	}

	public void setTeam_id(int team_id) {
		this.team_id = team_id;
	}

	public List<Team> getTList() {
		return tList;
	}

	public void setTList(List<Team> list) {
		tList = list;
	}

	public List<Question> getQList() {
		return qList;
	}

	public void setQList(List<Question> list) {
		qList = list;
	}

	public List<User> getUList() {
		return uList;
	}

	public void setUList(List<User> list) {
		uList = list;
	}

	public int getSurvey_id() {
		return survey_id;
	}

	public void setSurvey_id(int survey_id) {
		this.survey_id = survey_id;
	}

	public int getMemberSurvey() {
		return memberSurvey;
	}

	public void setMemberSurvey(int memberSurvey) {
		this.memberSurvey = memberSurvey;
	}
}
