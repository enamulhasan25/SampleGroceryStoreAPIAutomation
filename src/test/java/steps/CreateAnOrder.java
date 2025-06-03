package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;

import static java.lang.System.out;

public class CreateAnOrder {

    private String cartId;
    private static String randomCustomerName;
    private static String orderId;

    public static String getOrderId() {
        return orderId;
    }

    public static void setOrderId(String orderId) {
        CreateAnOrder.orderId = orderId;
    }

    @Given("I read cartId from the text file {string}")
    public void iReadGeneratedCartIdFromTextFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            this.cartId = reader.readLine();
            out.println("Readed CartId from  file is : " + this.cartId);
        }
    }

    @And("I generate a random customer name")
    public void generateRandomCustomerName() {
        randomCustomerName = UUID.randomUUID().toString().substring(0, 4);
        out.println("Customer name generated is : " + "customer_" + randomCustomerName);
    }

    @Then("capture the orderId from the response payload")
    public void captureOrderIdFromResponse() {
        //ServiceCalls.res.body().prettyPrint();
        String actualOrderIdFromResponse = ServiceCalls.res.jsonPath().getString("orderId");
        if (actualOrderIdFromResponse != null) {
            actualOrderIdFromResponse = actualOrderIdFromResponse.trim();
            out.println("OrderId is : " + actualOrderIdFromResponse);

            // Save OrderId to a variable
            setOrderId(actualOrderIdFromResponse);
            // Save to file
            SavingResponsePayloadsData.saveOrderId(actualOrderIdFromResponse);
        } else {
            throw new RuntimeException("Failed to capture itemId from response.");
        }
    }

    public static String getGeneratedCustomerName() {
        return randomCustomerName;
    }

    public String getCartId() {
        return cartId;
    }
}