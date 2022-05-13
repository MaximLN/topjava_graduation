package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.MenuItem;
import ru.javawebinar.topjava.model.Restaurant;
import ru.javawebinar.topjava.to.RestaurantTo;

import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class RestaurantMenuTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "menuItem");
    public static MatcherFactory.Matcher<RestaurantTo> TO_MATCHER = MatcherFactory.usingEqualsComparator(RestaurantTo.class);

    public static final MatcherFactory.Matcher<MenuItem> MENU_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(MenuItem.class, "restaurant");
    public static final MatcherFactory.Matcher<MenuItem> MENU_GET_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(MenuItem.class, "restaurant", "dateTime");

    public static final int NOT_FOUND = 10;
    public static final int RESTAURANT_ID = START_SEQ + 10;
    public static final int MENU_ID = START_SEQ + 15;

    public static LocalDateTime dayWhenMenuWasCreated = LocalDateTime.of(LocalDateTime.now().getYear(),
            LocalDateTime.now().getMonth(), LocalDateTime.now().getDayOfMonth(), 0, 0);

    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT_ID, "restaurant 1");
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT_ID + 1, "restaurant 2");
    public static final Restaurant restaurant3 = new Restaurant(RESTAURANT_ID + 2, "restaurant 3");
    public static final Restaurant restaurant4 = new Restaurant(RESTAURANT_ID + 3, "restaurant 4");
    public static final Restaurant restaurant5 = new Restaurant(RESTAURANT_ID + 4, "restaurant 5");

    public static final MenuItem menuItem1 = new MenuItem(MENU_ID, dayWhenMenuWasCreated, "menu 1", 200);
    public static final MenuItem menuItem2 = new MenuItem(MENU_ID + 1, dayWhenMenuWasCreated, "menu 2", 250);

    public static final Restaurant restaurantWithMenu = new Restaurant(RESTAURANT_ID, "restaurant 1");

    public static final List<Restaurant> restaurants = List.of(restaurant1, restaurant2, restaurant3, restaurant4, restaurant5);
    public static final List<MenuItem> menuItems = List.of(menuItem1, menuItem2);

    public static Restaurant getNewRestaurant() {
        return new Restaurant(null, "New restaurant");
    }

    public static Restaurant getUpdatedRestaurant() {
        return new Restaurant(RESTAURANT_ID, "Updated restaurant");
    }

    public static MenuItem getNewMenu() {
        return new MenuItem(null, dayWhenMenuWasCreated, "New menu", 345);
    }

    public static MenuItem getUpdatedMenu() {
        return new MenuItem(MENU_ID, dayWhenMenuWasCreated, "Updated menu", 545);
    }
}
