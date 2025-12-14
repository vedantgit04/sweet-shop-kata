# 🍬 Royal Sweet Shop - Full Stack Management System

A premium, full-stack Sweet Shop Management System built using **Test Driven Development (TDD)** principles. This application features a secure Spring Boot backend with JWT authentication and a luxurious React frontend with role-based access control.

---

## 🎥 Project Demo

*(Upload your demo video here showing Login, Admin Restock, and User Purchase flow)*

### 📸 Screenshots

#### 1. Premium Login & Registration
*(Place screenshot of Login page here)*

#### 2. Dashboard (User View - Purchase)
*(Place screenshot of User Dashboard with "Buy Now" buttons here)*

#### 3. Dashboard (Admin View - Restock/Delete)
*(Place screenshot of Admin Dashboard with "Add Sweet" and "Restock" buttons here)*

#### 4. Premium Custom Modals
*(Place screenshot of the "Sweetness Confirmed" or "Stock Updated" popup here)*

---

## 🤖 My AI Usage

**I strictly followed the AI Co-authorship policy for this project.**

* **AI Tool Used:** Google Gemini
* **Role:** Pair Programmer / Thought Partner

### How I Used AI:
1.  **TDD Workflow (Backend):**
    * I used Gemini to generate **Unit Tests first** (Red Phase) for my `AuthenticationService` and `SweetController`, ensuring I had failing tests before writing code.
    * I then asked for the minimal implementation code (Green Phase) to make those tests pass.
    * *Example:* "Generate a JUnit test for `SweetService.purchaseSweet` that fails if stock is insufficient."

2.  **Debugging & Problem Solving:**
    * **CORS & Security:** AI helped me diagnose a `403 Forbidden` error by suggesting the implementation of a `JwtAuthenticationFilter` and configuring `CorsConfigurationSource` in Spring Security.
    * **Git Issues:** AI guided me through fixing a "Nested Git Repository" issue where my Frontend folder was not being tracked by the main repository.

3.  **Frontend Design:**
    * I used AI to generate the **Glassmorphism CSS** and the "Premium Indian Sweet Shop" color palette (Deep Maroon & Gold) to meet the aesthetic requirements.
    * AI helped refactor the `Home.js` component to fix React Hook dependency warnings (`useCallback`).

### Reflection:
Using AI significantly sped up the boilerplate setup (like Security Config) and allowed me to focus on the business logic. The TDD approach forced me to understand *what* I was building before I built it, ensuring the AI code was accurate and testable.

---

## 🚀 Features

### 🛡️ Security & Authentication
* **JWT Authentication:** Stateless security using JSON Web Tokens.
* **Role-Based Access Control (RBAC):**
    * **Admin:** Can Add, Delete, and Restock sweets.
    * **User:** Can Browse, Search, Sort (Price High/Low), and Purchase sweets.
* **BCrypt Hashing:** Passwords are encrypted before storage.

### 🍬 Product Management
* **Dynamic Inventory:** Real-time stock updates upon purchase.
* **Smart Search:** Search by name or category.
* **Premium UI:** "Royal" theme with custom assets and glassmorphism effects.

---

## 🛠️ Tech Stack

### Backend
* **Java 21** & **Spring Boot 3.4**
* **Spring Security 6** (JWT Filter, Stateless Session)
* **Spring Data JPA** & **SQLite**
* **JUnit 5 & Mockito** (Testing)

### Frontend
* **React.js** (Hooks: `useState`, `useEffect`, `useCallback`)
* **Axios** (Interceptors for Bearer Token)
* **Bootstrap 5** & **Custom CSS**

---

## ⚙️ How to Run Locally

### 1. Backend Setup
1.  Navigate to the `backend` folder.
2.  Run the application:
    ```bash
    ./mvnw spring-boot:run
    ```
3.  The server will start on `http://localhost:8080`.

### 2. Frontend Setup
1.  Navigate to the `frontend` folder.
2.  Install dependencies:
    ```bash
    npm install
    ```
3.  Start the React app:
    ```bash
    npm start
    ```
4.  Open `http://localhost:3000` in your browser.

---

## 🧪 Test Report (TDD)
*(You can run tests using `./mvnw test` and paste a screenshot of the green checkmarks here)*

---

### 👨‍💻 Developed by Vedant
Built using the **Red-Green-Refactor** TDD cycle.
