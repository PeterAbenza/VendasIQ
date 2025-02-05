package com.br.VendasIQ.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.VendasIQ.model.registroUsuario;

@Controller
public class RoutesSit {
	
	@GetMapping("/")
	public ModelAndView showHomeDashboard() {
		ModelAndView mv = new ModelAndView("home/index");
		return mv;
	}
	
	@GetMapping("/login")
	public ModelAndView showLogin() {
		ModelAndView mv = new ModelAndView("login/index");
		return mv;
	}
	
	@GetMapping("/registro")
	public ModelAndView showRegistro() {
	    ModelAndView mv = new ModelAndView("registro/index");
	    mv.addObject("registroUsuario", new registroUsuario());
	    return mv;
	}
}