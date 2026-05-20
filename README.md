# Digital Payment Gateway API Simulation

A robust and secure RESTful API simulation of a Digital Payment Gateway transaction lifecycle. Built using Java and the Spring Boot framework, this backend service manages secure transaction creations, data validation, automated status handling, and persistence using a PostgreSQL database.

This project demonstrates a production-ready **3-Tier Layered Architecture** (Controller, Service, Repository) engineered specifically to align with financial technology integration standards.

## 🚀 Tech Stack & Tools
* **Programming Language:** Java 17
* **Framework:** Spring Boot 3.x
* **Database:** PostgreSQL
* **Data Access:** Spring Data JPA / Hibernate
* **Data Validation:** Jakarta Validation (DTO)
* **API Testing:** Thunder Client

## ✨ Key Features
* **3-Tier Architecture:** Complete decoupling of concerns between API Endpoints (Controller), Business Logic (Service), and Data Persistence (Repository).
* **Automated Transaction Lifecycle:** Newly initialized transactions are rigorously validated and automatically locked into a `PENDING` state.
* **Strict Inbound Data Validation:** Utilizing Data Transfer Objects (DTO) to enforce business rules (e.g., rejecting payloads below the minimum transaction limit of Rp 10,000).
* **Centralized Global Exception Handling:** Mapped custom handlers (`@RestControllerAdvice`) to intercept validation failures, transforming raw Spring Boot stack traces into clean, client-ready JSON error payloads.

## 🗄️ Database Schema & Architecture
The system automatically generates and manages a relational schema in PostgreSQL via Hibernate. The central `Transaction` entity maps the following properties:
* `id` (UUID, Primary Key) - Ensured high collision resistance for distributed payment records.
* `merchantId` (String, Not Null) - Identifies the client institution.
* `amount` (BigDecimal, Not Null) - Adheres to financial software compliance to prevent floating-point rounding errors.
* `status` (String) - Represents state mutations (`PENDING`, `SUCCESS`).
* `createdAt` (LocalDateTime) - Automated transaction timestamping.

## 📡 API Endpoints Specification

### 1. Create Transaction Intent
* **Endpoint:** `POST /api/transactions`
* **Purpose:** Registers a payment intent. Validates merchant credentials and minimum funding requirements.
* **Request Body Example:**
  ```json
  {
    "merchantId": "MERCHANT-001",
    "amount": 50000
  }
Success Response (200 OK):

```json
JSON

{
"id": "e4f8c2b1-9a7d-4c3e-b2a1-8f7d6e5c4b3a",
"merchantId": "MERCHANT-001",
"amount": 50000,
"status": "PENDING",
"createdAt": "2026-05-20T23:00:00"
}
```

2. Inbound Data Validation Failure Case
   Endpoint: POST /api/transactions

Trigger: Attempting to process an empty merchant ID or a payload below Rp 10,000.

Payload Tested:
```json
JSON
{
"merchantId": "",
"amount": 5000
}
```
Handled JSON Response (400 Bad Request):
```json
JSON
{
"merchantId": "Merchant ID cannot be empty",
"amount": "Minimum transaction amount is Rp 10,000"
}
```
3. Fetch Transaction History
   Endpoint: GET /api/transactions

Purpose: Retrieves a collective log of all stored payment actions.

4. Fetch Specific Transaction Details
   Endpoint: GET /api/transactions/{id}

Purpose: Queries a distinct record using its unique UUID.

5. Simulate Transaction Settlement (Payment Execution)
   Endpoint: PUT /api/transactions/{id}/pay

Purpose: Simulates an external bank callback/webhook, mutating the ledger status from PENDING to SUCCESS.

🛠️ How to Run Locally
1. Clone the repository:

```json
git clone [https://github.com/YOUR_USERNAME/payment-gateway-springboot.git](https://github.com/YOUR_USERNAME/payment-gateway-springboot.git)
cd payment-gateway-springboot
```

2. Configure Database Connection:
Update your PostgreSQL credentials inside src/main/resources/application.properties:

```json
Properties
spring.datasource.url=jdbc:postgresql://localhost:5171/payment_gateway
spring.datasource.username=YOUR_POSTGRES_USERNAME
spring.datasource.password=YOUR_POSTGRES_PASSWORD
```

3. Run the Application:
Execute via your preferred IDE (IntelliJ IDEA) or run via terminal:
```json
Bash
./mvnw spring-boot:run
```