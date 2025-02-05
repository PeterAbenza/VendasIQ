package com.br.VendasIQ.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.VendasIQ.dao.UserDao;
import com.br.VendasIQ.model.registroUsuario;

import jakarta.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    private UserDao userRepositorio;

    @PostMapping("/InsertUser")
    public ModelAndView insertUser(@Valid @ModelAttribute registroUsuario user, BindingResult result) {
        ModelAndView mv = new ModelAndView();
        if (result.hasErrors()) {
            mv.setViewName("registro/index");
            mv.addObject("message", "Erro ao registrar usuário: " + result.getAllErrors().get(0).getDefaultMessage());
            return mv;
        }

        // Verificar se o email já está em uso
        Optional<registroUsuario> existingUser = userRepositorio.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            mv.setViewName("registro/index");
            mv.addObject("message", "Email já está em uso. Por favor, use um email diferente.");
            return mv;
        }

        try {
            userRepositorio.save(user);
            mv.setViewName("redirect:/login");
        } catch (Exception e) {
            mv.setViewName("registro/index");
            mv.addObject("message", "Erro ao registrar usuário: " + e.getMessage());
        }
        return mv;
    }

    @PostMapping("/login")
    public ModelAndView login(String email, String password, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        Optional<registroUsuario> optionalUser = userRepositorio.findByEmail(email);
        if (optionalUser.isPresent()) {
            registroUsuario user = optionalUser.get();
            if (user.getSenha().equals(password)) {
                redirectAttributes.addFlashAttribute("username", user.getNome());
                mv.setViewName("redirect:/");
            } else {
                mv.setViewName("login/index");
                mv.addObject("errorMessage", "Senha incorreta");
            }
        } else {
            mv.setViewName("login/index");
            mv.addObject("errorMessage", "Email não encontrado");
        }
        return mv;
    }
}