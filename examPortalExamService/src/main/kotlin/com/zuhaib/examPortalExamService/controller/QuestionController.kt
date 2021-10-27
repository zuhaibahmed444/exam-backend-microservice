package com.zuhaib.examPortalExamService.controller


import com.zuhaib.examPortalExamService.model.EvalMail
import com.zuhaib.examPortalExamService.model.Question
import com.zuhaib.examPortalExamService.model.Quiz
import com.zuhaib.examPortalExamService.service.QuestionService
import com.zuhaib.examPortalExamService.service.QuizService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.web.bind.annotation.*
import java.util.Map


@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/question")
class QuestionController {
    var qid:String? = null
    @Autowired
    private val service: QuestionService? = null

    @Autowired
    private val quizService: QuizService? = null

    @Autowired
    private val mailSender: JavaMailSender? = null

    //add question
    @PostMapping("/")
    fun add(@RequestBody question: Question?): ResponseEntity<Question?> {
        println(question?.quiz?.getqId())
        return ResponseEntity.ok(service!!.addQuestion(question))
    }

    //update the question
    @PutMapping("/")
    fun update(@RequestBody question: Question?): ResponseEntity<Question?> {
        return ResponseEntity.ok(service!!.updateQuestion(question))
    }

    //get all question of any quiz
    @GetMapping("/quiz/{qid}")
    fun getQuestionsOfQuiz(@PathVariable("qid") qid: String?): ResponseEntity<*> {
//        val quiz = quizService?.getQuiz(qid)
//        val questions: Set<Question?>? = service!!.getQuestionsOfQuiz(quiz)
//        var list: List<Question?> = ArrayList(questions)
//        if (quiz != null) {
//            if (list.size > quiz.getNumberOfQuestion().toInt()) {
//                list = list.subList(0, (quiz.getNumberOfQuestion() + 1).toInt())
//            }
//        }
        val quiz = Quiz()
        quiz.setqId(qid)
        this.qid = qid
        val list :Set<Question?>? = service?.getQuestionsOfQuiz(quiz)
        return ResponseEntity.ok(list)
    }

    @GetMapping("/quiz/all/{qid}")
    fun getQuestionsOfQuizAdmin(@PathVariable("qid") qid: String?): ResponseEntity<*> {
        val quiz = Quiz()
        quiz.setqId(qid)
        val questionsOfQuiz :Set<Question?>? = service?.getQuestionsOfQuiz(quiz)
        return ResponseEntity.ok(questionsOfQuiz)

//        return ResponseEntity.ok(list);
    }

    //get single question
    @GetMapping("/{quesId}")
    operator fun get(@PathVariable("quesId") quesId: String?): Question? {
        return service!!.getQuestion(quesId)
    }

    //delete question
    @DeleteMapping("/{quesId}")
    fun delete(@PathVariable("quesId") quesId: String?) {
        service!!.deleteQuestion(quesId)
    }

    //eval quiz
    @PostMapping("/eval-quiz")
    fun evalQuiz(@RequestBody questions: List<Question>): ResponseEntity<*> {
        println(questions)
        val quiz = quizService?.getQuiz(questions[0].quiz?.getqId())
        var marksGot = 0.0
        var correctAnswers = 0
        var attempted = 0
        for (q in questions) {
            //single questions
            val question = service?.getQuestion(q.quesId)
            if (question!!.answer == q.givenAnswer) {
                //correct
                correctAnswers++
                val marksSingle =  quiz!!.getMaxMarks().toDouble() / questions.size
                //       this.questions[0].quiz.maxMarks / this.questions.length;
                marksGot += marksSingle
            }
            if (q.givenAnswer != null) {
                attempted++
            }
        }
        val map = Map.of<String, Any>("marksGot", marksGot, "correctAnswers", correctAnswers, "attempted", attempted)

        return ResponseEntity.ok(map)
    }

    @PostMapping("/result/mail")
    fun evalmail(@RequestBody evalmail : EvalMail):ResponseEntity<*>{
        var mss : String =""
        try {
            val title  = quizService?.getQuiz(qid)?.getTitle()
            var em = evalmail.getEmail()
            val message = SimpleMailMessage()
            message.setFrom("zuhaibahmed444@gmail.com")
            message.setTo(em)
            message.setSubject("Quiz Results From Exam Portal")
            message.setText("""
             RESULTS
             Quiz: $title
             Marks Scored:${evalmail.getMarksgained()}
             Correct Answers:${evalmail.getCorrect()}
             Attempted :${evalmail.getAttmept()}
             """.trimIndent())
            mailSender?.send(message)
            println("Quiz mail sent")
            mss = "Mail sent SuccessFully"

        }catch (e:Exception){
            mss = "$e"
        }

        return ResponseEntity.ok(mss)
    }


}