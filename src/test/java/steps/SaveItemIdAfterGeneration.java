package steps;

import java.io.*;
import java.nio.file.Paths;

import static java.lang.System.out;

public class SaveItemIdAfterGeneration {
    private static final String FILE_PATH = Paths.get("src", "test", "resources", "itemId.txt").toString();

    public static void saveItemId(String cartId) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(cartId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getSavedItemId() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            return reader.readLine();
        } catch (IOException e) {
            out.println("Item ID not found. Run the Add item to cart scenario first.");
            return null;
        }
    }
}