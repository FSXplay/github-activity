package models.GitHubEvents;

import java.time.OffsetDateTime;

public class DeleteEvent extends GitHubEvent {
    private enum RefType {
        BRANCH,
        TAG
    }

    private RefType refType;
    private String ref;

    public DeleteEvent(String _repoName, OffsetDateTime _createdAt, RefType _refType, String _ref) {
        super("DeleteEvent", _repoName, _createdAt);
        this.refType = _refType;
        this.ref = _ref;
    }

    public RefType getRefType() {
        return this.refType;
    }

    public String getRef() {
        return this.ref;
    }

    @Override
    public String toString() {
        return "Deleted " + this.getRefType().toString() + " " + this.getRef() + " from " + this.getRepoName();
    }
}