package steps;

import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;

public class GetAllProductsList {

    @Then("verify the response contains a list of products")
    public void printListOfAllProducts() {
        ServiceCalls.res.body().prettyPrint();
        List<Object> allProductsInResponse = ServiceCalls.res.jsonPath().getList("$");
        for (Object allProducts : allProductsInResponse) {
            Assert.assertTrue("id not found in ResponsePayload",allProducts.toString().contains("id"));
            Assert.assertTrue("category not found in ResponsePayload",allProducts.toString().contains("category"));
            Assert.assertTrue("name not found in ResponsePayload",allProducts.toString().contains("name"));
            Assert.assertTrue("inStock not found in ResponsePayload",allProducts.toString().contains("inStock"));
        }
    }
}