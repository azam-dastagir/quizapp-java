package com.quizapp.services;

import java.util.List;
import com.quizapp.entity.Question;

public interface QuestionService {


	List<Question> getAllQuestions();

	List<Question>  getQuestionsByCategory(String category);

	String addQuestion(Question question);

	String deleteQuestion(int id);

	String updateQuestion(Question question);

}
