package com.customersupport.controllers;

import com.customersupport.dto.CategoryRequest;
import com.customersupport.dto.CategoryResponse;
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
    public List<CategoryResponse> getAll(){
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public CategoryResponse getById(@PathVariable Long id){
        return categoryService.getById(id);
    }

    @PostMapping
    public CategoryResponse createCategory(@RequestBody CategoryRequest newCategory){
        return categoryService.createCategory(newCategory);
    }

    @PutMapping("/{id}")
    public CategoryResponse updateCategory(@RequestBody CategoryRequest newCategory, @PathVariable Long id){
        return categoryService.updateCategory(newCategory, id);
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return "Category Deleted";
    }
}
