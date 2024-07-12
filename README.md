# Securing REST APIs for a Todo App using Spring Security and JWT

This repository demonstrates the implementation of Spring Security concepts to secure REST APIs using JWT (JSON Web Tokens), illustrated through a Todo application. The primary focus is on securing endpoints with JWT and implementing role-based authorization.

## Project Overview

The aim of this project is to provide a comprehensive example of how to secure RESTful APIs in a Spring Boot application using JWT. The Todo application serves as a context to demonstrate these security concepts. Key features include:

- **JWT Authentication**: Secure the application by issuing and validating JSON Web Tokens.
- **Role-Based Authorization**: Different access levels for users (`ROLE_USER`) and administrators (`ROLE_ADMIN`).
- **Todo Management**: CRUD operations for managing todo items, demonstrating secured endpoints.
- **Swagger UI Integration**: Interactive API documentation and testing.

## Features

- **JWT Authentication**: Users authenticate with JWT, ensuring secure communication.
- **Role-Based Authorization**: Users with `ROLE_USER` can access GET and PATCH endpoints, while users with `ROLE_ADMIN` have access to all endpoints, including POST, PUT, and DELETE.
- **Public Endpoints**: Register and login endpoints are accessible to all users.

## Role-Based Authorization

- **`ROLE_USER`**: 
  - Can access GET and PATCH endpoints to retrieve and update existing todo items.
  - Cannot access POST, PUT, and DELETE endpoints.
- **`ROLE_ADMIN`**:
  - Has full access to all endpoints including creating, updating, and deleting todo items.

## Endpoints

### Authentication Endpoints

- **Register**: Accessible to all users.
  - `POST /api/auth/register`
- **Login**: Accessible to all users.
  - `POST /api/auth/login`

### Todo Endpoints

- **Add Todo**: Accessible only to administrators.
  - `POST /api/todos/add`
- **Get Todo**: Accessible to users and administrators.
  - `GET /api/todos/get/{id}`
- **Get All Todos**: Accessible to users and administrators.
  - `GET /api/todos/allTodos`
- **Update Todo**: Accessible only to administrators.
  - `PUT /api/todos/update/{id}`
- **Delete Todo**: Accessible only to administrators.
  - `DELETE /api/todos/delete/{id}`
- **Complete Todo**: Accessible to users and administrators.
  - `PATCH /api/todos/complete/{id}`
- **Mark Todo as Incomplete**: Accessible to users and administrators.
  - `PATCH /api/todos/incomplete/{id}`

## Technologies Used

- **Spring Boot**
- **Spring Security**
- **JWT (JSON Web Tokens)**
- **Hibernate**
- **PostgreSql**
- **Lombok**
- **ModelMapper**

