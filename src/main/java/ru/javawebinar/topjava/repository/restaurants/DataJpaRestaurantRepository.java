package ru.javawebinar.topjava.repository.restaurants;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Restaurant;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaRestaurantRepository implements RestaurantRepository {

    private final CrudRestaurantRepository crudRestaurantRepository;

    public DataJpaRestaurantRepository(CrudRestaurantRepository crudRestaurantRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return crudRestaurantRepository.save(restaurant);
    }

    @CacheEvict(value = "winner", allEntries = true)
    @Override
    public boolean delete(int id) {
        return crudRestaurantRepository.delete(id) != 0;
    }

    @Override
    public Restaurant get(int id) {
        return crudRestaurantRepository.findById(id)
                .orElse(null);
    }

    @Override
    public List<Restaurant> getAll() {
        return crudRestaurantRepository.getAll();
    }

    @Override
    public Restaurant getWithMenu(int id, LocalDateTime todayDate, LocalDateTime tomorrowDate) {
        return crudRestaurantRepository.getWithMenu(id, todayDate, tomorrowDate);
    }
    @Override
    public List<Restaurant> getAllWithMenu(LocalDateTime todayDate, LocalDateTime tomorrowDate) {
        return crudRestaurantRepository.getAllWithMenu(todayDate, tomorrowDate);
    }
}
