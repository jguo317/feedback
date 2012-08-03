package com.active.feedback.bean;

import java.util.List;
import java.util.Map;

import com.active.feedback.dao.QuestionDao;
import com.active.feedback.dao.QuestionTypeDao;
import com.active.feedback.dao.SurveyDao;
import com.active.feedback.entities.Question;
import com.active.feedback.entities.QuestionType;
import com.active.feedback.impl.QuestionDaoImpl;
import com.active.feedback.impl.QuestionTypeDaoImpl;
import com.active.feedback.impl.SurveyDaoImpl;
import com.opensymphony.xwork2.ActionContext;

public class QuestionBean {
	
	private List<Question> qList;	
	private List<Question> qCList;	
	
	private QuestionDao qDao = new QuestionDaoImpl();
	
	private SurveyDao sDao = new SurveyDaoImpl();

	public List<Question> getQList() {
		Map sessionMap = ActionContext.getContext().getSession();
		int user_id = 1;
		if (sessionMap.containsKey("user_id")) {			
			user_id = (Integer)sessionMap.get("user_id");
		}
		return qDao.getAllQuestionByUserId(user_id);
	}

	public void setQList(List<Question> qtList) {
		this.qList = qtList;
	}	

	public List<Question> getQCList() {		
		Map sessionMap = ActionContext.getContext().getSession();
		int survey_id = 0;
		if (sessionMap.containsKey("survey_id")) {			
			survey_id = (Integer)sessionMap.get("survey_id");
		} 
		qCList = sDao.getQuestionsBySurveyId(survey_id);

		return qCList;
	}

	public void setQCList(List<Question> list) {
		qCList = list;
	}
}
