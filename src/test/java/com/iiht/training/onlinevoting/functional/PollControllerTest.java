package com.iiht.training.onlinevoting.functional;

import static com.iiht.training.onlinevoting.testutils.MasterData.asJsonString;
import static com.iiht.training.onlinevoting.testutils.MasterData.getPoll;
import static com.iiht.training.onlinevoting.testutils.MasterData.getPollList;
import static com.iiht.training.onlinevoting.testutils.MasterData.randomStringWithSize;
import static com.iiht.training.onlinevoting.testutils.TestUtils.businessTestFile;
import static com.iiht.training.onlinevoting.testutils.TestUtils.currentTest;
import static com.iiht.training.onlinevoting.testutils.TestUtils.testReport;
import static com.iiht.training.onlinevoting.testutils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;

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
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.iiht.training.onlinevoting.controller.PollController;
import com.iiht.training.onlinevoting.entity.Poll;
import com.iiht.training.onlinevoting.service.PollService;

public class PollControllerTest {

	@Mock
	private PollService pollService;

	@InjectMocks
	private PollController pollController;

	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(pollController)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
	}

	@After
	public void afterAll() {
		testReport();
	}

	@Test
	public void testPollControllerAddPolls() throws Exception {
		MvcResult result = this.mockMvc.perform(get("/poll/add")).andReturn();
		yakshaAssert(currentTest(), result.getModelAndView().getViewName().contentEquals("addPolls") ? true : false,
				businessTestFile);

	}

	@Test
	public void testPollControllerUpdatePolls() throws Exception {
		Poll poll = getPoll();
		when(pollService.get(poll.getPollId().intValue())).thenReturn(poll);
		MvcResult result = this.mockMvc.perform(get("/poll/update").param("pollId", poll.getPollId().toString()))
				.andReturn();
		yakshaAssert(currentTest(), result.getModelAndView().getViewName().contentEquals("updatePolls") ? true : false,
				businessTestFile);

	}

	@Test
	public void testPollControllerListPollsHome() throws Exception {
		try {
			List<Poll> expected = getPollList(5);
			Pageable pageable = PageRequest.of(0, 5);
			Page<Poll> pollPage = new PageImpl<>(expected);

			when(pollService.getAll(pageable)).thenReturn(pollPage);
			MvcResult result = this.mockMvc.perform(get("/")).andReturn();
			yakshaAssert(currentTest(),
					result.getModelAndView() != null && result.getModelAndView().getViewName() != null
							&& result.getModelAndView().getViewName().contentEquals("list-polls")
							&& asJsonString(expected)
									.equals(asJsonString(result.getModelAndView().getModel().get("polls"))) ? "true"
											: "false",
					businessTestFile);
		} catch (Exception ex) {
			yakshaAssert(currentTest(), "false", businessTestFile);
		}
	}

	@Test
	public void testPollControllerListPolls() throws Exception {
		List<Poll> expected = getPollList(5);
		Pageable pageable = PageRequest.of(0, 5);
		Page<Poll> pollPage = new PageImpl<>(expected);

		when(pollService.getAll(pageable)).thenReturn(pollPage);
		MvcResult result = this.mockMvc.perform(get("/poll/list")).andReturn();
		yakshaAssert(currentTest(),
				result.getModelAndView() != null && result.getModelAndView().getViewName() != null
						&& result.getModelAndView().getViewName().contentEquals("list-polls")
						&& asJsonString(expected).equals(asJsonString(result.getModelAndView().getModel().get("polls")))
								? "true"
								: "false",
				businessTestFile);
	}

	@Test
	public void testPollControllerAddPollHandleForm() throws Exception {
		Poll poll = getPoll();
		MvcResult result = this.mockMvc.perform(post("/poll/handleForm").flashAttr("poll", poll)).andReturn();
		yakshaAssert(currentTest(),
				result.getModelAndView().getViewName().contentEquals("redirect:/poll/list") ? true : false,
				businessTestFile);
	}

	@Test
	public void testPollControllerDeletePoll() throws Exception {
		Poll poll = getPoll();
		when(pollService.delete(poll.getPollId().intValue())).thenReturn(true);
		MvcResult result = this.mockMvc.perform(get("/poll/deletePoll/" + poll.getPollId().toString())).andReturn();
		yakshaAssert(currentTest(),
				result.getModelAndView().getViewName().contentEquals("redirect:/poll/list") ? true : false,
				businessTestFile);
	}

	@Test
	public void testPollControllerSearchPollsWithSearchValue() throws Exception {
		String key = randomStringWithSize(2);
		List<Poll> expected = getPollList(5);
		Pageable pageable = PageRequest.of(0, 5);
		Page<Poll> pollPage = new PageImpl<>(expected);

		when(pollService.searchPolls(key, pageable)).thenReturn(pollPage);
		MvcResult result = this.mockMvc.perform(post("/search").param("theSearchName", key)).andReturn();
		yakshaAssert(currentTest(),
				result.getModelAndView() != null && result.getModelAndView().getViewName() != null
						&& result.getModelAndView().getViewName().contentEquals("list-polls")
						&& asJsonString(expected).equals(asJsonString(result.getModelAndView().getModel().get("polls")))
								? "true"
								: "false",
				businessTestFile);
	}

	@Test
	public void testPollControllerSearchPollsWithSearchValueAsNull() throws Exception {
		List<Poll> expected = getPollList(5);
		Pageable pageable = PageRequest.of(0, 5);
		Page<Poll> pollPage = new PageImpl<>(expected);

		when(pollService.searchPolls(null, pageable)).thenReturn(pollPage);
		MvcResult result = this.mockMvc.perform(post("/search")).andReturn();
		yakshaAssert(currentTest(),
				result.getModelAndView() != null && result.getModelAndView().getViewName() != null
						&& result.getModelAndView().getViewName().contentEquals("list-polls")
						&& asJsonString(expected).equals(asJsonString(result.getModelAndView().getModel().get("polls")))
								? "true"
								: "false",
				businessTestFile);
	}

}
