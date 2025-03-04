package steps;

import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

import static java.lang.System.out;

public class GetProductsByCategory {

    @Then("all products should have the category {string}")
    public void all_products_should_have_the_category(String expectedCategory) {
        JsonPath jsonPath = JsonPath.given(ServiceCalls.res.body().prettyPrint());
        List<Map<String, Object>> products = jsonPath.getList("$");

        boolean allMatch = products.stream()
                .allMatch(product -> product.get("category").equals(expectedCategory));
        out.println("All product categories are coffee: " + allMatch);
        Assert.assertTrue("Not all products have the category: " + expectedCategory, allMatch);
    }
}