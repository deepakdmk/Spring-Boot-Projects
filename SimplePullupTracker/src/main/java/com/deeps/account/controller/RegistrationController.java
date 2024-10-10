package com.deeps.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.deeps.account.service.RegistrationService;
import com.deeps.account.view.UserRegistrationView;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

	@Autowired
	private RegistrationService regService;

	@ModelAttribute("user")
	public UserRegistrationView userRegistrationView() {
		return new UserRegistrationView();
	}

	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}

	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationView registrationView) {
		if (regService.convertViewToEntity(registrationView) != null) {
			return "redirect:/registration?success";
		} else {
			return "redirect:/registration?exists";
		}
	}

}