package models.GitHubEvents;

import java.time.OffsetDateTime;

public class WatchEvent extends GitHubEvent {
    public WatchEvent(String _repoName, OffsetDateTime _createdAt) {
        super("WatchEvent", _repoName, _createdAt);
    }

    @Override
    public String toString() {
        return "WatchEvent - Starred " + this.getRepoName();
    }
}
