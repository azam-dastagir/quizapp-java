package com.quizapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizapp.entity.Question;
import com.quizapp.services.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {
	@Autowired
	QuestionService questionService;
	
	@GetMapping("allquestions")
	public ResponseEntity<List<Question>> getAllQuestions() {
		List<Question> qlist= questionService.getAllQuestions();
		return new ResponseEntity<>(qlist,HttpStatus.OK);
	}
	
	@GetMapping("category/{category}")
	public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category) {
		try {
		return new ResponseEntity<>(questionService.getQuestionsByCategory(category),HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("addQuestion")
	public ResponseEntity<String> addQuestion(@RequestBody Question question) {
		String msg = questionService.addQuestion(question);
		return new ResponseEntity<>("success",HttpStatus.CREATED);
	}
	
	@DeleteMapping("deleteQuestion/{id}")
	public String deleteQuestion(@PathVariable int id) {
		String msg = questionService.deleteQuestion(id);
		return msg;
	}
	
	@PostMapping("updateQuestion")
	public String updateQuestion(@RequestBody Question question) {
		String msg = questionService.updateQuestion(question);
		return msg;
	}
}
