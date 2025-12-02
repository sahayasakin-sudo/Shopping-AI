Shopping App Backend (Spring Boot)

A minimal, clean Spring Boot backend that provides:

 Product CRUD API (/api/products)

AI Conversation API (/api/ai/ask) with demo mode when no API key is set

 MySQL database integration

RESTful architecture using Spring Web + JPA



🚀 Features
🔹 Product Management (CRUD)

Endpoints:

POST /api/products – Create a product

GET /api/products – List all products

GET /api/products/{id} – Get product by ID

PUT /api/products/{id} – Update product

DELETE /api/products/{id} – Delete product

🤖 AI Conversation Endpoint

POST /api/ai/ask

Works in demo mode when no AI_API_KEY is set

Accepts:

{
  "message": "Recommend a phone for photography"
}

🛠️ Tech Stack

Java 17

Spring Boot

Spring Web

Spring Data JPA

MySQL

Maven

Example API Requests
Create a Product
curl -X POST http://localhost:8080/api/products \
-H "Content-Type: application/json" \
-d '{"name":"Phone","description":"Smartphone","price":199.99}'

src/
 └── main/
     ├── java/com/example/shop
     │    ├── controller/  → REST controllers
     │    ├── model/       → Entity classes
     │    ├── repository/  → JPA repositories
     │    └── service/     → Business logic
     └── resources/
          ├── application.properties
          └── static/templates

