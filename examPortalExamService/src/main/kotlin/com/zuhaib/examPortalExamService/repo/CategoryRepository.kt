package com.zuhaib.examPortalExamService.repo

import com.zuhaib.examPortalExamService.model.Category
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : MongoRepository<Category,String> {
    fun save(category: Category?): Category?


}