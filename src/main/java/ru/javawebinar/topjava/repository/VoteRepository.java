package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface VoteRepository {
    // null if updated vote does not belong to userId
    Vote save(Vote vote, int userId, int restaurantId);

    // false if vote does not belong to userId
    boolean delete(int id, int userId);

    // null if vote does not belong to userId
    Vote get(int id, int userId);

    // ORDERED dateTime desc
    List<Vote> getAll(int userId);

    List<Vote> getResultsOfTodayVote(LocalDate todayLocalDate);

    default Vote getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}