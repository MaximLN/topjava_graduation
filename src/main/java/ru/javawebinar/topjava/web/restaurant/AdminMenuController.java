package ru.javawebinar.topjava.web.restaurant;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjava.View;
import ru.javawebinar.topjava.model.MenuItem;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static ru.javawebinar.topjava.util.validation.ValidationUtil.*;

@RestController
@RequestMapping(value = AbstractRestaurantMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminMenuController extends AbstractRestaurantMenuController {

    @GetMapping("/{restaurantId}/menu-items/{menuId}")
    public MenuItem getMenu(@PathVariable int restaurantId, @PathVariable int menuId) {
        log.info("get menu {} for restaurant {}", menuId, restaurantId);
        return checkNotFoundWithId(menuItemRepository.get(menuId, restaurantId), menuId);
    }

    @GetMapping("/{restaurantId}/menu-items/for-date/{date}")
    public List<MenuItem> getAllMenuForDate(@PathVariable int restaurantId, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        log.info("get menu for date {} for restaurant {}", date, restaurantId);
        return menuItemRepository.getAll(restaurantId, date.atStartOfDay());
    }

    @DeleteMapping("/{restaurantId}/menu-items/{menuId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restaurantId, @PathVariable int menuId) {
        log.info("delete restaurant {}", restaurantId);
        checkNotFoundWithId(menuItemRepository.delete(menuId, restaurantId), menuId);
    }

    @PostMapping(value = "/{restaurantId}/menu-items/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MenuItem> createWithLocation(@Validated(View.Web.class) @RequestBody MenuItem menuItem, @PathVariable int restaurantId) {
        Assert.notNull(menuItem, "menu must not be null");
        menuItem.setDateTime(menuItem.getDateTime().toLocalDate().atStartOfDay());
        checkNew(menuItem);
        MenuItem created = menuItemRepository.save(menuItem, restaurantId);
        log.info("create menu {}", menuItem);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{restaurantId}/menu-items/" + created.getId())
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{restaurantId}/menu-items/{menuId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Validated(View.Web.class) @RequestBody MenuItem menuItem,
                       @PathVariable int restaurantId,
                       @PathVariable int menuId) {
        assureIdConsistent(menuItem, menuId);
        log.info("update menu {}", menuItem);
        Assert.notNull(menuItem, "menu must not be null");
        checkNotFoundWithId(menuItemRepository.save(menuItem, restaurantId), menuItem.id());
    }
}