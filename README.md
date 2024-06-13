
# Cases Documentation

## Table of Contents

- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
  - [Logging In](#logging-in)
  - [Dashboard](#dashboard)
  - [Account Information](#account-information)
  - [Adding a Support Case](#adding-a-support-case)
  - [Adding a Comment](#adding-a-comment)
  - [Adding Attachments](#adding-attachments)
- [Deployment](#deployment)
- [Built With](#built-with)
- [Authors](#authors)
- [Tutorial on Using the App](#tutorial-on-using-the-app)
- [Additional Notes](#additional-notes)

---

# Getting Started

## Prerequisites
Ensure you have the following installed:
- Java Development Kit (JDK) version 11 or later
- Apache Maven
- MySQL or another relational database

## Installation
Clone the repository:
```bash
git clone https://github.com/your/repository.git
```
Navigate to the project directory:
```bash
cd your-project-directory
mvn clean package
```

# Usage

## Logging In
1. Navigate to the login page at [http://localhost:8080/login](http://localhost:8080/login).
2. Enter your username and password.
3. Click the "Login" button.

## Dashboard
- Upon logging in, you will be redirected to the dashboard ([http://localhost:8080/dashboard](http://localhost:8080/dashboard)).
- The dashboard displays all available support cases.
- Click on a support case to view details including comments and attachments.

## Account Information
- Navigate to account information at [http://localhost:8080/account-info](http://localhost:8080/account-info).
- Details such as username, email, first name, last name, phone, address, number of cases, and number of comments are displayed.

## Adding a Support Case
1. Navigate to the dashboard ([http://localhost:8080/dashboard](http://localhost:8080/dashboard)).
2. Click on the "Add Case" button.
3. Fill in the required fields (title, description).
4. Click the "Save" button to add the support case.

## Adding a Comment
1. Navigate to a specific support case from the dashboard.
2. Scroll down to the comments section.
3. Enter your comment in the provided text area.
4. Click the "Add Comment" button to save the comment.

## Adding Attachments
1. Navigate to a specific support case from the dashboard.
2. Scroll down to the attachments section.
3. Click the "Upload" button.
4. Select the file you want to upload and confirm.

# Deployment
Deploy the application to a production environment using a Java application server like Apache Tomcat or any other servlet container.

# Built With
- Java - Programming language
- Spring Boot - Java framework
- Vaadin - Java framework for building web applications
- PSQL - Relational database

# Authors
Takudzwa Josiah Mombe - [GitHub Profile](https://github.com/Takue-Mombe)

# Tutorial on Using the App

## Logging In:
- Navigate to the login page.
- Enter your credentials.
- Click "Login".

## Dashboard:
- View all support cases.
- Click on a case to view details.

## Account Information:
- View and edit personal details.

## Adding a Support Case:
- Go to the dashboard.
- Click "Add Case".
- Fill in the details.
- Click "Save".

## Adding a Comment:
- Navigate to a support case.
- Scroll to the comments section.
- Enter your comment.
- Click "Add Comment".

## Adding Attachments:
- Navigate to a support case.
- Scroll to the attachments section.
- Click "Upload".
- Select the file and confirm.

---
