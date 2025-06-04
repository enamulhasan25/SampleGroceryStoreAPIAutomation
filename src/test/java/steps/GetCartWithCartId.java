package steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.io.IOException;

import static java.lang.System.out;

public class GetCartWithCartId {

    @Before
    public void setupScenario() {
        // Essential to reset the static runtimeSavedCartId before each scenario!
        SavingResponsePayloadsData.setRuntimeSavedCartId(null);
        out.println("DEBUG: Scenario setup in GetCartWithCartId - runtimeSavedCartId reset.");
    }

    @Given("user has cart ID generated and stored in the file {string}")
    public void userHasGeneratedCartId(String filePath) throws IOException {
        out.println("DEBUG: Executing Given step in GetCartWithCartId: 'user has cart ID generated and stored in the file " + filePath + "'");
        String retrievedCartId = SavingResponsePayloadsData.getSavedCartId();

        // Final validation: Ensure it was successfully retrieved and is available globally
        if (retrievedCartId == null || retrievedCartId.isEmpty()) {
            throw new RuntimeException("Cart ID is null or empty after attempting to retrieve. " +
                    "Ensure a cart was created/saved, or 'cartId.txt' contains a valid ID.");
        }
        out.println("INFO: The cart ID is now confirmed available via SavingResponsePayloadsData: " + retrievedCartId);
    }

    @Then("print the response payload")
    public void printTheResponsePayload() {
        ServiceCalls.res.body().prettyPrint();
    }
}