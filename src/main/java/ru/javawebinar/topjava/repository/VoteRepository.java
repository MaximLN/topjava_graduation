package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository {

    Vote save(Vote vote, int userId);

    Vote get(int id, int userId);

    List<Vote> getAll(int userId);

    List<Integer> getResultsOfTodayVote(LocalDate todayLocalDate);

    default Vote getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}