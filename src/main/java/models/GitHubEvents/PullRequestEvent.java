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

        String finalString = "PullRequestEvent - ";
        switch (action) {
            case OPENED:
                finalString += "Opened pull request #" + pullReqNum + " in " + repoName;
            case CLOSED:
                finalString += "Closed pull request #" + pullReqNum + " in " + repoName;
            case MERGED:
                finalString += "Merged pull request #" + pullReqNum + " in " + repoName;
            case REPOPENED:
                finalString += "Reopened pull request #" + pullReqNum + " in " + repoName;
            case ASSIGNED:
                finalString += "Assigned pull request #" + pullReqNum + " in " + repoName;
            case UNASSIGNED:
                finalString += "Unassigned pull request #" + pullReqNum + " in " + repoName;
            case LABELED:
                finalString += "Labeled pull request #" + pullReqNum + " in " + repoName;
            case UNLABELED:
                finalString += "Unlabeled pull request #" + pullReqNum + " in " + repoName;
            default:
                finalString += "Updated pull request #" + pullReqNum + " in " + repoName;
        }
        return finalString;
    }
}
