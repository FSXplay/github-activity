package models.GitHubEvents;

import java.time.OffsetDateTime;

public class ReleaseEvent extends GitHubEvent {
    private String releaseName; // Example: v1.0.0

    public ReleaseEvent(String _repoName, OffsetDateTime _createdAt, String _releaseName) {
        super("ReleaseEvent", _repoName, _createdAt);
        this.releaseName = _releaseName;
    }

    public String getReleaseName() { return this.releaseName; }

    @Override
    public String toString() {
        return "ReleaseEvent - Published release " + this.getReleaseName() + " in " + this.getRepoName();
    }
}
