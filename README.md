# Set Up Project
1. pull docker image with this command
```
docker pull mysql
```
2. run docker image
```
docker run --name mysql-allianz --rm -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -d mysql 
```
2. go to mysql workbench or any tool you use
3. create schema by using this command
```
CREATE SCHEMA IF NOT EXISTS `allianz` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
```
4. How to call api topic will tell you about calling api

# How to call api 
 1. I put all collection for calling api in postman folder
 2. List of api in postman folder
    1. Register
    2. Login
    3. GetAllEmployee
    4. GetEmployeeById
    5. UpdateEmployeeById
    6. DeleteEmployeeById

## Requirements
- [x] Scaffold the project with data source connection (any database but in memory one is preferred). 
- [x] Create API for authentication, check User existing and return generated token (Use this token to secure APIs on point 3-6).
- [x] Create API for retrieving all employee.
- [x] Create API for retrieving one employee by ID.
- [x] Create API for saving one employee.
- [x] Create API for modifying one employee.
- [x] Create API for deleting one employee by ID.
- [X] Unit testing. (do not finish all case)
- [] Integration testing.
- [] Swagger is a plus.
- [] Liquibase is a plus.
- [x] MapStruct is a plus.
- [] Docker is a plus.


# Environment
1. JAVA11
2. Spring Boot Version 2.5.2
3. MYSQL DB (run by docker image)

    
