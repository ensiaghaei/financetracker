# Finance Tracker API
This project demonstrates backend development skills including REST API design, database integration, testing, and containerization.
A RESTful API for managing personal expenses.  
The project includes CRUD operations, validation, exception handling, filtering, pagination, PostgreSQL database integration, and Docker support.

## Live Demo
Deployed on AWS EC2 using Docker and PostgreSQL.

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
## Architecture

Controller
→ Service
→ Repository
→ PostgreSQL

GlobalExceptionHandler handles API errors.
DTOs are used for request validation and response mapping.
## Project Structure
```
src/main/java/com/ensi/financetracker

├── controller
├── service
├── repository
├── entity
├── dto
├── exception
└── config
```
# Deployment (AWS EC2)
The application is deployed on an AWS EC2 instance using Docker and Docker Compose.
## Infrastructure
- AWS EC2 (Ubuntu)
- Docker & Docker Compose
- Security Group configured to allow traffic on port 8080

## Steps to Deploy
Connect to Ec2:
```bash
ssh ubuntu@<your-public-ip>
```
Clone the repository:
```bash
git clone https://github.com/ensiaghaei/financetracker.git
cd financetracker
```
Start the application:
```bash
docker compose up --build -d
```
## Access
The API is accessible via:
```bash
http://<your-public-ip>:8080/api/expenses
```
## Testing

- Unit tests using JUnit 5 and Mockito
- Controller tests using MockMvc
- Service layer testing with mocked repositories
### Test the API
Replace `<your-public-ip>` with your EC2 public IP address.
Use the following command to create a sample expense:
```bash
curl -X POST http://<your-public-ip>:8080/api/expenses \
-H "Content-Type: application/json" \
-d '{
  "title": "Coffee",
  "amount": 5.5,
  "category": "Food",
  "expenseDate": "2026-04-15",
  "note": "Starbucks"
}'
```
### Get all expenses:
```bash
curl http://<your-public-ip>:8080/api/expenses
```
## Persistence
PostgreSQL data is persisted using Docker volumes:
- Data remains intact after container restarts
- Verified by restarting containers and EC2 instance
## Restart Behavior
- Containers are configured with restarts:always
- Application automatically starts after EC2 reboot