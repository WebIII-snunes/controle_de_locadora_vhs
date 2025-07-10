package com.example.locadoravhs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.locadoravhs.models.VHS;
import com.example.locadoravhs.services.VHSService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/vhs")
public class VHSController {
    @Autowired
    VHSService vhsService;
    @GetMapping
    public String findAll(Model model){
        List<VHS> vhsList = vhsService.findAll();
        model.addAttribute("vhsList", vhsList);
        return "vhs-list";
    }
}
