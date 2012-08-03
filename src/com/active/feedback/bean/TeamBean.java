package com.active.feedback.bean;

import java.util.List;
import java.util.Map;

import com.active.feedback.dao.TeamDao;
import com.active.feedback.entities.Team;
import com.active.feedback.impl.TeamDaoImpl;
import com.opensymphony.xwork2.ActionContext;
public class TeamBean {

	public List<Team> tList;
	
	private Map sessionMap = ActionContext.getContext().getSession();

	public List<Team> getTListByUserId() {
		
		int user_id = 1;
		
		if (sessionMap.containsKey("user_id")) {
			user_id = (Integer)sessionMap.get("user_id");						
		} 

		TeamDao tDao = new TeamDaoImpl();
		tList = tDao.getAllTeamByUserId(user_id);
		return tList;
	}

	public void setTList(List<Team> list) {
		tList = list;
	}

	public List<Team> getTList() {
		return getTListByUserId();
	}
	
	
	
	
}
