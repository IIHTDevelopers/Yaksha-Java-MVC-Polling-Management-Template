package com.iiht.training.onlinevoting.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iiht.training.onlinevoting.entity.Poll;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping(value = { "/poll", "/" })
public class PollController {

	@RequestMapping("/add")
	public String addPollsPage(Model model) {
		// write your logic here
		return "";
	}

	@GetMapping("/update")
	public String showFormForUpdate(@RequestParam("pollId") int pollId, Model model) {
		// write your logic here
		return "";
	}

	@RequestMapping(value = { "/", "/list" })
	public String home(@PageableDefault(size = 5) Pageable pageable, Model model) {
		// write your logic here
		return "";
	}

	@RequestMapping("/handleForm")
	public String addPollHandleForm(@Valid @ModelAttribute Poll poll, BindingResult bindingResult) {
		// write your logic here
		return "";
	}

	@RequestMapping("/deletePoll/{id}")
	public String deletePoll(@PathVariable("id") int id, HttpServletRequest request) {
		// write your logic here
		return "";
	}

	@RequestMapping("/search")
	public String searchPolls(@RequestParam(value = "theSearchName", required = false) String theSearchName,
			@PageableDefault(size = 5) Pageable pageable, Model theModel) {

		// write your logic here
		return "";
	}

}
