package ru.javawebinar.topjava.repository.votes;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Vote;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Vote v WHERE v.id=:id AND v.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT v FROM Vote v JOIN FETCH v.restaurant r WHERE v.id=:id AND v.user.id=:userId")
    Vote get(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT v FROM Vote v JOIN FETCH v.restaurant r WHERE v.dateTime = :date AND v.user.id=:userId")
    Vote getForDate(@Param("date") LocalDateTime date, @Param("userId") int userId);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId ORDER BY v.dateTime DESC")
    List<Vote> getAll(@Param("userId") int userId);

    @Query("SELECT v.restaurant.id FROM Vote AS v WHERE v.dateTime >= :startDate AND v.dateTime < :endDate " +
            "GROUP BY v.restaurant.id ORDER BY COUNT(v.restaurant.id) DESC")
    List<Integer> getWinnerId(Pageable pageable, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}