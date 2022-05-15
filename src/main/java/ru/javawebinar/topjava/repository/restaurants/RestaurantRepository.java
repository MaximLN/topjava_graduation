package ru.javawebinar.topjava.repository.restaurants;

import ru.javawebinar.topjava.model.Restaurant;

import java.time.LocalDateTime;
import java.util.List;

public interface RestaurantRepository {

    Restaurant save(Restaurant restaurant);

    boolean delete(int id);

    Restaurant get(int id);

    List<Restaurant> getAll();

    Restaurant getWithMenu(int id, LocalDateTime todayDate, LocalDateTime tomorrowDate);

    List<Restaurant> getAllWithMenu(LocalDateTime todayDate, LocalDateTime tomorrowDate);
}
