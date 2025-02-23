package ui.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class DuckDuckGoApiAndUiTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        driver = new ChromeDriver();
    }

    @Test
    public void testSearchIntegration() {
        // Step 1: API Test - Fetch search results from an API (simulate DuckDuckGo API)
        String query = "Selenium WebDriver";

        // Example API endpoint (replace with the actual URL if available)
        Response apiResponse = RestAssured
                .given()
                .queryParam("q", query)
                .when()
                .get("https://api.duckduckgo.com/")
                .then()
                .statusCode(200) // Validate the API returns status 200
                .body("RelatedTopics", not(empty())) // Ensure results exist
                .extract()
                .response();

        // Extract the first result title for later validation
        String topApiResult = apiResponse
                .jsonPath()
                .getString("RelatedTopics[0].Text");

        // Step 2: UI Test - Perform a search on the DuckDuckGo homepage
        // Open DuckDuckGo homepage
        driver.get("https://www.duckduckgo.com");

        // Perform search
        WebElement searchInput = driver.findElement(By.name("q"));
        searchInput.sendKeys(query);
        searchInput.submit();

        // Wait for results to load
        WebElement firstResult = driver.findElement(By.cssSelector(".result__title"));

        // Compare UI displayed result with API result
        assertEquals("The top search result should match the API result", topApiResult, firstResult.getText());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}