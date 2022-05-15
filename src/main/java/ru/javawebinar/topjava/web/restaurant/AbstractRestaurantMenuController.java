package ru.javawebinar.topjava.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.repository.menuItems.MenuItemRepository;
import ru.javawebinar.topjava.service.RestaurantService;

public abstract class AbstractRestaurantMenuController {
    static final String REST_URL = "/rest/admin/restaurants";

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected RestaurantService restaurantService;

    @Autowired
    protected MenuItemRepository menuItemRepository;
}
