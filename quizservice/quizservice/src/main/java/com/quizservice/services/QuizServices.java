package com.quizservice.services;

import java.util.List;

import com.quizservice.entities.QuizEntity;

public interface QuizServices {
	
	public QuizEntity addQuiz(QuizEntity entity);
	
	public List<QuizEntity> getAllQuiz();
	
	public QuizEntity getQuizById(Long id);

}
