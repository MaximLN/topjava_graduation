package ru.javawebinar.topjava.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.model.Vote;
import ru.javawebinar.topjava.service.VoteService;
import ru.javawebinar.topjava.to.RestaurantTo;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.List;

import static ru.javawebinar.topjava.util.validation.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.validation.ValidationUtil.checkNew;

public abstract class AbstractVoteController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService service;

    public List<RestaurantTo> getTodayResult() {
        log.info("getToday result");
        return service.getTodayResult();
    }

    public Vote get(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("get vote {} for user {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete vote {} for user {}", id, userId);
        service.delete(id, userId);
    }

    public List<Vote> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll for user {}", userId);
        return service.getAll(userId);
    }

    public Vote create(Vote vote, int restaurantId) {
        int userId = SecurityUtil.authUserId();
        log.info("create {} for user {}", vote, userId);
        checkNew(vote);
        return service.create(vote, userId, restaurantId);
    }

    public void update(Vote vote, int restaurantId, int id) {
        int userId = SecurityUtil.authUserId();
        log.info("update {} for user {}", vote, userId);
        assureIdConsistent(vote, id);
        service.update(vote, userId, restaurantId);
    }

}