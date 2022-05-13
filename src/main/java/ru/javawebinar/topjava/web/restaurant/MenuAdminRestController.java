package ru.javawebinar.topjava.web.restaurant;

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

import static ru.javawebinar.topjava.util.validation.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.validation.ValidationUtil.checkNotFoundWithId;

@RestController
@RequestMapping(value = AbstractRestaurantMenuRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuAdminRestController extends AbstractRestaurantMenuRestController {

    @DeleteMapping("/{restaurantId}/menu/{menuId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restaurantId, @PathVariable int menuId) {
        log.info("delete restaurant {}", restaurantId);
        checkNotFoundWithId(menuItemRepository.delete(menuId, restaurantId), menuId);
    }

    @PostMapping(value = "/{restaurantId}/menu/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MenuItem> createWithLocation(@Validated(View.Web.class) @RequestBody MenuItem menuItem, @PathVariable int restaurantId) {
        Assert.notNull(menuItem, "menu must not be null");
        MenuItem created = menuItemRepository.save(menuItem, restaurantId);
        log.info("create menu {}", menuItem);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{restaurantId}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{restaurantId}/menu/{menuId}", consumes = MediaType.APPLICATION_JSON_VALUE)
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