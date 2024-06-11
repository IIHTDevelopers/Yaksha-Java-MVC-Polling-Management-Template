package com.iiht.training.onlinevoting.exception;

import com.iiht.training.onlinevoting.controller.OptionController;
import com.iiht.training.onlinevoting.entity.Poll;
import com.iiht.training.onlinevoting.entity.PollOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.iiht.training.onlinevoting.testutils.MasterData.getPoll;
import static com.iiht.training.onlinevoting.testutils.MasterData.getPollOption;
import static com.iiht.training.onlinevoting.testutils.TestUtils.currentTest;
import static com.iiht.training.onlinevoting.testutils.TestUtils.exceptionTestFile;
import static com.iiht.training.onlinevoting.testutils.TestUtils.testReport;
import static com.iiht.training.onlinevoting.testutils.TestUtils.yakshaAssert;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class OptionExceptionTest {

    @InjectMocks
    private OptionController optionController;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(optionController).build();
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testExceptionAddPollOptionNameBlank() throws Exception {
        Poll poll = getPoll();
        PollOption pollOption = getPollOption(poll.getPollId().intValue());
        pollOption.setOptionName("");
        MvcResult result = this.mockMvc.perform(post("/pollOption/handleForm")
                        .param("pollId", poll.getPollId().toString())
                        .flashAttr("pollOption", pollOption))
                .andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("addOption")), exceptionTestFile);
    }

    @Test
    public void testExceptionAddPollOptionNameNull() throws Exception {
        Poll poll = getPoll();
        PollOption pollOption = getPollOption(poll.getPollId().intValue());
        pollOption.setOptionName(null);
        MvcResult result = this.mockMvc.perform(post("/pollOption/handleForm")
                        .param("pollId", poll.getPollId().toString())
                        .flashAttr("pollOption", pollOption))
                .andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("addOption")), exceptionTestFile);
    }
}


