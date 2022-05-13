package ru.javawebinar.topjava.web.menu;


import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javawebinar.topjava.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.RestaurantMenuTestData.*;
import static ru.javawebinar.topjava.TestUtil.userHttpBasic;
import static ru.javawebinar.topjava.UserTestData.user1;

class MenuItemControllerTest extends AbstractControllerTest {
    private static final String REST_URL = "/rest/restaurants/";
    private static final String REST_MENU_URL = "/menu-items/";

    @Test
    void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RESTAURANT_ID + REST_MENU_URL + MENU_ID))
                .andExpect(status().isUnauthorized());
    }

   @Test
    void getRestaurantWithMenu() throws Exception {
        restaurantWithMenu.setMenuItems(menuItems);
        perform(MockMvcRequestBuilders.get(REST_URL + RESTAURANT_ID + "/with-menu")
                .with(userHttpBasic(user1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(restaurantWithMenu));
    }
}