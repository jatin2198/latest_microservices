package com.questionservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.questionservice.entites.QusestionEntity;
import com.questionservice.repos.QuestionRepo;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	QuestionRepo questionRepo;
	@Override
	public QusestionEntity createQuestion(QusestionEntity entity) {
		// TODO Auto-generated method stub
		return questionRepo.save(entity);
	}

	@Override
	public List<QusestionEntity> getAllQuestions() {
		// TODO Auto-generated method stub
		return questionRepo.findAll();
	}

	@Override
	public QusestionEntity getQuesById(Long id) {
		// TODO Auto-generated method stub
		return questionRepo.findById(id).orElseThrow(()->new RuntimeException("Question Id doesn't exist"));
	}

	@Override
	public List<QusestionEntity> getbyQuesId(Long quizId) {
		// TODO Auto-generated method stub
		return questionRepo.findByQuizId(quizId);
	}

}
