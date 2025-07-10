package com.example.locadoravhs.filter;


import java.io.IOException;

import org.springframework.stereotype.Component;

import com.example.locadoravhs.models.User;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AutenticacaoFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest   httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        HttpSession sessao = httpRequest.getSession();
        User usuario = (User) sessao.getAttribute("usuarioLogado");

        String uri = httpRequest.getRequestURI(); 

        Boolean acessoLiberado = 
        uri.startsWith("/css") 
        || uri.startsWith("/login") 
        || uri.startsWith("/cadastro");
        
        if (acessoLiberado || usuario != null) {
            chain.doFilter(httpRequest, httpResponse); //continua com a requisicao
        } else {
            httpResponse.sendRedirect("/login");
        }
    }
    
}