package com.demoqa.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

/**
 * Base test class containing common setup and teardown methods
 * for all test classes in the automation suite.
 */
public class BaseTest {
    
    protected WebDriver driver;
    protected static final String BASE_URL = "https://demoqa.com";
    
    @BeforeMethod
    public void setUp() {
        // Setup WebDriverManager for Chrome
        WebDriverManager.chromedriver().setup();
        
        // Configure Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        // Uncomment the line below to run in headless mode
        // options.addArguments("--headless");
        
        // Initialize WebDriver
        driver = new ChromeDriver(options);
        
        // Set implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        // Maximize window
        driver.manage().window().maximize();
        
        // Navigate to base URL
        driver.get(BASE_URL);
    }
    
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}