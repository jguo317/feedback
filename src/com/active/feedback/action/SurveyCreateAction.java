package com.active.feedback.action;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.active.feedback.dao.SurveyDao;
import com.active.feedback.dao.UserDao;
import com.active.feedback.entities.Survey;
import com.active.feedback.entities.User;
import com.active.feedback.impl.SurveyDaoImpl;
import com.active.feedback.impl.UserDaoImpl;
import com.active.feedback.util.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SurveyCreateAction extends ActionSupport{
	
	private String surveyTitle;

	public String getSurveyTitle() {
		return surveyTitle;
	}

	public void setSurveyTitle(String surveyTitle) {
		this.surveyTitle = surveyTitle;
	}

	private void create() {
		Map sessionMap = ActionContext.getContext().getSession();
		Survey survey = null;
		SurveyDao sd = new SurveyDaoImpl();
		
		survey = new Survey();
		UserDao ud = new UserDaoImpl();
		Integer userId = (Integer)sessionMap.get("user_id");
		if (userId == null) {
			userId = 1;
		}
		survey.setCreateBy(userId);
		survey.setTitle(this.surveyTitle);
		survey.setEndDate(Calendar.getInstance().getTime());
		survey.setStartDate(Calendar.getInstance().getTime());
		
		sd.add(survey);
		
		sessionMap.put("survey_id", survey.getId());
		sessionMap.put("survey_title", survey.getTitle());
	}
	
	public String execute() {
		Map session=ActionContext.getContext().getSession();
		if (!session.containsKey("user_id")) {
			return Constant.LOGIN;
		}
		create();
		return SUCCESS;
	}

}
