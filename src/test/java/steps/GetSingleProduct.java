package steps;

import io.cucumber.java.en.Then;
import org.junit.Assert;

public class GetSingleProduct {

    @Then("productId {string} should display in the response payload")
    public void productIdShouldDisplayInTheResponsePayload(String expectedProductId) {
        ServiceCalls.res.body().prettyPrint();
        // Fetch productId from the response and it will compare here
        String actualProductId = ServiceCalls.res.jsonPath().getString("id");
        Assert.assertEquals("Product ID does not match", expectedProductId, actualProductId);
    }
}