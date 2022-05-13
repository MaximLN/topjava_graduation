package ru.javawebinar.topjava.web.menu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javawebinar.topjava.RestaurantMenuTestData;
import ru.javawebinar.topjava.model.MenuItem;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;
import ru.javawebinar.topjava.web.restaurant.RestaurantMenuController;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.RestaurantMenuTestData.*;
import static ru.javawebinar.topjava.TestUtil.userHttpBasic;
import static ru.javawebinar.topjava.UserTestData.admin;
import static ru.javawebinar.topjava.util.exception.ErrorType.VALIDATION_ERROR;

class AdminMenuItemControllerTest extends AbstractControllerTest {
    private static final String REST_URL = "/rest/admin/restaurants/";
    private static final String REST_MENU_URL = "/menu/";

    @Autowired
    private RestaurantMenuController restaurantMenuController;

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + RESTAURANT_ID + REST_MENU_URL + MENU_ID)
                .with(userHttpBasic(admin)))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> restaurantMenuController.getMenu(RESTAURANT_ID, MENU_ID));
    }

    @Test
    void deleteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + RESTAURANT_ID + REST_MENU_URL + NOT_FOUND)
                .with(userHttpBasic(admin)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void update() throws Exception {
        MenuItem updated = RestaurantMenuTestData.getUpdatedMenu();
        perform(MockMvcRequestBuilders.put(REST_URL + RESTAURANT_ID + REST_MENU_URL + MENU_ID).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(admin))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        MENU_MATCHER.assertMatch(restaurantMenuController.getMenu(RESTAURANT_ID, MENU_ID), updated);
    }

    @Test
    void create() throws Exception {
        MenuItem newMenuItem = RestaurantMenuTestData.getNewMenu();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + RESTAURANT_ID + REST_MENU_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(admin))
                .content(JsonUtil.writeValue(newMenuItem)));

        MenuItem created = MENU_MATCHER.readFromJson(action);
        int newId = created.id();
        newMenuItem.setId(newId);
        MENU_MATCHER.assertMatch(created, newMenuItem);
        MENU_MATCHER.assertMatch(restaurantMenuController.getMenu(RESTAURANT_ID, newId), newMenuItem);
    }


    @Test
    void createInvalid() throws Exception {
        MenuItem invalid = new MenuItem(null, null, null, 0);
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
        MenuItem invalid = new MenuItem(MENU_ID, null, "", 0);
        perform(MockMvcRequestBuilders.put(REST_URL + RESTAURANT_ID + REST_MENU_URL + MENU_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR));
    }
}