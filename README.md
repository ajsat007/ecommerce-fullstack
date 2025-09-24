# 🛒 Full-Stack E-Commerce Website
A production-ready full-stack E-Commerce website built with ReactJS, TailwindCSS, Framer Motion, Spring Boot, and MySQL. Features JWT authentication, role-based access, product management, cart &amp; orders, animated responsive UI, Stripe test payments, and Dockerized deployment with Nginx.

A production-ready **Java + React Full-Stack E-Commerce application**, with:

* **Frontend**: ReactJS (Vite), TailwindCSS, Framer Motion
* **Backend**: Spring Boot, Spring Security (JWT), Flyway migrations
* **Database**: MySQL
* **Deployment**: Docker + Nginx + docker-compose

---

## 🚀 Features

* 🔐 JWT Authentication (User & Admin roles)
* 👤 User Profile + Auth system
* 📦 Products (CRUD for Admin, Browse for Users)
* 🛒 Cart & Orders (user checkout flow)
* 💳 Payment simulation (Stripe test keys or mock gateway)
* 📊 Admin Dashboard (manage products & orders)
* 🎨 Responsive Animated UI (Framer Motion + TailwindCSS)
* 📄 API Documentation via Swagger/OpenAPI
* 🐳 Dockerized for local + production setup

---

## 📂 Project Structure

```
ecommerce-fullstack/
│── backend/             # Spring Boot backend
│   ├── src/main/java/com/ecommerce/...
│   ├── src/main/resources/db/migration/  # Flyway migrations
│   ├── Dockerfile
│   └── pom.xml
│
│── frontend/            # React + Vite frontend
│   ├── src/pages/...
│   ├── src/components/...
│   ├── Dockerfile
│   └── package.json
│
│── infra/nginx/
│   └── nginx.conf       # Nginx reverse proxy for production
│
│── docker-compose.yml   # Orchestration (backend + frontend + MySQL + Nginx)
│── .env.example         # Example environment variables
│── README.md
```

---

## ⚙️ Setup Instructions

### 1. Clone & Enter Project

```bash
git clone <repo-url> ecommerce-fullstack
cd ecommerce-fullstack
```

### 2. Configure Environment Variables

Copy `.env.example` → `.env` and update values:

```
MYSQL_ROOT_PASSWORD=secret
MYSQL_DATABASE=ecommerce
MYSQL_USER=ecom_user
MYSQL_PASSWORD=ecom_pass
JWT_SECRET=my-jwt-secret
STRIPE_SECRET_KEY=sk_test_123456789
```

### 3. Run Locally (Dev Mode)

Backend:

```bash
cd backend
mvn spring-boot:run
```

Frontend:

```bash
cd frontend
npm install
npm run dev
```

* Backend → [http://localhost:8080](http://localhost:8080)
* Frontend → [http://localhost:3000](http://localhost:3000)

### 4. Run with Docker (Production)

```bash
docker-compose up --build
```

* App served at: [http://localhost](http://localhost)
* Nginx routes traffic:

  * `/api` → Spring Boot backend
  * `/` → React frontend

---

## 👥 Test Users

* **Admin**

  * Email: `admin@example.com`
  * Password: `Admin@123`

* **Customer**

  * Email: `user@example.com`
  * Password: `User@123`

---

## 📜 API Documentation

After backend starts, visit:
👉 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 🛠️ Tech Stack

* **Frontend**: React, TailwindCSS, Framer Motion, React Router, Axios
* **Backend**: Spring Boot, Spring Security, JWT, Flyway
* **Database**: MySQL
* **Deployment**: Docker, Docker Compose, Nginx

---

## 📌 Next Steps

* Implement Stripe integration (live/test)
* Add Redux Toolkit for complex state
* Improve admin dashboard analytics
* Add CI/CD (GitHub Actions + Docker Hub + AWS/GCP/Azure deployment)

---

