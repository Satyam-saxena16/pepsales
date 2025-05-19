
Pepsales Notification Service
=============================

Assignment Overview
-------------------
This project implements a Notification Service backend using Spring Boot. It supports sending notifications via Email, SMS, and In-App channels. The service is designed to be extensible and testable, following best practices for Java backend development.

Features
--------
- RESTful API for sending and retrieving notifications
- Support for multiple notification types (Email, SMS, In-App)
- Scheduled processing of unsent notifications
- In-memory H2 database for development and testing
- Unit and integration tests

Setup Instructions
------------------

### Prerequisites
- Java 21 or higher
- Maven 3.8+
- Git (for cloning the repository)

### Dependencies
All dependencies are managed via Maven and will be installed automatically. Key dependencies include:
- Spring Boot 3.4.5
- Spring Data JPA
- H2 Database
- Spring Web
- JUnit 5 & Mockito (for testing)

### How to Run
1. Clone the repository:
   git clone <your-repo-link>
   cd pepsales

2. Build and run the application:
   mvn spring-boot:run

3. Access the application:
   - API endpoints: http://localhost:8080 (see controller classes for available endpoints)
   - H2 Console: http://localhost:8080/h2-console (JDBC URL: jdbc:h2:mem:notificationdb)

Assumptions
-----------
- No frontend is provided; only backend REST APIs are implemented.
- The root URL / does not serve a static page (404 is expected).
- All configuration is set for local development with an in-memory database.
