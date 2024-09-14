# **Selenium Web UI Automation Framework**

### **Description**

This Java framework automates web UI testing using Selenium WebDriver, managed by Maven and executed through TestNG and Cucumber. Here are some main features of the framework:

* Runs tests on multiple environments (local, qa, production, etc.)
* Runs tests in single-threaded or parallel mode
* Supports cross-browser testing
* Enables data-driven testing with Excel/JSON reader and data providers
* Generates test data automatically using datafaker and model/data factory classes
* Provides keywords for interacting with web UI elements
* Connects to and queries databases
* Generates test reports using Allure reports
* Distribute tests across multiple browsers and machines using docker build and Selenium Grid
* Supports CI/CD integration with GitHub Actions and Jenkins

Additionally, the framework supports writing tests in both TDD (TestNG) and BDD (Cucumber) formats, offering flexibility in choosing the testing approach that best suits your needs

![Static Badge](https://img.shields.io/badge/Java-green)
![Static Badge](https://img.shields.io/badge/Maven-blue?style=flat)
![Static Badge](https://img.shields.io/badge/Selenium-purple?style=flat)

![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/hongsim-tran/STAutomationFramework/test-execution.yml)
![Static Badge](https://img.shields.io/badge/Test%20report%20-%20orange?style=flat&link=https%3A%2F%2Fgithub.com%2Fhongsim-tran%2FSTAutomationFramework%2Fdeployments%2Fgithub-pages)

**Example of running tests on local environment**


https://github.com/hongsim-tran/WebAutomationFramework/assets/29735755/9383b9fd-c239-4559-9515-dc0936636715



**Example of running tests on production environment**



https://github.com/hongsim-tran/WebAutomationFramework/assets/29735755/c5cea01b-68bb-4c44-aa30-c9ef0cacb524



**Example of running tests from Github Actions**



https://github.com/hongsim-tran/WebAutomationFramework/assets/29735755/05839733-2660-4b91-b6b4-697662704f63


**Cucumber test report**


<img width="1799" alt="Screenshot 2024-06-18 at 23 04 55" src="https://github.com/hongsim-tran/WebAutomationFramework/assets/29735755/f896dccd-5fee-4d9c-876b-75eb052589fd">



### **Prerequisites**

* Java 21

* Maven 3.9.6 (This build includes a Maven wrapper, so you don't need to install Maven on your system to run the project's build commands)

* Allure Report 2.28.0

* For my sample testing project, I'm using [Evershop](https://github.com/evershopcommerce/evershop?tab=readme-ov-file)

### **Installation**

1. **Clone the Project:**

  ````
  git clone https://github.com/hongsim-tran/selenium-ui-automation-framework.git
  ````


2. **Run Tests:**

  ```
  cd selenium-ui-automation-framework
  ```


There are 2 profiles supported: local and production.
  If run the TestNG tests:
* Local environment:
  * Prerequisite: Have [Evershop](https://evershop.io/docs/development/getting-started/installation-guide) installed and started locally. 
  * Run tests: 
  ```
  ./mvnw clean install -Ptestng-local
  ```
  If run the Cucumber tests:
  ```
  ./mvnw clean install -Pcucumber-local
  ```

* Production environment:
  * Run tests: 
  ````
  ./mvnw clean install -Ptestng-prod
  ````
  If run the Cucumber tests:
    ```
  ./mvnw clean install -Pcucumber-prod
    ```
* Run tests with Docker
  * Build the docker image for the tests (optional)
  ```
  docker build -t simtran/automation-tests:latest .
  ```
  * Run the tests with Docker. As the tests docker image doesn't contain browsers, we leverage browsers from separate Selenium Grid containers
  - Start the Grid container
  ```
  docker compose -f grid/grid.yaml up -d
  ```
  - Start the test execution container 
    - For TestNG tests
    
    ```
    docker compose -f grid/testng-prod.yaml up --pull=always
    ```
    - For Cucumber tests
    ```
    docker compose -f grid/cucumber-prod.yaml up --pull=always
    ```

### **Tech Stacks**

* Java: programming language
* Maven: project management tool
* Selenium Webdriver: web automation testing tool
* Selenium Grid: a tool for distributing tests across multiple browsers and machines
* TestNG: automation framework to support test creation
* Allure Report: the testing report
* Owner: handle configuration through properties files
* Log4J2: for generating and managing log
* Data faker: for generating test data
* Lombok: to simplify test data creation for the model package 
* Postgresql: as a JDBC driver to get connection to PostgresSQL database
* GitHub Actions: CI/ CD tool
* Jenkins: CI/ CD tool
* Docker

### **Project Structure**


````
Web Automation Framework
├── .ci
├── .github
├── Dockerfile
├── grid
│   ├── cucumber-prod.yaml
│   ├── grid.yaml
│   └── testng-prod.yaml
├── pom.xml
└── src
    └── main
        ├── java
        │   └── simtran
        │       ├── core
        │       │   ├── base
        │       │   │   └── BasePage.java
        │       │   ├── config
        │       │   │   ├── ConfigManager.java
        │       │   │   ├── EnvironmentConfiguration.java
        │       │   │   └── GeneralConfiguration.java
        │       │   ├── constants
        │       │   │   └── Framework.java
        │       │   ├── enums
        │       │   │   ├── IframeIdentifier.java
        │       │   │   └── Months.java
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
        │           │       ├── ProductDetailsPage.java
        │           │       └── ...
        │           └── tests
        │               ├── cucumber
        │               │   ├── features
        │               │   │   ├── ProductDetails.feature
        │               │   │   └── ...
        │               │   ├── runner
        │               │   │   ├── TestRunnerLocal.java
        │               │   │   └── TestRunnerProd.java
        │               │   └── stepDefinitions
        │               │       ├── Hooks.java
        │               │       ├── StepSetup.java
        │               │       ├── ProductDetailsStep.java
        │               │       └── ...
        │               └── testng
        │                   ├── ProductDetailsTests.java
        │                   └── ...
        └── resources
            ├── config
            │   ├── env.properties
            │   └── general.properties
            ├── suites
            │   ├── cucumber-local.xml
            │   ├── cucumber-prod.xml
            │   ├── testng-local.xml
            │   └── testng-prod.xml
            └── test-data

````

**Source code breakdown**: there are 2 main parts: `core` and `evershop` (the application under test)
* `core`: This package contains core functionalities of the framework
  * `wdm`: simplifies the process of downloading and managing WebDriver binaries for various browsers. It offers the following classes to interact with WebDriver management:
    * ``BrowserFactory``: provides methods to create new WebDriver instances for different browsers.
    ```
    CHROME {
        @Override
        public ChromeOptions getOptions(String target) {
            ChromeOptions options = new ChromeOptions();

            options.addArguments(BrowserArguments.START_MAXIMIZED);
            options.addArguments(BrowserArguments.DISABLE_NOTIFICATIONS);
            options.addArguments(BrowserArguments.DISABLE_POPUP_BLOCKING);

            if (config(target).headless())
                options.addArguments(BrowserArguments.CHROME_HEADLESS);
            return options;
        }

        @Override
        public WebDriver initDriver(String target) {
            return new ChromeDriver(getOptions(target));
        }
    }
    ```
    
    * `DriverManager`: handles the downloading and management of WebDriver binaries. 
    * `BrowserArguments`: allows you to set arguments for the browser when creating a WebDriver instance.
  * `config`: provides functionalities for managing configuration data within the web automation framework. It utilizes the Owner library to simplify reading configuration properties from a properties file
  * `base`: provides foundational classes for tests and page objects. The `BasePage` class provides the keywords to perform actions on web UI elements by passing the UI locator variable of type `By` or UI element variable of type `WebElement`. For example, a keyword to select a dropdown based on dropdown's locator or element and the dropdown option value
    ```
    protected <T> void selectDropdown(T element, String dropdownOption) {
        WebElement el;
        if (element.getClass().getName().contains("By")) {
            el = DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated((By) element));
        } else el = DriverManager.getWait().until(ExpectedConditions.visibilityOf((WebElement) element));

        Select select = new Select(el);
        MyLogger.debug(String.format("Select the option: %s for the dropdown: %s ", dropdownOption, element));
        select.selectByVisibleText(dropdownOption);
    }
    ```
  * `retry`: implements test retries on failures
  * `report`: handles test reporting using TestNG and Allure report
  * `utils`: provides utility functions for the framework such as currency handling, Database connection utilities, date time handling, logging, excel file reading, etc.
  
* `evershop`: contains application-specific code to test the "evershop" application
  * `models`: defines data models representing application entities. For example, NewCouponModel representing a new coupon
    
    ```
    public class NewCouponModel {

      String couponCode;
      String description;
      Double discountAmount;
      String discountType;
      int targetProduct;

      public enum DiscountType{
          FIXED_DISCOUNT_ENTIRE_ORDER("fixed_discount_to_entire_order"),
          PERCENTAGE_DISCOUNT_ENTIRE_ORDER("percentage_discount_to_entire_order"),
          FIXED_DISCOUNT_SPECIFIC_PRODUCTS("fixed_discount_to_specific_products"),
          PERCENTAGE_DISCOUNT_SPECIFIC_PRODUCTS("percentage_discount_to_specific_products"),
          BUY_X_GET_Y("buy_x_get_y")
          ;

          private final String type;

          DiscountType(String type) {
              this.type = type;
          }

          @Override
          public String toString() {
              return type;
          }
      }
    }
    ```

  * `datafactory`: provides methods for creating test data objects from models using datafaker
    ```
    public static NewCouponModel generateValidCouponData(NewCouponModel.DiscountType type){
        NewCouponModel newCoupon = new NewCouponModel();
        newCoupon.setCouponCode(faker.text().text(8));
        newCoupon.setDescription(faker.lorem().paragraph(5));
        newCoupon.setDiscountAmount(faker.number().randomDouble(2, 1, 99));
        newCoupon.setDiscountType(type.toString());
        return newCoupon;
    }
    ```

  * `dbqueries`: stores database queries used in tests
  * `pages`: implements the Page Object Model (POM) design pattern for the web automation framework. It provides a structured way to interact with web elements on different pages of the test application
    * `Page`: defines a foundation for all page object classes in the project. `getInstance` method in `Page` class provides a generic way to instantiate specific page object classes
    ```
    private static <TPage extends BasePage> TPage getInstance(Class<TPage> pageClass) {
        try {
            return pageClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            MyLogger.error(e.getMessage());
        }
        return null;
    }
    
    public static Homepage homepage() {
        return getInstance(Homepage.class);
    }
    ```  
    * Page Object classes encapsulate: 
      * Element locators: these classes define how to locate specific web elements on the page (e.g., buttons, text fields). 
      * Interaction methods: These methods provide functionalities to interact with the located web elements. This may include actions like clicking buttons, entering text, or retrieving element text.
      ```
        public class Homepage extends Navigation {

        //**************** Page's locators ****************
        private final By txtHeader = By.cssSelector(".h1");
        private final By btnCategoryList = By.xpath("//a[@class='button primary']");
  
        //**************** Page's attributes ****************
        public String getHeader() {
          return getText(txtHeader);
        }
    
        //**************** Page's actions ****************
        @Step
        public CategoryPage clickCategoryButton(int index) {
          click(findElements(btnCategoryList).get(index));
          return Page.categoryPage();
        }
      }
      ```
  
  * `tests`: contains automated test cases for the evershop application which are written in both TDD (TestNG) and BDD (Cucumber) formats


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
