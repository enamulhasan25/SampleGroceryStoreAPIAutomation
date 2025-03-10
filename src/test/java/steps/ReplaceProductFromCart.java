package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static java.lang.System.out;

public class ReplaceProductFromCart {
    public static final String jsonFilePath = "src/test/resources/requestPayloads/replaceProductFromCart.json";

    public static JSONObject getProductWhichNeedsToBeReplacedFromCartUsingRequestPayloadTemplate() throws IOException {
        String content = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);
        return new JSONObject(content);
    }

    @Given("I have request payload to replace product from cart")
    public void iHaveRequestPayloadToAddAnItemToCart() throws IOException {
        JSONObject payload = getProductWhichNeedsToBeReplacedFromCartUsingRequestPayloadTemplate();
        out.println("Request Payload: " + payload.toString());
    }

    @Then("print the response payload after product is replaced")
    public void printTheResponse() {
        ServiceCalls.res.body().prettyPrint();
    }
}