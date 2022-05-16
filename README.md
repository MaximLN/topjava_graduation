# Voting for restaurants
Graduate work

The TOR for the project is to:
Create a voting system to decide where to have lunch.
2 types of users: administrators and regular users.
The administrator can enter the restaurant and its lunch menuItems of the day (usually 2-5 dishes, just the name of the dish and the price).
The menuItems changes every day (administrators make updates).
Users can vote for which restaurant they want to have lunch at,
only one vote is counted for each user
If the user votes again on the same day:
If it happens before 11:00, we assume that he has changed his mind.
If it's after 11:00, then it's too late, the vote can't be changed.
Every restaurant offers a new menuItems every day.

Technologies and tools used: Java, Maven, Spring MVC, Security, JPA(Hibernate), REST(Jackson), jUnit tests, HSQLDB.

http://localhost:8080/topjava/swagger-ui.html

For testing user functionality, use:
User: admin@gmail.com
Password: admin

For testing administrator functionality, use:
User: user1@yandex.ru
Password: password
