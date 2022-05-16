package ru.javawebinar.topjava.repository.menuItems;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.MenuItem;
import ru.javawebinar.topjava.repository.restaurants.CrudRestaurantRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaMenuItemRepository implements MenuItemRepository {

    private final CrudMenuItemRepository crudMenuItemRepository;
    private final CrudRestaurantRepository crudRestaurantRepository;

    public DataJpaMenuItemRepository(CrudMenuItemRepository crudMenuItemRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudMenuItemRepository = crudMenuItemRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Override
    @CacheEvict(value = "restaurants-with-menu", allEntries = true)
    @Transactional
    public MenuItem save(MenuItem menuItem, int restaurantId) {
        if (!menuItem.isNew() && get(menuItem.getId(), restaurantId) == null) {
            return null;
        }
        menuItem.setRestaurant(crudRestaurantRepository.getById(restaurantId));
        return crudMenuItemRepository.save(menuItem);
    }

    @Override
    @CacheEvict(value = "restaurants-with-menu", allEntries = true)
    public boolean delete(int id, int restaurantId) {
        return crudMenuItemRepository.delete(id, restaurantId) != 0;
    }

    @Override
    public MenuItem get(int id, int restaurantId) {
        return crudMenuItemRepository.findById(id)
                .filter(menu -> menu.getRestaurant().getId() == restaurantId)
                .orElse(null);
    }

    @Override
    public List<MenuItem> getAll(int restaurantId, LocalDateTime date) {
        return crudMenuItemRepository.getAll(restaurantId, date);
    }
}
