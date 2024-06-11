package com.iiht.training.onlinevoting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.iiht.training.onlinevoting.entity.PollOption;

public interface PollOptionRepository extends JpaRepository<PollOption, Long> {

	// write your logic here
	List<PollOption> findByPollId(int pollId);

	// write your logic here
	void deleteByPollId(@Param("pollId") int pollId);
}
