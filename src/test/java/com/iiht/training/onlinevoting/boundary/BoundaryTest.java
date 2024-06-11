package com.iiht.training.onlinevoting.boundary;

import com.iiht.training.onlinevoting.entity.Poll;
import com.iiht.training.onlinevoting.entity.PollOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;

import static com.iiht.training.onlinevoting.testutils.MasterData.getPoll;
import static com.iiht.training.onlinevoting.testutils.MasterData.getPollOption;
import static com.iiht.training.onlinevoting.testutils.MasterData.randomStringWithSize;
import static com.iiht.training.onlinevoting.testutils.TestUtils.boundaryTestFile;
import static com.iiht.training.onlinevoting.testutils.TestUtils.currentTest;
import static com.iiht.training.onlinevoting.testutils.TestUtils.testReport;
import static com.iiht.training.onlinevoting.testutils.TestUtils.yakshaAssert;

@ExtendWith(SpringExtension.class)
public class BoundaryTest {

    private static Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testBoundaryPollNameNotBlank() throws Exception {
        Poll poll = getPoll();
        poll.setPollName("");
        Set<ConstraintViolation<Poll>> violations = validator.validate(poll);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testBoundaryPollNameNotNull() throws Exception {
        Poll poll = getPoll();
        poll.setPollName(null);
        Set<ConstraintViolation<Poll>> violations = validator.validate(poll);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testBoundaryPollNameMinThree() throws Exception {
        Poll poll = getPoll();
        poll.setPollName(randomStringWithSize(2));
        Set<ConstraintViolation<Poll>> violations = validator.validate(poll);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testBoundaryPollNameMaxFifty() throws Exception {
        Poll poll = getPoll();
        poll.setPollName(randomStringWithSize(51));
        Set<ConstraintViolation<Poll>> violations = validator.validate(poll);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testBoundaryPollOptionNameNotBlank() throws Exception {
        PollOption pollOption = getPollOption(1);
        pollOption.setOptionName("");
        Set<ConstraintViolation<PollOption>> violations = validator.validate(pollOption);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testBoundaryPollOptionNameNotNull() throws Exception {
        PollOption pollOption = getPollOption(1);
        pollOption.setOptionName(null);
        Set<ConstraintViolation<PollOption>> violations = validator.validate(pollOption);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testBoundaryPollOptionNameMinThree() throws Exception {
        PollOption pollOption = getPollOption(1);
        pollOption.setOptionName(randomStringWithSize(2));
        Set<ConstraintViolation<PollOption>> violations = validator.validate(pollOption);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testBoundaryPollOptionNameMaxFifty() throws Exception {
        PollOption pollOption = getPollOption(1);
        pollOption.setOptionName(randomStringWithSize(51));
        Set<ConstraintViolation<PollOption>> violations = validator.validate(pollOption);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

}
