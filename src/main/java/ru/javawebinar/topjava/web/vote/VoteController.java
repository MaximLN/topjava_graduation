package ru.javawebinar.topjava.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjava.View;
import ru.javawebinar.topjava.model.Vote;
import ru.javawebinar.topjava.service.VoteService;
import ru.javawebinar.topjava.to.RestaurantTo;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.net.URI;
import java.util.List;

import static ru.javawebinar.topjava.util.validation.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    static final String REST_URL = "/rest/votes";

    @Autowired
    private VoteService service;

    @GetMapping
    public List<RestaurantTo> getTodayResult() {
        log.info("getToday result");
        return service.getTodayResult();
    }

    @GetMapping("/{id}")
    public Vote get(@PathVariable int id) {
        int userId = SecurityUtil.authUserId();
        log.info("get vote {} for user {}", id, userId);
        return service.get(id, userId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete vote {} for user {}", id, userId);
        service.delete(id, userId);
    }

    @GetMapping("/by-user")
    public List<Vote> getAllVoteByUser() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll for user {}", userId);
        return service.getAll(userId);
    }

    @PutMapping(value = "/{id}/restaurants/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Validated(View.Web.class) @RequestBody Vote vote, @PathVariable int restaurantId, @PathVariable int id) {
        int userId = SecurityUtil.authUserId();
        log.info("update {} for user {}", vote, userId);
        assureIdConsistent(vote, id);
        service.update(vote, userId, restaurantId);
    }

    @PostMapping(value = "/restaurants/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createWithLocation(@Validated(View.Web.class) @RequestBody Vote vote, @PathVariable int restaurantId) {
        checkNew(vote);
        int userId = SecurityUtil.authUserId();
        Vote created = service.create(vote, userId, restaurantId);
        log.info("create {} for user {}", vote, userId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}