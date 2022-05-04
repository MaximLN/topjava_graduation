package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Restaurant;
import ru.javawebinar.topjava.repository.RestaurantRepository;

import java.util.List;

import static ru.javawebinar.topjava.util.validation.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {

    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant get(int restaurantId) {
        return checkNotFoundWithId(repository.get(restaurantId), restaurantId);
    }

    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    public Restaurant getWithMenu(int restaurantId) {
        return checkNotFoundWithId(repository.getWithMenu(restaurantId), restaurantId);
    }
}