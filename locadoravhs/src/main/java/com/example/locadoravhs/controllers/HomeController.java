package com.example.locadoravhs.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.locadoravhs.models.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
    
    @GetMapping({"", "/", "/home"})
    public String home(HttpSession sessao, Model model){

        User usuario = (User) sessao.getAttribute("usuarioLogado");

        model.addAttribute("usuario", usuario);
        return "home";

    }

}