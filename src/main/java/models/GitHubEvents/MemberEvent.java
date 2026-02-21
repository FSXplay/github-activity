package models.GitHubEvents;

import java.time.OffsetDateTime;

public class MemberEvent extends GitHubEvent {
    private String loginUsername;

    public MemberEvent(String _repoName, OffsetDateTime _createdAt, String _loginUsername) {
        super("MemberEvent", _repoName, _createdAt);
        this.loginUsername = _loginUsername;
    }

    public String getLoginUsername() { return this.loginUsername; }

    @Override
    public String toString() {
        return "MemberEvent - Added " + this.getLoginUsername() + " as a collaborator to " + this.getRepoName();
    }
}
