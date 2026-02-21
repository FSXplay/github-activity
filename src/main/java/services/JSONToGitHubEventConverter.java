package services;

import org.json.JSONArray;
import org.json.JSONObject;

import api.GitHubClientAPI;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import models.GitHubEvents.*;
import models.GitHubEvents.enums.*;

/**
 * A utility class for converting JSON into GitHubEvent object
 */
public class JSONToGitHubEventConverter {
    /**
     * Accept a whole JSON and return a list of GitHubEvent objects.
     * @param json
     * @return A list of GitHubEvent objects.
     */
    public static List<GitHubEvent> convertToGitHubEvents(String json) throws IllegalStateException {
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
     * Accept a JSON entry that represent a GitHub event, then convert it into a
     * GitHubEvent object.
     * 
     * @param eventData
     * @return A GitHubEvent object.
     */
    public static GitHubEvent convertToGitHubEvent(JSONObject eventJson) throws IllegalStateException {
        String type = eventJson.getString("type");
        String repoName = eventJson.getJSONObject("repo").getString("name");
        OffsetDateTime createdAt = OffsetDateTime.parse(eventJson.getString("created_at"));
        JSONObject payload = eventJson.getJSONObject("payload");

        switch (type) {
            case "CommitCommentEvent":
                return parseCommitCommentEvent(repoName, createdAt, payload);
            case "CreateEvent":
                return parseCreateEvent(repoName, createdAt, payload);
            case "DeleteEvent":
                return parseDeleteEvent(repoName, createdAt, payload);
            case "DiscussionEvent":
                return parseDiscussionEvent(repoName, createdAt, payload);
            case "ForkEvent":
                return parseForkEvent(repoName, createdAt, payload);
            case "GollumEvent":
                return parseGollumEvent(repoName, createdAt, payload);
            case "IssueCommentEvent":
                return parseIssueCommentEvent(repoName, createdAt, payload);
            case "IssuesEvent":
                return parseIssuesEvent(repoName, createdAt, payload);
            case "MemberEvent":
                return parseMemberEvent(repoName, createdAt, payload);
            case "PublicEvent":
                return parsePublicEvent(repoName, createdAt, payload);
            case "PullRequestEvent":
                return parsePullRequestEvent(repoName, createdAt, payload);
            case "PullRequestReviewEvent":
                return parsePullRequestReviewEvent(repoName, createdAt, payload);
            case "PullRequestReviewCommentEvent":
                return parsePullRequestReviewCommentEvent(repoName, createdAt, payload);
            case "PushEvent":
                String owner = eventJson.getJSONObject("actor").getString("login");
                return parsePushEvent(owner, repoName, createdAt, payload);
            case "ReleaseEvent":
                return parseReleaseEvent(repoName, createdAt, payload);
            case "WatchEvent":
                return parseWatchEvent(repoName, createdAt, payload);
            default:
                throw new IllegalStateException("Unsupported event type: " + type);
        }
    }

    // Helper methods for parsing values into different types of GitHubEvent objects
    private static CommitCommentEvent parseCommitCommentEvent(String repoName, OffsetDateTime createdAt,
            JSONObject payload) {
        String commitSHA = payload.getJSONObject("comment").getString("commit_id");
        return new CommitCommentEvent(repoName, createdAt, commitSHA);
    }

    private static CreateEvent parseCreateEvent(String repoName, OffsetDateTime createdAt, JSONObject payload) {
        CreateEventRefType refType = CreateEventRefType.valueOf(
                payload.getString("ref_type").toUpperCase());
        String ref = payload.getString("ref");
        return new CreateEvent(repoName, createdAt, refType, ref);
    }

    private static DeleteEvent parseDeleteEvent(String repoName, OffsetDateTime createdAt, JSONObject payload) {
        DeleteEventRefType refType = DeleteEventRefType.valueOf(
                payload.getString("ref_type").toUpperCase());
        String ref = payload.getString("ref");
        return new DeleteEvent(repoName, createdAt, refType, ref);
    }

    private static DiscussionEvent parseDiscussionEvent(String repoName, OffsetDateTime createdAt, JSONObject payload) {
        String discussionTitle = payload.getJSONObject("discussion").getString("title");
        return new DiscussionEvent(repoName, createdAt, discussionTitle);
    }

    private static ForkEvent parseForkEvent(String repoName, OffsetDateTime createdAt, JSONObject payload) {
        String forkeeRepoName = payload.getString("full_name");
        return new ForkEvent(repoName, forkeeRepoName, createdAt);
    }

    private static GollumEvent parseGollumEvent(String repoName, OffsetDateTime createdAt, JSONObject payload) {
        JSONArray pagesArray = payload.getJSONArray("pages");
        List<GollumEvent.Page> pages = new ArrayList<>();

        for (int i = 0; i < pagesArray.length(); i++) {
            JSONObject pageJson = pagesArray.getJSONObject(i);

            // Extract all page fields
            String pageName = pageJson.getString("page_name");
            String title = pageJson.getString("title");
            String summary = pageJson.optString("summary", null); // Can be null
            String sha = pageJson.getString("sha");
            String htmlUrl = pageJson.getString("html_url");

            // Convert action string to enum
            String actionStr = pageJson.getString("action").toUpperCase();
            PageAction action = PageAction.valueOf(actionStr); // "created" -> CREATED, "edited" -> EDITED

            // Create Page object
            GollumEvent.Page page = new GollumEvent.Page(
                    pageName,
                    title,
                    summary,
                    action,
                    sha,
                    htmlUrl);

            pages.add(page);
        }

        return new GollumEvent(repoName, createdAt, pages);
    }

    private static IssueCommentEvent parseIssueCommentEvent(String repoName, OffsetDateTime createdAt, JSONObject payload) {
        int issueNumber = Integer.parseInt(payload.getString("number"));
        return new IssueCommentEvent(repoName, createdAt, issueNumber);
    }

    private static IssuesEvent parseIssuesEvent(String repoName, OffsetDateTime createdAt, JSONObject payload) {
        IssueAction action = IssueAction.valueOf(
            payload.getString("action").toUpperCase()
        );
        return new IssuesEvent(repoName, action, createdAt);
    }

    private static MemberEvent parseMemberEvent(String repoName, OffsetDateTime createdAt, JSONObject payload) {
        String loginUsername = payload.getString("login");
        return new MemberEvent(repoName, createdAt, loginUsername);
    }

    private static PublicEvent parsePublicEvent(String repoName, OffsetDateTime createdAt, JSONObject payload) {
        return new PublicEvent(repoName, createdAt);
    }

    private static PullRequestEvent parsePullRequestEvent(String repoName, OffsetDateTime createdAt, JSONObject payload) {
        PullRequestAction action = PullRequestAction.valueOf(
            payload.getString("action").toUpperCase()
        );
        int number = payload.getInt("number");
        return new PullRequestEvent(repoName, createdAt, action, number);
    }

    private static PullRequestReviewEvent parsePullRequestReviewEvent(String repoName, OffsetDateTime createdAt, JSONObject payload) {
        PullRequestReviewAction action = PullRequestReviewAction.valueOf(
            payload.getString("action").toUpperCase()
        );
        int number = Integer.parseInt(
            payload.getJSONObject("pull_request").getString("number")
        );
        return new PullRequestReviewEvent(repoName, createdAt, action, number);
    }

    private static PullRequestReviewCommentEvent parsePullRequestReviewCommentEvent(String repoName, OffsetDateTime createdAt, JSONObject payload) {
        int number = Integer.parseInt(
            payload.getJSONObject("pull_request").getString("number")
        );
        return new PullRequestReviewCommentEvent(repoName, createdAt, number);
    }

    private static PushEvent parsePushEvent(String owner, String repoName, OffsetDateTime createdAt, JSONObject payload) {
        String head = payload.getString("head");
        String before = payload.getString("before");
        int commitsCount = GitHubClientAPI.compareTwoCommits(owner, repoName, before, head).getInt("total_commits");
        return new PushEvent(repoName, commitsCount, repoName, createdAt);
    }

    private static ReleaseEvent parseReleaseEvent(String repoName, OffsetDateTime createdAt, JSONObject payload) {
        String releaseName = payload.getJSONObject("release").getString("name");
        return new ReleaseEvent(repoName, createdAt, releaseName);
    }

    private static WatchEvent parseWatchEvent(String repoName, OffsetDateTime createdAt, JSONObject payload) {
        return new WatchEvent(repoName, createdAt);
    }
}
