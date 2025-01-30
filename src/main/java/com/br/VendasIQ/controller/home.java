package com.br.VendasIQ.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class home {
	
	@GetMapping("/")
	public ModelAndView showHomeDashboard() {
		ModelAndView mv = new ModelAndView("home/index");
		return mv;
	}
}