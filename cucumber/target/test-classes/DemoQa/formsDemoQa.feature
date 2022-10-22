Feature: forms DemoQa

  Background:
    Given I am in "https://demoqa.com/automation-practice-form"


  Scenario Outline: As user I want to fill form inputs
    When I fill "<input name>" input with "<data>"
    Then validate "<input name>" input with "<data>"
    Examples:
      |input name    | data                   |
      |name          | joaquin                |
      |lastName      | mateos                 |
      |email         | veryrealemail@email.com|
      |mobile        | 1234567890             |
      |subjects      | Computer Science       |
      |address       | veryrealaddress        |
    When I click on gender button
    Then button status change
    When I complete brithdate
    Then validate "<date>"
    Examples:
      |date       |
      |22 Mar 1991|
    When I click on checkbox
    Then check box is marked
    When I upload image
    Then image is uploaded
    When I type "<option>"
    Then "<option>" is selected
    Examples:
      |option |
      |Haryana|
