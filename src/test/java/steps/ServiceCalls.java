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

    private final CartIdSharedContext testContext;

    public ServiceCalls() {
        this.testContext = CartIdSharedContext.getInstance();
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
        String cartId = testContext.getCartId();
        if (cartId == null) {
            cartId = SavingCartIdAfterGeneration.getSavedCartId();  // Retrieve from file if null
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
        String cartId = testContext.getCartId();
        if (cartId == null) {
            cartId = SavingCartIdAfterGeneration.getSavedCartId();
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