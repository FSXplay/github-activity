package services.tests;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import services.JSONToGitHubEventConverter;

public class Test_JSONToGitHubEventConverter {
    private static final String FILE_PATH = "src/main/java/services/tests/testJSON.json";

    public static void main(String[] args) {
        test_convertToGitHubEvent();
    }

    private static void test_convertToGitHubEvent() {
        
    }

    // Helper methods
    private static String readJSONAsString(String filePath) throws IOException {
        StringBuilder json = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                json.append(line).append("\n");
            }
        }
        return json.toString();
    }
}
