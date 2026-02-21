# GitHub User Activity CLI

A Java-based command-line interface (CLI) that fetches and displays the recent activity of a GitHub user in the terminal.

## Overview

This project demonstrates working with APIs, JSON parsing, and building a CLI application. It fetches real-time activity data from the GitHub API and presents it in a readable format.

## Features

- ✅ Fetch recent activity of any GitHub user
- ✅ Display activity in a structured, readable format
- ✅ Support for multiple GitHub event types (Push, Pull Request, Issue, Star, Fork, etc.)
- ✅ Graceful error handling for invalid usernames and API failures
- ✅ Built with Java and Maven

## Requirements

- Java 11 or higher
- Maven 3.6+
- Internet connection (to access GitHub API)

## Installation

1. Clone the repository:
```bash
git clone https://github.com/FSXplay/github-activity.git
cd github-activity
```

2. Build the project:
```bash
mvn clean package
```

This will create an executable JAR file in the `target/` directory.

## Usage

Run the CLI with a GitHub username as an argument:

```bash
java -jar target/github-activity-1.0-SNAPSHOT.jar <username>
```

### Example

```bash
java -jar target/github-activity-1.0-SNAPSHOT.jar octocat
```

### Output

```
octocat activities on GitHub:
PushEvent: Pushed 3 commits to octocat/Hello-World
=============================
IssuesEvent: Opened a new issue in octocat/Hello-World
=============================
WatchEvent: Starred octocat/Hello-World
=============================
```

## Supported Event Types

The CLI recognizes and displays the following GitHub event types:

- **PushEvent** - Commits pushed to a repository
- **PullRequestEvent** - Pull request opened/closed/merged
- **IssuesEvent** - Issue opened/closed/reopened
- **WatchEvent** - Repository starred
- **ForkEvent** - Repository forked
- **CreateEvent** - Branch or tag created
- **DeleteEvent** - Branch or tag deleted
- **ReleaseEvent** - Release published
- **MemberEvent** - Collaborator added
- **IssueCommentEvent** - Comment added to an issue
- **PullRequestReviewEvent** - Review added to a pull request
- **PullRequestReviewCommentEvent** - Review comment on a PR
- **CommitCommentEvent** - Comment added to a commit
- **GollumEvent** - Wiki page created/edited
- **DiscussionEvent** - Discussion created/answered
- **PublicEvent** - Repository made public

## Project Structure

```
github-activity/
├── src/main/java/
│   ├── Main.java                          # Entry point
│   ├── api/
│   │   ├── GitHubClientAPI.java          # GitHub API client
│   │   └── APItest.java                   # API testing
│   ├── models/
│   │   └── GitHubEvents/                  # Event model classes
│   │       ├── GitHubEvent.java           # Base class
│   │       ├── PushEvent.java
│   │       ├── IssuesEvent.java
│   │       ├── WatchEvent.java
│   │       └── ... (other event types)
│   │       └── enums/                     # Event-specific enums
│   └── services/
│       └── JSONToGitHubEventConverter.java # JSON to Object conversion
├── pom.xml                                # Maven configuration
└── README.md                              # This file
```

## How It Works

1. **CLI Entry Point** (`Main.java`) - Accepts GitHub username as command-line argument
2. **API Client** (`GitHubClientAPI.java`) - Fetches data from GitHub's public events API endpoint
3. **JSON Parsing** (`JSONToGitHubEventConverter.java`) - Converts JSON responses to Java objects
4. **Event Models** - Type-specific event classes that represent different GitHub activities
5. **Display** - Prints formatted activity to the console

## API Details

The application uses the GitHub public API endpoint:
```
https://api.github.com/users/<username>/events
```

This endpoint returns up to 30 recent public events for the specified user. No authentication is required for public data.

## Error Handling

The application gracefully handles:
- **Invalid usernames** - Displays an error message if the user is not found
- **Network errors** - Handles connection failures gracefully
- **API rate limiting** - Respects GitHub's rate limits (60 requests/hour for unauthenticated requests)
- **Empty activity** - Handles users with no public activity

## Dependencies

- **org.json** (v20240303) - For JSON parsing and manipulation
- **Maven** - Build automation and dependency management

## Building and Testing

### Compile only:
```bash
mvn clean compile
```

### Run all tests:
```bash
mvn test
```

### Build entire project:
```bash
mvn clean package
```

## Running the Application

After building, run with any GitHub username:

```bash
# Single word usernames
java -jar target/github-activity-1.0-SNAPSHOT.jar github

# Usernames with hyphens
java -jar target/github-activity-1.0-SNAPSHOT.jar kamranahmedse
```

## Future Enhancements

- Filter activity by event type
- Display user profile information alongside activity
- Format output as JSON or CSV
- Add caching to improve performance
- Implement pagination for large activity lists
- Add authentication for higher API rate limits

## License

This project is part of the roadmap.sh community project initiative.

## References

- [GitHub API Documentation](https://docs.github.com/en/rest/reference/activity)
- [GitHub Events API](https://docs.github.com/en/developers/webhooks-and-events/events/github-event-types)
- [roadmap.sh - GitHub User Activity Project](https://roadmap.sh/projects/github-user-activity)