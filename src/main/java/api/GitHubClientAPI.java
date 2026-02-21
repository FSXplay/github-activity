package api;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

import services.JSONToGitHubEventConverter;
import java.util.List;
import org.json.JSONObject;

import models.GitHubEvents.GitHubEvent;

public class GitHubClientAPI {
    private static final String API_URL = "https://api.github.com";
    private static HttpClient client = HttpClient.newHttpClient();

    public static List<GitHubEvent> getEvents(String username) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format(API_URL + "/users/%s/events", username)))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            List<GitHubEvent> events = JSONToGitHubEventConverter.convertToGitHubEvents(response.body());
            return events;
        } catch (Exception e) {
            System.err.println("Error response");
            return null;
        }
    }

    public static JSONObject compareTwoCommits(String owner, String repo, String before, String head) {
        String url = String.format(API_URL + "/repos/%s/%s/compare/%s...%s", owner, repo, before, head);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/vnd.github+json")
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new JSONObject(response.body());
        } catch (Exception e) {
            System.err.println("Error fetching commit comparison: " + e.getMessage());
            return null;
        }
    }
}
