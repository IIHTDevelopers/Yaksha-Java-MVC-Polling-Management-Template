package com.iiht.training.onlinevoting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iiht.training.onlinevoting.entity.PollOption;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pollOption")
public class OptionController {

	@RequestMapping("/add/{id}")
	public String addOptionPage(@PathVariable("id") int id, Model model) {
		// write your logic here
		return "";
	}

	@RequestMapping("/handleForm")
	public String addOptionHandleForm(@RequestParam("pollId") int id, @Valid @ModelAttribute PollOption pollOption,
			BindingResult bindingResult) {
		// write your logic here
		return "";
	}

	@RequestMapping("/deletePollOption/{pollId}/{optionId}")
	public String deletePoll(@PathVariable("pollId") int pollId, @PathVariable("optionId") int optionId) {
		// write your logic here
		return "";
	}
}
