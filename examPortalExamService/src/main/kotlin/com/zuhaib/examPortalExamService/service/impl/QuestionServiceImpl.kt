package com.zuhaib.examPortalExamService.service.impl

import com.fasterxml.uuid.Generators
import com.zuhaib.examPortalExamService.model.Question
import com.zuhaib.examPortalExamService.model.Quiz
import com.zuhaib.examPortalExamService.repo.QuestionRepository
import com.zuhaib.examPortalExamService.service.QuestionService
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.HashSet

@Service
class QuestionServiceImpl(private val questionRepository: QuestionRepository) : QuestionService {

    override fun addQuestion(question: Question?): Question? {
        val uuid : UUID = Generators.timeBasedGenerator().generate()
        question?.quesId = uuid.toString()
        return questionRepository.save(question)
    }

    override fun updateQuestion(question: Question?): Question? {
        return questionRepository.save(question)
    }

    override fun getQuestions(): Set<Question?>? {
        return HashSet(questionRepository.findAll())
    }

    override fun getQuestion(questionId: String?): Question? {
        return questionRepository.findById(questionId!!).get()
    }

    override fun getQuestionsOfQuiz(quiz: Quiz?): Set<Question?>? {
        return questionRepository.findByQuiz(quiz)
    }

    override fun deleteQuestion(quesId: String?) {
        val question = Question()
        question.quesId = quesId!!

        questionRepository.delete(question)
    }

    //to get single question using questionId
    override fun get(questionsId: String?): Question? {
        return questionRepository.findById(questionsId)
    }
}