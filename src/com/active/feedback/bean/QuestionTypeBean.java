package com.active.feedback.bean;

import java.util.List;

import com.active.feedback.dao.QuestionTypeDao;
import com.active.feedback.entities.QuestionType;
import com.active.feedback.impl.QuestionTypeDaoImpl;

public class QuestionTypeBean {
	
	private List<QuestionType> qtList;
	
	private QuestionTypeDao qdDao = new QuestionTypeDaoImpl();

	public List<QuestionType> getQtList() {
		return qdDao.getAllQuestionType();
	}

	public void setQtList(List<QuestionType> qtList) {
		this.qtList = qtList;
	}
	
	
	
}
