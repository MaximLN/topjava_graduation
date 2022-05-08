# topjava_graduation
Graduate work

### curl samples (application deployed at application context `topjava_graduation`).
> For windows use `Git Bash`

#### get All Users
`curl -s http://localhost:8080/topjava/rest/admin/users --user admin@gmail.com:admin`

#### get Users 100001
`curl -s http://localhost:8080/topjava/rest/admin/users/100001 --user admin@gmail.com:admin`

#### register User
`curl -s -i -X POST -d '{"name":"New User","email":"test@mail.ru","password":"test-password"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/topjava/rest/profile`

#### get All Restaurants

#### get Restaurants 100003

#### get Restaurants not found

#### delete Restaurants

#### create Restaurants

#### update Restaurants

#### validate with Error