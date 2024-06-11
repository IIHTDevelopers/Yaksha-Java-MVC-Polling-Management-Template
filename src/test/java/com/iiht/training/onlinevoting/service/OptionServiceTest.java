package com.iiht.training.onlinevoting.service;

import com.iiht.training.onlinevoting.entity.Poll;
import com.iiht.training.onlinevoting.entity.PollOption;
import com.iiht.training.onlinevoting.repository.PollOptionRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static com.iiht.training.onlinevoting.testutils.MasterData.asJsonString;
import static com.iiht.training.onlinevoting.testutils.MasterData.getPoll;
import static com.iiht.training.onlinevoting.testutils.MasterData.getPollOption;
import static com.iiht.training.onlinevoting.testutils.MasterData.getPollOptionList;
import static com.iiht.training.onlinevoting.testutils.TestUtils.businessTestFile;
import static com.iiht.training.onlinevoting.testutils.TestUtils.currentTest;
import static com.iiht.training.onlinevoting.testutils.TestUtils.testReport;
import static com.iiht.training.onlinevoting.testutils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

public class OptionServiceTest {

    @Mock
    private PollOptionRepository pollOptionRepository;

    @InjectMocks
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
    public void testOptionServiceSaveOption() throws Exception {
        PollOption pollOption = getPollOption(1);
        boolean value = optionService.save(pollOption);
        yakshaAssert(currentTest(), value ? true : false, businessTestFile);
    }

    @Test
    public void testOptionServiceGetAllOption() throws Exception {
        List<PollOption> actual = getPollOptionList(1);
        when(pollOptionRepository.findAll()).thenReturn(actual);
        List<PollOption> expected = optionService.getAll();
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testOptionServiceGetOptionById() throws Exception {
        PollOption actual = getPollOption(1);
        when(pollOptionRepository.findById(actual.getOptionId())).thenReturn(Optional.of(actual));
        PollOption expected = (PollOption) optionService.get(actual.getPollId());
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testOptionServiceGetOptionByPollId() throws Exception {
        List<PollOption> actual = getPollOptionList(1);
        when(pollOptionRepository.findByPollId(1)).thenReturn(actual);
        List<PollOption> expected = optionService.getOptionByPollId(1);
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testOptionServiceDeleteOption() throws Exception {
        PollOption pollOption = getPollOption(1);
        boolean value = optionService.delete(pollOption.getPollId());
        yakshaAssert(currentTest(), value ? true : false, businessTestFile);
    }

    @Test
    public void testOptionServiceDeleteOptionByPollId() throws Exception {
        Poll poll = getPoll();
        PollOption pollOption = getPollOption(1);
        pollOption.setPollId(poll.getPollId().intValue());
        boolean value = optionService.deleteOptionByPollId(poll.getPollId().intValue());
        yakshaAssert(currentTest(), value ? true : false, businessTestFile);
    }

}
