package ru.javawebinar.topjava.repository.votes;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Vote;
import ru.javawebinar.topjava.repository.users.CrudUserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaVoteRepository implements VoteRepository {

    private final CrudVoteRepository crudVoteRepository;
    private final CrudUserRepository crudUserRepository;

    public DataJpaVoteRepository(CrudVoteRepository crudVoteRepository, CrudUserRepository crudUserRepository) {
        this.crudVoteRepository = crudVoteRepository;
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    @Transactional
    public Vote save(Vote vote, int userId) {
        if (!vote.isNew() && get(vote.getId(), userId) == null) {
            return null;
        }
        vote.setUser(crudUserRepository.getById(userId));
        return crudVoteRepository.save(vote);
    }

    @Override
    //add .orElse(null);
    public Vote get(int id, int userId) {
        return crudVoteRepository.get(id, userId);
    }

    @Override
    public Vote getForDate(LocalDate date, int userId) {
        return crudVoteRepository.getForDate(date.atStartOfDay(), userId);
    }

    @Override
    public List<Vote> getAll(int userId) {
        return crudVoteRepository.getAll(userId);
    }

    @Override
    public List<Integer> getResultsOfTodayVote(LocalDate todayLocalDate) {
        return crudVoteRepository.getWinnerId(PageRequest.of(0, 1),
                LocalDateTime.of(todayLocalDate.getYear(), todayLocalDate.getMonth(), todayLocalDate.getDayOfMonth(),
                        LocalDateTime.MIN.getHour(), LocalDateTime.MIN.getMinute()),
                LocalDateTime.of(todayLocalDate.getYear(), todayLocalDate.getMonth(), todayLocalDate.getDayOfMonth(),
                        LocalDateTime.MAX.getHour(), LocalDateTime.MAX.getMinute()));
    }
}
