DELETE
FROM user_roles;
DELETE
FROM meals;
DELETE
FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password, calories_per_day)
VALUES ('Admin', 'admin@gmail.com', '{noop}admin', 1900),
       ('User1', 'user1@yandex.ru', '{noop}password', 2005),
       ('User2', 'user2@yandex.ru', '{noop}password', 2005),
       ('User3', 'user3@yandex.ru', '{noop}password', 2005),
       ('User4', 'user4@yandex.ru', '{noop}password', 2005),
       ('User5', 'user5@yandex.ru', '{noop}password', 2005),
       ('User6', 'user6@yandex.ru', '{noop}password', 2005),
       ('User7', 'user7@yandex.ru', '{noop}password', 2005),
       ('User8', 'user8@yandex.ru', '{noop}password', 2005),
       ('Guest', 'guest@gmail.com', '{noop}guest', 2000);

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100000),
       ('USER', 100001),
       ('USER', 100002),
       ('USER', 100003),
       ('USER', 100004),
       ('USER', 100005),
       ('USER', 100006),
       ('USER', 100007),
       ('USER', 100008);

INSERT INTO restaurant (description)
VALUES ('restaurant 1'),
       ('restaurant 2'),
       ('restaurant 3'),
       ('restaurant 4'),
       ('restaurant 5');

INSERT INTO menu (description, prices, restaurant_id)
VALUES ('meal1 1', 200, 100010),
       ('meal1 2', 250, 100010),
       ('meal1 3', 150, 100014),
       ('meal1 4', 175, 100011),
       ('meal1 5', 300, 100012);

INSERT INTO vote (date_time, restaurant_id, user_id)
VALUES (CURRENT_TIMESTAMP, 100010, 100000),
       (CURRENT_TIMESTAMP, 100010, 100001),
       (CURRENT_TIMESTAMP, 100010, 100002),
       (CURRENT_TIMESTAMP, 100012, 100003),
       (CURRENT_TIMESTAMP, 100011, 100004),
       (CURRENT_TIMESTAMP, 100014, 100005),
       (CURRENT_TIMESTAMP, 100014, 100006),
       (CURRENT_TIMESTAMP, 100010, 100007);


INSERT INTO meals (date_time, description, calories, user_id)
VALUES ('2020-01-30 10:00:00', 'Завтрак', 500, 100000),
       ('2020-01-30 13:00:00', 'Обед', 1000, 100000),
       ('2020-01-30 20:00:00', 'Ужин', 500, 100000),
       ('2020-01-31 0:00:00', 'Еда на граничное значение', 100, 100000),
       ('2020-01-31 10:00:00', 'Завтрак', 500, 100000),
       ('2020-01-31 13:00:00', 'Обед', 1000, 100000),
       ('2020-01-31 20:00:00', 'Ужин', 510, 100000),
       ('2020-01-31 14:00:00', 'Админ ланч', 510, 100001),
       ('2020-01-31 21:00:00', 'Админ ужин', 1500, 100001);