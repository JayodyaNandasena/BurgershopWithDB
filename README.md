# 🍔 Burger Shop Management System

A **Java Swing** application to manage a Burger Shop with persistent data storage using **MySQL**. This app allows users to place and manage burger orders, search for orders/customers, and update existing data, all within a user-friendly graphical interface.

---

## 📌 Features

### ✅ Place Order
- Automatically generates unique Order IDs (e.g., `B0001`, `B0002`, ...).
- Prompts the user to select whether the order is for an existing customer or a new customer.
  - **If existing customer:**
    - Prompts for Customer ID.
    - Checks if the ID exists in the database.
    - If found, retrieves and uses the existing customer information.
    - If not found, prompts the user to re-enter the ID or choose to add a new customer.
  - **If new customer:**
    - Automatically generates a unique Customer ID (e.g., `C0001`, `C0002`, ...).
    - Prompts for the customer’s name.
    - Saves the new customer to the database.
- Calculates the total bill (`burger price × quantity`).
- Asks for confirmation before saving the order.

### 📋 View Orders
- View all orders.
- View orders based on their status:
  - Delivered Orders
  - Preparing Orders
  - Cancelled Orders

### ✏️ Update Order Details
- Only `PREPARING` orders can be updated.
- User can choose to update:
  - Burger quantity (must be greater than 0)
  - Order status (to Delivered or Cancelled)
- Displays updated information after changes.

### 👑 Search Best Customer
- Displays all customers in descending order of total purchase value.

### 🔍 Search Order
- Allows searching for order details by Order ID.
- If Order ID exists, displays full order details.
- Shows appropriate message if Order ID is not found.

### 👤 Search Customer
- Allows searching for a customer by Customer ID.
- Displays all orders made by that customer.
- Shows message if customer is not found.

---

## 🛠️ Tech Stack

- **Java** (Swing for GUI)
- **MySQL** (via JDBC)

---

## 🗃️ Database Schema

### Table: `orders`

| Column         | Type         | Description                           |
|----------------|--------------|---------------------------------------|
| `id`     | VARCHAR(5)   | Unique ID starting with 'B'           |
| `quantity`     | INT          | Number of burgers ordered             |
| `status`       | INT          | Preparing, Delivered, Cancelled |
| `value`        | INT          | Total bill (quantity × burger price)           |
| `customer_id`  | VARCHAR(10)  | Foreign key referencing customer table    |

### Table: `customer`

| Column         | Type         | Description                           |
|----------------|--------------|---------------------------------------|
| `id`     | VARCHAR(5)   | Unique ID starting with 'C'           |
| `name`| VARCHAR(100) | Customer's name                       |


---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/JayodyaNandasena/BurgershopWithDB.git
cd BurgershopWithDB
```

### 2. Set Up MySQL

1. Open your MySQL client (such as **MySQL Workbench**, **phpMyAdmin**, or terminal).
2. Run the following SQL to create the database and the required table:

    ```sql
    CREATE DATABASE burgershop_db;
    USE burgershop_db;

    CREATE TABLE `customer` (
      `id` varchar(5) NOT NULL,
      `name` varchar(75) NOT NULL,
      PRIMARY KEY (`id`)
    );

    CREATE TABLE `orders` (
      `id` varchar(5) NOT NULL,
      `status` enum('PREPARING','DELIVERED','CANCELLED') NOT NULL,
      `quantity` int NOT NULL,
      `value` double(7,2) NOT NULL,
      `customerId` varchar(5) NOT NULL,
      PRIMARY KEY (`id`),
      KEY `customerId` (`customerId`),
      CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`customerId`) REFERENCES `customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
    );
    ```

### 3. Configure Database Connection

Update your MySQL credentials in the `src/db/DBConnection.java`:

```java
private static final String USER = "your_mysql_username";
private static final String PASSWORD = "your_mysql_password";
```
Make sure:
- The MySQL server is running.
- The credentials are correct.
- The JDBC driver is properly added to your project dependencies (e.g., mysql-connector-java in your classpath).

### 4. Run the Application
1. Open the project in your preferred Java IDE (such as IntelliJ IDEA, Eclipse, or NetBeans).
2. Build and run the main class (`src/Main.java`).
3. The Java Swing UI will launch and display the burger shop management system.

### 5. Usage Guide
1. Start with "Place Order" to create a new burger order.
2. Use the navigation to:
   - 📦 **View Orders** by status: Preparing, Delivered, or Cancelled
   - ✏️ **Update Orders**: Modify burger quantity or change the order status (only for orders that are still Preparing)
   - 🔍 **Search** for specific orders or customers using their IDs
   - 🏆 **View Best Customers** ranked by their total purchase value

All data is persisted in the MySQL database.

---

## 🏁 Conclusion
Thank you for exploring the Burger Shop Management System! This application provides a user-friendly interface for managing burger orders and customer data while ensuring that all information is stored securely in a MySQL database.

Feel free to customize and extend the application as needed. If you encounter any issues or have any suggestions, please feel free to open an issue or contribute to the repository.

---

## 📫 Contact
If you have any questions or suggestions, please reach out to:

  GitHub: [JayodyaNandasena](https://github.com/JayodyaNandasena)
  
  Email: jayodyanandasena@gmail.com
