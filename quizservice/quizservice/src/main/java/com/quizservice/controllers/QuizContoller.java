package com.quizservice.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.quizservice.models.QuestionData;
import com.quizservice.services.QuestionClient;
import com.quizservice.services.QuizServiceImpl;
import com.quizservice.services.QuizServices;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/quiz")
public class QuizContoller {
	
	Logger logger=LoggerFactory.getLogger(QuizContoller.class);
	@Autowired
	QuizServiceImpl  services;
	
	@Autowired
	QuestionClient questionClient;
	
	@PostMapping("/create")
	public ResponseEntity createQuiz(@RequestBody QuizEntity entity) {
		return ResponseEntity.status(HttpStatus.CREATED).body(services.addQuiz(entity));
		
	}
	
	@GetMapping("/all_quiz")
	//@CircuitBreaker(name = "getAllQuestions", fallbackMethod = "getAllQuestion")
	@Retry(name = "getAllQuestions", fallbackMethod = "getAllQuestion" )
	public ResponseEntity getAllQuiz() {
	List<QuizEntity> list= services.getAllQuiz().stream().map(quiz->{
		
		quiz.setQuestions(questionClient.getQuestions(quiz.getId()));
		return quiz;
	}
			
			).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(list);
		
	}
	
	
	@GetMapping("/get_quiz/{id}")
	//@CircuitBreaker(name = "quizQuestionService", fallbackMethod = "getAllQuizFallback")
	//@Retry(name = "quizQuestionService", fallbackMethod = "getAllQuizFallback")
	@RateLimiter(name = "getAllQuestionsLimiter",fallbackMethod = "getAllQuizFallback")
	public ResponseEntity getByQuiz(@PathVariable Long id) {
		QuizEntity entity=services.getQuizById(id);
		entity.setQuestions(questionClient.getQuestions(entity.getId()));
		return ResponseEntity.status(HttpStatus.OK).body(entity);
		
	}
	
	int retry=1;
	
	public ResponseEntity getAllQuizFallback(Long id, Exception exception) {
		logger.info("Fallback "+exception.getMessage());
	logger.info("No of retries= "+retry);
	retry++;
		QuizEntity entity=new QuizEntity();
		entity.setTitle("fallback response");
	    entity.setId(0000L);
	   
		 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(entity);
		
	}
	
	public ResponseEntity getAllQuestion(Exception exception) {
		logger.info("Fallback "+exception.getMessage());
	
		QuizEntity entity=new QuizEntity();
		entity.setTitle("fallback response");
	    entity.setId(0000L);
	   
		 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(entity);
		
	}

}
