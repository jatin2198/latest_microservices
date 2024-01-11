package com.quizservice.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.quizservice.entities.QuizEntity;
import com.quizservice.services.QuestionClient;
import com.quizservice.services.QuizServiceImpl;
import com.quizservice.services.QuizServices;

@RestController
@RequestMapping("/quiz")
public class QuizContoller {
	@Autowired
	QuizServiceImpl  services;
	
	@Autowired
	QuestionClient questionClient;
	
	@PostMapping("/create")
	public ResponseEntity createQuiz(@RequestBody QuizEntity entity) {
		return ResponseEntity.status(HttpStatus.CREATED).body(services.addQuiz(entity));
		
	}
	
	@GetMapping("/all_quiz")
	public ResponseEntity getAllQuiz() {
	List<QuizEntity> list= services.getAllQuiz().stream().map(quiz->{
		
		quiz.setQuestions(questionClient.getQuestions(quiz.getId()));
		return quiz;
	}
			
			).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(list);
		
	}
	
	@GetMapping("/get_quiz/{id}")
	public ResponseEntity getByQuiz(@PathVariable Long id) {
		QuizEntity entity=services.getQuizById(id);
		entity.setQuestions(questionClient.getQuestions(entity.getId()));
		return ResponseEntity.status(HttpStatus.OK).body(entity);
		
	}


}
