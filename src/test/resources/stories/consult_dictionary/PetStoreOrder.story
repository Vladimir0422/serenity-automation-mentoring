Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: user checks pet by status
Given system is clean
When the user checks pet by status <status>
Then system returns status code <code>

Examples:
|status   |code      |
|AVAILABLE|200       |
|SOLD     |200       |