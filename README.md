# CustomerApp

## Overview

This is a Spring Boot application that manages customer data. It provides functionalities such as customer registration, deletion, and synchronization with a remote API.

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Endpoints](#endpoints)
- [Configuration](#configuration)

## Features

- login
- CRUD operations with customer data
- Customer data synchronization with a remote API
- JWT-based authentication for secure operations

## Prerequisites

- Java 21
- Maven
- Spring Boot 

## Installation

- clone the repo
- open the project in your favourite IDE
- **Build the Application:**

    ```bash
    mvn clean install
    ```
- **Run the Application:**
    ```bash
    mvn spring-boot:run
    ```
-  **Access the Application at** `http://localhost:8080` (or as configured).
  

## Endpoints

###login

**GET** `/`

Redirected to login page

use login_id: test@sunbasedata.com  password:Test@123

### Register Customer

**POST** `/customer/register-customer`

Register a new customer.

### Delete Customer

**GET** `/customer/delete-customer/{id}/{token}`

Delete a customer by ID.

### Sync Customer

**POST** `/customer/sync-customer/{token}`

Synchronize customer data with a remote API.

### Update Customer

**PUT** `/customer/update-customer`

Update customer details.

### Get All Customers

**GET** `/customer/get-all-customer`

Retrieve details of all customers.

### Get Customer by ID

**GET** `/customer/get-customer/{id}`

Retrieve details of a specific customer by ID.

## Configuration

- `application.properties`: Contains application-specific configurations.
- `WebConfig.java`: Configures web interceptors.
- `JwtUtils.java`: Handles JWT token generation and validation.

