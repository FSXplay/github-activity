import java.util.List;

import api.GitHubClientAPI;
import models.GitHubEvents.GitHubEvent;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Invalid arguments!");
            System.out.println("Usage: github-activity <username>");
            return;
        }

        String username = args[0];

        List<GitHubEvent> events = GitHubClientAPI.getEvents(username);
        
        if (events == null) return;

        System.out.println(username + " activities on GitHub:");
        for (GitHubEvent event : events) {
            System.out.println(event);
            System.out.println("=============================");
        }
    }
}