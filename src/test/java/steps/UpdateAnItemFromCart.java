package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static java.lang.System.out;

public class UpdateAnItemFromCart {

    public static final String jsonFilePath = "src/test/resources/requestPayloads/updateQuantityInCart.json";

    public static JSONObject getQuantityFromRequestPayloadTemplate() throws IOException {
        String content = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);
        return new JSONObject(content);
    }

    @Given("I have request payload to update an item to cart")
    public void iHaveRequestPayloadToAddAnItemToCart() throws IOException {
        JSONObject payload = getQuantityFromRequestPayloadTemplate();
        out.println("Request Payload: " + payload.toString());
    }

    @Then("print the response payload after quantity added")
    public void printTheResponse() {
        ServiceCalls.res.body().prettyPrint();
    }
}