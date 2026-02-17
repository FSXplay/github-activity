package models.GitHubEvents;

import java.time.OffsetDateTime;

public class PushEvent extends GitHubEvent {
    private int commitsCount;
    private String ref;
    
    public PushEvent(String _repoName, int _commitsCount, String _ref) {
        super("PushEvent", _repoName, OffsetDateTime.now());
        this.commitsCount = _commitsCount;
        this.ref = _ref;
    }

    public int getCommitsCount() { return this.commitsCount; }
    public String getRef() { return this.ref; }

    @Override
    public String toString() {
        String branch = ref.replace("refs/heads/", "");
        return "Pushed " + commitsCount + " commit" + (commitsCount != 1 ? "s" : "") + " to " + repoName + " (" + branch + ")";
    }
}
