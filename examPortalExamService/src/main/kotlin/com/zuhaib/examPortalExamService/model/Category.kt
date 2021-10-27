package com.zuhaib.examPortalExamService.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


@Document(collection = "category")
class Category() {
    @Id
    private var cid : String =""
    private var title: String = ""
    private var description: String = ""

    constructor(title: String, description: String) : this() {
        this.title = title
        this.description = description
    }


    fun getCid():String{
        return cid
    }
    fun setCid(cid:String){
        this.cid =cid
    }
    fun setTitle(title:String){
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


}