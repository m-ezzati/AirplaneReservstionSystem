✈️ Flight Booking System — Spring Boot Project

This project is a Flight Booking System built with Spring Boot, featuring secure authentication, clean layered architecture, and concurrency-safe seat reservation using Optimistic Locking.

🚀 Technologies Used

Spring Boot

Spring Security

Spring Data JPA

Hibernate Optimistic Locking

PostgreSQL

Gradle Wrapper (gradlew)

📌 Features

User authentication and access control using Spring Security
Create and manage flights (with validation)
Book flights with safe concurrent seat handling
Optimistic locking prevents race-condition issues when multiple users book the same flight

Layered architecture:
Controller
Service
Repository
Entity / DTO

🔐 Security

The project uses Spring Security to secure endpoints.
Public routes (e.g., user registration/login) are open, while all other endpoints require authentication.

⚙️ Concurrency Handling

Flight entities use:

@Version
private Long version;


This enables Optimistic Locking, ensuring that simultaneous seat bookings are handled safely and consistently.

▶️ Running the Project

Configure the database in application.properties or application.yml.

Build the project:

./gradlew clean build


Run the application:

./gradlew bootRun

