package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Menu;

import java.util.List;

public interface MenuRepository {
    // null if updated meal does not belong to userId
    Menu save(Menu menu, int restaurantId);

    // false if meal does not belong to userId
    boolean delete(int id, int restaurantId);

    // null if meal does not belong to userId
    Menu get(int id, int restaurantId);

    // ORDERED dateTime desc
    List<Menu> getAll(int restaurantId);

//    default Meal getWithUser(int id, int userId) {
//        throw new UnsupportedOperationException();
//    }
}
