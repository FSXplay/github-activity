import java.util.List;

import api.GitHubClientAPI;
import models.GitHubEvents.GitHubEvent;

public class Main {
    public static void main(String[] args) {
        String username = args[0];

        List<GitHubEvent> events = GitHubClientAPI.getEvents(username);
        
        System.out.println(username + " activities on GitHub:");
        for (GitHubEvent event : events) {
            System.out.println(event);
            System.out.println("=============================");
        }
    }
}