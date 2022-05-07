package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Menu;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Menu m WHERE m.id=:id AND m.restaurant.id=:restaurantId")
    int delete(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Query("SELECT m FROM Menu m WHERE m.restaurant.id=:restaurantId ORDER BY m.description DESC")
    List<Menu> getAll(@Param("restaurantId") int userId);

    //
    @Query("SELECT m FROM Menu m JOIN FETCH m.restaurant WHERE m.id = ?1 and m.restaurant.id = ?2")
    Menu getWithRestaurant(int id, int restaurantId);
}