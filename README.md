
# 📚 Library Management System

A basic **Library Management System** using **Java**, **MySQL**, and a minimal **web interface**. 
This system helps librarians, students, and faculty efficiently manage book issues, returns, and fines. 🚀

---

## ✨ Features

- 🔍 **Search, issue, and return books**.
- ⏳ **Fine calculation** for overdue books.
- 🛡️ **Role-based permissions** for students, faculty, and librarians.

---

## 🛠️ Technologies Used

- ☕ **Java**: Core application logic.
- 🗃️ **MySQL**: Database for storing data.
- 🎨 **HTML/CSS**: Web interface for user interaction.
- 🐱 **Tomcat**: Web server for running the app.

---

## ⚙️ Installation

### Prerequisites
- ☕ **Java** (JDK 11+)
- 🗃️ **MySQL**
- 🐱 **Apache Tomcat**

### Steps
1. Clone the repository:
   ```bash
   git clone git@github.com:marielouisehanna/library-management-system.git
   cd library-management-system
   ```

2. Set up the database:
   ```bash
   mysql -u root -p < db/schema.sql
   ```
   Update `db/config.env` with your DB credentials.

3. Compile and run:
   ```bash
   javac src/*.java
   java src.App
   ```

4. Access the app in your browser at `http://localhost:8080`. 🌐

---

## 🖥️ Usage

- **Librarian**: Add/search books, manage users.
- **Students/Faculty**: Search and issue books, view fines.

---

