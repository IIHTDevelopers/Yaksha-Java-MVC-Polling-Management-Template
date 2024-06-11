package com.iiht.training.onlinevoting.service;

import static com.iiht.training.onlinevoting.testutils.MasterData.asJsonString;
import static com.iiht.training.onlinevoting.testutils.MasterData.getPoll;
import static com.iiht.training.onlinevoting.testutils.MasterData.getPollList;
import static com.iiht.training.onlinevoting.testutils.MasterData.randomStringWithSize;
import static com.iiht.training.onlinevoting.testutils.TestUtils.businessTestFile;
import static com.iiht.training.onlinevoting.testutils.TestUtils.currentTest;
import static com.iiht.training.onlinevoting.testutils.TestUtils.testReport;
import static com.iiht.training.onlinevoting.testutils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.iiht.training.onlinevoting.entity.Poll;
import com.iiht.training.onlinevoting.repository.PollRepository;

public class PollServiceTest {

	@Mock
	private PollRepository pollRepository;

	@InjectMocks
	private PollService pollService;

	@Mock
	private OptionService optionService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void afterAll() {
		testReport();
	}

	@Test
	public void testPollServiceSavePoll() throws Exception {
		Poll poll = getPoll();
		boolean value = pollService.save(poll);
		yakshaAssert(currentTest(), value ? true : false, businessTestFile);
	}

	@Test
	public void testPollServiceGetPollById() throws Exception {
		Poll actual = getPoll();
		when(pollRepository.findById(actual.getPollId())).thenReturn(Optional.of(actual));
		Poll expected = (Poll) pollService.get(actual.getPollId().intValue());
		yakshaAssert(currentTest(), (asJsonString(expected).equals(asJsonString(actual)) ? "true" : "false"),
				businessTestFile);
	}

	@Test
	public void testPollServiceGetAllPoll() throws Exception {
		try {
			List<Poll> actual = getPollList(5);
			Pageable pageable = PageRequest.of(0, 5);
			Page<Poll> pagePoll = new PageImpl<>(actual);

			when(pollRepository.findAllPoll(pageable)).thenReturn(pagePoll);
			Page<Poll> expected = pollService.getAll(pageable);
			yakshaAssert(currentTest(),
					(asJsonString(expected.getContent()).equals(asJsonString(actual)) ? "true" : "false"),
					businessTestFile);
		} catch (Exception ex) {
			yakshaAssert(currentTest(), "false", businessTestFile);
		}
	}

	@Test
	public void testPollServiceDeletePoll() throws Exception {
		Poll poll = getPoll();
		when(optionService.deleteOptionByPollId(poll.getPollId().intValue())).thenReturn(true);
		boolean value = pollService.delete(poll.getPollId().intValue());
		yakshaAssert(currentTest(), value ? true : false, businessTestFile);
	}

	@Test
	public void testPollServiceUpdatePoll() throws Exception {
		Poll poll = getPoll();
		boolean value = pollService.update(poll);
		yakshaAssert(currentTest(), value ? true : false, businessTestFile);
	}

	@Test
	public void testPollServiceSearchPollWithSearchName() throws Exception {
		try {
			String searchKey = randomStringWithSize(2);
			List<Poll> actual = getPollList(5);
			Pageable pageable = PageRequest.of(0, 5);
			Page<Poll> pollPage = new PageImpl<>(actual);

			when(pollRepository.findByPollName(searchKey, pageable)).thenReturn(pollPage);
			Page<Poll> expected = pollService.searchPolls(searchKey, pageable);
			yakshaAssert(currentTest(),
					(asJsonString(expected.getContent()).equals(asJsonString(actual)) ? "true" : "false"),
					businessTestFile);
		} catch (Exception ex) {
			yakshaAssert(currentTest(), "false", businessTestFile);
		}
	}

	@Test
	public void testPollServiceSearchPollWithSearchNameAsNull() throws Exception {
		try {
			List<Poll> actual = getPollList(5);
			Pageable pageable = PageRequest.of(0, 5);
			Page<Poll> pollPage = new PageImpl<>(actual);

			when(pollRepository.findAllPoll(pageable)).thenReturn(pollPage);
			Page<Poll> expected = pollService.searchPolls(null, pageable);
			yakshaAssert(currentTest(),
					(asJsonString(expected.getContent()).equals(asJsonString(actual)) ? "true" : "false"),
					businessTestFile);
		} catch (Exception ex) {
			yakshaAssert(currentTest(), "false", businessTestFile);
		}
	}

}
