package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * Home view
 *
 * @author Quentin
 */
@Controller
public class HomeController
{
	@RequestMapping("/")
	public String getHomeView(Model model)
	{
		return "home";
	}

	@RequestMapping("/admin/home")
	public String adminHome(Model model)
	{
		return "redirect:/bidList/list";
	}


}
