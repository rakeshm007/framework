package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class DuckDuckGoSearchResultsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor to initialize WebDriver
    public DuckDuckGoSearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Method to validate that the search results page title contains the query
    public boolean isTitleCorrect(String query) {
        wait.until(ExpectedConditions.titleContains(query)); // Ensure the title contains the query
        String title = driver.getTitle();
        return title.toLowerCase().contains(query.toLowerCase());
    }
}