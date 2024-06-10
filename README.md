# **Web Automation Framework**

### **Description**

This Java framework automates web UI testing using Selenium WebDriver, managed by Maven and executed through TestNG. Here are some main features of the framework:

* Runs tests on multiple environments (local, QA, production, etc.)
* Runs tests in single-threaded or parallel mode
* Supports cross-browser testing
* Enables data-driven testing with Excel/JSON reader and data providers
* Generates test data automatically using datafaker and model/data factory classes
* Provides keywords for interacting with web UI elements
* Connects to and queries databases
* Generates test reports using Allure reports
* Supports CI/CD integration with GitHub Actions and Jenkins

![Static Badge](https://img.shields.io/badge/Java-green)
![Static Badge](https://img.shields.io/badge/Maven-blue?style=flat)
![Static Badge](https://img.shields.io/badge/Selenium-purple?style=flat)

![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/hongsim-tran/STAutomationFramework/test-execution.yml)
![Static Badge](https://img.shields.io/badge/Test%20report%20-%20orange?style=flat&link=https%3A%2F%2Fgithub.com%2Fhongsim-tran%2FSTAutomationFramework%2Fdeployments%2Fgithub-pages)

**Example of running tests on local environemnt**


https://github.com/hongsim-tran/WebAutomationFramework/assets/29735755/9383b9fd-c239-4559-9515-dc0936636715



**Example of running tests on production environment**



https://github.com/hongsim-tran/WebAutomationFramework/assets/29735755/c5cea01b-68bb-4c44-aa30-c9ef0cacb524



**Example of running tests from Github Actions**



https://github.com/hongsim-tran/WebAutomationFramework/assets/29735755/05839733-2660-4b91-b6b4-697662704f63





### **Prerequisites**

* Java 21

* Maven 3.9.6 (This build includes a Maven wrapper, so you don't need to install Maven on your system to run the project's build commands)

* Allure Report 2.28.0

* For my sample testing project, I'm using [Evershop](https://github.com/evershopcommerce/evershop?tab=readme-ov-file)

### **Installation**

1. **Clone the Project:**

    `git clone https://github.com/hongsim-tran/STAutomationFramework.git`


2. **Run Tests:**


    `cd STAutomationFramework`

There are 2 profiles supported: local and production.
* Local environment:
  * Prerequisite: Have [Evershop](https://evershop.io/docs/development/getting-started/installation-guide) installed and started locally. 
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
* Github Actions: CI/ CD tool
* Jenkins: CI/ CD tool



### **Project Structure**


````
Web Automation Framework
├── .github
│   └── workflows
│       └── test-execution.yml
├── Jenkinsfile
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
        │       │   ├── constants
        │       │   │   └── Framework.java
        │       │   ├── enums
        │       │   │   ├── IframeIdentifier.java
        │       │   │   └── ...
        │       │   ├── exceptions
        │       │   │   └── ProvidedClassNotAnEnum.java
        │       │   ├── report
        │       │   │   ├── AllureManager.java
        │       │   │   └── MyListener.java
        │       │   ├── retry
        │       │   │   ├── AnnotationTransformer.java
        │       │   │   └── RetryAnalyzer.java
        │       │   ├── utils
        │       │   │   ├── CurrencyUtils.java
        │       │   │   ├── DBConnection.java
        │       │   │   ├── DateTimeUtils.java
        │       │   │   ├── ExcelReader.java
        │       │   │   ├── MyLogger.java
        │       │   │   └── RandomGenerator.java
        │       │   └── wdm
        │       │       ├── BrowserArguments.java
        │       │       ├── BrowserFactory.java
        │       │       └── DriverManager.java
        │       └── evershop
        │           ├── datafactory
        │           │   └── CouponDataFactory.java
        │           ├── dbqueries
        │           │   └── Queries.java
        │           ├── models
        │           │   └── NewCouponModel.java
        │           ├── pages
        │           │   ├── Page.java
        │           │   └── store
        │           │       ├── Homepage.java
        │           │       └── ...
        │           └── tests
        │               └── store
        │                   ├── CategoryTests.java
        │                   └── ...
        └── resources
            ├── config
            │   └── config.properties
            ├── suites
            │   ├── local.xml
            │   └── prod.xml
            └── test-data

````

**Naming convention for Web UI elements**
```
+----------+----------------------------+--------+-----------------+
| Category |      UI/Control type       | Prefix |     Example     |
+----------+----------------------------+--------+-----------------+
| Basic    | Button                     | btn    | btnExit         |
| Basic    | Check box                  | chk    | chkReadOnly     |
| Basic    | Combo box                  | cbo    | cboEnglish      |
| Basic    | Common dialog              | dlg    | dlgFileOpen     |
| Basic    | Date picker                | dtp    | dtpPublished    |
| Basic    | Dropdown List / Select tag | ddl    | ddlCountry      |
| Basic    | Form                       | frm    | frmEntry        |
| Basic    | Frame                      | fra    | fraLanguage     |
| Basic    | Image                      | img    | imgIcon         |
| Basic    | Text                       | txt    | txtDescription  |
| Basic    | Links/Anchor Tags          | lnk    | lnkForgotPwd    |
| Basic    | List box                   | lst    | lstPolicyCodes  |
| Basic    | Menu                       | mnu    | mnuFileOpen     |
| Basic    | Radio button / group       | rdo    | rdoGender       |
| Basic    | RichTextBox                | rtf    | rtfReport       |
| Basic    | Table                      | tbl    | tblCustomer     |
| Basic    | TabStrip                   | tab    | tabOptions      |
| Basic    | Text area                  | txa    | txaDescription  |
| Basic    | Text box                   | txb    | txbLastName     |
| Complex  | Chevron                    | chv    | chvProtocol     |
| Complex  | Data grid                  | dgd    | dgdTitles       |
| Complex  | Data list                  | dbl    | dblPublisher    |
| Complex  | Directory list box         | dir    | dirSource       |
| Complex  | Drive list box             | drv    | drvTarget       |
| Complex  | File list box              | fil    | filSource       |
| Complex  | Panel/Fieldset             | pnl    | pnlGroup        |
| Complex  | ProgressBar                | prg    | prgLoadFile     |
| Complex  | Slider                     | sld    | sldScale        |
| Complex  | Spinner                    | spn    | spnPages        |
| Complex  | StatusBar                  | sta    | staDateTime     |
| Complex  | Timer                      | tmr    | tmrAlarm        |
| Complex  | Toolbar                    | tlb    | tlbActions      |
| Complex  | TreeView                   | tre    | treOrganization |
+----------+----------------------------+--------+-----------------+
```
