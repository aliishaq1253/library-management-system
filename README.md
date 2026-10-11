# Library Management System

A RESTful API for managing a library's book catalog, members, and borrowing workflow, built as a backend portfolio project with Spring Boot.

It covers the full lifecycle of a small library: maintaining a book catalog, registering members, and tracking who has borrowed which book and when it's due back — including validation, custom business exceptions, and centralized error handling.

## Tech Stack

| Technology       | Purpose                                      |
|------------------|-----------------------------------------------|
| Java 21          | Core language                                 |
| Spring Boot 4    | Application framework / auto-configuration    |
| Spring Data JPA  | Persistence layer, repository abstraction      |
| H2 Database      | In-memory relational database for development  |
| Lombok           | Boilerplate reduction (getters/setters, constructors) |
| Maven            | Build tool and dependency management           |

## Project Structure

```
src/main/java/com/ali/library_management_system/
├── controller/     REST endpoints (HTTP layer only – no business logic)
├── service/        Business logic (CRUD, borrow/return workflow, validation rules)
├── repository/      Spring Data JPA interfaces (database access)
├── model/           JPA entities (Book, User, Loan)
└── exception/       Custom exceptions + GlobalExceptionHandler
```

The project follows a classic **layered architecture**:

```
Controller  →  Service  →  Repository  →  Database
```

Each layer has a single responsibility: controllers handle HTTP concerns, services contain business rules, and repositories talk to the database.

## API Endpoints

### Books

| Method | URL                          | Description                          |
|--------|-------------------------------|---------------------------------------|
| GET    | `/api/books`                  | Get all books                         |
| GET    | `/api/books/{id}`             | Get a single book by id               |
| POST   | `/api/books`                  | Create a new book                     |
| PUT    | `/api/books/{id}`             | Update an existing book               |
| DELETE | `/api/books/{id}`             | Delete a book                         |
| GET    | `/api/books/search?author=`   | Search books by author (contains, case-insensitive) |

### Users

| Method | URL                 | Description             |
|--------|----------------------|---------------------------|
| GET    | `/api/users`         | Get all users             |
| GET    | `/api/users/{id}`    | Get a single user by id   |
| POST   | `/api/users`         | Create a new user         |
| PUT    | `/api/users/{id}`    | Update an existing user   |
| DELETE | `/api/users/{id}`    | Delete a user              |

### Loans

| Method | URL                                     | Description                                         |
|--------|-------------------------------------------|-------------------------------------------------------|
| POST   | `/api/loans/borrow?userId=&bookId=`      | Borrow a book for a user (book must be available)     |
| PUT    | `/api/loans/{id}/return`                 | Return a borrowed book                                 |
| GET    | `/api/loans`                              | Get all currently active (not yet returned) loans       |
| GET    | `/api/loans/user/{userId}`               | Get the full loan history for a user                     |

Borrowing a book sets its `available` flag to `false`; returning it sets it back to `true`.

## Getting Started

### Prerequisites

- Java 21
- Maven (or use the included wrapper, no local install needed)

### Run the application

```bash
./mvnw spring-boot:run
```

The API starts on `http://localhost:8080`.

### H2 Console

With the app running, open the H2 web console to inspect the in-memory database:

```
http://localhost:8080/h2-console
```

Connection settings:

| Field       | Value                     |
|-------------|----------------------------|
| JDBC URL    | `jdbc:h2:mem:librarydb`    |
| User Name   | `sa`                       |
| Password    | *(leave empty)*            |

## Key Concepts

This project is also a demonstration of core backend engineering concepts:

- **OOP principles** – Entities (`Book`, `User`, `Loan`) encapsulate their own state behind getters/setters; shared behavior (CRUD patterns, exception structure) is factored into consistent, reusable patterns across services and exceptions rather than duplicated per entity.
- **Layered Architecture** – A strict separation between `Controller` (HTTP), `Service` (business rules), and `Repository` (persistence) keeps each layer independently testable and replaceable.
- **Exception Handling** – Custom unchecked exceptions (`BookNotFoundException`, `UserNotFoundException`, `BookNotAvailableException`) represent specific business failures. A single `@RestControllerAdvice` (`GlobalExceptionHandler`) translates them into consistent HTTP responses (404, 409, 400 for validation errors, 500 as a fallback) so controllers stay free of try/catch blocks.
- **REST API Design** – Resource-oriented URLs, correct use of HTTP methods and status codes (201 Created, 204 No Content, 404 Not Found, 409 Conflict), and request validation (`@Valid` / `@NotBlank`) on write operations.

## Frontend

A separate React frontend for this API is available: *(link coming soon)*
