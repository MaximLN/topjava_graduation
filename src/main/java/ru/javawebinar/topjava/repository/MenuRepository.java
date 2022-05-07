package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Menu;

import java.util.List;

public interface MenuRepository {
    // null if updated menu does not belong to userId
    Menu save(Menu menu, int restaurantId);

    // false if menu does not belong to userId
    boolean delete(int id, int restaurantId);

    // null if menu does not belong to userId
    Menu get(int id, int restaurantId);

    // ORDERED dateTime desc
    List<Menu> getAll(int restaurantId);
}
