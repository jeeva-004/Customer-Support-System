package com.customersupport.controllers;

import com.customersupport.entities.Category;
import com.customersupport.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAll(){
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable Long id){
        return categoryService.getById(id);
    }

    @PostMapping
    public String createCategory(@RequestBody Category newCategory){
        categoryService.createCategory(newCategory);
        return "Category Created";
    }

    @PutMapping("/{id}")
    public String updateCategory(@RequestBody Category newCategory, @PathVariable Long id){
        categoryService.updateCategory(newCategory, id);
        return "Category Updated";
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return "Category Deleted";
    }
}
