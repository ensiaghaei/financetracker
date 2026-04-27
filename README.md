# Finance Tracker API
This project demonstrates backend development skills including REST API design, database integration, testing, and containerization.
A RESTful API for managing personal expenses.  
The project includes CRUD operations, validation, exception handling, filtering, pagination, PostgreSQL database integration, and Docker support.

## Live Demo

Base URL:
http://3.96.64.137:8080/api/expenses

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

## API Endpoints

- GET /api/expenses → get all expenses
- GET /api/expenses/{id} → get expense by id
- POST /api/expenses → create expense
- PUT /api/expenses/{id} → update expense
- DELETE /api/expenses/{id} → delete expense
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
## Sample Request

POST /api/expenses

```json
{
  "title": "Coffee",
  "amount": 5.5,
  "category": "Food",
  "expenseDate": "2026-04-15",
  "note": "Starbucks"
}
```