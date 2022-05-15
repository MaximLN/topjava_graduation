package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.MenuItem;
import ru.javawebinar.topjava.repository.MenuItemRepository;

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
    @Transactional
    public MenuItem save(MenuItem menuItem, int restaurantId) {
        if (!menuItem.isNew() && get(menuItem.getId(), restaurantId) == null) {
            return null;
        }
        menuItem.setRestaurant(crudRestaurantRepository.getById(restaurantId));
        return crudMenuItemRepository.save(menuItem);
    }

    @Override
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
    public List<MenuItem> getAll(int restaurantId, LocalDateTime todayDate) {
        return crudMenuItemRepository.getAll(restaurantId, todayDate);
    }
}
