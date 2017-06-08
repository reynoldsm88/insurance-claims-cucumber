Feature: Report an Incident

@wip
Scenario: As a Customer I want to report a collision that will be investigated as a Damage claim

Given I am the following Subscriber:
|   Id      |   First Name      |       Last Name       |
|   1       |   Chev            |       Chelios         |

And I have the following Policy:
|   Id      |       Subcriber Id        |       Name                    |
|   1       |           1               |       Basic Car Insurance     |

And the Policy has the following Coverages:
|       Id      |       Policy Id       |       Name                |   Effective Date  |   Termination Date	|
|       1       |          1            |   Coverage Year 2016      |   06-30-2016      |   06-30-2017			|

When I report the following Incident:
|   Subscriber Id   |       Type          |           Description                 |   Date Occurred   |
|       1           |     COLLISION       |   I drove my car into a wall          |   12-25-2017      |             

Then I expect the following Claim to be created:
|   Foo     |
|   Bar     |