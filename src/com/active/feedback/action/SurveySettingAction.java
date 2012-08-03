package com.active.feedback.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.active.feedback.dao.SurveyDao;
import com.active.feedback.dao.TeamDao;
import com.active.feedback.dao.UserDao;
import com.active.feedback.entities.Question;
import com.active.feedback.entities.Survey;
import com.active.feedback.entities.Survey2Team;
import com.active.feedback.entities.Team;
import com.active.feedback.entities.User;
import com.active.feedback.impl.SurveyDaoImpl;
import com.active.feedback.impl.TeamDaoImpl;
import com.active.feedback.impl.UserDaoImpl;
import com.active.feedback.util.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SurveySettingAction extends ActionSupport {
	
	private Date survey_start_date;
	private Date survey_end_date;
	private String selected_teams;
	private int selected_survey_id;
	private int memberSurvey;
	private String action;
	private String surveyTitle;
	private List<Question> qList;
	private List<Team> tList;
	
	
	public Date getSurvey_start_date() {
		return survey_start_date;
	}
	public void setSurvey_start_date(Date date) {
		this.survey_start_date = date;
	}
	public Date getSurvey_end_date() {
		return survey_end_date;
	}
	public void setSurvey_end_date(Date survey_end_date) {
		this.survey_end_date = survey_end_date;
	}
	
	public String execute() {
		Map session=ActionContext.getContext().getSession();
		if (!session.containsKey("user_id")) {
			return Constant.LOGIN;
		}
		if (action.trim().equals("select")) {
			selectSurvey();
			return this.INPUT;
		} else {
			saveOrUpdateSurvey();
			return SUCCESS;
		}
	}
	
	private void saveSurvey2Team() {
		String[] teams = selected_teams.split(";");
		TeamDao tDao = new TeamDaoImpl();
		for(int i=0; i<teams.length;i++) {
			int team_id = Integer.parseInt(teams[i].trim());
			if (team_id > 0) {
				Team t = tDao.getTeamById(team_id);
			}
		}
		
	}
	
	private void saveOrUpdateSurvey() {
		Map sessionMap = ActionContext.getContext().getSession();
		Survey survey = null;
		SurveyDao sDao = new SurveyDaoImpl();
		int survey_id = 0;
		if (sessionMap.containsKey("survey_id")) {			
			survey_id = (Integer)sessionMap.get("survey_id");
		} 
		int user_id = 1;
		if (sessionMap.containsKey("user_id")) {			
			user_id = (Integer)sessionMap.get("user_id");
		} 
		
		if (survey_id == 0) {

			survey = new Survey();
			survey.setCreateBy(user_id);
			survey.setEndDate(this.survey_end_date);
			survey.setStartDate(this.survey_start_date);
			sDao.add(survey);
			sessionMap.put("survey_id", survey.getId());
		} else {
			survey = sDao.getSurveyById(survey_id);
			survey.setCreateBy(user_id);
			survey.setEndDate(this.survey_end_date);
			survey.setStartDate(this.survey_start_date);
			survey.setMemberSurvey(memberSurvey);			
			sDao.update(survey);
		}
		
		String[] teams = selected_teams.split(";");
		sDao.deleteTeamsBySurveyId(survey_id);
		for(int i=0; i<teams.length;i++) {
			int team_id = Integer.parseInt(teams[i].trim());
			if (team_id > 0) {
				Survey2Team s2t = new Survey2Team();
				s2t.setSurvey_id(survey.getId());
				s2t.setTeam_id(team_id);
				sDao.addSurvey2Team(s2t);
			}
		}
		
	}
	
	private void selectSurvey() {
		SurveyDao sDao = new SurveyDaoImpl();
		Survey survey = sDao.getSurveyById(selected_survey_id);
		Map sessionMap = ActionContext.getContext().getSession();
		sessionMap.put("survey_id", survey.getId());
		this.setSurvey_start_date(survey.getStartDate());
		this.setSurvey_end_date(survey.getEndDate());
		this.setSurveyTitle(survey.getTitle());
		this.setQList(sDao.getQuestionsBySurveyId(selected_survey_id));
		this.setMemberSurvey(survey.getMemberSurvey());
		System.out.println("this.memberSurvey========" + this.memberSurvey);
		
		TeamDao tDao = new TeamDaoImpl();
		int user_id = 1;
		
		if (sessionMap.containsKey("user_id")) {			
			user_id = (Integer)sessionMap.get("user_id");
		} 
		List<Team> tList = tDao.getAllTeamByUserId(user_id);
		
		List<Team> selectedTeamList = sDao.getTeamsBySurveyId(selected_survey_id);
		List<Integer> selectedList = new ArrayList<Integer>();
		for (Team t: selectedTeamList) {
			selectedList.add(t.getId());
		}		
		for(Team t : tList) {
			if (selectedList.contains(t.getId())) {
				t.setSelected(true);
			} else {
				t.setSelected(false);
			}
		}
		this.setTList(tList);
	}
	
	public String getSelected_teams() {
		return selected_teams;
	}
	public void setSelected_teams(String selected_teams) {
		this.selected_teams = selected_teams;
	}
	public int getSelected_survey_id() {
		return selected_survey_id;
	}
	public void setSelected_survey_id(int selected_survey_id) {
		this.selected_survey_id = selected_survey_id;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getSurveyTitle() {
		return surveyTitle;
	}
	public void setSurveyTitle(String surveyTitle) {
		this.surveyTitle = surveyTitle;
	}
	public List<Question> getQList() {
		return qList;
	}
	public void setQList(List<Question> list) {
		qList = list;
	}
	public List<Team> getTList() {
		return tList;
	}
	public void setTList(List<Team> list) {
		tList = list;
	}
	public int getMemberSurvey() {
		return memberSurvey;
	}
	public void setMemberSurvey(int memberSurvey) {
		this.memberSurvey = memberSurvey;
	}
	
	

}
