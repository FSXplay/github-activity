package models.GitHubEvents;

import java.time.OffsetDateTime;

enum RefType {
    BRANCH,
    TAG,
    REPOSITORY
}

public class CreateEvent extends GitHubEvent {
    private String ref;
    private RefType refType;

    public CreateEvent(String _repoName, OffsetDateTime _createdAt, RefType _refType, String _ref) {
        super("CreateEvent", _repoName, _createdAt);
        this.ref = _ref;
        this.refType = _refType;
    }

    public RefType getRefType() { return this.refType; }
    public String getRef() { return this.ref; }

    @Override
    public String toString() {
        if (refType == RefType.REPOSITORY) {
            return "Created a new repository " + this.getRepoName();
        }
        return "Created " + this.getRefType().toString() + " " + this.getRef() + " in " + this.getRepoName();
    }
}