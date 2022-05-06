package ru.javawebinar.topjava.web.meal;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javawebinar.topjava.RestaurantMenuTestData;
import ru.javawebinar.topjava.model.Restaurant;
import ru.javawebinar.topjava.service.RestaurantService;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.RestaurantMenuTestData.RESTAURANT_ID;
import static ru.javawebinar.topjava.RestaurantMenuTestData.RESTAURANT_MATCHER;
import static ru.javawebinar.topjava.TestUtil.userHttpBasic;
import static ru.javawebinar.topjava.UserTestData.admin;
import static ru.javawebinar.topjava.util.exception.ErrorType.VALIDATION_ERROR;

class RestaurantAdminRestControllerTest extends AbstractControllerTest {

    //        private static final String REST_URL = RestaurantMenuUserRestController.REST_URL + '/';
    private static final String REST_URL = "/rest/admin/restaurant/";

    @Autowired
    private RestaurantService restaurantService;

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + RESTAURANT_ID)
                .with(userHttpBasic(admin)))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> restaurantService.get(RESTAURANT_ID));
    }

    @Test
    void deleteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + RestaurantMenuTestData.NOT_FOUND)
                .with(userHttpBasic(admin)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void update() throws Exception {
        Restaurant updated = RestaurantMenuTestData.getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + RESTAURANT_ID).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(admin))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        RESTAURANT_MATCHER.assertMatch(restaurantService.get(RESTAURANT_ID), updated);
    }

    @Test
    void create() throws Exception {
        Restaurant newRestaurant = RestaurantMenuTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(admin))
                .content(JsonUtil.writeValue(newRestaurant)));

        Restaurant created = RESTAURANT_MATCHER.readFromJson(action);
        int newId = created.id();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(restaurantService.get(newId), newRestaurant);
    }


    @Test
    void createInvalid() throws Exception {
        Restaurant invalid = new Restaurant(null, null);
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR));
    }

    @Test
    void updateInvalid() throws Exception {
        Restaurant invalid = new Restaurant(RESTAURANT_ID, null);
        perform(MockMvcRequestBuilders.put(REST_URL + RESTAURANT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR));
    }
}