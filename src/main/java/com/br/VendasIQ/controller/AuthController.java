package com.br.VendasIQ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.VendasIQ.dao.UserDao;
import com.br.VendasIQ.model.registroUsuario;

import jakarta.validation.Valid;

import org.springframework.validation.BindingResult;

@Controller
public class AuthController {

	@Autowired
	private UserDao UserRepositorio;

	@PostMapping("/InsertUser")
	public ModelAndView insertUser(@Valid @ModelAttribute registroUsuario user, BindingResult result) {
		ModelAndView mv = new ModelAndView();
		if (result.hasErrors()) {
			mv.setViewName("registro/index");
			mv.addObject("message", "Erro ao registrar usuário: " + result.getAllErrors().get(0).getDefaultMessage());
			System.out.println("Erro de validação: " + result.getAllErrors().get(0).getDefaultMessage());
			return mv;
		}
		try {
			System.out.println("Tentando salvar usuário: " + user.toString());
			UserRepositorio.save(user);
			mv.setViewName("redirect:/login");
			System.out.println("Usuário registrado: " + user.toString());
		} catch (Exception e) {
			mv.setViewName("registro/index");
			mv.addObject("message", "Erro ao registrar usuário: " + e.getMessage());
			System.out.println("Erro ao registrar usuário: " + e.getMessage());
		}
		return mv;
	}
}
