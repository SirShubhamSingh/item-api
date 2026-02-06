Item Management API

A RESTful Java backend application for managing an inventory of items. Built with Spring Boot 3 and Java 17.

Features

In-Memory Data Storage: Uses a thread-safe CopyOnWriteArrayList to mimic database operations.

Input Validation: Ensures data integrity using Jakarta Validation constraints.

RESTful Architecture: Follows standard HTTP methods (GET, POST).

Tech Stack

Java 17

Spring Boot 3.4.2 (Web, Validation)

Maven

API Endpoints

1. Add a New Item

Endpoint: POST /api/items

Description: Creates a new item in the inventory.

Headers: Content-Type: application/json

Request Body:

{
  "name": "Wireless Mouse",
  "description": "Ergonomic 2.4GHz mouse",
  "price": 25.99,
  "category": "Electronics"
}


Response: 201 Created

2. Get Item by ID

Endpoint: GET /api/items/{id}

Description: Retrieves details of a specific item.

Response: 200 OK (JSON Object) or 404 Not Found.

3. Get All Items

Endpoint: GET /api/items

Description: Returns a list of all items.

How to Run

Clone the repository.

Open terminal in the root folder.

Run: ./mvnw spring-boot:run

Access at: http://localhost:8080/api/items