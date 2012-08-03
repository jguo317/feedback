package com.active.feedback.action;

import java.util.List;

import com.active.feedback.dao.SurveyDao;
import com.active.feedback.dao.TeamDao;
import com.active.feedback.entities.Team;
import com.active.feedback.entities.User;
import com.active.feedback.impl.SurveyDaoImpl;
import com.active.feedback.impl.TeamDaoImpl;
import com.opensymphony.xwork2.ActionSupport;

public class SurveyReportTeamAction extends ActionSupport {

	private int selected_survey_id;
	private int team_id;
	private List<Team> tList;	
	private List<User> uList;
	private int memberSurvey;
	
	public String execute() throws Exception {

		SurveyDao sDao = new SurveyDaoImpl();
		tList = sDao.getTeamsBySurveyId(this.selected_survey_id);
		memberSurvey = sDao.getSurveyById(selected_survey_id).getMemberSurvey();
		System.out.println("memberSurvey==========================" + memberSurvey);
		TeamDao tDao = new TeamDaoImpl();
		uList = tDao.getMembersByTeamId(team_id);

		return SUCCESS;
	}

	public int getSelected_survey_id() {
		return selected_survey_id;
	}

	public void setSelected_survey_id(int selected_survey_id) {
		this.selected_survey_id = selected_survey_id;
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

	public List<User> getUList() {
		return uList;
	}

	public void setUList(List<User> list) {
		uList = list;
	}

	public int getMemberSurvey() {
		return memberSurvey;
	}

	public void setMemberSurvey(int memberSurvey) {
		this.memberSurvey = memberSurvey;
	}

}
