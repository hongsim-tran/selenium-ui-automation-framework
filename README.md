# **ST Automation Framework**

### **Description**

This Java framework automates web UI testing using Selenium WebDriver, managed by Maven and executed through TestNG

[![Test run](https://github.com/alfio-event/alf.io/workflows/build/badge.svg)](https://github.com/hongsim-tran/STAutomationFramework/actions)


### **Prerequisites**

* Java 21

* Maven 3.9.6 (This build includes a Maven wrapper, so you don't need to install Maven on your system to run the project's build commands)

* Allure Report 2.28.0

* For my sample testing project, I'm using [Alfio](https://github.com/alfio-event/alf.io)

### **Installation**

1. **Clone the Project:**

    `git clone https://github.com/hongsim-tran/STAutomationFramework.git`


2. **Run Tests:**


    `cd STAutomationFramework`

There are 2 profiles supported: local and production.
* Local environment:
  * Prerequisite: Have [Alfio](https://github.com/alfio-event/alf.io) installed locally. 
  * Run tests: `./mvnw clean install -Plocal-test`
  
  
* Production environment:
  * Run tests: `./mvnw clean install -Pprod-test`



### **Tech Stacks**

* Java: programming language
* Maven: project management tool
* Selenium Webdriver: web automation testing tool
* TestNG: automation framework to support test creation
* Allure Report: the testing report
* Owner: handle configuration through properties files
* Log4J2: for generating and managing log
* Data faker: for generating test data
* Lombok: to simplify test data creation for the model package 
* Postgresql: as a JDBC driver to get connection to PostgresSQL database



### **Project Structure**


````
ST Automation Framework
├── pom.xml
└── src
    └── main
        ├── java
        │   └── simtran
        │       ├── core
        │       │   ├── base
        │       │   │   ├── BasePage.java
        │       │   │   └── BaseTest.java
        │       │   ├── config
        │       │   │   ├── ConfigManager.java
        │       │   │   └── Configuration.java
        │       │   ├── enums
        │       │   ├── exceptions
        │       │   ├── report
        │       │   │   ├── AllureManager.java
        │       │   │   └── MyListener.java
        │       │   ├── retry
        │       │   ├── utils
        │       │   │   ├── DBConnection.java
        │       │   │   ├── DateTimeUtils.java
        │       │   │   ├── ExcelReader.java
        │       │   │   ├── MyLogger.java
        │       │   │   └── RandomGenerator.java
        │       │   └── wdm
        │       │       ├── BrowserArguments.java
        │       │       ├── BrowserFactory.java
        │       │       └── DriverManager.java
        │       └── alfiotest
        │           ├── datafactory
        │           │   ├── EventCategoryDataFactory.java
        │           │   ├── ...
        │           ├── dbqueries
        │           │   └── Queries.java
        │           ├── models
        │           │   ├── EventCategoryModel.java
        │           │   ├── ...
        │           ├── pages
        │           │   ├── Page.java
        │           │   ├── loggedin
        │           │   │   ├── common
        │           │   │   │   ├── Navigation.java
        │           │   │   │   └── ...
        │           │   │   ├── events
        │           │   │   │   ├── EventsPage.java
        │           │   │   │   ├── ...
        │           │   │   ├── ...
        │           │   └── ...
        │           └── tests
        │               ├── loggedin
        │               │   ├── EventsTest.java
        │               │   ├── OrganizationsTest.java
        │               │   └── PreconditionBase.java
        │               └── prelogin
        │                   └── LoginTest.java
        └── resources
            ├── config
            │   └── config.properties
            ├── suites
            │   ├── local.xml
            │   └── prod.xml
            └── test-data
````

