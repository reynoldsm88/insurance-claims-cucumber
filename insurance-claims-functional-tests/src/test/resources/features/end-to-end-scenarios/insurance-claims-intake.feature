Feature: Report an Incident

@wip
Scenario: As a customer I want to report an Incident to file a Claim

Given I am the following Subscriber:
|   Id      |   First Name      |       Last Name       |
|   1       |   Chev            |       Chelios         |

And I have the following Policy:
|   Id      |       Subcriber Id        |       Name                    |
|   1       |           1               |       Basic Car Insurance     |

And the Policy has the following Coverages:
|       Id      |       Policy Id       |       Name                |   Effective Date  |   Termination Date	|
|       1       |          1            |   Coverage Year 2016      |   06-30-2016      |   06-30-2017			|

When I choose to "Report an Incident"

And I report the following Incident:
|   Subscriber Id   |       Type        |           Description                 |   Date Occurred   |
|       1           |   COLLISION       |   I drove my car into a wall          |   12-25-2017      |             

Then I expect the following Claim to be created:
|   foo     |
|   bar     |