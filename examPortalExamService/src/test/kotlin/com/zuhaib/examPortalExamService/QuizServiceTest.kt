package com.zuhaib.examPortalExamService

import com.zuhaib.examPortalExamService.model.Quiz
import com.zuhaib.examPortalExamService.repo.QuizRepository
import com.zuhaib.examPortalExamService.service.QuizService
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class QuizServiceTest {
    @Autowired
    val quizService : QuizService?=null

    @Mock
    val quizRepository: QuizRepository?=null

    val quiz : Quiz = Quiz("Physics 12","This consist of class 12 Physic","100","10",false)

    @Test
    fun addQuizTest(){
        `when`(quizRepository?.save(quiz)).thenReturn(quiz)
        assertEquals(quiz,quizService?.addQuiz(quiz))
    }

    @Test
    fun updateQuizTest(){
        `when`(quizRepository?.save(quiz)).thenReturn(quiz)
        assertEquals(quiz,quizService?.addQuiz(quiz))
    }

    @Test
    fun getQuizTest(){
        val quiz1 = quizService?.addQuiz(quiz)
        assertEquals(quiz.getMaxMarks(),quiz1?.getMaxMarks())
    }

    @Test
    fun deleteQuizTest(){
        val quizId :String = "96e5b126-3b3a-11ec-887e-5daf0dfc44a7"
        assertEquals(Unit,quizService?.deleteQuiz(quizId))
    }

    @Test
    fun getActiveQuizzesTest(){
        var qu =  quizService?.getActiveQuizzes()?.size
        if (qu != null) {
            assertTrue(qu > 0)
        }
    }



}