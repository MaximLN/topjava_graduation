package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.MenuItem;

import java.time.LocalDateTime;
import java.util.List;

public interface MenuItemRepository {
    // null if updated menu does not belong to userId
    MenuItem save(MenuItem menuItem, int restaurantId);

    // false if menu does not belong to userId
    boolean delete(int id, int restaurantId);

    // null if menu does not belong to userId
    MenuItem get(int id, int restaurantId);

    // FILTERED dateTime desc
    List<MenuItem> getAll(int restaurantId, LocalDateTime todayDate);
}
