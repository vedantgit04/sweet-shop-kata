# 🍬 Royal Sweet Shop - Full Stack Management System

A premium, full-stack Sweet Shop Management System built using **Test Driven Development (TDD)** principles. This application features a secure Spring Boot backend with JWT authentication and a luxurious React frontend with role-based access control.

---

## 🎥 Project Demo


https://github.com/user-attachments/assets/1c1baae9-6540-44fa-aa9d-c47fb27adcaf


### 📸 Screenshots

#### 1. Premium Login & Registration

<img width="1920" height="1080" alt="Screenshot (7076)" src="https://github.com/user-attachments/assets/d88f0fc9-0245-4880-b2ef-a6b73eadac65" />
<img width="1920" height="1080" alt="Screenshot (7087)" src="https://github.com/user-attachments/assets/06c0574b-1a9f-4df1-9980-301126304c96" />
<img width="1920" height="1080" alt="Screenshot (7089)" src="https://github.com/user-attachments/assets/891273e3-d279-4f64-97a7-ca0f4efd04f5" />
<img width="1920" height="1080" alt="Screenshot (7090)" src="https://github.com/user-attachments/assets/33c8747b-88f2-4f56-b429-9fdf7a71de74" />



#### 2. Dashboard (User View - Purchase)
<img width="1920" height="1080" alt="Screenshot (7093)" src="https://github.com/user-attachments/assets/cb97e3af-6bf8-43d4-be5e-9e592e9cecbe" />
<img width="1920" height="1080" alt="Screenshot (7092)" src="https://github.com/user-attachments/assets/444ecc68-64fe-4171-b56e-0e5beda1d06f" />


#### 3. Dashboard (Admin View - Restock/Delete)
<img width="1920" height="1080" alt="Screenshot (7096)" src="https://github.com/user-attachments/assets/f70b6179-2d7e-490c-b662-7fe8efe3da9c" /> 
<img width="1920" height="1080" alt="Screenshot (7094)" src="https://github.com/user-attachments/assets/f5aad381-3d4f-4344-b2bd-8347e811486e" />
<img width="1920" height="1080" alt="Screenshot (7095)" src="https://github.com/user-attachments/assets/a7db2d04-ab89-40ec-8a05-91899e8bd04d" />
<img width="1920" height="1080" alt="Screenshot (7096)" src="https://github.com/user-attachments/assets/d80514f5-9f2c-4e01-8eff-6d2ce123cb31" />
<img width="1920" height="1080" alt="Screenshot (7097)" src="https://github.com/user-attachments/assets/9dca836a-13d5-4ae1-a344-2cd9090b7dda" />


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

<img width="1920" height="1080" alt="Screenshot (7098)" src="https://github.com/user-attachments/assets/3d61f7b4-10da-4c10-8c6b-6a9450b58491" />
<img width="1920" height="1080" alt="Screenshot (7099)" src="https://github.com/user-attachments/assets/a54b7749-2a5a-4909-aa39-0d47975ec656" />
<img width="1920" height="1080" alt="Screenshot (7100)" src="https://github.com/user-attachments/assets/a0c9fc86-d105-483c-8153-ddb7051d6ac6" />
<img width="1920" height="1080" alt="Screenshot (7101)" src="https://github.com/user-attachments/assets/01d6063a-0131-44f1-93b4-ad6e101cecf8" />

---

### 👨‍💻 Developed by Vedant
Built using the **Red-Green-Refactor** TDD cycle.
