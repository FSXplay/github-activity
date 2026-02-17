package models.GitHubEvents;

import java.time.OffsetDateTime;

public class ForkEvent extends GitHubEvent {
    private String forkeeRepoName;

    public ForkEvent(String _repoName, String _forkeeRepoName, OffsetDateTime _createdAt) {
        super("ForkEvent", _repoName, _createdAt);
    }

    public String getForkeeRepoName() { return forkeeRepoName; }

    @Override
    public String toString() {
        return "ForkEvent - Forked " + repoName + " to " + forkeeRepoName;
    }
}