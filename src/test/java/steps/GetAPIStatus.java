package steps;

import io.cucumber.java.en.Then;
import org.junit.Assert;

public class GetAPIStatus {

    @Then("validate the status value as {string} from response payload")
    public void validateTheStatusValueAsUPFromResponsePayload(String expectedStatus) {
        ServiceCalls.res.body().prettyPrint();
        String statusValueFromResponsePayload = ServiceCalls.res.jsonPath().getString("status");
        Assert.assertEquals("The status value is not 'up'", expectedStatus, statusValueFromResponsePayload.toUpperCase());
    }
}