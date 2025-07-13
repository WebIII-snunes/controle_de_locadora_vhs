package com.example.locadoravhs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import com.example.locadoravhs.models.Category;
import com.example.locadoravhs.services.CategoryService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public String findAll(Model model) {
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);
        return "category-list"; // arquivo HTML da listagem
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("category", new Category());
        return "category-create"; // arquivo HTML do formulário
    }

    @PostMapping("/create")
    public String save(Category category) {
        categoryService.save(category);
        return "redirect:/category";
    }
}