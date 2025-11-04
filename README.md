# Alex's Student Attendance Management System

A Java-based backend web application to manage students, courses, and attendance records using RESTful services. This project was developed as part of TechProAcademy's Full-Stack stream semester project.

---

## 📌 Project Overview

This system provides a backend-only interface for managing student data, course enrollments, and attendance records. It is designed to operate entirely in-memory, with no reliance on a database, making it lightweight and simple for educational and demonstration purposes.

The application utilizes core Java and Java EE technologies and runs on an Apache Tomcat server. It demonstrates object-oriented programming, servlet-based web service design, and handling of HTTP requests and responses.

---

## 🧱 Technologies Used

- **Java SE** – Core Java functionality
- **Java EE (Servlets)** – For handling REST-style HTTP requests
- **Apache Tomcat** – For local server deployment
- **In-Memory Data Storage** – No external database required
- **XML** – Servlet configuration using `web.xml`

---

## 🧑‍🎓 Entities

### `Student`
- Unique ID
- Full Name
- Email Address
- List of Enrolled Course IDs

### `Course`
- Unique ID
- Course Name
- Instructor Name

### `AttendanceRecord`
- Unique ID
- Associated Student ID
- Associated Course ID
- Date of Record
- Presence Status (true/false)

---
