package com.active.feedback.dao;

import java.util.List;

import com.active.feedback.entities.QuestionData;

public interface QuestionDataDao {
	
	public List<QuestionData> getAllQuestionDataByQId(int q_id);
	
	public void save(QuestionData qd);
	
	public void update(QuestionData qd);
	
	public void delete(QuestionData qd);
	
	public QuestionData getQuestionDataById(int id);
}
