package ru.javawebinar.topjava.web.restaurant;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javawebinar.topjava.model.MenuItem;
import ru.javawebinar.topjava.model.Restaurant;

import java.util.List;

import static ru.javawebinar.topjava.util.validation.ValidationUtil.checkNotFoundWithId;

@RestController
@RequestMapping(value = RestaurantMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantMenuController extends AbstractRestaurantMenuController {
    static final String REST_URL = "/rest/restaurants";

    @GetMapping
    public List<Restaurant> getAll() {
        return restaurantService.getAll();
    }

    @GetMapping("/with-menu")
    public List<Restaurant> getAllWithMenu() {
        return restaurantService.getAllWithMenu();
    }

    @GetMapping("/{restaurantId}")
    public Restaurant get(@PathVariable int restaurantId) {
        log.info("get restaurant {}", restaurantId);
        return restaurantService.get(restaurantId);
    }

    @GetMapping("/{restaurantId}/with-menu")
    public Restaurant getWithMenu(@PathVariable int restaurantId) {
        return restaurantService.getWithMenu(restaurantId);
    }
}