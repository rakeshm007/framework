package api;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import utils.ConfigManager;

public class ApiClient {
    private static final String BASE_URL = ConfigManager.getProperty("api_base_url");

    public static Response search(String query) {
        return given()
                .queryParam("q", query)
                .get(BASE_URL + ConfigManager.getProperty("api_search_endpoint"));
    }
}