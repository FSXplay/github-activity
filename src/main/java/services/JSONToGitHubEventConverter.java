package services;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import models.GitHubEvents.*;

/**
 * A utility class for converting JSON into GitHubEvent object
 */
public class JSONToGitHubEventConverter {
    /**
     * Accept a whole JSON and return a list of GitHubEvent objects.
     * @param json
     * @return A list of GitHubEvent objects.
     */
    public static List<GitHubEvent> convertToGitHubEvents(String json) {
        List<GitHubEvent> events = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(json);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject eventJson = jsonArray.getJSONObject(i);
            GitHubEvent event = convertToGitHubEvent(eventJson);
            if (event != null) {
                events.add(event);
            }
        }

        return events;
    }

    /**
     * Accept a JSON entry that represent a GitHub event, then convert it into a GitHubEvent object.
     * @param eventData
     * @return A GitHubEvent object.
     */
    public static GitHubEvent convertToGitHubEvent(JSONObject eventData) {
        
    }
}
