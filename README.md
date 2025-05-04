# User Management & Role Assignment System

This is a Spring Boot project that demonstrates a User Management System built with **Clean Architecture** principles. It allows you to:

- Create users and roles
- Assign roles to users
- Retrieve user details along with their assigned roles
- Enforce validation using Jakarta Validation
- Test endpoints via Swagger UI

---

## 🏗️ Project Structure

src
├── domain # Entity classes (pure Java objects)
├── application # Service interfaces and business logic
├── infrastructure
│ ├── controller # REST API Controllers
│ ├── dto # Data Transfer Objects with validation
│ ├── persistence # JPA repositories and adapters
│ └── exception # Global exception handlers
└── configuration # Spring configuration files (e.g., BeanConfig)


---

## 📦 Tech Stack

- Java 17+
- Spring Boot
- Spring Data JPA (H2 in-memory DB for development)
- Jakarta Validation
- Swagger/OpenAPI
- Clean Architecture

---

## 🚀 Getting Started

### ✅ Prerequisites

- Java 17+
- Maven

### ▶️ Run the Application

```bash
./mvnw spring-boot:run
The app will be available at: http://localhost:8080

Test the API with Swagger:

http://localhost:8080/swagger-ui/index.htm

API Endpoints
👤 Users

    POST /users - Create a new user

    POST /users/{userId}/assign-role/{roleId} - Assign a role to a user

    GET /users/{id} - Get user with roles

🔑 Roles

    POST /roles - Create a new role

Validation

Validation is applied to all DTOs using Jakarta Validation annotations like:

    @NotBlank

    @Email

Global validation errors are handled in GlobalExceptionHandler.java.

Unit Testing

Unit tests can be added in the application layer to test business logic, and optionally for controllers using mock services.