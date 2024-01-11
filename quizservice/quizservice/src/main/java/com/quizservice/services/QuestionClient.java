package com.quizservice.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.quizservice.models.QuestionData;

//@FeignClient(url = "http://localhost:8081/questions",value = "Question-Client")
@FeignClient(name="QUESTIONS-SERVICE")
public interface QuestionClient {
	
	@GetMapping("/questions/getByQuizId/{id}")
	List<QuestionData> getQuestions(@PathVariable Long id);

}
