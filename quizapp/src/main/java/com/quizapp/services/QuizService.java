package com.quizapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.quizapp.controller.Response;
import com.quizapp.entity.QuestionWrapper;
import com.quizapp.entity.Quiz;

public interface QuizService {

	ResponseEntity<String> createQuiz(String category, int numOfQ, String title);

	ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id);

	ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses);
	
}
