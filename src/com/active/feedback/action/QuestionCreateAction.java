package com.active.feedback.action;

import java.util.Map;
import com.active.feedback.dao.QuestionDao;
import com.active.feedback.dao.QuestionTypeDao;
import com.active.feedback.dao.SurveyDao;
import com.active.feedback.dao.UserDao;
import com.active.feedback.entities.Question;
import com.active.feedback.entities.QuestionType;
import com.active.feedback.entities.Survey;
import com.active.feedback.entities.Survey2Question;
import com.active.feedback.impl.QuestionDaoImpl;
import com.active.feedback.impl.QuestionTypeDaoImpl;
import com.active.feedback.impl.SurveyDaoImpl;
import com.active.feedback.impl.UserDaoImpl;
import com.active.feedback.util.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class QuestionCreateAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String q_form_action;
	private int q_type;
	private String q_name;
	private int q_id;
	private String surveyTitle;

	public String getQ_form_action() {
		return q_form_action;
	}
	public void setQ_form_action(String q_form_action) {
		this.q_form_action = q_form_action;
	}
	public int getQ_type() {
		return q_type;
	}
	public void setQ_type(int q_type) {
		this.q_type = q_type;
	}
	public String getQ_name() {
		return q_name;
	}
	public void setQ_name(String q_name) {
		this.q_name = q_name;
	}
	
	private void save() {
		Question q = null;
		QuestionDao qd = new QuestionDaoImpl();
		System.out.println(this.q_form_action.trim());
		if (this.q_form_action.trim().equalsIgnoreCase("add_exist_q")) {
			QuestionDao qDao = new QuestionDaoImpl();
			q = qDao.getQuestionById(this.q_id);
			
		} else {
			q = new Question();
			QuestionTypeDao qtDao = new QuestionTypeDaoImpl();
			q.setName(this.q_name);
			QuestionType qt = new QuestionType();
			qt.setId(this.q_type);
			q.setQt(qt);
			
			qd.add(q);	
		}
		setQ_id(q.getId());
		Map sessionMap = ActionContext.getContext().getSession();
		Survey survey = null;
		SurveyDao sd = new SurveyDaoImpl();
		int survey_id = 0;
		if(!sessionMap.containsKey("survey_id") || (Integer)sessionMap.get("survey_id") == 0) {
			survey = new Survey();
			UserDao ud = new UserDaoImpl();
			Integer user_id = (Integer)sessionMap.get("user_id");
			if (user_id == null) {
				user_id = 1;
			}
			System.out.println(user_id);
		
			survey.setCreateBy(user_id);
			survey.setTitle("");
		
			sd.add(survey);
			survey_id = survey.getId();
			sessionMap.put("survey_id", survey.getId());
		} else {
			
			survey_id = (Integer)sessionMap.get("survey_id");		
		}

		Survey2Question s2q = new Survey2Question();
		s2q.setQ_id(this.q_id);
		s2q.setSurvey_id(survey_id);
		s2q.setOrder_id(0);
		sd.addSurvey2Question(s2q);
		survey = sd.getSurveyById(survey_id);
		this.setSurveyTitle(survey.getTitle());
	}
	
	private void delete() {
		Map sessionMap = ActionContext.getContext().getSession();
		int survey_id = (Integer)sessionMap.get("survey_id");
		SurveyDao sd = new SurveyDaoImpl();
		sd.deleteQuestionBySurveyId(survey_id, q_id);
	}
	
	private void edit() {
		QuestionDao qd = new QuestionDaoImpl();
		Question q = qd.getQuestionById(q_id);
		this.setQ_name(q.getName());
		this.setQ_type(q.getQt().getId());
	}
	
	private void editQuestion() {
		QuestionDao qd = new QuestionDaoImpl();
		Question q = qd.getQuestionById(q_id);
		QuestionTypeDao qtDao = new QuestionTypeDaoImpl();
		QuestionType qt = qtDao.getQuestionTypeById(q_type);
		q.setName(this.q_name);
		q.setQt(qt);
		qd.Update(q);
	}
	
	public String execute() {
		Map session=ActionContext.getContext().getSession();
		if (!session.containsKey("user_id")) {
			return Constant.LOGIN;
		}		
		if (this.q_form_action.trim().equalsIgnoreCase("next")) {
			save();
			return INPUT;
		} else if (this.q_form_action.trim().equalsIgnoreCase("edit_next")) {
			editQuestion();
			return "edit_answer";			
		} else if (this.q_form_action.trim().equalsIgnoreCase("edit_save")){
			editQuestion();
			return SUCCESS;
		} else if (this.q_form_action.trim().equalsIgnoreCase("add_exist_q")) {
			save();
			return SUCCESS;
		} else if (this.q_form_action.trim().equalsIgnoreCase("delete_exist_q")){
			delete();
			return SUCCESS;
		} else if (this.q_form_action.trim().equalsIgnoreCase("edit_exist_q")){
			edit();
			return "edit";
		} else {
			save();
			return SUCCESS;
		}
	}
	public int getQ_id() {
		return q_id;
	}
	public void setQ_id(int q_id) {
		this.q_id = q_id;
	}
	public String getSurveyTitle() {
		return surveyTitle;
	}
	public void setSurveyTitle(String surveyTitle) {
		this.surveyTitle = surveyTitle;
	}
}
