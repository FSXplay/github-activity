package models.GitHubEvents;

import java.time.OffsetDateTime;

enum PullRequestAction {
    OPENED,
    CLOSED,
    MERGED,
    REPOPENED,
    ASSIGNED,
    UNASSIGNED,
    LABELED,
    UNLABELED
}

public class PullRequestEvent extends GitHubEvent {
    private PullRequestAction action;
    private int number;

    public PullRequestEvent(String _repoName, OffsetDateTime _createdAt, PullRequestAction _action, int _number) {
        super("PullRequestEvent", _repoName, _createdAt);
        this.action = _action;
        this.number = _number;
    }

    public PullRequestAction getAction() {
        return this.action;
    }

    public int getNumber() {
        return this.number;
    }

    @Override
    public String toString() {
        int pullReqNum = this.getNumber();
        String repoName = this.getRepoName();
        switch (action) {
            case OPENED:
                return "Opened pull request #" + pullReqNum + " in " + repoName;
            case CLOSED:
                return "Closed pull request #" + pullReqNum + " in " + repoName;
            case MERGED:
                return "Merged pull request #" + pullReqNum + " in " + repoName;
            case REPOPENED:
                return "Reopened pull request #" + pullReqNum + " in " + repoName;
            case ASSIGNED:
                return "Assigned pull request #" + pullReqNum + " in " + repoName;
            case UNASSIGNED:
                return "Unassigned pull request #" + pullReqNum + " in " + repoName;
            case LABELED:
                return "Labeled pull request #" + pullReqNum + " in " + repoName;
            case UNLABELED:
                return "Unlabeled pull request #" + pullReqNum + " in " + repoName;
            default:
                return "Updated pull request #" + pullReqNum + " in " + repoName;
        }
    }
}
