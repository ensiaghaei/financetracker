# Finance Tracker API

A RESTful API for managing personal expenses.  
The project includes CRUD operations, validation, exception handling, filtering, pagination, PostgreSQL database integration, and Docker support.

## Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Docker
- Docker Compose
- Maven
- JUnit
- Mockito
- MockMvc

## Features

- Create expense
- Get all expenses
- Get expense by ID
- Update expense
- Delete expense
- Filter expenses by category
- Pagination
- Request validation
- Global exception handling
- PostgreSQL database
- Dockerized application
- Database healthcheck
- Unit and integration tests

## Run Locally

Make sure PostgreSQL is running locally on port 5432
(if you are not using Docker).

```bash
./mvnw spring-boot:run
```
## Run with Docker

Build and start the application and database:

```bash
docker compose up --build -d
```
Stop the containers:
```bash
docker compose down
```
View logs:
```bash
docker compose logs -f
```
## Environment Variables
The project uses a `.env` file for configuration.
```env
POSTGRES_DB=finance_db
POSTGRES_USER=finance_user
POSTGRES_PASSWORD=finance_pass

SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/finance_db
SPRING_DATASOURCE_USERNAME=finance_user
SPRING_DATASOURCE_PASSWORD=finance_pass
```
