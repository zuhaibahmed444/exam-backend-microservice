package com.zuhaib.examPortalExamService.repo

import com.zuhaib.examPortalExamService.model.Category
import com.zuhaib.examPortalExamService.model.Quiz
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface QuizRepository :MongoRepository<Quiz,String> {

    fun findBycategory(category: Category?): List<Quiz?>?
    fun findByActive(b: Boolean?): List<Quiz?>?
    fun findBycategoryAndActive(c: Category?,b: Boolean?):List<Quiz?>?
    fun save(quiz: Quiz?): Quiz
    fun findById(quizId: String?): Quiz


}