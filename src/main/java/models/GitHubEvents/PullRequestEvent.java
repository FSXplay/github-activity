package models.GitHubEvents;

import java.time.OffsetDateTime;
import models.GitHubEvents.enums.PullRequestAction;

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

        String verb;
        switch (action) {
            case OPENED:
                verb = "Opened";
                break;
            case CLOSED:
                verb = "Closed";
                break;
            case MERGED:
                verb = "Merged";
                break;
            case REPOPENED:
                verb = "Reopened";
                break;
            case ASSIGNED:
                verb = "Assigned";
                break;
            case UNASSIGNED:
                verb = "Unassigned";
                break;
            case LABELED:
                verb = "Labeled";
                break;
            case UNLABELED:
                verb = "Unlabeled";
                break;
            default:
                verb = "Updated";
                break;
        }

        return "PullRequestEvent - " + verb + " pull request #" + pullReqNum + " in " + repoName;
    }
}