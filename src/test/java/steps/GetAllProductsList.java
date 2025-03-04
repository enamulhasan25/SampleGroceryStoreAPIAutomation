package steps;

import io.cucumber.java.en.Then;
import static java.lang.System.out;

public class GetAllProductsList {

    @Then("print the list of products from the response")
    public void printListOfAllProducts() {
        out.println(ServiceCalls.res.body().prettyPrint());
    }
}