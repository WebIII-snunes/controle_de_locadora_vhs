package com.example.locadoravhs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.locadoravhs.models.Category;
import com.example.locadoravhs.models.TapeStatus;

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


    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id,  RedirectAttributes redirectAttributes) {
        try {
        categoryService.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "Excluído com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Não é possível excluir: Categoria vinculada a uma VHS");
        }
        return "redirect:/category";
    }

    
    @GetMapping("/update/{id}")
    public String updateForm(Model model, @PathVariable Long id) {
        Category category = categoryService.findById(id);

        model.addAttribute("category", category);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("statuses", TapeStatus.values());
        return "category-update";
    }

    @PostMapping("/update")
    public String update(Category category) {



        categoryService.update(category);

        return "redirect:/category";
    }
}