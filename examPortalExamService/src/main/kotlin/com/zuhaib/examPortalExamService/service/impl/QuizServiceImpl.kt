package com.zuhaib.examPortalExamService.service.impl

import com.fasterxml.uuid.Generators
import com.zuhaib.examPortalExamService.model.Category
import com.zuhaib.examPortalExamService.model.Quiz
import com.zuhaib.examPortalExamService.repo.QuizRepository
import com.zuhaib.examPortalExamService.service.QuizService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import kotlin.collections.HashSet

@Service
@Transactional
class QuizServiceImpl(
     @Autowired
    private val quizRepository: QuizRepository
): QuizService {


    override fun addQuiz(quiz: Quiz?): Quiz? {
        val uuid : UUID = Generators.timeBasedGenerator().generate()
        quiz?.setqId(uuid.toString())
        return quizRepository.save(quiz)
    }

    override fun updateQuiz(quiz: Quiz?): Quiz? {
        return quizRepository.save(quiz)
    }


    override fun getQuiz(quizId: String?): Quiz? {
        return quizRepository.findById(quizId)
    }

    override fun quizzes(): Set<Quiz?>? {
        return HashSet(quizRepository.findAll())
    }

    override fun deleteQuiz(quizId: String?){
        val quiz : Quiz = Quiz("","","","",false)
        if(quizId != null){
            quiz.setqId(quizId)
        }
        return quizRepository?.delete(quiz)
    }

    override fun getQuizzesOfCategory(category: Category?): List<Quiz?>? {
        return quizRepository.findBycategory(category)
    }

    override fun getActiveQuizzes(): List<Quiz?>? {
        return quizRepository.findByActive(true)
    }

    override fun getActiveQuizzesOfCategory(c: Category?): List<Quiz?>? {
        return quizRepository.findBycategoryAndActive(c, true)
    }

}