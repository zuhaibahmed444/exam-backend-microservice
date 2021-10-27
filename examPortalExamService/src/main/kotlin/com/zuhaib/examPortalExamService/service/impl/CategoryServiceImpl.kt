package com.zuhaib.examPortalExamService.service.impl

import com.fasterxml.uuid.Generators
import com.zuhaib.examPortalExamService.model.Category
import com.zuhaib.examPortalExamService.repo.CategoryRepository
import com.zuhaib.examPortalExamService.service.CategoryService
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.LinkedHashSet

@Service
class CategoryServiceImpl( private val categoryRepository: CategoryRepository): CategoryService {

    override fun addCategory(category: Category?): Category?{
        val uuid : UUID = Generators.timeBasedGenerator().generate()
        category?.setCid(uuid.toString())
        return categoryRepository.save(category)
    }

    override fun updateCategory(category: Category?): Category? {
        return categoryRepository.save(category)
    }



    override fun getCategories(): LinkedHashSet<Category?> = LinkedHashSet(categoryRepository.findAll())

    override fun getCategory(categoryId: String?): Category? {
        return categoryRepository.findById(categoryId!!).get()
    }

    override fun deleteCategory(categoryId: String?) {
        val category = Category("","")
        if (categoryId != null) {
            category.setCid(categoryId)
        }
        categoryRepository.delete(category)
    }
}