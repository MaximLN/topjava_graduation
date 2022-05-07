package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    // null if updated restaurant does not belong to userId
    Restaurant save(Restaurant restaurant);

    // false if restaurant does not belong to userId
    boolean delete(int id);

    // null if restaurant does not belong to userId
    Restaurant get(int id);

    List<Restaurant> getAll();

    Restaurant getWithMenu(int id);
}
