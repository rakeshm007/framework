package ui.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.DuckDuckGoHomePage;
import pages.DuckDuckGoSearchResultsPage;
import org.junit.jupiter.api.Assertions;

public class DuckDuckGoSteps {
    private WebDriver driver;
    private DuckDuckGoHomePage homePage;
    private DuckDuckGoSearchResultsPage resultsPage;

    @Given("I am on the DuckDuckGo homepage")
    public void iAmOnTheDuckDuckGoHomepage() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-infobars");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        homePage = new DuckDuckGoHomePage(driver);
        homePage.openHomePage();
        System.out.println("Opened DuckDuckGo homepage.");
    }

    @When("I search for {string}")
    public void iSearchFor(String query) {
        homePage.search(query);
        resultsPage = new DuckDuckGoSearchResultsPage(driver);
        System.out.println("Performed search for: " + query);
    }

    @Then("the search results page title should contain {string}")
    public void theSearchResultsPageTitleShouldContain(String query) {
        boolean isTitleCorrect = resultsPage.isTitleCorrect(query);
        Assertions.assertTrue(isTitleCorrect,
                "Expected title to contain \"" + query + "\", but it did not.");
        System.out.println("Validated that the title contains: " + query);

        // Close the browser
        driver.quit();
        System.out.println("Browser closed.");
    }
}