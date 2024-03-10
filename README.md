# REST CRUD application for work with Posts based on Servlets with multi-layers (Servlet,Controller,Service,Dto,Mapper,Repository,Entity) and DB PostesSQL

## Application starts on port 8080

## Creation of new post
POST http://localhost:8080/api/posts

## Reading all posts
GET http://localhost:8080/api/posts

## Reading N post
GET http://localhost:8080/api/posts/N

## Update post
PUT http://localhost:8080/api/posts

## Delete N post
DELETE http://localhost:8080/api/posts/N

## Tests
- There are tests with Mockito и JUnit usage
- Thare is integration tests with Testcontainer usage
- There is Docker-image 'crud101:latest101' for integration tests
