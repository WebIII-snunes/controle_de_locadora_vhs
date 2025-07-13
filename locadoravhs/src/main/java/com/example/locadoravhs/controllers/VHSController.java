package com.example.locadoravhs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import com.example.locadoravhs.models.TapeStatus;
import com.example.locadoravhs.models.VHS;
import com.example.locadoravhs.services.VHSService;
import com.example.locadoravhs.services.CategoryService;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/vhs")
public class VHSController {
    @Autowired
    VHSService vhsService;

    @Autowired
    CategoryService categoryService;
    @GetMapping
    public String findAll(Model model){
        List<VHS> vhsList = vhsService.findAll();
        model.addAttribute("vhsList", vhsList);
        return "vhs-list";
    }


    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("vhs", new VHS());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("statuses", TapeStatus.values());
        return "vhs-create";
    }
    @PostMapping("/create")
    public String save(VHS vhs){
        vhsService.save(vhs);

        return "redirect:/vhs";

    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        vhsService.deleteById(id);
        return "redirect:/vhs";
    }





}
