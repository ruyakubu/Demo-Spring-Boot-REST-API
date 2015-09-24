# Demo-Spring-Boot-REST-API
This is project is to demo: Login security, Auth Token, Rest API, Spring Data

Here are some endpoints you can call:

via Web
http://localhost:8080/
http://localhost:8080/login
http://localhost:8080/user
http://localhost:8080/file
http://localhost:8080/logout

via REST API + secured authToken
http://localhost:8080/login?username=user@demo.com&password=demo2015
http://localhost:8080/api/users  (to get all user data)
http://localhost:8080/api/users/{country}  (to get users by country filter)  (to show all the files in a directory)
http://localhost:8080/api/file/directory  (to show all the files in a directory)

