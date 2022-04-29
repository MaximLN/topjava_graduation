package ru.javawebinar.topjava.web.meal;

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
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Restaurant;
import ru.javawebinar.topjava.model.Vote;
import ru.javawebinar.topjava.repository.RestaurantRepository;
import ru.javawebinar.topjava.repository.VoteRepository;
import ru.javawebinar.topjava.to.RestaurantTo;
import ru.javawebinar.topjava.util.VoteUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    static final String REST_URL = "/rest/profile/vote";

    @Autowired
    private VoteRepository repository;

    @Autowired
    private RestaurantRepository restaurantRepository;


    @GetMapping("/{id}")
    public Vote get(@PathVariable int id) {
        int userId = SecurityUtil.authUserId();
        return repository.get(id, userId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        int userId = SecurityUtil.authUserId();
        repository.delete(id, userId);
    }

    @GetMapping("/by-user")
    public List<Vote> getAllVoteByUser() {
        int userId = SecurityUtil.authUserId();
        log.info("----------------- " + userId);
        return repository.getAll(userId);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Validated(View.Web.class) @RequestBody Vote vote, @PathVariable int id) {
        int userId = SecurityUtil.authUserId();
        repository.save(vote, userId, 100012);
//        super.update(meal, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createWithLocation(@Validated(View.Web.class) @RequestBody Vote vote) {
//        Meal created = super.create(meal);
        int userId = SecurityUtil.authUserId();
        Vote created = repository.save(vote, userId, 100012);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping("/today")
    public List<Vote> getBetween(){
        LocalDate todayLocalDate = LocalDate.now();
        return repository.getResultsOfTodayVote(todayLocalDate);
    }
    @GetMapping
    public List<RestaurantTo> getAll() {
        return VoteUtil.getTos(repository.getResultsOfTodayVote(LocalDate.now()));
    }
}