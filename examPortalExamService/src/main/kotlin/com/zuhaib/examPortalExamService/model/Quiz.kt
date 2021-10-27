package com.zuhaib.examPortalExamService.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "quiz")
class Quiz() {
    @Id
    private var qId:String =" "
    private var title:String=" "
    private var description:String=" "
    private var maxMarks:String =" "
    private var numberOfQuestions:String =" "
    private var active : Boolean = false

    private var category:Category? =null

    private var questions:Set<Question> = HashSet()

    constructor(title: String, description: String, maxMarks: String, numberOfQuestions: String, active: Boolean) : this() {
        this.title = title
        this.description = description
        this.maxMarks = maxMarks
        this.numberOfQuestions = numberOfQuestions
        this.active = active
    }


    fun setqId(qid: String?){
        if (qid != null) {
            this.qId = qid
        }
    }
    fun getqId():String{
        return qId
    }
    fun setTitle(title: String){
        this.title =title
    }
    fun getTitle():String{
        return title
    }
    fun setDescription(description: String){
        this.description =description
    }
    fun getDescription():String{
        return description
    }

    fun setMaxMarks(maxMarks: String){
        this.maxMarks = maxMarks
    }
    fun getMaxMarks():String{
        return maxMarks
    }
    fun setNumberOfQuestion(numberOfQuestion: String){
        this.numberOfQuestions = numberOfQuestion
    }
    fun getNumberOfQuestion():String{
        return numberOfQuestions
    }
    fun setIsActive(active:Boolean){
        this.active = active
    }
    fun getIsActive():Boolean{
        return active
    }

    fun setCategory(category: Category){
        this.category = category
    }
    fun getCategory():Category?{
        return category
    }
    fun setQuestions(question: Set<Question>){
        this.questions = question
    }
    fun getQuestions():Set<Question>{
        return questions
    }



}