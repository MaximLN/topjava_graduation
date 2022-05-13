package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javawebinar.topjava.model.Restaurant;
import ru.javawebinar.topjava.repository.RestaurantRepository;

import java.time.LocalDate;
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
        return checkNotFoundWithId(repository.getWithMenu(restaurantId, LocalDate.now().atStartOfDay(),
                LocalDate.now().plusDays(1).atStartOfDay()), restaurantId);
    }

    public List<Restaurant> getAllWithMenu() {
        return repository.getAllWithMenu(LocalDate.now().atStartOfDay(),
                LocalDate.now().plusDays(1).atStartOfDay());
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(repository.save(restaurant), restaurant.id());
    }

    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }
}