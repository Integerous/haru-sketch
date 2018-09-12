package com.harusketch.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {

	@GetMapping("/form")
	public String form() {
		
		return "/product/form";
	}
}
