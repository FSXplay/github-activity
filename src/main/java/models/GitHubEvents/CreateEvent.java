package models.GitHubEvents;

import java.time.OffsetDateTime;
import models.GitHubEvents.enums.CreateEventRefType;

public class CreateEvent extends GitHubEvent {
    private enum RefType {
        BRANCH,
        TAG,
        REPOSITORY
    }
    private String ref;
    private CreateEventRefType refType;

    public CreateEvent(String _repoName, OffsetDateTime _createdAt, CreateEventRefType _refType, String _ref) {
        super("CreateEvent", _repoName, _createdAt);
        this.ref = _ref;
        this.refType = _refType;
    }

    public CreateEventRefType getRefType() {
        return this.refType;
    }

    public String getRef() {
        return this.ref;
    }

    @Override
    public String toString() {
        if (refType == CreateEventRefType.REPOSITORY) {
            return "Created a new repository " + this.getRepoName();
        }
        return "Created " + this.getRefType().toString() + " " + this.getRef() + " in " + this.getRepoName();
    }
}