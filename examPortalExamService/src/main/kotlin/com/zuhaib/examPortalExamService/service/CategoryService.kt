package com.zuhaib.examPortalExamService.service

import com.zuhaib.examPortalExamService.model.Category

interface CategoryService {
    fun addCategory(category: Category?): Category?
    fun updateCategory(category: Category?): Category?
    fun getCategories(): LinkedHashSet<Category?>
    fun getCategory(categoryId: String?): Category?
    fun deleteCategory(categoryId: String?)
}