package com.active.feedback.bean;

import java.util.List;
import java.util.Map;

import com.active.feedback.dao.SurveyDao;
import com.active.feedback.entities.Survey;
import com.active.feedback.impl.SurveyDaoImpl;
import com.opensymphony.xwork2.ActionContext;

public class SurveyBean {

	/*private Date survey_start_date;
	private Date survey_end_date;*/
	private SurveyDao sDao = new SurveyDaoImpl();
	
	private Map sessionMap = ActionContext.getContext().getSession();
	
	private Survey survey;
	
	private List<Survey> surveyList;

	public Survey getSurvey() {		
		Map sessionMap = ActionContext.getContext().getSession();
		if (sessionMap.containsKey("survey_id")) {
			int survey_id = (Integer)sessionMap.get("survey_id");	
			survey = sDao.getSurveyById(survey_id);			
		} else {
			survey = new Survey();
		}	
		System.out.println("1111111111111111222-------"+survey.getEndDate());
		return survey;
	}
	
	public List<Survey> getAllSurvey() {
		int user_id = 1;
		
		if (sessionMap.containsKey("user_id")) {
			user_id = (Integer)sessionMap.get("user_id");						
		} 

		surveyList = sDao.getAllSurveysByUserId(user_id);

		return surveyList;
		
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}
	
	public void setSurveyList(List<Survey> surveyList) {
		this.surveyList = surveyList;
	}

	public List<Survey> getSurveyList() {
		return getAllSurvey();
	}

}
