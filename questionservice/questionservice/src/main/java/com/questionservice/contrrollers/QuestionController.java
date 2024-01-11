package com.questionservice.contrrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.questionservice.entites.QusestionEntity;
import com.questionservice.services.QuestionServiceImpl;

@RestController
@RequestMapping("/questions")
public class QuestionController {
	
	@Autowired
	QuestionServiceImpl serviceImpl;
	
	@PostMapping("/create")
	public ResponseEntity createQuestion(@RequestBody QusestionEntity entity) {
		return ResponseEntity.status(HttpStatus.CREATED).body(serviceImpl.createQuestion(entity));
		
	}
	
	@GetMapping("/get_all")
	public ResponseEntity getAllQues() {
		return ResponseEntity.status(HttpStatus.OK).body(serviceImpl.getAllQuestions());
		
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity getById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(serviceImpl.getQuesById(id));
		
	}

	@GetMapping("/getByQuizId/{id}")
	public ResponseEntity getByQuizId(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(serviceImpl.getbyQuesId(id));
		
	}
}
