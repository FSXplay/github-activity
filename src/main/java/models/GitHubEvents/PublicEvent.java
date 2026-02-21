package models.GitHubEvents;

import java.time.OffsetDateTime;

public class PublicEvent extends GitHubEvent {
    public PublicEvent(String _repoName, OffsetDateTime _createdAt) {
        super("PublicEvent", _repoName, _createdAt);
    }

    @Override
    public String toString() {
        return "PublicEvent - Made " + this.getRepoName() + " public";
    }
}