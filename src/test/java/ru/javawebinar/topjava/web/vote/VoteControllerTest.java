//package ru.javawebinar.topjava.web.vote;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import ru.javawebinar.topjava.VoteTestData;
//import ru.javawebinar.topjava.model.Vote;
//import ru.javawebinar.topjava.service.VoteService;
//import ru.javawebinar.topjava.web.AbstractControllerTest;
//import ru.javawebinar.topjava.web.json.JsonUtil;
//
//import java.time.LocalDateTime;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static ru.javawebinar.topjava.RestaurantMenuTestData.*;
//import static ru.javawebinar.topjava.TestUtil.userHttpBasic;
//import static ru.javawebinar.topjava.UserTestData.USER1_ID;
//import static ru.javawebinar.topjava.UserTestData.user1;
//import static ru.javawebinar.topjava.VoteTestData.VOTE_ID;
//import static ru.javawebinar.topjava.VoteTestData.VOTE_MATCHER;
//
//class VoteControllerTest extends AbstractControllerTest {
//    private static final String REST_URL = VoteController.REST_URL + '/';
//    private static final String REST_RESTAURANT_URL = "/restaurants/";
//
//    @Autowired
//    private VoteService voteService;
//
//    @Test
//    void get() throws Exception {
//        perform(MockMvcRequestBuilders.get(REST_URL + VOTE_ID)
//                .with(userHttpBasic(user1)))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
//    }
//
//    @Test
//    void getUnauth() throws Exception {
//        perform(MockMvcRequestBuilders.get(REST_URL + "by-user"))
//                .andExpect(status().isUnauthorized());
//    }
//
//    @Test
//    void getNotFound() throws Exception {
//        perform(MockMvcRequestBuilders.get(REST_URL + NOT_FOUND)
//                .with(userHttpBasic(user1)))
//                .andDo(print())
//                .andExpect(status().isUnprocessableEntity());
//    }
//
//    @Test
//    void update() throws Exception {
//        Vote updated = VoteTestData.getUpdatedVote();
//        perform(MockMvcRequestBuilders.put(REST_URL + VOTE_ID + REST_RESTAURANT_URL + (RESTAURANT_ID + 1)).contentType(MediaType.APPLICATION_JSON)
//                .with(userHttpBasic(user1))
//                .content(JsonUtil.writeValue(updated)))
//                .andExpect(status().isNoContent());
//        VOTE_MATCHER.assertMatch(voteService.get(VOTE_ID, (USER1_ID)), updated);
//    }
//
//    @Test
//    void create() throws Exception {
//        Vote newVote = VoteTestData.getNewVote();
//        newVote.setRestaurant(restaurant1);
//        newVote.setDateTime(LocalDateTime.now());
//        newVote.setDateTime(LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(),
//                LocalDateTime.now().getDayOfMonth(), 0, 0));
//        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + "restaurants/" + RESTAURANT_ID)
//                .contentType(MediaType.APPLICATION_JSON)
//                .with(userHttpBasic(user1))
//                .content(JsonUtil.writeValue(newVote)));
//
//        Vote created = VOTE_MATCHER.readFromJson(action);
//        int newId = created.id();
//        newVote.setId(newId);
//        VOTE_MATCHER.assertMatch(created, newVote);
//        VOTE_MATCHER.assertMatch(voteService.get(newId, (USER1_ID)), newVote);
//    }
//}