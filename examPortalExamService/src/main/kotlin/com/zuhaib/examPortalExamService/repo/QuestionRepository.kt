package com.zuhaib.examPortalExamService.repo

import com.zuhaib.examPortalExamService.model.Quiz
import com.zuhaib.examPortalExamService.model.Question
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestionRepository : MongoRepository<Question,String> {
    fun findByQuiz(quiz: Quiz?): Set<Question?>?
    fun save(question: Question?): Question
    fun findById(questionsId: String?) : Question

}