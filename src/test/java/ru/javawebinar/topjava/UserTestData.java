package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.util.Collections;
import java.util.Date;

import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static final MatcherFactory.Matcher<User> USER_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(User.class, "registered", "password");

    public static final int ADMIN_ID = START_SEQ;
    public static final int USER1_ID = START_SEQ + 1;
    public static final int GUEST_ID = START_SEQ + 9;
    public static final int NOT_FOUND = 10;

    public static final User user1 = new User(USER1_ID, "User1", "user1@yandex.ru", "password", Role.USER);
    public static final User user2 = new User(USER1_ID + 1, "User2", "user2@yandex.ru", "password", Role.USER);
    public static final User user3 = new User(USER1_ID + 2, "User3", "user3@yandex.ru", "password", Role.USER);
    public static final User user4 = new User(USER1_ID + 3, "User4", "user4@yandex.ru", "password", Role.USER);
    public static final User user5 = new User(USER1_ID + 4, "User5", "user5@yandex.ru", "password", Role.USER);
    public static final User user6 = new User(USER1_ID + 5, "User6", "user6@yandex.ru", "password", Role.USER);
    public static final User user7 = new User(USER1_ID + 6, "User7", "user7@yandex.ru", "password", Role.USER);
    public static final User user8 = new User(USER1_ID + 7, "User8", "user8@yandex.ru", "password", Role.USER);
    public static final User admin = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ADMIN, Role.USER);
    public static final User guest = new User(GUEST_ID, "Guest", "guest@gmail.com", "guest");

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", false, new Date(), Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        User updated = new User(user1);

// In case of update with user.id=null in body needs workaround
// ValidationUtil.assureIdConsistent called after validation
//      updated.setEmail("update@gmail.com");
        updated.setName("UpdatedName");
        updated.setPassword("newPass");
        updated.setEnabled(false);
        updated.setRoles(Collections.singletonList(Role.ADMIN));
        return updated;
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }
}
