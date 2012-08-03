package com.active.feedback.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.active.feedback.dao.QuestionDao;
import com.active.feedback.dao.QuestionDataDao;
import com.active.feedback.dao.QuestionTypeDao;
import com.active.feedback.dao.SurveyDao;
import com.active.feedback.entities.Question;
import com.active.feedback.entities.QuestionData;
import com.active.feedback.entities.Survey;
import com.active.feedback.impl.QuestionDaoImpl;
import com.active.feedback.impl.QuestionDataDaoImpl;
import com.active.feedback.impl.QuestionTypeDaoImpl;
import com.active.feedback.impl.SurveyDaoImpl;
import com.active.feedback.util.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
public class AnswerCreateAction extends ActionSupport {

	private int q_id;
	private int qt_id;
	private String answer;
	private List<QuestionData> answerList;
	private String answer_action;
	private List<Question> questions;
	private String surveyTitle;
	
	public int getQ_id() {
		return q_id;
	}
	public void setQ_id(int q_id) {
		this.q_id = q_id;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public List<QuestionData> getAnswerList() {
		return answerList;
	}
	public void setAnswerList(List<QuestionData> answerList) {
		this.answerList = answerList;
	}
	public String getAnswer_action() {
		return answer_action;
	}
	public void setAnswer_action(String answer_action) {
		this.answer_action = answer_action;
	}
	
	private void save() {
		QuestionDao qDao = new QuestionDaoImpl();
		QuestionData qd = new QuestionData();
		QuestionDataDao qdDao = new QuestionDataDaoImpl();
		if (!answer.trim().equals("")) {
			qd.setQ_id(q_id);
			qd.setValue(answer);			
			qdDao.save(qd);
		}		
		setAnswerList(qdDao.getAllQuestionDataByQId(q_id));
		

	}
	
	private void delete() {
		QuestionDataDao qdDao = new QuestionDataDaoImpl();
		
		QuestionData qd = qdDao.getQuestionDataById(this.qt_id);
		qd.setValue(this.answer);
		qdDao.delete(qd);

		setAnswerList(qdDao.getAllQuestionDataByQId(q_id));
	}
	
	private void update() {
		QuestionDataDao qdDao = new QuestionDataDaoImpl();
		QuestionData qd = qdDao.getQuestionDataById(this.qt_id);

		qd.setValue(this.answer);
		qdDao.update(qd);
		setAnswerList(qdDao.getAllQuestionDataByQId(q_id));
	}
	
	private void initQuestions() {
		SurveyDao sDao = new SurveyDaoImpl();
		Map sessionMap = ActionContext.getContext().getSession();
		int survey_id = (Integer)sessionMap.get("survey_id");			
		
		this.setQuestions(sDao.getQuestionsBySurveyId(survey_id));
	}
	
	public String execute() {
		Map sessionMap = ActionContext.getContext().getSession();
		if (!sessionMap.containsKey("user_id")) {
			return Constant.LOGIN;
		}
		this.setSurveyTitle(sessionMap.get("survey_title").toString());
		System.out.println(sessionMap.get("survey_title").toString());
		String action = this.answer_action.trim();
		if (action.equalsIgnoreCase("delete_answer")) {
			delete();
		} else if (action.equalsIgnoreCase("update_answer")) {
			update();
		} else if (action.equalsIgnoreCase("add_answer")) {
			save();
		} else {
			initQuestions();
		}
		
		if (action.equalsIgnoreCase("ok")) {
			return SUCCESS;
		} else {
			return INPUT;
		}
	}

	public int getQt_id() {
		return qt_id;
	}
	public void setQt_id(String qt_id) {
		if (qt_id == null || qt_id.equals(""))
			this.qt_id = 0;
		else		
			this.qt_id = Integer.valueOf(qt_id);
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	public String getSurveyTitle() {
		return surveyTitle;
	}
	public void setSurveyTitle(String surveyTitle) {
		this.surveyTitle = surveyTitle;
	}

}
