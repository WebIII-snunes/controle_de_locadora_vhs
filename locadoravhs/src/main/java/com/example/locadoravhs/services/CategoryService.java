package com.example.locadoravhs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.locadoravhs.models.Category;

import com.example.locadoravhs.repositories.CategoryRepository;
import com.example.locadoravhs.repositories.VHSRepository;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    VHSRepository vhsRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public Category findById(Long id) {
        
        return categoryRepository.findById(id).orElse(null);

    }
    public void deleteById(Long id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id inválido");
        }

        Category existingCategory = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category não encontrado"));
    
        if (vhsRepository.existsByCategory(existingCategory)) {
            throw new RuntimeException();
        }

        categoryRepository.deleteById(existingCategory.getId());
        
        
    }

    public Category update(Category updatedCategory) {
        
        if (updatedCategory == null || updatedCategory.getId() < 0) {
            throw new IllegalArgumentException("Category inválido");
        }

        Category existingCategory = categoryRepository.findById(updatedCategory.getId()).orElseThrow(() -> new RuntimeException("Category não encontrado"));
        
        
    
        existingCategory.setName(updatedCategory.getName());
        

        return categoryRepository.save(existingCategory);
    }

}
