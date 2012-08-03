package com.active.feedback.action;

import java.util.Map;

import com.active.feedback.entities.User;
import com.active.feedback.impl.UserDaoImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private Map<String, Object> session;

	public LoginAction() {
	}

	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String execute() {
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		session = ActionContext.getContext().getSession();
		User user = userDaoImpl.getUserByUserName(username, password);
		if (user.getId() > 0) {
			session.put("user_id", user.getId());
			session.put("user_name", user.getFname() + ' ' + user.getLname());
			return SUCCESS;
		} else {
			return INPUT;
		}
	}

	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
}
