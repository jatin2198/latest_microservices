package com.quizservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizservice.entities.QuizEntity;
import com.quizservice.repo.QuizRepo;
@Service
public class QuizServiceImpl implements QuizServices {

	@Autowired
	QuizRepo quizRepo;
	
	@Override
	public QuizEntity addQuiz(QuizEntity entity) {
		// TODO Auto-generated method stub
		return quizRepo.save(entity);
	}

	@Override
	public List<QuizEntity> getAllQuiz() {
		// TODO Auto-generated method stub
		return quizRepo.findAll();
	}

	@Override
	public QuizEntity getQuizById(Long id) {
		// TODO Auto-generated method stub
		return quizRepo.findById(id).orElseThrow(()->new RuntimeException("Quiz Id doesn't exist"));
	}

}
