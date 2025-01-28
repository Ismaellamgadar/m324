import requests
import os

# GitHub API-URL und Token
REPO = "username/repository"
TOKEN = os.getenv("GITHUB_TOKEN")
API_URL = f"https://api.github.com/repos/{REPO}"

# Daten abrufen
def fetch_data():
    headers = {"Authorization": f"token {TOKEN}"}
    data = {}

    # Allgemeine Repo-Statistiken
    repo_data = requests.get(API_URL, headers=headers).json()
    data["commits"] = repo_data["open_issues"]
    data["open_issues"] = repo_data["open_issues_count"]

    # Pull Requests
    pulls = requests.get(f"{API_URL}/pulls", headers=headers).json()
    data["open_pulls"] = len(pulls)

    return data

# README aktualisieren
def update_readme(data):
    with open("README.md", "r") as file:
        readme = file.readlines()

    # README updaten (hier kannst du es formatieren)
    readme_updated = "\n".join(readme).replace(
        "COMMITS_PLACEHOLDER", str(data["commits"])
    ).replace(
        "ISSUES_PLACEHOLDER", str(data["open_issues"])
    ).replace(
        "PULLS_PLACEHOLDER", str(data["open_pulls"])
    )

    with open("README.md", "w") as file:
        file.write(readme_updated)

# Hauptlogik
if __name__ == "__main__":
    data = fetch_data()
    update_readme(data)
