package com.zuhaib.examPortalExamService.service

import com.zuhaib.examPortalExamService.model.Category
import com.zuhaib.examPortalExamService.model.Quiz

interface QuizService {

    fun addQuiz(quiz: Quiz?): Quiz?
    fun updateQuiz(quiz: Quiz?): Quiz?
    fun getQuiz(quizId: String?): Quiz?
    fun quizzes(): Set<Quiz?>?
    fun deleteQuiz(quizId: String?)
    fun getQuizzesOfCategory(category: Category?): List<Quiz?>?
    fun getActiveQuizzes(): List<Quiz?>?
    fun getActiveQuizzesOfCategory(c: Category?): List<Quiz?>?

}