package com.br.VendasIQ.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
	
	@GetMapping("/test-db")
	public String testDatabaseConnection() {
	    try (Connection connection = DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/vendasiq", "root", "qzc4b4iru")) {
	        return "Conexão bem-sucedida!";
	    } catch (SQLException e) {
	        return "Erro de conexão: " + e.getMessage();
	    }
	}
}