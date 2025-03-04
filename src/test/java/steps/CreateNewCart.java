package steps;

import io.cucumber.java.en.Then;

import static java.lang.System.out;

public class CreateNewCart {
    private final CartIdSharedContext cartContext = CartIdSharedContext.getInstance();

    @Then("capture the newly created cartId from the response")
    public void captureNewlyCreatedCart() {
        if (ServiceCalls.res != null) {
            String newlyCreatedCartId = ServiceCalls.res.body().jsonPath().getString("cartId");
            if (newlyCreatedCartId != null) {
                newlyCreatedCartId = newlyCreatedCartId.trim();
                out.println("Newly created cart id is: " + newlyCreatedCartId);

                // Save to shared context
                cartContext.setCartId(newlyCreatedCartId);
                // Save to file
                SavingCartIdAfterGeneration.saveCartId(newlyCreatedCartId);
            } else {
                out.println("cartId not found in the response.");
            }
        } else {
            out.println("Response is null. Please check the API call.");
        }
    }
}