# topjava_graduation
Graduate work

The TOR for the project is to:
Create a voting system to decide where to have lunch.
2 types of users: administrators and regular users
The administrator can enter the restaurant and its lunch menu of the day (usually 2-5 dishes, just the name of the dish and the price).
The menu changes every day (administrators make updates)
Users can vote for which restaurant they want to have lunch at,
only one vote is counted for each user
If the user votes again on the same day:
If it happens before 11:00, we assume that he has changed his mind.
If it's after 11:00, then it's too late, the vote can't be changed.
Every restaurant offers a new menu every day.

### curl samples (application deployed at application context `topjava_graduation`).
> For windows use `Postman`

#### get All Users
`curl -s http://localhost:8080/topjava/rest/admin/users --user admin@gmail.com:admin`

#### get Users 100001
`curl -s http://localhost:8080/topjava/rest/admin/users/100001 --user admin@gmail.com:admin`

#### register User
`curl -s -i -X POST -d '{"name":"New User","email":"test@mail.ru","password":"test-password"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/topjava/rest/profile`

#### get All Restaurants
`curl --location --request GET 'http://localhost:8080/topjava/rest/user/restaurant' \
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`

#### get Restaurants 100010 with-menu
`curl --location --request GET 'http://localhost:8080/topjava/rest/user/restaurant/100010/with-menu' \
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`

#### get Restaurants 100110 not found
`curl --location --request GET 'http://localhost:8080/topjava/rest/user/restaurant/100110/with-menu' \
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`

#### delete Restaurants
`curl --location --request DELETE 'http://localhost:8080/topjava/rest/admin/restaurant/100012/' \
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`

#### create Restaurants
`curl --location --request POST 'http://localhost:8080/topjava/rest/admin/restaurant/' \
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' \
--header 'Content-Type: application/json' \
--data-raw '{
"description": "Add restaurant"
}'`

#### update Restaurants 100014
`curl --location --request PUT 'http://localhost:8080/topjava/rest/admin/restaurant/100014' \
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' \
--header 'Content-Type: application/json' \
--data-raw '    {
"description": "restaurant Update",
"menu": null
}'`

#### validate with Error create Restaurants
`curl --location --request POST 'http://localhost:8080/topjava/rest/admin/restaurant/' \
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' \
--header 'Content-Type: application/json' \
--data-raw '{
"description": ""
}'`