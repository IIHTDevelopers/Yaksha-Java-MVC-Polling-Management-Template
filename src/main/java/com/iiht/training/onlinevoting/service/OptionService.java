package com.iiht.training.onlinevoting.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.iiht.training.onlinevoting.entity.PollOption;

@Component
public class OptionService {

	public boolean save(PollOption pollOption) {
		// write your logic here
		return false;
	}

	public List<PollOption> getAll() {
		// write your logic here
		return null;
	}

	public Object get(int id) {
		// write your logic here
		return null;
	}

	public List<PollOption> getOptionByPollId(int pollId) {
		// write your logic here
		return null;
	}

	public boolean delete(int id) {
		// write your logic here
		return false;
	}

	public boolean deleteOptionByPollId(int pollId) {
		// write your logic here
		return false;
	}
}
