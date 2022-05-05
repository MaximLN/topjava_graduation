package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Menu;
import ru.javawebinar.topjava.model.Restaurant;
import ru.javawebinar.topjava.to.RestaurantTo;

import java.util.List;

import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class RestaurantMenuTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "menu");
    public static MatcherFactory.Matcher<RestaurantTo> TO_MATCHER = MatcherFactory.usingEqualsComparator(RestaurantTo.class);

    public static final MatcherFactory.Matcher<Menu> MENU_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Menu.class);

    public static final int NOT_FOUND = 10;
    public static final int RESTAURANT_ID = START_SEQ + 10;
    public static final int MENU_ID = START_SEQ + 15;

    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT_ID, "restaurant 1");
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT_ID + 1, "restaurant 2");
    public static final Restaurant restaurant3 = new Restaurant(RESTAURANT_ID + 2, "restaurant 3");
    public static final Restaurant restaurant4 = new Restaurant(RESTAURANT_ID + 3, "restaurant 4");
    public static final Restaurant restaurant5 = new Restaurant(RESTAURANT_ID + 4, "restaurant 5");

    public static final Menu menu1 = new Menu(MENU_ID,"meal1 1",200);
    public static final Menu menu2 = new Menu(MENU_ID + 1,"meal1 2",250);

    public static final List<Restaurant> restaurants = List.of(restaurant1, restaurant2, restaurant3, restaurant4, restaurant5);

    public static Restaurant getNew() {
        return new Restaurant(null, "New restaurant");
    }

    public static Restaurant getUpdated() {
        return new Restaurant("Updated restaurant");
    }
}
