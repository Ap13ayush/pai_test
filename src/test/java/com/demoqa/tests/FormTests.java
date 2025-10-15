package com.demoqa.tests;

import com.demoqa.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * FormTests class containing test scenarios for form submissions and assertions
 * including Text Box and Check Box functionality on DemoQA website.
 */
public class FormTests extends BaseTest {
    
    @Test(groups = {"Smoke", "Regression"}, description = "Test text box submission with name and email")
    public void testTextBoxSubmission() {
        // Navigate to Text Box page
        driver.get(BASE_URL + "/text-box");
        
        // Test data
        String fullName = "John Doe";
        String email = "john.doe@example.com";
        
        // Find and fill out the Full Name field
        WebElement fullNameField = driver.findElement(By.id("userName"));
        fullNameField.clear();
        fullNameField.sendKeys(fullName);
        
        // Find and fill out the Email field
        WebElement emailField = driver.findElement(By.id("userEmail"));
        emailField.clear();
        emailField.sendKeys(email);
        
        // Scroll to submit button and click
        WebElement submitButton = driver.findElement(By.id("submit"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
        
        // Wait for output to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("output")));
        
        // Verify that the output contains the submitted name and email
        String outputText = output.getText();
        Assert.assertTrue(outputText.contains("Name:" + fullName), 
            "Output should contain the submitted full name");
        Assert.assertTrue(outputText.contains("Email:" + email), 
            "Output should contain the submitted email");
        
        System.out.println("Text Box Submission test passed successfully!");
    }
    
    @Test(groups = {"Smoke", "Regression"}, description = "Test check box selection and verification")
    public void testCheckBoxSelection() {
        // Navigate to Check Box page
        driver.get(BASE_URL + "/checkbox");
        
        // Wait for page to load and expand Home directory
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // Click on the expand button for Home directory
        WebElement homeExpandButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("button[title='Expand all']")));
        homeExpandButton.click();
        
        // Wait a moment for expansion
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Find and click the Documents checkbox
        WebElement documentsCheckbox = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//span[text()='Documents']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", documentsCheckbox);
        documentsCheckbox.click();
        
        // Wait for result text to appear
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.id("result")));
        
        // Verify that Documents has been selected
        String resultText = result.getText();
        Assert.assertTrue(resultText.contains("documents"), 
            "Result should confirm that Documents has been selected");
        
        System.out.println("Check Box Selection test passed successfully!");
    }
}