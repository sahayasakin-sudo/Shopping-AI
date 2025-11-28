# Shopping Web App (Spring Boot) - Demo

## What this is
A minimal Spring Boot backend with:
- Product CRUD endpoints: `/api/products`
- Simple AI conversation endpoint: `/api/ai/ask` (demo mode when no API key)

## Run
Requirements: Java 17, Maven.

1. Build & run:
   mvn spring-boot:run

2. Example requests:
   - Create product:
     curl -X POST http://localhost:8080/api/products -H "Content-Type: application/json" -d '{"name":"Phone","description":"Smartphone","price":199.99}'
   - List products:
     curl http://localhost:8080/api/products
   - AI demo (no key needed):
     curl -X POST http://localhost:8080/api/ai/ask -H "Content-Type: application/json" -d '{"message":"Recommend a phone for photography"}'

## Notes
- H2 console: http://localhost:8080/h2-console (JDBC URL: jdbc:h2:mem:shopdb)
- To enable real AI calls, set environment variable `AI_API_KEY` before running.
