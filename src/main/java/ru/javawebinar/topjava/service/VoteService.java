package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javawebinar.topjava.model.Vote;
import ru.javawebinar.topjava.repository.VoteRepository;
import ru.javawebinar.topjava.to.RestaurantTo;
import ru.javawebinar.topjava.util.VoteUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.util.validation.ValidationUtil.checkNotFoundWithId;
import static ru.javawebinar.topjava.util.validation.ValidationUtil.checkVotingTime;

@Service
public class VoteService {

    private final VoteRepository repository;

    public VoteService(VoteRepository repository) {
        this.repository = repository;
    }

    public Vote get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public List<Vote> getAll(int userId) {
        return repository.getAll(userId);
    }

    public void update(Vote vote, int userId) {
        Assert.notNull(vote, "vote must not be null");
        checkVotingTime(LocalDateTime.now());
        checkNotFoundWithId(repository.save(vote, userId), vote.id());
    }

    public Vote create(Vote vote, int userId) {
        Assert.notNull(vote, "vote must not be null");
        return repository.save(vote, userId);
    }

    public List<RestaurantTo> getTodayResult() {
        return VoteUtil.getTos(repository.getResultsOfTodayVote(LocalDate.now()));
    }
}