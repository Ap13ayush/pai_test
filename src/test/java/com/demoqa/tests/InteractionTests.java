package com.demoqa.tests;

import com.demoqa.base.BaseTest;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * InteractionTests class containing test scenarios for handling complex UI elements
 * including Alerts and iFrames on DemoQA website.
 */
public class InteractionTests extends BaseTest {
    
    @Test(groups = {"Smoke", "Regression"}, description = "Test handling confirm alert box")
    public void testHandleAlerts() {
        // Navigate to Alerts page
        driver.get(BASE_URL + "/alerts");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // Find and click the button that triggers confirm box
        WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.id("confirmButton")));
        
        // Scroll to button and click
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", confirmButton);
        confirmButton.click();
        
        // Switch to alert and accept it
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        System.out.println("Alert text: " + alertText);
        alert.accept();
        
        // Wait for confirmation message and verify
        WebElement confirmResult = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.id("confirmResult")));
        
        String confirmationMessage = confirmResult.getText();
        Assert.assertEquals(confirmationMessage, "You selected Ok", 
            "Confirmation message should be 'You selected Ok'");
        
        System.out.println("Alert handling test passed successfully!");
    }
    
    @Test(groups = {"Smoke", "Regression"}, description = "Test handling iFrames")
    public void testHandleIFrames() {
        // Navigate to Frames page
        driver.get(BASE_URL + "/frames");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // Wait for the iframe to be available and switch to it
        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.id("frame1")));
        
        // Switch to the iframe
        driver.switchTo().frame(iframe);
        
        // Read the heading text from inside the iframe
        WebElement heading = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.id("sampleHeading")));
        
        String headingText = heading.getText();
        System.out.println("Text inside iframe: " + headingText);
        
        // Assert that the text is correct
        Assert.assertEquals(headingText, "This is a sample page", 
            "The heading text should be 'This is a sample page'");
        
        // Switch back to main content
        driver.switchTo().defaultContent();
        
        System.out.println("iFrame handling test passed successfully!");
    }
}