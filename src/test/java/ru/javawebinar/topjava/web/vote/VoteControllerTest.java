package ru.javawebinar.topjava.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javawebinar.topjava.RestaurantMenuTestData;
import ru.javawebinar.topjava.model.Vote;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.RestaurantMenuTestData.RESTAURANT_MATCHER;
import static ru.javawebinar.topjava.RestaurantMenuTestData.restaurantWithMenu;
import static ru.javawebinar.topjava.TestUtil.userHttpBasic;
import static ru.javawebinar.topjava.UserTestData.user1;
import static ru.javawebinar.topjava.VoteTestData.VOTE_ID;
import static ru.javawebinar.topjava.util.exception.ErrorType.VALIDATION_ERROR;

class VoteControllerTest extends AbstractControllerTest {
    private static final String REST_URL = VoteController.REST_URL + '/';

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + VOTE_ID)
                .with(userHttpBasic(user1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "by-user"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RestaurantMenuTestData.NOT_FOUND)
                .with(userHttpBasic(user1)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void getWinner() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(user1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(restaurantWithMenu));
    }

    @Test
    void getNotFoundForDate() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "for-date" + "/" + LocalDate.now().plusDays(1))
                .with(userHttpBasic(user1)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void createInvalid() throws Exception {
        Vote invalid = new Vote(null, null);
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(user1)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR));
    }

    @Test
    void updateInvalid() throws Exception {
        Vote invalid = new Vote(VOTE_ID, null);
        perform(MockMvcRequestBuilders.put(REST_URL + "/" + VOTE_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(user1)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR));
    }
}