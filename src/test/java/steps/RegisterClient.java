package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import static java.lang.System.out;

public class RegisterClient {

    private static String accessToken;

    public static String getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(String accessToken) {
        RegisterClient.accessToken = accessToken;
    }

    private static String createAPIClientPayload;

    // Getter
    public static String getCreateAPIClientPayload() {
        return createAPIClientPayload;
    }

    // Setter
    public static void setCreateAPIClientPayload(String createAPIClientPayload) {
        RegisterClient.createAPIClientPayload = createAPIClientPayload;
    }

    private final String jsonFilePath = "src/test/resources/requestPayloads/registerAPIClient.json";

    // To generate a random string for client email using UUID class
    private String generateRandomEmail(String jsonFilePath) {
        String randomString = UUID.randomUUID().toString().substring(0, 4); // Shorten to 8 characters
        return "test" + randomString + "@example.com";
    }

    // Update the json file with the generated random email to the clientEmail variable name in .json file.
    private void updateJsonFile(String filePath, String key, String value) throws IOException {
        String content = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        JSONObject jsonObject = new JSONObject(content);
        // Update the specified key with the new value
        jsonObject.put(key, value);
        // Write the updated JSON back to the file
        FileUtils.writeStringToFile(new File(filePath), jsonObject.toString(4), StandardCharsets.UTF_8);
    }

    // Reading the JSON file and converts it to a String
    public String generateStringFromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    @Given("I have request payload")
    public void iHaveRequestBody() throws IOException {
        String randomEmail = generateRandomEmail(jsonFilePath);
        // Update the JSON file with the random email
        updateJsonFile(jsonFilePath, "clientEmail", randomEmail);
        // Read the updated JSON file as a string
        createAPIClientPayload = generateStringFromResource(jsonFilePath);
    }

    @And("capture the access token")
    public void capturingAccessToken() {
        if (ServiceCalls.res != null) {
            String accessTokenFromResponsePayload = ServiceCalls.res.jsonPath().getString("accessToken");
            if (accessTokenFromResponsePayload != null) {
                ServiceCalls.res.body().prettyPrint();
                accessTokenFromResponsePayload = accessTokenFromResponsePayload.trim();
                out.println("Newly generated access token is: " + accessTokenFromResponsePayload);
                setAccessToken(accessTokenFromResponsePayload);
                // Save to file
                steps.SavingResponsePayloadsData.saveAccessToken(accessTokenFromResponsePayload);
            } else {
                out.println("Access Token not found in the response.");
            }
        } else {
            out.println("Response is null. Please check the API call.");
        }
    }
}