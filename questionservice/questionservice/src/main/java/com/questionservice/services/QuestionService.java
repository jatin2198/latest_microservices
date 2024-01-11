package com.questionservice.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.questionservice.entites.QusestionEntity;

public interface QuestionService {

	public QusestionEntity createQuestion(QusestionEntity entity);
	public List<QusestionEntity> getAllQuestions();
	public QusestionEntity getQuesById(Long id);
	public List<QusestionEntity> getbyQuesId(Long quizId);
}
