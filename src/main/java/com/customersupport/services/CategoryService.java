package com.customersupport.services;

import com.customersupport.dto.CategoryRequest;
import com.customersupport.dto.CategoryResponse;
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

    // Entity to Response

    private CategoryResponse toResponse(Category c){
        CategoryResponse response = new CategoryResponse();
        response.setId(c.getId());
        response.setTitle(c.getTitle());
        response.setDescription(c.getDescription());
        return response;
    }

    // DTO Request to Entity

    private Category toCategory (CategoryRequest c){
        Category category = new Category();
        category.setTitle(c.getTitle());
        category.setDescription(c.getDescription());
        return category;
    }

    public List<CategoryResponse> getAll(){
        return categoryRepository.findAll().stream().map(this::toResponse).toList();
    }

    public CategoryResponse getById(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("Record does't exist ID "+id));

        return toResponse(category);
    }

    public CategoryResponse createCategory(CategoryRequest c){
        Category category = toCategory(c);
        Category savedCategory =  categoryRepository.save(category);
        return toResponse(savedCategory);
    }

    public CategoryResponse updateCategory(CategoryRequest c, Long id){

        Category category = categoryRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("Record does't exist ID "+id));
        category.setTitle(c.getTitle());
        category.setDescription(c.getDescription());

        Category updatedCategory = categoryRepository.save(category);

        return toResponse(updatedCategory);
    }

    public String deleteCategory(Long id){
        categoryRepository.delete(categoryRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("Record does't exist ID "+id)));

        return "Category Deleted";
    }
}
