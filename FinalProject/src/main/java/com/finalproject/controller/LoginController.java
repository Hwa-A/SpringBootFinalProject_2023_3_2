package com.finalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.finalproject.domain.Question;
import com.finalproject.domain.ShowQuestion;
import com.finalproject.service.QuestionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {
	
//	@GetMapping("/")
//	public String firstLoginForm() {		
//		return "";
//	}

	@RequestMapping("/login")
	public String loginForm() {		
		return "login";
	}
	
}
