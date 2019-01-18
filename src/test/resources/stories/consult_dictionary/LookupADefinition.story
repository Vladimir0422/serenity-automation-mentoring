Lookup a definition
Narrative:
In order to talk better
As an English student
I want to look up word definitions

Scenario: Looking up the definition of the word accourding to enviroment properties
Given the user is on the Wikionary home page
When the user looks up the definition of the word
Then they should see the definition
And they should click on the link

Scenario: Looking up the definition for words from examples table
Meta:
@ignore
Given the user is on the Wikionary home page
When the user looks up the definition of the word
Then they should see the definition <definition>
And they should click on the link <link>

Examples:
|word  | definition                                                                                    | link
|apple | A common, round fruit produced by the tree Malus domestica, cultivated in temperate climates. | common
|pear  | An edible fruit produced by the pear tree, similar to an apple but elongated towards the stem.| apple

Scenario: Looking up the definition of 'apple' and 'pear'
Given the user is on the Wikionary home page
When the user looks up the definition of the words in table
|word  | definition                                                                                    |
|apple | A common, round fruit produced by the tree Malus domestica, cultivated in temperate climates. |
|pear  | An edible fruit produced by the pear tree, similar to an apple but elongated towards the stem.|
Then in the table they should see the definition
