package steps;

import java.io.*;
import java.nio.file.Paths;

import static java.lang.System.out;

public class SavingResponsePayloadsData {

    private static final String ACCESS_TOKEN_FILE_PATH = Paths.get("src", "test", "resources", "accessToken.txt").toString();
    private static final String CART_ID_FILE_PATH = Paths.get("src", "test", "resources", "cartId.txt").toString();
    private static final String ITEM_ID_FILE_PATH = Paths.get("src", "test", "resources", "itemId.txt").toString();
    private static final String ORDER_ID_FILE_PATH = Paths.get("src", "test", "resources", "orderId.txt").toString();


    // This static variable holds the cart ID generated and saved during the *current test run*
    private static String runtimeSavedCartId = null;

    // Method to set the cart ID at runtime (called after a POST /carts API call)
    public static void setRuntimeSavedCartId(String cartId) {
        runtimeSavedCartId = cartId;
        out.println("DEBUG: runtimeSavedCartId set to: " + cartId);
    }

    // Saving Access Token in a text file and fetching that wherever it needs
    public static void saveAccessToken(String accessToken) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ACCESS_TOKEN_FILE_PATH))) {
            writer.write(accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getAccessToken() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ACCESS_TOKEN_FILE_PATH))) {
            return reader.readLine();
        } catch (IOException e) {
            out.println("AccessToken not found. Run the Register Client API Service first.");
            return null;
        }
    }

    // Saving Cart Id in a text file and fetching that wherever it needs
    public static void saveCartId(String cartId) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CART_ID_FILE_PATH))) {
            writer.write(cartId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static String getSavedCartId() {
//        try (BufferedReader reader = new BufferedReader(new FileReader(CART_ID_FILE_PATH))) {
//            return reader.readLine();  // Read the cartId from the file
//        } catch (IOException e) {
//            out.println("Cart ID not found. Run the Create Cart scenario first.");
//            return null;
//        }
//    }

    // Method to retrieve the cart ID (prioritizes runtime, then file)
    public static String getSavedCartId() {
        // First, check if a cart ID was saved during the *current runtime*
        if (runtimeSavedCartId != null && !runtimeSavedCartId.isEmpty()) {
            out.println("DEBUG: getSavedCartId() returning runtime-saved ID.");
            return runtimeSavedCartId;
        }

        // If not found in runtime, try to read from the file
        try (BufferedReader reader = new BufferedReader(new FileReader(CART_ID_FILE_PATH))) {
            String fileCartId = reader.readLine();
            if (fileCartId != null && !fileCartId.trim().isEmpty()) {
                out.println("DEBUG: getSavedCartId() returning ID from file.");
                return fileCartId.trim();
            }
            out.println("WARN: Cart ID file found but is empty or contains only whitespace.");
            return null; // File exists but is empty
        } catch (IOException e) {
            out.println("WARN: Cart ID file ('" + CART_ID_FILE_PATH + "') not found or inaccessible. Returning null.");
            return null; // File not found or read error
        }
    }

    // Saving Item Id in a text file and fetching that wherever it needs
    public static void saveItemId(String cartId) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ITEM_ID_FILE_PATH))) {
            writer.write(cartId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getSavedItemId() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ITEM_ID_FILE_PATH))) {
            return reader.readLine();
        } catch (IOException e) {
            out.println("Item ID not found. Run the Add item to cart scenario first.");
            return null;
        }
    }

    // Saving OrderId in a text file and fetching that wherever it needs
    public static void saveOrderId(String orderId) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ORDER_ID_FILE_PATH))) {
            writer.write(orderId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getOrderId() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ORDER_ID_FILE_PATH))) {
            return reader.readLine();
        } catch (IOException e) {
            out.println("Order ID not found. Run the Create An Order scenario first.");
            return null;
        }
    }
}