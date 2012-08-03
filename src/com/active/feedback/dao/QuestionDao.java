package com.active.feedback.dao;

import java.util.List;

import com.active.feedback.entities.Question;

public interface QuestionDao {
	
	public void add(Question question);
	
	public void Update(Question question);
	
	public Question getQuestionById(int id);
	
	public List<Question> getAllQuestion();
	
	public List<Question> getAllQuestionByUserId(int userId);

}
