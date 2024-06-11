package com.iiht.training.onlinevoting.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.iiht.training.onlinevoting.entity.Poll;

@Component
public class PollService {

	public boolean save(Poll poll) {
		// write your logic here
		return false;
	}

	public Poll get(int id) {
		// write your logic here
		return null;
	}

	public Page<Poll> getAll(Pageable pageable) {
		// write your logic here
		return null;
	}

	public boolean delete(int id) {
		// write your logic here
		return false;
	}

	public boolean update(Poll poll) {
		// write your logic here
		return false;
	}

	public Page<Poll> searchPolls(String theSearchName, Pageable pageable) {
		// write your logic here
		return null;
	}
}
