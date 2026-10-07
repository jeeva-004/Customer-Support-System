package com.customersupport.services;

import com.customersupport.entities.Category;
import com.customersupport.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAll(){

        return categoryRepository.findAll();
    }

    public Category getById(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("Record does't exist ID "+id));
    }

    public String createCategory(Category c){
        categoryRepository.save(c);

        return "Category Added";
    }

    public String updateCategory(Category c, Long id){

        Category oldCategory = categoryRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("Record does't exist ID "+id));
        oldCategory.setTitle(c.getTitle());
        oldCategory.setDescription(c.getDescription());
        categoryRepository.save(oldCategory);

        return "Category Updated";
    }

    public String deleteCategory(Long id){
        categoryRepository.delete(categoryRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("Record does't exist ID "+id)));

        return "Category Deleted";
    }
}
