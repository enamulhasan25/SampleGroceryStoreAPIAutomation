package steps;

import java.io.*;
import java.nio.file.Paths;

import static java.lang.System.out;

public class SavingCartIdAfterGeneration {

    private static final String FILE_PATH = Paths.get("src", "test", "resources", "cartId.txt").toString();

    public static void saveCartId(String cartId) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(cartId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getSavedCartId() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            return reader.readLine();  // Read the cartId from the file
        } catch (IOException e) {
            out.println("Cart ID file not found. Run the Create Cart scenario first.");
            return null;
        }
    }
}