package models.GitHubEvents;

import java.time.OffsetDateTime;
import java.util.List;

enum PageAction {
    CREATED,
    EDITED
}

/**
 * Triggered when a wiki page is created or updated.
 * Each page entry has an action of either: created or edited.
 */
public class GollumEvent extends GitHubEvent {
    public static class Page {
        private String pageName;
        private String title;
        private String summary; // optional, can be null
        private PageAction action; // created or edited
        private String sha;
        private String htmlUrl;

        public Page(String pageName, String title, String summary,
                PageAction action, String sha, String htmlUrl) {
            this.pageName = pageName;
            this.title = title;
            this.summary = summary;
            this.action = action;
            this.sha = sha;
            this.htmlUrl = htmlUrl;
        }

        public String getPageName() {
            return pageName;
        }

        public String getTitle() {
            return title;
        }

        public String getSummary() {
            return summary;
        }

        public PageAction getAction() {
            return action;
        }

        public String getSha() {
            return sha;
        }

        public String getHtmlUrl() {
            return htmlUrl;
        }
    }

    private List<Page> pages;

    public GollumEvent(String _repoName, OffsetDateTime _createdAt, List<Page> _pages) {
        super("GollumEvent", _repoName, _createdAt);
        this.pages = _pages;
    }

    public List<Page> getPages() {
        return this.pages;
    }

    @Override
    public String toString() {
        long created = pages.stream().filter(p -> p.getAction() == PageAction.CREATED).count();
        long edited = pages.stream().filter(p -> p.getAction() == PageAction.EDITED).count();
        String repoName = this.getRepoName();

        String finalString = "GollumEvent - ";
        if (created > 0 && edited > 0) {
            finalString += "Created " + created + " and edited " + edited + " wiki pages in " + repoName;
        } else if (created > 0) {
            finalString += "Created " + created + " wiki page" + (created > 1 ? "s" : "") + " in " + repoName;
        } else {
            finalString += "Edited " + edited + " wiki page" + (edited > 1 ? "s" : "") + " in " + repoName;
        }

        return finalString;
    }
}