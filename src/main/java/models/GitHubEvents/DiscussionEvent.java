package models.GitHubEvents;

import java.time.OffsetDateTime;

/**
 * Triggered when a discussion is created in a repository.
 * action can be: created
 */
public class DiscussionEvent extends GitHubEvent {
    private String discussionTitle;

    public DiscussionEvent(String _repoName, OffsetDateTime _createdAt, String _discussionTitle) {
        super("DiscussionEvent", _repoName, _createdAt);
        this.discussionTitle = _discussionTitle;
    }

    public String getDiscussionTitle() { return discussionTitle; }

    @Override
    public String toString() {
        return "Created discussion \"" + this.getDiscussionTitle() + "\" in " + this.getRepoName();
    }
}