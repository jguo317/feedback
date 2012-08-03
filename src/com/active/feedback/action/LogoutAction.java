package com.active.feedback.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport {

	public String execute() {
		Map sessionMap = ActionContext.getContext().getSession();
		if (sessionMap.containsKey("user_id")) {			
			sessionMap.remove("user_id");
		}
		return SUCCESS;
	}
}
