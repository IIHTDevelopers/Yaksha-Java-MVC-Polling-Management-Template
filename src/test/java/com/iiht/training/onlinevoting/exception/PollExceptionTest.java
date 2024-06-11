package com.iiht.training.onlinevoting.exception;

import com.iiht.training.onlinevoting.controller.PollController;
import com.iiht.training.onlinevoting.entity.Poll;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.iiht.training.onlinevoting.testutils.MasterData.getPoll;
import static com.iiht.training.onlinevoting.testutils.TestUtils.currentTest;
import static com.iiht.training.onlinevoting.testutils.TestUtils.exceptionTestFile;
import static com.iiht.training.onlinevoting.testutils.TestUtils.testReport;
import static com.iiht.training.onlinevoting.testutils.TestUtils.yakshaAssert;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class PollExceptionTest {

    @InjectMocks
    private PollController pollController;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(pollController).build();
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testExceptionAddPollNameBlank() throws Exception {
        Poll poll = getPoll();
        poll.setPollName("");
        MvcResult result = this.mockMvc.perform(post("/poll/handleForm")
                        .flashAttr("poll", poll))
                .andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("updatePolls")), exceptionTestFile);
    }

    @Test
    public void testExceptionAddPollNameNull() throws Exception {
        Poll poll = getPoll();
        poll.setPollName(null);
        MvcResult result = this.mockMvc.perform(post("/poll/handleForm")
                        .flashAttr("poll", poll))
                .andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("updatePolls")), exceptionTestFile);
    }

}
