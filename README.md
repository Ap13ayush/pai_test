# DemoQA Test Automation Suite

A comprehensive test automation suite for the DemoQA website using Selenium WebDriver, Java, and TestNG framework.

## Project Overview

This project implements automated test scenarios for various UI components on the DemoQA website including:

- **Form Testing**: Text box submissions and checkbox selections
- **UI Interactions**: Alert handling and iFrame operations  
- **Data-Driven Testing**: Practice form submission with multiple data sets

## Technology Stack

- **Java 11+**
- **Selenium WebDriver 4.15.0**
- **TestNG 7.8.0**
- **WebDriverManager 5.6.2**
- **Maven** (Build tool)

## Project Structure

```
testnow/
├── src/
│   ├── main/java/
│   └── test/
│       ├── java/com/demoqa/
│       │   ├── base/
│       │   │   └── BaseTest.java
│       │   └── tests/
│       │       ├── FormTests.java
│       │       ├── InteractionTests.java
│       │       └── DataDrivenStudentFormTests.java
│       └── resources/
│           └── testng.xml
├── pom.xml
└── README.md
```

## Test Classes

### 1. FormTests
- **testTextBoxSubmission**: Tests text box form submission with name and email validation
- **testCheckBoxSelection**: Tests checkbox selection and result verification

### 2. InteractionTests  
- **testHandleAlerts**: Tests confirm alert handling and verification
- **testHandleIFrames**: Tests iFrame switching and content reading

### 3. DataDrivenStudentFormTests
- **testStudentFormSubmission**: Data-driven test with @DataProvider for practice form submission

## Prerequisites

1. **Java 11 or higher** installed
2. **Maven 3.6+** installed
3. **Chrome browser** installed (tests run on Chrome by default)
4. **Git** for version control

## Setup Instructions

1. Clone the repository:
```bash
git clone <repository-url>
cd testnow
```

2. Install dependencies:
```bash
mvn clean install
```

## Running Tests

### Run All Tests
```bash
mvn test
```

### Run Specific Test Groups
```bash
# Run Smoke tests only
mvn test -Dgroups=Smoke

# Run Regression tests only  
mvn test -Dgroups=Regression
```

### Run Specific Test Class
```bash
# Run FormTests only
mvn test -Dtest=FormTests

# Run InteractionTests only
mvn test -Dtest=InteractionTests

# Run DataDrivenStudentFormTests only
mvn test -Dtest=DataDrivenStudentFormTests
```

### Run with TestNG XML
```bash
mvn test -DsuiteXmlFile=src/test/resources/testng.xml
```

## Test Configuration

### Browser Configuration
The tests are configured to run on Chrome browser with the following options:
- Window size: 1920x1080
- Disabled sandbox and dev-shm-usage for CI environments
- Implicit wait: 10 seconds

To run in headless mode, uncomment the headless option in `BaseTest.java`:
```java
options.addArguments("--headless");
```

### Test Groups
All tests are organized into two groups:
- **Smoke**: Critical functionality tests
- **Regression**: Comprehensive test coverage

## Target Application
- **URL**: https://demoqa.com/
- **Test Pages**:
  - Text Box: `/text-box`
  - Check Box: `/checkbox`  
  - Alerts: `/alerts`
  - Frames: `/frames`
  - Practice Form: `/automation-practice-form`

## Reporting
TestNG generates test reports in the `target/surefire-reports` directory after test execution.

## Git Workflow
The project follows a systematic commit approach:
- Initial setup commits
- Individual feature commits for each test class
- Clear commit messages tracking progress

## Troubleshooting

### Common Issues:
1. **WebDriver not found**: WebDriverManager automatically handles driver downloads
2. **Element not clickable**: Tests use explicit waits and JavaScript executors as fallback
3. **Timeouts**: Adjust implicit/explicit wait times in BaseTest.java if needed

## Contributing
1. Follow the existing code structure and naming conventions
2. Add appropriate TestNG annotations and groups
3. Include proper assertions and error messages
4. Update documentation for new test scenarios