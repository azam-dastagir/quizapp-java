package com.quizapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizapp.entity.Question;
import com.quizapp.repository.QuestionRepository;

@Service
public class QuestionServiceImplementation implements QuestionService{
	@Autowired
	QuestionRepository questionRepo;

	@Override
	public List<Question> getAllQuestions() {
		return questionRepo.findAll();
	}

	@Override
	public List<Question>  getQuestionsByCategory(String category){
		return questionRepo.getQuestionsByCategory(category);
	}

	@Override
	public String addQuestion(Question question) {
		questionRepo.save(question);
		return "Question Added!";
	}

	@Override
	public String deleteQuestion(int id) {
		questionRepo.deleteById(id);
		return "Question Deleted!";
	}

	public String updateQuestion(Question question) {
		questionRepo.save(question);
		return "Question Updated!";
	}

}
