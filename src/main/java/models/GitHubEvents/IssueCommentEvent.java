package models.GitHubEvents;

import java.time.OffsetDateTime;

public class IssueCommentEvent extends GitHubEvent {
    private int issueNumber;

    public IssueCommentEvent(String _repoName, OffsetDateTime _createdAt, int _issueNumber) {
        super("IssueCommentEvent", _repoName, _createdAt);
        this.issueNumber = _issueNumber;
    }

    public int getIssueNumber() { return issueNumber; }

    @Override
    public String toString() {
        return "Commented on issue #" + issueNumber + " in " + this.getRepoName();
    }
}
