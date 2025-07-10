package com.example.locadoravhs.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.locadoravhs.models.User;
import com.example.locadoravhs.services.UserService;


@Controller
public class UserController {
 @Autowired
    UserService usuarioService;

    @GetMapping("/cadastro")
    public String formCadastro(){

        return "cadastro";

    }

    @PostMapping("/cadastro")
    public String cadastrar(
        @RequestParam String nome,
        @RequestParam String email,
        @RequestParam String senha,
        RedirectAttributes model){

        User usuario = new User();

        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);

        try {
            usuarioService.save(usuario); //Caso ocorra uma excecao, para aqui e vai para o block catch
            return "redirect:/login";

        } catch (Exception exception) {
            model.addFlashAttribute("mensagem", exception.getMessage());
            return "redirect:/cadastro";
        }
    }



    
}