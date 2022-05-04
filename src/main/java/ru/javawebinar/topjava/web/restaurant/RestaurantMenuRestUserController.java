package ru.javawebinar.topjava.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javawebinar.topjava.model.Menu;
import ru.javawebinar.topjava.model.Restaurant;
import ru.javawebinar.topjava.service.MenuService;
import ru.javawebinar.topjava.service.RestaurantService;

import java.util.List;

@RestController
@RequestMapping(value = RestaurantMenuRestUserController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantMenuRestUserController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    static final String REST_URL = "/rest/user/restaurant";

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuService menuService;

    @GetMapping
    public List<Restaurant> getAll() {
        return restaurantService.getAll();
    }

    @GetMapping("/{restaurantId}")
    public Restaurant get(@PathVariable int restaurantId) {
        log.info("get restaurant {}", restaurantId);
        return restaurantService.get(restaurantId);
    }

    @GetMapping("/{restaurantId}/menu/{menuId}")
    public Menu getMenu(@PathVariable int restaurantId, @PathVariable int menuId) {
        log.info("get menu {} for restaurant {}", menuId, restaurantId);
        return menuService.get(restaurantId, menuId);
    }

    @GetMapping("/{restaurantId}/with-menu")
    public Restaurant getWithMenu(@PathVariable int restaurantId) {
        return restaurantService.getWithMenu(restaurantId);
    }
}