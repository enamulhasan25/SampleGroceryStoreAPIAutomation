package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static java.lang.System.out;

public class ServiceCalls {

    public static Response res;

    String cartId = CreateNewCart.getCartId();
    String itemId = AddAnItemToCart.getItemId();

    public ServiceCalls() {
    }

    // GET Service Call
    @When("a GET call is made to the {string}")
    public void aGETCallMadeToThe(String endpoint) {
        res = given().get(endpoint);
    }

    // GET call for the single product search
    @When("a GET call is made to the single product endpoint {string} with productId {string}")
    public void aGetCallMadeToTheSingleProductEndpoint(String endpoint, String productId) {
        res = given().pathParam("productId", productId).get(endpoint);
    }

    // GET call for retrieving the newly created cart
    @When("a GET call is made to the newly created cartId {string}")
    public void aGetCallMadeToTheCartEndpoint(String endpoint) {
        if (cartId == null) {
            cartId = steps.SavingCartIdAfterGeneration.getSavedCartId();  // Retrieve from file if null
        }
        if (cartId == null) {
            throw new RuntimeException("Cart ID is null! Make sure the cart is created first.");
        }
        out.println("Using Cart ID: " + cartId);
        res = given().pathParam("cartId", cartId).get(endpoint);
    }

    // POST call for adding an item into cart
    @When("a POST call is made to the add cart endpoint {string} with cartId")
    public void aPOSTCallMadeToTheAddCartEndpoint(String endpoint) throws IOException {
        if (cartId == null) {
            cartId = steps.SavingCartIdAfterGeneration.getSavedCartId();
        }
        if (cartId == null) {
            throw new RuntimeException("Cart ID is null! Make sure the cart is created first.");
        }

        out.println("Using Cart ID: " + cartId);
        res = given()
                .contentType("application/json")
                .body(AddAnItemToCart.getProductIdFromRequestPayloadTemplate().toString())
                .pathParam("cartId", cartId)
                .when()
                .post(endpoint);
    }

    // Patch call is for updating the quantity in the cart
    @When("a PATCH call is made to the update cart endpoint {string} with itemId")
    public void aPATCHCallMadeToTheUpdateCartEndpoint(String endpoint) throws IOException {
        if (itemId == null && cartId == null) {
            itemId = steps.SaveItemIdAfterGeneration.getSavedItemId();
            cartId = steps.SavingCartIdAfterGeneration.getSavedCartId();
        } else {
            throw new RuntimeException("ItemId ID is null! Make sure the product is added to the cart first.");
        }
        out.println("Using Item ID: " + itemId);
        res = given()
                .contentType("application/json")
                .body(UpdateAnItemFromCart.getQuantityFromRequestPayloadTemplate().toString())
                .pathParam("cartId", cartId)
                .pathParam("itemId", itemId)
                .when()
                .patch(endpoint);
    }

    // PUT call is for replacing product from the cart
    @When("a PUT call is made to replacing a product from cart endpoint {string} with cartId and itemId")
    public void aPUTCallMadeToTheReplacingAnItemFromCart(String endpoint) throws IOException {
        if (itemId == null && cartId == null) {
            itemId = steps.SaveItemIdAfterGeneration.getSavedItemId();
            cartId = steps.SavingCartIdAfterGeneration.getSavedCartId();
        } else {
            throw new RuntimeException("ItemId and CartId are null! Make sure the product is added for the existing cart.");
        }
        res = given()
                .contentType("application/json")
                .body(ReplaceProductFromCart.getProductWhichNeedsToBeReplacedFromCartUsingRequestPayloadTemplate().toString())
                .pathParam("cartId", cartId)
                .pathParam("itemId", itemId)
                .when()
                .put(endpoint);
    }

    // DELETE call is for removing item from the cart
    @When("a DELETE call is made for product removing from cart {string} with cartId and itemId")
    public void aDELETECallMadeToTheRemovingAnItemFromCart(String endpoint) throws IOException {
        if (itemId == null && cartId == null) {
            itemId = steps.SaveItemIdAfterGeneration.getSavedItemId();
            cartId = steps.SavingCartIdAfterGeneration.getSavedCartId();
        } else {
            throw new RuntimeException("ItemId and CartId are null! Make sure the product is added in the cart.");
        }
        res = given()
                .contentType("application/json")
                .pathParam("cartId", cartId)
                .pathParam("itemId", itemId)
                .when()
                .delete(endpoint);
    }


    // POST call for registering a client
    @When("a POST call is made to the {string}")
    public void aPostCallIsMadeToTheRegister(String endpoint) {
        res = given().contentType("application/json").body(RegisterClient.getCreateAPIClientPayload()).when().post(endpoint);
    }

    // POST call without body for creating a cart
    @When("a POST call without body is made to the {string}")
    public void aPostCallWithoutBodyIsMadeToThe(String endpoint) {
        res = given().when().post(endpoint);
    }

    // Validating the response code
    @And("response code should be {int}")
    public void responseCodeShouldBe(int expectedCode) {
        Assert.assertEquals("Response code mismatch", expectedCode, res.getStatusCode());
    }
}