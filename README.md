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

#### get All Users
curl -X GET "http://localhost:8080/topjava/rest/admin/users" -H "accept: application/json"

#### get Users 100001
curl -X GET "http://localhost:8080/topjava/rest/admin/users/100001" -H "accept: application/json"

#### create User
curl -X POST "http://localhost:8080/topjava/rest/admin/users" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"email\": \"string@r.ru\", \"enabled\": true, \"name\": \"string\", \"password\": \"string\", \"registered\": \"2022-05-09T09:41:40.119Z\", \"roles\": [ \"USER\" ]}"

#### get All Restaurants
curl -X GET "http://localhost:8080/topjava/rest/user/restaurants" -H "accept: application/json"

#### get Restaurants 100010
curl -X GET "http://localhost:8080/topjava/rest/user/restaurants/100010" -H "accept: application/json"

#### get Menu 100015 Restaurants 100010
curl -X GET "http://localhost:8080/topjava/rest/user/restaurants/100010/menu/100015" -H "accept: application/json"

#### get Restaurants 100010 with all menu
curl -X GET "http://localhost:8080/topjava/rest/user/restaurants/100010/with-menu" -H "accept: application/json"

#### get Restaurants 100110 not found
curl -X GET "http://localhost:8080/topjava/rest/user/restaurants/100110" -H "accept: application/json"

#### delete Restaurants
curl -X DELETE "http://localhost:8080/topjava/rest/admin/restaurants/100011" -H "accept: application/json"

#### create Restaurants
curl -X POST "http://localhost:8080/topjava/rest/admin/restaurants" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"description\": \"Add restaurant\"}"

#### update Restaurants 100010
curl -X PUT "http://localhost:8080/topjava/rest/admin/restaurants/100010" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"description\": \"restaurant Update\"}"

#### validate with Error create Restaurants
curl -X POST "http://localhost:8080/topjava/rest/admin/restaurants" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"description\": \"X\"}"

#### getTodayResult voting
curl -X GET "http://localhost:8080/topjava/rest/votes" -H "accept: application/json"

#### create Votes for the restaurant 100010
curl -X POST "http://localhost:8080/topjava/rest/votes/restaurants/100010" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"dateTime\": \"2022-05-09T09:21:34.627Z\"}"

#### create all Votes by user
curl -X GET "http://localhost:8080/topjava/rest/votes/by-user" -H "accept: application/json"

#### update Votes
curl -X PUT "http://localhost:8080/topjava/rest/votes/100020/restaurants/100010" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"dateTime\": \"2022-05-09T09:23:51.163Z\"}"

#### validate with Error create Votes /Voting time is over/
curl -X PUT "http://localhost:8080/topjava/rest/votes/100020/restaurants/100010" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"dateTime\": \"2022-05-01T21:23:51.163Z\"}"