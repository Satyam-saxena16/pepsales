# Pepsales Notification Service

## Assignment Overview
This project implements a Notification Service backend using Spring Boot. It supports sending notifications via Email, SMS, and In-App channels. The service is designed to be extensible and testable, following best practices for Java backend development.

## Features
- RESTful API for sending and retrieving notifications
- Support for multiple notification types (Email, SMS, In-App)
- Scheduled processing of unsent notifications
- In-memory H2 database for development and testing
- Unit and integration tests

## Setup Instructions

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
1. **Clone the repository:**
   ```bash
   git clone <your-repo-link>
   cd pepsales
   mvn clean package
   java -jar target/pepsales-notification-0.0.1-SNAPSHOT.jar
   ```

The application will start on port 8080 by default.

## Implementation Details

### Notification Types

- **Email**: For sending email notifications
- **SMS**: For sending SMS notifications
- **In-App**: For sending real-time notifications within the application

### Scheduled Processing

The application includes scheduled tasks to process unsent notifications:

- Email notifications: Every 5 minutes
- SMS notifications: Every 3 minutes
- In-App notifications: Every minute