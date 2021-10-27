package com.zuhaib.examPortalExamService.model

class EvalMail {
    private var marksgained :String =""
    private var attempt:String =""
    private var correct: String =""
    private var email:String=""

    fun getEmail():String{
        return email;
    }
    fun setEmail(email:String){
        this.email=email
    }

    fun getMarksgained():String{
        return marksgained
    }
    fun setMarksgained(marksgained:String){
        this.marksgained = marksgained
    }

    fun setAttempt(attempt:String){
        this.attempt = attempt
    }
    fun getAttmept():String{
        return attempt
    }
    fun getCorrect():String{
        return correct
    }

    fun setCorrect(correct:String){
        this.correct =correct
    }

}