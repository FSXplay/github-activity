package models.GitHubEvents;

import java.time.OffsetDateTime;

public class PushEvent extends GitHubEvent {
    private int commitsCount;
    private String ref;
    
    public PushEvent(String _repoName, int _commitsCount, String _ref, OffsetDateTime _createdAt) {
        super("PushEvent", _repoName, _createdAt);
        this.commitsCount = _commitsCount;
        this.ref = _ref;
    }

    public int getCommitsCount() { return this.commitsCount; }
    public String getRef() { return this.ref; }

    @Override
    public String toString() {
        String branch = this.getRef().replace("refs/heads/", "");
        return "Pushed " + this.getCommitsCount() + " commit" + (this.getCommitsCount() != 1 ? "s" : "") + " to " + this.getRepoName() + " (" + branch + ")";
    }
}
