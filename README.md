# 📦 Warehouse Management System  

A GUI-based application for managing client orders, inventory, and billing in a warehouse, designed for efficiency and ease of use.  

---

## 📖 Table of Contents  
- [📌 Description](#-description)  
  - [💛 Motivation](#-motivation)  
  - [🎯 Why this project?](#-why-this-project)  
  - [✅ What problem does it solve?](#-what-problem-does-it-solve)  
  - [💡 What I learned](#-what-i-learned)  
- [🚀 Usage](#-usage)  
  - [📥 Key Functionalities](#-key-functionalities)  
  - [🖼️ Interface Preview](#%EF%B8%8F-interface-preview)  
- [✨ Features](#-features)  
- [🛠️ Built With](#%EF%B8%8F-built-with)  

---

## 📌 Description  

This project simplifies warehouse operations by providing tools to manage clients, orders, stock levels, and billing through an intuitive GUI.  

### 💛 Motivation  
Manual order management and inventory tracking are error-prone. This system automates these tasks, ensuring accuracy and saving time.  

### 🎯 Why this project?  
I aimed to explore **layered architecture**, **JDBC**, and real-time GUI-database synchronization while building a practical tool for businesses.  

### ✅ What problem does it solve?  
It reduces manual errors in order processing, streamlines inventory updates, and generates invoices automatically.  

### 💡 What I learned  
- How to use **Java Swing** for responsive GUI design.  
- How to integrate **JDBC** for MySQL database integration.  
- How to implement a **layered architecture** (Presentation, Business Logic, Data Access).  
- How to execute **CRUD operations** with real-time data synchronization.  

---

## 🚀 Usage  

### 📥 Key Functionalities  
1. **Client/Product Management**:  
   - Add, update, or delete clients/products.  
   - Example: Add client `Someone, Somewhere 20, someone@gmail.com`.  
2. **Order Processing**:  
   - Place orders with stock validation.  
   - Example: Order `5 units of Product X` (fails if stock < 5).  
3. **Billing**:  
   - Automatically generate invoices for orders.  

### 🖼️ Interface Preview  

<img width="400" src="https://github.com/user-attachments/assets/c3feb28f-0d7f-49b8-bf93-7cd1bbbf6da6" />  


---

## ✨ Features  
✅ **CRUD Operations**  
- Manage clients, products, and orders seamlessly.  

<img width="400" src="https://github.com/user-attachments/assets/d80b9dea-b973-444d-850f-e709073198e7" />


✅ **Real-Time Sync**  
- GUI updates instantly with database changes.  

<img width="400" src="https://github.com/user-attachments/assets/3f357740-8e1e-42c0-8458-08fb60104cd1" />  


✅ **Stock Validation**  
- Prevents orders exceeding available stock.  

<img width="400" src="https://github.com/user-attachments/assets/cb08cc53-2764-4e95-b189-cada6dd8eed9" />  


✅ **Layered Architecture**  
- Clear separation of Presentation, Business Logic, and Data Access layers.  


✅ **Input Validation**  
- Ensures valid data formats for emails, IDs, and quantities.  

---

## 🛠️ Built With  
| Technology  | Description                     |  
|-------------|---------------------------------|  
| **Java**    | Core programming language       |  
| **Swing**   | GUI framework                   |  
| **JDBC**    | Database connectivity           |  
| **MySQL**   | Database management             |  
