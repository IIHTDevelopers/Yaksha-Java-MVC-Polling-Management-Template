package com.iiht.training.onlinevoting.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.iiht.training.onlinevoting.entity.Poll;

public interface PollRepository extends JpaRepository<Poll, Long> {

	// write your logic here
	Page<Poll> findAllPoll(Pageable pageable);

	// write your logic here
	Page<Poll> findByPollName(@Param("keyword") String keyword, Pageable pageable);
}
