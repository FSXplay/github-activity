package models.GitHubEvents;

import java.time.OffsetDateTime;

public class CommitCommentEvent extends GitHubEvent {
    private String commitSHA;
    
    public CommitCommentEvent(String _repoName, OffsetDateTime _createdAt, String _commitSHA) {
        super("CommitCommentEvent", _repoName, _createdAt);
        this.commitSHA = _commitSHA;
    }   

    public String getCommitSHA() { return this.commitSHA; }

    @Override
    public String toString() {
        String shortSha = this.commitSHA != null && this.commitSHA.length() > 7
                ? this.commitSHA.substring(0, 7)
                : this.commitSHA;
        return "CommitCommentEvent - Created a comment on commit " + shortSha + " in " + this.getRepoName();
    }
}
