package ru.javawebinar.topjava.web.restaurant;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjava.View;
import ru.javawebinar.topjava.model.Menu;
import ru.javawebinar.topjava.model.Restaurant;

import java.net.URI;

import static ru.javawebinar.topjava.util.validation.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = RestaurantMenuAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantMenuAdminRestController extends AbstractRestaurantMenuRestController {
    static final String REST_URL = "/rest/admin/restaurant";

    @DeleteMapping("/{restaurantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restaurantId) {
        restaurantService.delete(restaurantId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@Validated(View.Web.class) @RequestBody Restaurant restaurant) {
        Restaurant created = restaurantService.create(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{restaurantId}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Validated(View.Web.class) @RequestBody Restaurant restaurant, @PathVariable int restaurantId) {
        assureIdConsistent(restaurant, restaurantId);
        restaurantService.update(restaurant);
    }

    @DeleteMapping("/{restaurantId}/menu/{menuId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restaurantId, @PathVariable int menuId) {
        log.info("delete restaurant {}", restaurantId);
        menuService.delete(menuId, restaurantId);
    }

    @PostMapping(value = "/{restaurantId}/menu/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createWithLocation(@Validated(View.Web.class) @RequestBody Menu menu, @PathVariable int restaurantId) {
        Menu created = menuService.create(menu, restaurantId);
        log.info("create menu {}", menu);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{restaurantId}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{restaurantId}/menu/{menuId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Validated(View.Web.class) @RequestBody Menu menu,
                       @PathVariable int restaurantId,
                       @PathVariable int menuId) {
        assureIdConsistent(menu, menuId);
        log.info("update menu {}", menu);
        menuService.update(menu, restaurantId);
    }
}