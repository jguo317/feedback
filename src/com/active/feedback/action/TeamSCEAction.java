package com.active.feedback.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.active.feedback.dao.TeamDao;
import com.active.feedback.entities.Team;
import com.active.feedback.entities.Team2Admin;
import com.active.feedback.entities.User;
import com.active.feedback.impl.TeamDaoImpl;
import com.active.feedback.util.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class TeamSCEAction extends ActionSupport {
	
	private String teamName;
	private int teamId;
	private String action;
	private List<User> mList;
	
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	private void createTeam() {
		TeamDao tDao = new TeamDaoImpl();
		Team team = new Team();
		team.setName(teamName);
		tDao.add(team);
		this.teamId = team.getId();
		mList = new ArrayList<User>();
		Map sessionMap = ActionContext.getContext().getSession();
		int user_id = 1;
		if (sessionMap.containsKey("user_id")) {			
			user_id = (Integer)sessionMap.get("user_id");
		}
		User user = new User();
		user.setId(user_id);
		Team2Admin t2a = new Team2Admin();
		t2a.setTeam(team);
		t2a.setUser(user);
		tDao.addTeam2Admin(t2a);
	}
	
	private void editTeam() {
		TeamDao tDao = new TeamDaoImpl();
		Team team = new Team();
		team.setName(teamName);
		team.setId(teamId);
		tDao.update(team);
		mList = tDao.getMembersByTeamId(teamId);
	}
	
	public String execute() {
		Map session=ActionContext.getContext().getSession();
		if (!session.containsKey("user_id")) {
			return Constant.LOGIN;
		}
		if (action.trim().equals("select")) {
			return this.INPUT;
		} else if (action.trim().equals("edit")){
			editTeam();
			return this.SUCCESS;
		} else {
			createTeam();
			return this.SUCCESS;
		}
	}
	public List<User> getMList() {
		return mList;
	}
	public void setMList(List<User> list) {
		mList = list;
	}
}
