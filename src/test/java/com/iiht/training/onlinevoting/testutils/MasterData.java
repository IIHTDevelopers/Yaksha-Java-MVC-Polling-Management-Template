package com.iiht.training.onlinevoting.testutils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.iiht.training.onlinevoting.entity.Poll;
import com.iiht.training.onlinevoting.entity.PollOption;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MasterData {

    public static Poll getPoll() {
        Poll poll = new Poll();
        poll.setPollId(1L);
        poll.setPollName(randomStringWithSize(10));
        return poll;
    }

    public static List<Poll> getPollList(int size) {
        Long id = 0L;
        List<Poll> polls = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Poll poll = new Poll();
            poll.setPollId(++id);
            poll.setPollName(randomStringWithSize(10));
            polls.add(poll);
        }
        return polls;
    }

    public static PollOption getPollOption(int pollId) {
        PollOption pollOption = new PollOption();
        pollOption.setOptionId(1L);
        pollOption.setPollId(pollId);
        pollOption.setOptionName(randomStringWithSize(10));
        return pollOption;
    }

    public static List<PollOption> getPollOptionList(int pollId) {
        Long id = 0L;
        List<PollOption> pollOptions = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            PollOption pollOption = new PollOption();
            pollOption.setOptionId(++id);
            pollOption.setPollId(pollId);
            pollOption.setOptionName(randomStringWithSize(10));
            pollOptions.add(pollOption);
        }
        return pollOptions;
    }

    public static String randomStringWithSize(int size) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
        String s = "";
        for (int i = 0; i < size; i++) {
            s = s + (String.valueOf(alphabet.charAt(rnd.nextInt(alphabet.length()))));
        }
        return s;
    }

    public static boolean randomBoolean() {
        String alphabet = "1234567890";
        Random rnd = new Random();
        return rnd.nextInt(alphabet.length()) % 2 == 0;
    }

    public static Integer randomInteger() {
        String alphabet = "1234567890";
        Random rnd = new Random();
        return rnd.nextInt(alphabet.length());
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            final String jsonContent = mapper.writeValueAsString(obj);

            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
