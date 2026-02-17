package models.GitHubEvents;

import java.time.OffsetDateTime;

/**
 * Base class for all GitHub events.
 * Contains common properties shared across all event types.
 */
public abstract class GitHubEvent {
    // Common properties
    protected String type;
    protected String repoName;
    protected OffsetDateTime createdAt;

    // Constructor
    public GitHubEvent(String _type, String _repoName, OffsetDateTime _createdAt) {
        this.type = _type;
        this.repoName = _repoName;
        this.createdAt = _createdAt;
    }

    // Getters
    public String getType() { return this.type; }
    public String getRepoName() { return this.repoName; }
    public OffsetDateTime getCreatedAt() { return this.createdAt; } 

    // Convert the event object into String for easy logging
    public abstract String toString();
}
