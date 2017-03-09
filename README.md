# jwtExample
Simple example of jwt token with different server and client

## Database Mysql
Create db from [SQL Dump](db/users.sql) on a db name *jwtexample*

| Fields | Description |
| --- | --- |
| id | Autoincrease identifier |
| username | Nickname of the user | 
| password | Encrypted with bcrypt | 
| roles | Permissions for users |

## Server

### Route
| Route | Method | Params | Description |
| --- | --- | --- | --- |
| auth/authenticate | POST | username, password | Request an jwt authentication token |
| secret/tellmeasecret | GET | none | Required authentication first |
| anonymous/greetings | GET | none | Simple anonymous request |

### Spring 
```
cd server/spring/
mvn spring-boot:run
```
By default on `http://localhost:8080/`

### Silex
```
cd server/silex/
composer install
composer run
```

By default on `http://localhost:8888/`



## Client

### Angular2
```
cd client/angular2
npm install
ng serve
```

By default on `http://localhost:4200/`

### Ionic2
```
cd clinet/ionic2
npm install
ionic state restore
ionic serve
```
