package models.GitHubEvents;

import java.time.OffsetDateTime;
import models.GitHubEvents.enums.PullRequestReviewAction;

public class PullRequestReviewEvent extends GitHubEvent {
    private PullRequestReviewAction action;
    private int number;

    public PullRequestReviewEvent(String repoName, OffsetDateTime createdAt, PullRequestReviewAction _action,
            int _number) {
        super("PullRequestReviewEvent", repoName, createdAt);
        this.action = _action;
        this.number = _number;
    }

    public PullRequestReviewAction getAction() {
        return this.action;
    }

    public int getNumber() {
        return this.number;
    }

    @Override
    public String toString() {
        PullRequestReviewAction action = this.getAction();
        int number = this.getNumber();
        String repoName = this.getRepoName();

        String finalString = "PullRequestReviewEvent - ";
        switch (action) {
            case CREATED:
                finalString += "Reviewed pull request #" + number + " in " + repoName;
            case DISMISSED:
                finalString += "Dismissed a review on pull request #" + number + " in " + repoName;
            case UPDATED:
                finalString += "Updated a review on pull request #" + number + " in " + repoName;
        }
        return finalString;
    }
}
