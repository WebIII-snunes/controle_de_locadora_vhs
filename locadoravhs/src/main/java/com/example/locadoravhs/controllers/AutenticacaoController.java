package com.example.locadoravhs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.locadoravhs.models.User;
import com.example.locadoravhs.services.AutenticacaoService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AutenticacaoController {

    @Autowired
    AutenticacaoService autenticacaoService;

    @GetMapping("/login")
    public String loginForm(){

        return "login";

    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String senha, HttpSession sessao, RedirectAttributes model){

        try {
            User usuario = autenticacaoService.login(email, senha);
            //ja deu certo
            sessao.setAttribute("usuarioLogado", usuario);
            return "redirect:/vhs";

        } catch (Exception e) {
            model.addFlashAttribute("mensagem", e.getMessage());
            return "redirect:/login";
        }

    }

    @GetMapping("/logout")
    public String logout(HttpSession sessao){

        sessao.invalidate();
        return "redirect:/login";
    }

}