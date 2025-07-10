package com.example.locadoravhs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.locadoravhs.models.User;
import com.example.locadoravhs.repositories.UserRepository;



@Service
public class AutenticacaoService {
    
    @Autowired
    UserRepository usuarioRepository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User login(String email, String senha ){

        User usuario = usuarioRepository.findByEmail(email).get();

        if (usuario == null || !encoder.matches(senha, usuario.getSenha())) {
            throw new IllegalArgumentException("Usuário ou senha incorretos");
        }

        //criar condições de validações

        return usuario; 

    }
}