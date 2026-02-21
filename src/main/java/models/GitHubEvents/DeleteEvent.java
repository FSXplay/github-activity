package models.GitHubEvents;

import java.time.OffsetDateTime;
import models.GitHubEvents.enums.DeleteEventRefType;

public class DeleteEvent extends GitHubEvent {
    private DeleteEventRefType refType;
    private String ref;

    public DeleteEvent(String _repoName, OffsetDateTime _createdAt, DeleteEventRefType _refType, String _ref) {
        super("DeleteEvent", _repoName, _createdAt);
        this.refType = _refType;
        this.ref = _ref;
    }

    public DeleteEventRefType getRefType() {
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