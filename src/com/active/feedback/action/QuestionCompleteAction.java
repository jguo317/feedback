package com.active.feedback.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.active.feedback.bean.ResultBean;
import com.active.feedback.dao.AnswerDao;
import com.active.feedback.dao.QuestionDao;
import com.active.feedback.dao.QuestionDataDao;
import com.active.feedback.dao.QuestionTypeDao;
import com.active.feedback.dao.SurveyDao;
import com.active.feedback.entities.Question;
import com.active.feedback.entities.QuestionData;
import com.active.feedback.impl.AnswerDaoImpl;
import com.active.feedback.impl.QuestionDaoImpl;
import com.active.feedback.impl.QuestionDataDaoImpl;
import com.active.feedback.impl.QuestionTypeDaoImpl;
import com.active.feedback.impl.SurveyDaoImpl;
import com.active.feedback.util.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class QuestionCompleteAction extends ActionSupport {

	private int survey_id;
	private int team_id;
	private int member_id;

	public String execute() {
		handleRequest();
		return SUCCESS;
	}
	
	private void handleRequest() {
		HttpServletRequest request;	
		request = ServletActionContext.getRequest();
		List<Question> qList = generateQuestionList(survey_id);
		List<ResultBean> rList = new ArrayList<ResultBean>();
		QuestionDataDao qdDao = new QuestionDataDaoImpl();
		for (int i=0; i<qList.size();i++) {
			Question q = qList.get(i);
			String q_type = q.getQt().getName();
			List<Integer> qdList = new ArrayList<Integer>();
			String key;
			String value = null;
			if (q_type.equalsIgnoreCase("checkbox")) {
				List<QuestionData> qdSet = qdDao.getAllQuestionDataByQId(q.getId());
				Iterator qdIt = qdSet.iterator();
				
				while (qdIt.hasNext()) {
					QuestionData qd = (QuestionData) qdIt.next();
					key = "checkbox_" + qd.getId();
					value = request.getParameter(key);
					System.out.println(key +"============"+ value);
					if (value != null) {
						qdList.add(Integer.parseInt(value));
					}				
				}
				value = null;
			} else {
				if (q_type.equalsIgnoreCase("radio")) {
					key = "radio_" + q.getId();
				} else {
					key = "text_" + q.getId();
				}
				//key = String.valueOf(q.getId());
				value = request.getParameter(key);
				System.out.println(key +"============"+ value);
				if (q_type.equalsIgnoreCase("radio") && value != null) {
					qdList.add(Integer.parseInt(value));
					value = null;
				}
			}
			rList.add(new ResultBean(q.getId(), qdList, q.getQt().getId(), value));
		}
		AnswerDao aDao = new AnswerDaoImpl();	
		aDao.addAnswers(survey_id, member_id, rList);
	}
	
	public List<Question> generateQuestionList(int survey_id){
		QuestionDao qDao = new QuestionDaoImpl();
		SurveyDao sDao = new SurveyDaoImpl();
		List<Question> questionList = sDao.getQuestionsBySurveyId(survey_id);
		System.out.println("survey_id"+survey_id);
		List<Question> qList = new ArrayList<Question>();
		int count = questionList.size();
		System.out.println("11111count="+count);
		for (int i = 0; i < count; i++) {
			
			Question q = questionList.get(i);
			int q_id = q.getId();
			q = qDao.getQuestionById(q_id);
			qList.add(q);
		}
		return qList;
	}

	public int getSurvey_id() {
		return survey_id;
	}

	public void setSurvey_id(int survey_id) {
		this.survey_id = survey_id;
	}

	public int getTeam_id() {
		return team_id;
	}

	public void setTeam_id(int team_id) {
		this.team_id = team_id;
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
}
