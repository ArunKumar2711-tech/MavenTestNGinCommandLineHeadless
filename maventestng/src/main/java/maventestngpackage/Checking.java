package maventestngpackage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Checking {
    private WebDriver driver;
    private WebDriverWait wait;
    private String searchText;
    private String otherParam1;
    private String otherParam2;

    @BeforeClass
    public void setUp() {
        // Retrieve parameters from system properties
        searchText = System.getProperty("searchText");
        otherParam1 = System.getProperty("otherParam1");
        otherParam2 = System.getProperty("otherParam2");

        // Validate parameters
        if (searchText == null || searchText.isEmpty()) {
            throw new IllegalArgumentException("searchText must be provided.");
        }

        System.out.println("Setting up WebDriver...");

        // Setup ChromeOptions for headless mode
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run Chrome in headless mode
        options.addArguments("--disable-gpu"); // Disable GPU hardware acceleration
        options.addArguments("--no-sandbox"); // Disable sandboxing (optional, needed for some environments)

        // Initialize WebDriver with ChromeOptions
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test(priority = 1)
    public void testOpenBrowser() {
        System.out.println("Test: Opening browser...");
        driver.get("https://www.google.com");
        System.out.println("Page title is: " + driver.getTitle());
        System.out.println(driver.getCurrentUrl());
    }

    @Test(dependsOnMethods = "testOpenBrowser")
    public void searchText() {
        System.out.println("Test: Searching text...");
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
        searchBox.sendKeys(searchText);
        searchBox.submit(); // Optionally submit the search
    }

    @AfterClass
    public void tearDown() {
        System.out.println("Tearing down WebDriver...");
        if (driver != null) {
            driver.quit();
        }
    }
}
