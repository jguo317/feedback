package com.active.feedback.dao;

import java.util.List;

import com.active.feedback.bean.ResultBean;
import com.active.feedback.entities.Answer;

public interface AnswerDao {

	
	public List<Answer> getAnswersBySIdUId(int surveyId, int userId);
	public void addAnswers(int suerveyId, int userId, List<ResultBean> rList);
}
