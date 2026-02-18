package services.tests;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import services.JSONToGitHubEventConverter;

public class Test_JSONToGitHubEventConverter {
    private static final String FILE_PATH = "src/services/tests/testJSON.json";

    public static void main(String[] args) {
        test_convertToGitHubEvent();
    }

    private static void test_convertToGitHubEvent() {
        try {
            String json = readJSONAsString(FILE_PATH);
            json = json.substring(1, json.length() - 2).trim();
            String firstEntry = JSONToGitHubEventConverter.splitJsonEntries(json)[0];
            String type = extractValue(firstEntry, "\"type\"");
            System.out.println(type);
        } catch (IOException e) {
            System.err.println("Error reading json file");
        }
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

    private static String extractValue(String json, String key) {
        int keyIndex = json.indexOf(key);
        int colonIndex = json.indexOf(":", keyIndex);
        int startQuote = json.indexOf("\"", colonIndex);
        int endQuote = json.indexOf("\"", startQuote + 1);
        return json.substring(startQuote + 1, endQuote);
    }
}
