package com.active.feedback.action;

import java.util.List;
import java.util.Map;

import com.active.feedback.dao.TeamDao;
import com.active.feedback.dao.UserDao;
import com.active.feedback.impl.TeamDaoImpl;
import com.active.feedback.impl.UserDaoImpl;
import com.active.feedback.util.Constant;
import com.active.feedback.entities.Team;
import com.active.feedback.entities.Team2Member;
import com.active.feedback.entities.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MemberInfoCEAction extends ActionSupport {
	
	private String fname;
	private String mname;
	private String lname;
	private String email;
	private String action;
	private int teamId;
	
	private List<User> mList;
	private String teamName;
	
	
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	private void createMember() {
		User member = new User();
		member.setEmail(email);
		member.setFname(fname);
		member.setMname(mname);
		member.setLname(lname);
		UserDao uDao = new UserDaoImpl();
		uDao.add(member);
		Team t = new Team();
		t.setId(teamId);
		Team2Member t2m = new Team2Member();
		t2m.setTeam(t);
		t2m.setUser(member);
		TeamDao tDao = new TeamDaoImpl();
		tDao.addTeam2Member(t2m);
	}
	
	public String execute() {
		Map session=ActionContext.getContext().getSession();
		if (!session.containsKey("user_id")) {
			return Constant.LOGIN;
		}
		if (action.trim().equals("add")) {
			createMember();
		} else {
			
		}
		return this.SUCCESS;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public List<User> getMList() {
		TeamDao tDao = new TeamDaoImpl();
		mList = tDao.getMembersByTeamId(teamId);
		return mList;
	}
	public void setMList(List<User> list) {
		mList = list;
	}
	public String getTeamName() {
		TeamDao tDao = new TeamDaoImpl();
		Team t = tDao.getTeamById(teamId);
		return t.getName();
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
}
