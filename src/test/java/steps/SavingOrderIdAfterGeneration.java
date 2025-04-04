package steps;

import java.io.*;
import java.nio.file.Paths;

import static java.lang.System.out;

public class SavingOrderIdAfterGeneration {
    private static final String FILE_PATH = Paths.get("src", "test", "resources", "orderId.txt").toString();

    public static void saveOrderId(String orderId) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(orderId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getOrderId() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            return reader.readLine();
        } catch (IOException e) {
            out.println("Order ID not found. Run the Create An Order scenario first.");
            return null;
        }
    }
}