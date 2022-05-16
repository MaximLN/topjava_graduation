package ru.javawebinar.topjava.repository.menuItems;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.MenuItem;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMenuItemRepository extends JpaRepository<MenuItem, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM MenuItem m WHERE m.id=:id AND m.restaurant.id=:restaurantId")
    int delete(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Query("SELECT m FROM MenuItem m WHERE m.dateTime = :date AND m.restaurant.id=:restaurantId ORDER BY m.price DESC")
    List<MenuItem> getAll(@Param("restaurantId") int restaurantId, @Param("date") LocalDateTime date);
}