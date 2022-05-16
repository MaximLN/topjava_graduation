package ru.javawebinar.topjava.repository.restaurants;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Restaurant;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT r FROM Restaurant r ORDER BY r.description DESC")
    List<Restaurant> getAll();

    @EntityGraph(attributePaths = {"menuItems"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r JOIN r.menuItems m WHERE r.id=?1 AND m.dateTime>=?2 AND m.dateTime <?3")
    Restaurant getWithMenu(int id, LocalDateTime todayDate, LocalDateTime tomorrowDate);

    @EntityGraph(attributePaths = {"menuItems"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r JOIN r.menuItems m WHERE m.dateTime>=?1 AND m.dateTime <?2 ORDER BY r.description DESC")
    List<Restaurant> getAllWithMenu(LocalDateTime todayDate, LocalDateTime tomorrowDate);
}
