package com.example.locadoravhs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.locadoravhs.models.User;
import com.example.locadoravhs.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository usuarioRepository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public void save(User usuario) throws IllegalArgumentException {

        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }

        String senhaCriptografada = encoder.encode(usuario.getSenha());

        usuario.setSenha(senhaCriptografada);

        usuarioRepository.save(usuario);

    }
}
