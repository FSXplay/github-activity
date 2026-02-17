package models.GitHubEvents;

import java.time.OffsetDateTime;

public class PullRequestReviewCommentEvent extends GitHubEvent {
    private int number;
    
    public PullRequestReviewCommentEvent(String _repoName, OffsetDateTime _createdAt, int _number) {
        super("PullRequestReviewCommentEvent", _repoName, _createdAt);
        this.number = _number;
    }

    public int getNumber() { return this.number; }

    @Override
    public String toString() {
        return "PullRequestReviewCommentEvent - Commented on pull request #" + this.getNumber() + " in " + this.getRepoName();
    }
}
