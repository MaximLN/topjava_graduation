DELETE
FROM user_roles;
DELETE
FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('User1', 'user1@yandex.ru', '{noop}password'),
       ('User2', 'user2@yandex.ru', '{noop}password'),
       ('User3', 'user3@yandex.ru', '{noop}password'),
       ('User4', 'user4@yandex.ru', '{noop}password'),
       ('User5', 'user5@yandex.ru', '{noop}password'),
       ('User6', 'user6@yandex.ru', '{noop}password'),
       ('User7', 'user7@yandex.ru', '{noop}password'),
       ('User8', 'user8@yandex.ru', '{noop}password'),
       ('Guest', 'guest@gmail.com', '{noop}guest');

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

INSERT INTO menu (date_time, description, prices, restaurant_id)
VALUES (CURRENT_TIMESTAMP,'menu 1', 200, 100010),
       (CURRENT_TIMESTAMP,'menu 2', 250, 100010),
       (CURRENT_TIMESTAMP,'menu 3', 150, 100014),
       (CURRENT_TIMESTAMP,'menu 4', 175, 100011),
       (CURRENT_TIMESTAMP,'menu 5', 300, 100012);

INSERT INTO vote (date_time, restaurant_id, user_id)
VALUES (CURRENT_TIMESTAMP, 100010, 100000),
       (CURRENT_TIMESTAMP, 100010, 100001),
       (CURRENT_TIMESTAMP, 100010, 100002),
       (CURRENT_TIMESTAMP, 100012, 100003),
       (CURRENT_TIMESTAMP, 100011, 100004),
       (CURRENT_TIMESTAMP, 100014, 100005),
       (CURRENT_TIMESTAMP, 100014, 100006),
       (CURRENT_TIMESTAMP, 100010, 100007);