package com.zuhaib.examPortalExamService

import com.zuhaib.examPortalExamService.model.Category
import com.zuhaib.examPortalExamService.repo.CategoryRepository
import com.zuhaib.examPortalExamService.service.CategoryService
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CategoryServiceTest {
    @Autowired
    val categoryService: CategoryService?=null

    @Mock
    val categoryRepository : CategoryRepository?=null

    val category : Category = Category("Bio-Physics","This Category consist of quizzez od bio")

    @Test
    fun addCategoryTest(){
        `when`(categoryRepository?.save(category)).thenReturn(category)
        assertEquals(category,categoryService?.addCategory(category))
    }

    @Test
    fun updateCategoryTest(){
        `when`(categoryRepository?.save(category)).thenReturn(category)
        assertEquals(category,categoryService?.updateCategory(category))
    }

    @Test
    fun deleteCategoryTest(){
        val catid :String = "d9d98b39-3b39-11ec-80cd-a9f76cfc470a"
        assertEquals(kotlin.Unit,categoryService?.deleteCategory(catid))
    }

}