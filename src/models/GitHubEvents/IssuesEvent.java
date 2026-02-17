package models.GitHubEvents;

import java.time.OffsetDateTime;

enum Action {
    OPENED,
    CLOSED,
    REOPENED
}

public class IssuesEvent extends GitHubEvent {
    private Action action;

    public IssuesEvent(String _repoName, Action _action, OffsetDateTime _createdAt) {
        super("IssuesEvent", _repoName, _createdAt);
        this.action = _action;
    }

    public Action geAction() { return this.action; }

    @Override
    public String toString() {
        String finalString = "IssuesEvent -";
        switch (action) {
            case OPENED:
                finalString += "Opened a new issue in " + this.getRepoName();
                break;
            case CLOSED:
                finalString += "Closed an issue in " + this.getRepoName();
                break;
            case REOPENED:
                finalString += "Reopened an issue in " + this.getRepoName();
            default:
                finalString += "Updated an issue in " + this.getRepoName();
        }
        return finalString;
    }
}
