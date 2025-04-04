package steps;

import java.io.*;
import java.nio.file.Paths;

import static java.lang.System.out;

public class SavingAccessTokenForRegisteredClient {

    private static final String FILE_PATH = Paths.get("src", "test", "resources", "accessToken.txt").toString();

    public static void saveAccessToken(String accessToken) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getAccessToken() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            return reader.readLine();
        } catch (IOException e) {
            out.println("AccessToken not found. Run the Register Client API Service first.");
            return null;
        }
    }
}