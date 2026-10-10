
# Charu Mart — E-Commerce Backend

## Project Overview
Charu Mart is an e-commerce web application developed to support online product browsing and shopping. This repository contains the backend application and database integration.

## Technologies Used
- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- REST API

## Features
- Customer registration and login
- Product management and retrieval
- Database integration using MySQL
- REST APIs for frontend communication

## Project Structure
- `src/main/java` — Backend Java source code
- `src/main/resources` — Application configuration
- `pom.xml` — Maven dependencies and build configuration
- `Dockerfile` — Container configuration for deployment

## Running Locally

### Prerequisites
- Java 17
- MySQL
- Maven

### Steps
1. Create a MySQL database named `charu_mart`.
2. Configure the database connection using environment variables:
   - `DB_URL`
   - `DB_USERNAME`
   - `DB_PASSWORD`
3. Start the Spring Boot application:

   `mvn spring-boot:run`

4. The backend normally runs at `http://localhost:8080`.

## Frontend Repository
https://github.com/charubalamk333-eng/Charu-mart

## Project Status
The backend source code is available on GitHub. Public deployment and further feature development are ongoing.
