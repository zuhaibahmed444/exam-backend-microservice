package com.zuhaib.examPortalExamService.model

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "question")
class Question() {
    @Id
    var quesId :String =" "
    var content: String? = null
    var image: String? = null
    var option1: String? = null
    var option2: String? = null
    var option3: String? = null
    var option4: String? = null
    var answer: String? = null

    @Transient
    var givenAnswer: String? = null

    var quiz: Quiz? = null

    constructor(
        content: String?,
        image: String?,
        option1: String?,
        option2: String?,
        option3: String?,
        option4: String?,
        answer: String?
    ) : this() {
        this.content = content
        this.image = image
        this.option1 = option1
        this.option2 = option2
        this.option3 = option3
        this.option4 = option4
        this.answer = answer
    }
}