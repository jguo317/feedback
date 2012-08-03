package com.active.feedback.action;

import java.util.Map;

import com.active.feedback.dao.SurveyDao;
import com.active.feedback.email.EmailHandle;
import com.active.feedback.impl.SurveyDaoImpl;
import com.active.feedback.util.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SendEmailAction extends ActionSupport {

	private String survey_url;
	private int survey_id;

	public String getSurvey_url() {
		return survey_url;
	}

	public void setSurvey_url(String survey_url) {
		this.survey_url = survey_url;
	}
	
	public int getSurvey_id() {
		return survey_id;
	}

	public void setSurvey_id(int survey_id) {
		this.survey_id = survey_id;
	}
	
	public String execute() {
		Map session=ActionContext.getContext().getSession();
		if (!session.containsKey("user_id")) {
			return Constant.LOGIN;
		}
		String emailContent = "Thank you for your time to take this survey. Please click <a href='"+ Constant.LOCAL_HOST + survey_url +"'>here</a> to start.";
		SurveyDao sDao = new SurveyDaoImpl();
		Map<String, String> emailMap = sDao.getSurveyInfoForEmailById(survey_id);
		String emailSubject = emailMap.get("survey_title");
		String emailFrom = emailMap.get("email_from");
		String emailTo = emailMap.get("email_to") + emailFrom;
		EmailHandle eHandle = new EmailHandle();
		eHandle.sendEmail(emailSubject, emailContent, emailFrom, emailTo);
		
		return SUCCESS;
	}
}
