package pages;

import utils.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class DuckDuckGoHomePage {
    private WebDriver driver;
    private WebDriverWait wait;

     private void initializeDriver() {
         String browser = ConfigManager.getProperty("browser");
         if (browser.equalsIgnoreCase("chrome")) {
             System.setProperty("webdriver.chrome.driver", ConfigManager.getProperty("webdriver.chrome.driver"));
             driver = new ChromeDriver();
         } else if (browser.equalsIgnoreCase("firefox")) {
             System.setProperty("webdriver.gecko.driver", ConfigManager.getProperty("webdriver.gecko.driver"));
             driver = new FirefoxDriver();
         } else {
             throw new IllegalArgumentException("Unsupported browser: " + browser);
         }
     }

    // Locator for the search box
    private By searchBox = By.name("q");

    // Constructor to initialize WebDriver
    public DuckDuckGoHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Method to open the DuckDuckGo homepage
    public void openHomePage() {
        String homepage = ConfigManager.getProperty("homepage_url");
        driver.get(homepage);
    }

    // Method to perform a search
    public void search(String query) {
        // Wait until the search box is present
        WebElement searchInput = wait.until(ExpectedConditions.presenceOfElementLocated(searchBox));
        // Enter the query
        searchInput.sendKeys(query);
        // Submit the form
        searchInput.submit();
    }
    public String getTopSearchResult() {
        WebElement result = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".result__title")));
        return result.getText();
    }

}