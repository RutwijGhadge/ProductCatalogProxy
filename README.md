# 🛒 ProductCatalogProxy

ProductCatalogProxy is a **Spring Boot microservice** that serves as a **Product Catalog Service** for an e-commerce system.  
It proxies requests to the [Fake Store API](https://fakestoreapi.com/docs), handling **CRUD operations**, request validation, and **exception handling**.

---

## ✨ Features

- ✅ Exposes **RESTful endpoints** for Product Catalog management
- ✅ Performs **CRUD operations** (Create, Read, Update, Delete)
- ✅ Integrates with the external **Fake Store API**
- ✅ Implements **Controller & Service layers**
- ✅ Centralized **Exception Handling**
- ✅ Structured for future integration into a **microservices e-commerce ecosystem**

---


---

## 🚀 API Endpoints

### Products

| Method | Endpoint              | Description                      |
|--------|-----------------------|----------------------------------|
| GET    | `/products`           | Fetch all products               |
| GET    | `/products/{id}`      | Fetch product by ID              |
| POST   | `/products`           | Create a new product             |
| PUT    | `/products/{id}`      | Update an existing product       |
| DELETE | `/products/{id}`      | Delete a product by ID           |

> ⚡ All requests internally communicate with [Fake Store API](https://fakestoreapi.com/docs).

---

## ⚙️ Tech Stack

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Web (REST)**
- **Spring Validation**
- **Lombok** (for boilerplate code reduction)
- **Fake Store API** (external data source)

---

## 🛠️ Exception Handling

This service has centralized exception handling using `@ControllerAdvice`.  
It ensures meaningful error messages and consistent API responses.

