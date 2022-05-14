package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Vote;
import ru.javawebinar.topjava.repository.VoteRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaVoteRepository implements VoteRepository {

    private final CrudVoteRepository crudVoteRepository;
    private final CrudUserRepository crudUserRepository;
    private final CrudRestaurantRepository crudRestaurantRepository;

    public DataJpaVoteRepository(CrudVoteRepository crudVoteRepository, CrudUserRepository crudUserRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudVoteRepository = crudVoteRepository;
        this.crudUserRepository = crudUserRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Override
    @Transactional
    public Vote save(Vote vote, int userId) {
        if (!vote.isNew() && get(vote.getId(), userId) == null) {
            return null;
        }
        vote.setUser(crudUserRepository.getOne(userId));
        return crudVoteRepository.save(vote);
    }

    @Override
    //add .orElse(null);
    public Vote get(int id, int userId) {
        return crudVoteRepository.get(id,userId);
    }

    @Override
    public List<Vote> getAll(int userId) {
        return crudVoteRepository.getAll(userId);
    }

    @Override
    public List<Vote> getResultsOfTodayVote(LocalDate todayLocalDate) {
        return crudVoteRepository.getAllBetweenHalfOpen(
                LocalDateTime.of(todayLocalDate.getYear(),todayLocalDate.getMonth(),todayLocalDate.getDayOfMonth(),
                        LocalDateTime.MIN.getHour(), LocalDateTime.MIN.getMinute()),
                LocalDateTime.of(todayLocalDate.getYear(),todayLocalDate.getMonth(),todayLocalDate.getDayOfMonth(),
                        LocalDateTime.MAX.getHour(), LocalDateTime.MAX.getMinute()));
    }

}
