package com.active.feedback.action;

import java.util.List;
import java.util.Map;

import com.active.feedback.dao.SurveyDao;
import com.active.feedback.entities.SurveyReport;
import com.active.feedback.impl.SurveyDaoImpl;
import com.active.feedback.util.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SurveyReportAction extends ActionSupport {
	
	private int selected_survey_id;
	private int team_id;
	private int member_id;
	private List<SurveyReport> srList;

	public String execute() {
		Map session=ActionContext.getContext().getSession();
		if (!session.containsKey("user_id")) {
			return Constant.LOGIN;
		}
		SurveyDao sDao = new SurveyDaoImpl();
		srList = sDao.getSurveyReportByIds(selected_survey_id, team_id, member_id);
		System.out.println("member_id = " + member_id);
		for (int i=0; i<srList.size(); i++) {
			
				System.out.println("Answer ================= " + srList.get(i).getAnswerList().toString());
		}
		return SUCCESS;
	}

	public List<SurveyReport> getSrList() {
		return srList;
	}

	public void setSrList(List<SurveyReport> srList) {
		this.srList = srList;
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

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
}
