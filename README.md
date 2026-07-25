# Cognizant Digital Nurture (FSE) - Deep Skilling Program

Welcome to the **Cognizant Digital Nurture Full Stack Engineer (FSE)** Deep Skilling repository. This repository archives all hands-on exercises, assignments, and architectural implementations completed across the 6-week intensive full-stack skilling curriculum.

The program progresses from software engineering core foundations (design patterns, algorithms, databases) to enterprise Java (Spring Boot, Microservices) and ends with modern Single Page Applications (ReactJS).

---

## 📅 Curriculum & Directory structure

```microservice
Cognizant/
├── DeepSkill/
│   ├── Week-1/      # Algorithms, Design Patterns, PL/SQL, JUnit & Mockito
│   ├── Week-2/      # Spring Core, Maven, Spring Data JPA with Hibernate
│   ├── Week-3/      # Spring Boot REST APIs & JWT Security
│   ├── Week-4/      # Microservices Architecture, API Gateway, Service Registry
│   ├── Week-5/      # ReactJS Core Concepts (JSX, State, Props, Styling)
│   └── Week-6/      # Advanced ReactJS (Context, Forms, API Fetch, Jest/Enzyme Testing)
└── Upskill/         # Baseline technology reference manuals and solutions
```

---

### 📂 Detailed Week-Wise Breakdown

#### 🔹 [Week 1: Software Engineering Foundations](file:///a:/1.%20Placement/Cognizant/DeepSkill/Week-1)
*   **Algorithms & Data Structures:** Implementations of classic concepts optimized for performance.
    *   *Exercise 1:* Inventory Management System
    *   *Exercise 2:* E-commerce Search (Binary Search vs Linear Search)
    *   *Exercise 3:* Sorting Customer Orders (Bubble Sort, Insertion Sort, Quick Sort)
    *   *Exercise 4:* Employee Management System (Array-based insertion, search, traversal)
    *   *Exercise 5:* Task Management System (Singly Linked List)
    *   *Exercise 6:* Library Management System (Linear & Binary search on books)
    *   *Exercise 7:* Financial Forecasting (Recursive growth calculation)
*   **Design Patterns:** Implementation of 11 core GoF design patterns in Java.
    *   *Creational:* Singleton, Factory Method, Builder Pattern
    *   *Structural:* Adapter, Decorator, Proxy Pattern
    *   *Behavioral:* Observer, Strategy, Command, Dependency Injection, MVC Pattern
*   **PL/SQL Database Scripting:** SQL scripts covering advanced database logic and transaction control.
    *   *Control Structures, Error Handling, Stored Procedures, Functions, Triggers, Cursors, and Packages.*
*   **Testing & Logging:** Unit testing basics using **JUnit 5**, **Mockito** mocking framework, and **SLF4J** logging.

#### 🔹 [Week 2: Spring Framework & Object-Relational Mapping (ORM)](file:///a:/1.%20Placement/Cognizant/DeepSkill/Week-2)
*   **Spring Core & Maven:** Creating IoC/DI container applications.
    *   *LibraryManagement:* Demonstrating bean configurations, constructor/setter dependency injections, and XML vs Annotation configurations.
*   **Spring Data JPA with Hibernate:** Data access layer abstraction.
    *   *orm-learn:* Direct mappings of Java classes to SQL tables using JPA annotations, JPQL queries, custom query methods, and transaction management.

#### 🔹 [Week 3: Spring Boot REST APIs & Security](file:///a:/1.%20Placement/Cognizant/DeepSkill/Week-3)
*   **Spring REST Service:** Building production-ready REST controllers with validation, custom exception handlers, and response formatting.
    *   *spring-learn:* Hand-on REST web service managing countries and departments, integrating global HTTP exception handling.
*   **Token Authentication:** Implementation of stateless authentication using **JSON Web Tokens (JWT)**.

#### 🔹 [Week 4: Microservices Architecture](file:///a:/1.%20Placement/Cognizant/DeepSkill/Week-4)
*   **Distributed System Infrastructure:**
    *   *config-server:* Centralized configuration server sourcing profiles from local/git configs.
    *   *eureka-discovery-server:* Service registry for microservice registration and dynamic discovery.
    *   *api-gateway:* Spring Cloud API Gateway routing traffic, applying security, rate-limiting, and custom filtering.
*   **Functional Microservices:**
    *   `auth-service`, `greet-service`, `account-service`, `loan-service`, `product-service` communicating via Feign clients with client-side load balancing.
    *   **Resilience & Rate-Limiting:** Implementation of custom rate limiters and circuit breakers via Resilience4j.

#### 🔹 [Week 5: ReactJS Fundamentals (Core)](file:///a:/1.%20Placement/Cognizant/DeepSkill/Week-5)
*   **Core Concept Hands-on Labs:**
    *   `myfirstreact` (HOL 1) - Setup, create-react-app basics.
    *   `StudentApp` (HOL 2) - Creating and rendering React Class Components.
    *   `scorecalculatorapp` (HOL 3) - Functional components and CSS styling.
    *   `cohortdetailsapp` (HOL 5) - Working with **CSS Modules** (`.module.css`) and dynamic conditional classes.
    *   `shoppingapp` (HOL 7) - Prop drilling, custom props, and loop renders.
    *   `counterapp` (HOL 8) - State initialization and event handling (`useState`).
    *   `cricketapp` (HOL 9) - Blending ES6 features (`map()`, arrow functions, destructuring) inside React.
    *   `officespacerentalapp` (HOL 10) - JSX expressions, image attributes, and inline conditional CSS styling.

#### 🔹 [Week 6: Advanced ReactJS & Testing](file:///a:/1.%20Placement/Cognizant/DeepSkill/Week-6)
*   **Advanced Concept Hands-on Labs:**
    *   `blogapp` (HOL 4) - Component lifecycle hooks (`componentDidMount`), REST API integration, and error boundaries (`componentDidCatch`).
    *   `TrainersApp` (HOL 6) - Client-side Routing using `react-router-dom` (BrowserRouter, Routes, Link, Route, and useParams hook).
    *   `eventexamplesapp` (HOL 11) - Synthetic events, multiple handlers, and custom input converter sub-components.
    *   `ticketbookingapp` (HOL 12) - Conditional rendering based on authentication state (Guest vs User page structures).
    *   `bloggerapp` (HOL 13) - Advanced conditional rendering using element variables and map functions.
    *   `employeeapp` (HOL 14) - State sharing across nested child levels using the **React Context API** (`useContext` and context providers).
    *   `ticketraisingapp` (HOL 15) - Unvalidated React forms with input and textarea management.
    *   `mailregisterapp` (HOL 16) - Form input validation (validation triggers on submit).
    *   `fetchuserapp` (HOL 17) - Consuming asynchronous REST endpoints using native `fetch` client inside lifecycle events.
    *   `cohortstestapp` (HOL 18) - Unit Testing React components with **Jest** and **Enzyme** (shallow mounting, snapshot testing).
    *   `gitclientapp` (HOL 19) - Isolation testing, mock configurations, and spying on **Axios** requests in tests.

---

## 🛠️ Environment Prerequisites & Running Locally

### Backend (Java & Spring)
1. Ensure Java JDK 17+ and Maven are installed.
2. Build the root project or a specific module:
   ```bash
   mvn clean install
   ```
3. Run microservices in order:
   - Config Server ➔ Discovery Server ➔ Gateway ➔ Individual microservices.

### Frontend (React NPM Workspaces)
To run the React labs, navigate to either `DeepSkill/Week-5/Solution of React` or `DeepSkill/Week-6/Solution of React`. They are structured as NPM Workspace repositories:

```bash
# Install dependencies for all nested workspaces at once
npm install

# Run a specific application (e.g. StudentApp in Week 5)
npm start --workspace=StudentApp

# Run tests for a specific testing suite (e.g. cohortstestapp in Week 6)
$env:CI="true"; npm test --workspace=cohortstestapp
```