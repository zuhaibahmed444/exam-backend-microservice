package com.zuhaib.examPortalExamService

import com.zuhaib.examPortalExamService.model.Question
import com.zuhaib.examPortalExamService.repo.QuestionRepository
import com.zuhaib.examPortalExamService.service.QuestionService
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class QuestionServiceTest {
    @Autowired
    val questionService : QuestionService?=null

    @Mock
    val questionRepository : QuestionRepository?=null

    val question = Question("This is First Question","zyx.com","abc","efg","rst","xyz"
        ,"abc")

    @Test
    fun addQuestionTest(){
        `when`(questionRepository?.save(question)).thenReturn(question)
        assertEquals(question,questionService?.addQuestion(question))
    }
    @Test
    fun updateQuestionTest(){
        `when`(questionRepository?.save(question)).thenReturn(question)
        assertEquals(question,questionService?.addQuestion(question))
    }

    @Test
    fun getQuestionTest(){
        var quesId = questionService?.addQuestion(question)?.quesId
        assertEquals(question.answer,questionService?.getQuestion(quesId)?.answer)
    }

    @Test
    fun deleteQuestionTest(){
        var quesId = questionService?.addQuestion(question)?.quesId
        assertEquals(Unit,questionService?.deleteQuestion(quesId))
    }

}