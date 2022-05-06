package ru.javawebinar.topjava.web.json;

import org.junit.jupiter.api.Test;
import ru.javawebinar.topjava.model.Restaurant;
import ru.javawebinar.topjava.model.User;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.javawebinar.topjava.RestaurantMenuTestData.*;
import static ru.javawebinar.topjava.UserTestData.jsonWithPassword;
import static ru.javawebinar.topjava.UserTestData.user;

class JsonUtilTest {

    @Test
    void readWriteValue() {
        String json = JsonUtil.writeValue(restaurant1);
        System.out.println(json);
        Restaurant restaurant = JsonUtil.readValue(json, Restaurant.class);
        RESTAURANT_MATCHER.assertMatch(restaurant, restaurant1);
    }

    @Test
    void readWriteValues() {
        String json = JsonUtil.writeValue(restaurants);
        System.out.println(json);
        List<Restaurant> actual = JsonUtil.readValues(json, Restaurant.class);
        RESTAURANT_MATCHER.assertMatch(actual, restaurants);
    }

    @Test
    void writeOnlyAccess() {
        String json = JsonUtil.writeValue(user);
        System.out.println(json);
        assertThat(json, not(containsString("password")));
        String jsonWithPass = jsonWithPassword(user, "newPass");
        System.out.println(jsonWithPass);
        User user = JsonUtil.readValue(jsonWithPass, User.class);
        assertEquals(user.getPassword(), "newPass");
    }
}