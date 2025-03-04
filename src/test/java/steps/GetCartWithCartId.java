package steps;

import io.cucumber.java.en.Then;

public class GetCartWithCartId {

    @Then("print the response payload")
    public void printTheResponsePayload() {
        ServiceCalls.res.body().prettyPrint();
    }
}