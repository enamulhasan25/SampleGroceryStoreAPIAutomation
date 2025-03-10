package steps;

import io.cucumber.java.en.Then;

public class DeleteAnItemFromCart {
    @Then("print the response payload after product is deleted")
    public void printTheResponse() {
        ServiceCalls.res.body().prettyPrint();
    }
}