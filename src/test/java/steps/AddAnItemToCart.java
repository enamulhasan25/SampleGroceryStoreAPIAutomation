package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static java.lang.System.out;

public class AddAnItemToCart {

    private static String itemId;

    public static String getItemId() {
        return itemId;
    }

    public static void setItemId(String itemId) {
        AddAnItemToCart.itemId = itemId;
    }

    public static final String jsonFilePath = "src/test/resources/requestPayloads/addItemToCart.json";

    public static JSONObject getProductIdFromRequestPayloadTemplate() throws IOException {
        String content = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);
        return new JSONObject(content);
    }

    @Given("I have request payload to add an item to cart")
    public void iHaveRequestPayloadToAddAnItemToCart() throws IOException {
        JSONObject payload = getProductIdFromRequestPayloadTemplate();
        out.println("Request Payload: " + payload);
    }

    @Then("capture the itemId from the response")
    public void captureItemIdFromResponse() {
        ServiceCalls.res.body().prettyPrint();
        String actualItemIdFromResponse = ServiceCalls.res.jsonPath().getString("itemId");
        if (actualItemIdFromResponse != null) {
            actualItemIdFromResponse = actualItemIdFromResponse.trim();
            out.println("ItemId is : " + actualItemIdFromResponse);

            // Save ItemId to a variable
            setItemId(actualItemIdFromResponse);
            // Save to file
            SavingResponsePayloadsData.saveItemId(actualItemIdFromResponse);
        } else {
            throw new RuntimeException("Failed to capture itemId from response.");
        }
    }
}