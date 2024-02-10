package com.quizapp.services;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.quizapp.controller.Response;
import com.quizapp.entity.Question;
import com.quizapp.entity.QuestionWrapper;
import com.quizapp.entity.Quiz;
import com.quizapp.repository.QuestionRepository;
import com.quizapp.repository.QuizRepository;

@Service
public class QuizServiceImplementation implements QuizService {
	@Autowired
	QuizRepository quizRepository;
	
	@Autowired 
	QuestionRepository questionRepository;

	public ResponseEntity<String> createQuiz(String category, int numOfQ, String title) {
		
		List<Question> questions = questionRepository.findRandomQuestionsByCategory(category, numOfQ);
		
		Quiz quiz = new Quiz();
		quiz.setName(title);
		
		quiz.setQuestions(questions);
		quizRepository.save(quiz);
		return new ResponseEntity<>("success",HttpStatus.CREATED);
	}
	
	
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id){
		Optional<Quiz> quiz = quizRepository.findById(id);
		System.out.println(quizRepository.findById(id));
		List<Question> questionsFromDb = quiz.get().getQuestions();
		List<QuestionWrapper> questionsForUser = new ArrayList<>();
		
		for (Question q : questionsFromDb) {
			QuestionWrapper qw = new QuestionWrapper(q.getId(), 
					q.getQuestionTitle(), q.getOption1(), q.getOption2(), 
					q.getOption3(), q.getOption4());
			questionsForUser.add(qw);
		}
		
		return new ResponseEntity<>(questionsForUser,HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		Quiz quiz = quizRepository.findById(id).get();
		List<Question> questions = quiz.getQuestions();
		int right = 0;
		int i = 0;
		for (Response response: responses) {
			if(response.getResponse().equals(questions.get(i).getRightAnswer())) {
				right++;
			}
			i++;
		}
		return new ResponseEntity<>(right, HttpStatus.OK);
	} 
}
