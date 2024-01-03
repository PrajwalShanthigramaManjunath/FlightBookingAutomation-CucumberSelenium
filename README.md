# Automation project on Flight Search  - Ryanair

 This test automation suite is designed to perform flight search and booking activities on the Ryanair website. It includes the automation of searching for a flight, selecting fares, navigating through extras pages, and verifying certain elements. Ryanair https://www.ryanair.com/ie/en.

## Technologies Used

Maven: v3.9.5
Selenium Java: v4.10.0
JUnit: v4.13.2
Cucumber: v7.13.0
TestNG: v7.7.0
Java: v17.0.9
Chrome Driver: v120.0.6099.109

## Setup and Execution
### Clone the Repository
To clone this project from GitHub, use the following command:

    git clone <repository_url>

### Running with Maven

Maven is used as the build tool. Execute the following command to run the automation:

    mvn clean test
Maven Wrapper
Maven Wrapper has been included in the project. To run using the Maven Wrapper:

Unix-based systems:

    ./mvnw clean test
 
Windows:

    mvnw.cmd clean test

    
## IDE Setup
To run the project from IDE,
>    src\test\java\CucumberFramework\MainRunner.Java > Run or Run as > JUnit Test  


### Reporting
Reports are written to the ***/target*** directory. 
* HTML report  -     ***target/cucumber***
* JSON - ***target/cucumber.json***
* Extent HTML Report - ***target/report.html***

## Development

### Design Considerations 
#### Singleton Design Pattern 

Singleton design pattern was used to maintain a single instance of the browser session across the steps, ensuring efficiency and consistency throughout the test run. 

### Behavior-Driven Development (BDD) 

In the project, I've adopted Behavior-Driven Development (BDD) principles to collaboratively define software behavior. This involves close cooperation among developers, QA, and stakeholders like business analysts. Together, we articulate how the software should behave through scenarios written in a language understandable by all involved parties.

### Using Cucumber and Gherkin for Flight Search:
Within the project, I've utilized Cucumber and Gherkin to outline the behavior of the flight search feature. Departure locations, destinations, and dates for flights have been parameterized using Scenario Outlines and Examples. This enables me to create flexible scenarios by providing different inputs for these parameters. For instance, scenarios involving various departure cities, destinations, and dates are written once as templates and executed multiple times with distinct inputs, ensuring comprehensive testing of the flight search functionality.

#### Technology Selection

The selected technologies were chosen for their specific strengths: 

Selenium: Widely used for web automation due to its robustness and browser compatibility. 
Cucumber: Used for BDD-style test writing and readability. 
JUnit/TestNG: For test execution and reporting. Code Structure Project Structure 
Runner Class: Orchestrates the test execution. 
Feature Files: Written in Gherkin format to define test scenarios. 
Step Classes: Contain the step definitions corresponding to the feature files. 
Locators: Employed for identifying elements using XPath, CSS, etc.




