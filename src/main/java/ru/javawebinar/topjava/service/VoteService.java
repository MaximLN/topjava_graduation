package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javawebinar.topjava.model.Restaurant;
import ru.javawebinar.topjava.model.Vote;
import ru.javawebinar.topjava.repository.restaurants.RestaurantRepository;
import ru.javawebinar.topjava.repository.votes.VoteRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.util.validation.ValidationUtil.checkNotFoundWithId;
import static ru.javawebinar.topjava.util.validation.ValidationUtil.checkVotingTime;

@Service
public class VoteService {

    private final VoteRepository repository;
    private final RestaurantRepository restaurantRepository;

    public VoteService(VoteRepository repository, RestaurantRepository restaurantRepository) {
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
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

    public Restaurant getWinner() {
        return restaurantRepository.get(repository.getResultsOfTodayVote(LocalDate.now()).get(0));
    }
}