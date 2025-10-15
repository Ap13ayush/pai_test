package com.demoqa.tests;

import com.demoqa.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * DataDrivenStudentFormTests class containing data-driven test scenarios
 * for the Practice Form page on DemoQA website using TestNG @DataProvider.
 */
public class DataDrivenStudentFormTests extends BaseTest {
    
    /**
     * DataProvider method that provides two different, complete sets of student data
     * @return Object[][] containing test data sets
     */
    @DataProvider(name = "studentData")
    public Object[][] getStudentData() {
        return new Object[][] {
            {
                "John", "Doe", "john.doe@example.com", "Male", "1234567890"
            },
            {
                "Jane", "Smith", "jane.smith@example.com", "Female", "9876543210"
            }
        };
    }
    
    /**
     * Data-driven test method for Practice Form submission
     * @param firstName Student's first name
     * @param lastName Student's last name
     * @param email Student's email
     * @param gender Student's gender
     * @param mobile Student's mobile number
     */
    @Test(dataProvider = "studentData", groups = {"Smoke", "Regression"}, 
          description = "Data-driven test for Practice Form submission")
    public void testStudentFormSubmission(String firstName, String lastName, 
                                        String email, String gender, String mobile) {
        
        // Navigate to Practice Form page
        driver.get(BASE_URL + "/automation-practice-form");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // Fill First Name
        WebElement firstNameField = wait.until(ExpectedConditions.elementToBeClickable(
            By.id("firstName")));
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
        
        // Fill Last Name
        WebElement lastNameField = driver.findElement(By.id("lastName"));
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
        
        // Fill Email
        WebElement emailField = driver.findElement(By.id("userEmail"));
        emailField.clear();
        emailField.sendKeys(email);
        
        // Select Gender
        WebElement genderRadio;
        if (gender.equalsIgnoreCase("Male")) {
            genderRadio = driver.findElement(By.xpath("//label[@for='gender-radio-1']"));
        } else {
            genderRadio = driver.findElement(By.xpath("//label[@for='gender-radio-2']"));
        }
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", genderRadio);
        
        // Fill Mobile Number
        WebElement mobileField = driver.findElement(By.id("userNumber"));
        mobileField.clear();
        mobileField.sendKeys(mobile);
        
        // Scroll to submit button and submit the form
        WebElement submitButton = driver.findElement(By.id("submit"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
        
        // Wait a moment for scroll to complete
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Click submit using JavaScript to avoid potential click interception
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
        
        // Wait for confirmation modal to appear
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.className("modal-content")));
        
        // Verify that the modal contains the correct student name and email
        WebElement modalBody = driver.findElement(By.className("modal-body"));
        String modalText = modalBody.getText();
        
        // Create expected full name
        String expectedFullName = firstName + " " + lastName;
        
        // Assert that the confirmation modal contains the correct name and email
        Assert.assertTrue(modalText.contains(expectedFullName), 
            "Confirmation modal should contain the student's full name: " + expectedFullName);
        Assert.assertTrue(modalText.contains(email), 
            "Confirmation modal should contain the student's email: " + email);
        
        System.out.println("Data-driven form submission test passed for: " + expectedFullName);
        
        // Close the modal for next iteration using JavaScript to avoid ad interference
        try {
            WebElement closeButton = driver.findElement(By.id("closeLargeModal"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeButton);
        } catch (Exception e) {
            // Alternative: Press Escape key to close modal
            driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
        }
        
        // Wait for modal to close
        wait.until(ExpectedConditions.invisibilityOf(modal));
    }
}