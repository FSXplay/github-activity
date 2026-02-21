package services.tests;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import models.GitHubEvents.*;
import services.JSONToGitHubEventConverter;

public class Test_JSONToGitHubEventConverter {
    private static final String FILE_PATH = "src/main/java/services/tests/testJSON.json";

    public static void main(String[] args) {
        try {
            String json = readJSONAsString(FILE_PATH);
            List<GitHubEvent> events = JSONToGitHubEventConverter.convertToGitHubEvents(json);

            testByType(events, CommitCommentEvent.class);
            testByType(events, CreateEvent.class);
            testByType(events, DeleteEvent.class);
            testByType(events, ForkEvent.class);
            testByType(events, GollumEvent.class);
            testByType(events, IssueCommentEvent.class);
            testByType(events, IssuesEvent.class);
            testByType(events, MemberEvent.class);
            testByType(events, PublicEvent.class);
            testByType(events, PullRequestEvent.class);
            testByType(events, PullRequestReviewEvent.class);
            testByType(events, PullRequestReviewCommentEvent.class);
            testByType(events, PushEvent.class);
            testByType(events, ReleaseEvent.class);
            testByType(events, WatchEvent.class);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void testByType(List<GitHubEvent> events, Class<?> type) {
        System.out.println("=== " + type.getSimpleName() + " ===");

        List<GitHubEvent> filtered = events.stream()
                .filter(e -> e.getClass().equals(type))
                .toList();

        if (filtered.isEmpty()) {
            System.out.println("No events of this type found in test.json");
        } else {
            filtered.forEach(e -> System.out.println(e));
        }

        System.out.println();
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
