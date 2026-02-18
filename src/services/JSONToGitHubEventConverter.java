package services;

import java.util.ArrayList;
import java.util.List;

/**
 * A utility class for converting JSON into GitHubEvent object
 */
public class JSONToGitHubEventConverter {
    /**
     * Splits a JSON array or a list of objects into individual entries based on
     * top-level commas.
     * This method tracks brackets and braces to ensure it only splits at the root
     * level
     * and ignores commas inside nested objects or strings.
     * * @param json The JSON string to be split.
     * 
     * @return An array of Strings, where each element is a separate JSON entry.
     */
    private static String[] splitJsonEntries(String json) {
        List<String> entries = new ArrayList<>();
        int bracketCount = 0, braceCount = 0;
        boolean inQuotes = false;
        StringBuilder entry = new StringBuilder();

        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);

            if (c == '"') {
                inQuotes = !inQuotes;
            }

            if (!inQuotes) {
                if (c == '{') {
                    braceCount++;
                } else if (c == '}') {
                    braceCount--;
                } else if (c == '[') {
                    bracketCount++;
                } else if (c == ']') {
                    bracketCount--;
                }
            }

            if (c == ',' && !inQuotes && braceCount == 0 && bracketCount == 0) {
                entries.add(entry.toString().trim());
                entry.setLength(0);
            } else {
                entry.append(c);
            }
        }

        if (entry.length() > 0) {
            entries.add(entry.toString().trim());
        }

        return entries.toArray(new String[0]);
    }
}
