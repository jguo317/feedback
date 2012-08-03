package com.active.feedback.action;

import java.util.Calendar;
import java.util.Map;

import com.active.feedback.dao.SurveyDao;
import com.active.feedback.dao.UserDao;
import com.active.feedback.entities.Survey;
import com.active.feedback.impl.SurveyDaoImpl;
import com.active.feedback.impl.UserDaoImpl;
import com.active.feedback.util.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SurveyEditAction  extends ActionSupport{

	private String surveyTitle;
	private int surveyId;
	private String action;

	public String getSurveyTitle() {
		return surveyTitle;
	}

	public void setSurveyTitle(String surveyTitle) {
		this.surveyTitle = surveyTitle;
	}

	private void edit() {
		
		Survey survey = null;
		SurveyDao sd = new SurveyDaoImpl();
		survey = sd.getSurveyById(surveyId);
		survey.setTitle(this.surveyTitle);
		survey.setId(surveyId);
		sd.update(survey);
		
		
	}
	
	private void select() {
		Map sessionMap = ActionContext.getContext().getSession();
		sessionMap.put("survey_id", surveyId);
		sessionMap.put("survey_title", surveyTitle);
	}
	
	public String execute() {
		Map session=ActionContext.getContext().getSession();
		if (!session.containsKey("user_id")) {
			return Constant.LOGIN;
		}
		if (action.trim().equals("select")) {
			select();
			return INPUT;
		} else {
			edit();
			return SUCCESS;
		}
	}

	public int getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}
