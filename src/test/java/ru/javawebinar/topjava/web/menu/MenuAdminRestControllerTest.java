package ru.javawebinar.topjava.web.menu;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javawebinar.topjava.RestaurantMenuTestData;
import ru.javawebinar.topjava.model.Menu;
import ru.javawebinar.topjava.service.MenuService;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.RestaurantMenuTestData.*;
import static ru.javawebinar.topjava.TestUtil.userHttpBasic;
import static ru.javawebinar.topjava.UserTestData.admin;
import static ru.javawebinar.topjava.util.exception.ErrorType.VALIDATION_ERROR;

class MenuAdminRestControllerTest extends AbstractControllerTest {

    //        private static final String REST_URL = RestaurantMenuUserRestController.REST_URL + '/';
    private static final String REST_URL = "/rest/admin/restaurant/";
    private static final String REST_MENU_URL = "/menu/";

    @Autowired
    private MenuService menuService;

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + RESTAURANT_ID + REST_MENU_URL + MENU_ID)
                .with(userHttpBasic(admin)))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> menuService.get(MENU_ID, RESTAURANT_ID));
    }

    @Test
    void deleteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + RESTAURANT_ID + REST_MENU_URL + NOT_FOUND)
                .with(userHttpBasic(admin)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void update() throws Exception {
        Menu updated = RestaurantMenuTestData.getUpdatedMenu();
        perform(MockMvcRequestBuilders.put(REST_URL + RESTAURANT_ID + REST_MENU_URL + MENU_ID).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(admin))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        MENU_MATCHER.assertMatch(menuService.get(MENU_ID, RESTAURANT_ID), updated);
    }

    @Test
    void create() throws Exception {
        Menu newMenu = RestaurantMenuTestData.getNewMenu();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + RESTAURANT_ID + REST_MENU_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(admin))
                .content(JsonUtil.writeValue(newMenu)));

        Menu created = MENU_MATCHER.readFromJson(action);
        int newId = created.id();
        newMenu.setId(newId);
        MENU_MATCHER.assertMatch(created, newMenu);
        MENU_MATCHER.assertMatch(menuService.get(newId, RESTAURANT_ID), newMenu);
    }


    @Test
    void createInvalid() throws Exception {
        Menu invalid = new Menu(null, null, null, 0);
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
        Menu invalid = new Menu(MENU_ID, null, "", 0);
        perform(MockMvcRequestBuilders.put(REST_URL + RESTAURANT_ID + REST_MENU_URL + MENU_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR));
    }
}