package com.zuhaib.examPortalExamService.controller

import com.zuhaib.examPortalExamService.model.Category
import com.zuhaib.examPortalExamService.service.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/category")
class CategoryController {
    @Autowired
    private val categoryService: CategoryService? = null

    //add category
    @PostMapping("/")
    fun addCategory(@RequestBody category: Category?): ResponseEntity<Category?> {
        val category1 = categoryService!!.addCategory(category)
        return ResponseEntity.ok(category1)
    }
    //get category
    @GetMapping("/{categoryId}")
    fun getCategory(@PathVariable("categoryId") categoryId: String?): Category? {
        return categoryService!!.getCategory(categoryId)
    }
    //get all categories
    @GetMapping("/")
    fun getCatgories() : ResponseEntity<LinkedHashSet<Category?>> {
        return ResponseEntity.ok(categoryService!!.getCategories())
    }
    @GetMapping("/count")
    fun getCatgoriesCount() : ResponseEntity<*> {
        return ResponseEntity.ok(categoryService!!.getCategories()?.size)
    }

    //update category
    @PutMapping("/")
    fun updateCategory(@RequestBody category: Category?): Category? {
        return categoryService!!.updateCategory(category)
    }
    //delete category
    @DeleteMapping("/{categoryId}")
    fun deleteCategory(@PathVariable("categoryId") categoryId: String?) {
        categoryService!!.deleteCategory(categoryId)
    }
}