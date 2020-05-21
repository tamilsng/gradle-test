package com.aub.docs.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(value = "")
	public String index() {
		return "redirect:home.html";
	}
	@RequestMapping(value = "/")
	public String home() {
		return "redirect:home.html";
	}
	
	@RequestMapping(value = "/home")
	public String redirecthome() {
		return "redirect:home.html";
	}
}
