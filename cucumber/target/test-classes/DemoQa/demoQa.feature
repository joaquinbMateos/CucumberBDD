Feature: DemoQa Page

  @Forms
  Scenario: As user I navigate to practice form
    Given I am in "https://demoqa.com/"
    When I click on Forms
    Then I am redirected to "https://demoqa.com/forms"
    And I click on Practice Form
    Then I am redirected to "https://demoqa.com/automation-practice-form"

  @Inputs
  Scenario Outline: As user I want to fill form inputs
    Given I am in "https://demoqa.com/automation-practice-form"
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

  @Gender
  Scenario: As user I want to click on gender radio button
    Given I am in "https://demoqa.com/automation-practice-form"
    When I click on gender button
    Then button status change

  @BirthDate
  Scenario Outline: As user I want to complete my birthdate
    Given I am in "https://demoqa.com/automation-practice-form"
    When I complete "<day>","<month>","<year>"
    Then validate "<day>","<month>","<year>"
    Examples:
    |date       |day|month|year|
    |22 Mar 1991|22 |Mar  |1991|

  @Hobby
  Scenario: As user I want to select a hobby
    Given I am in "https://demoqa.com/automation-practice-form"
    When I click on checkbox
    Then check box is marked

  @Image
  Scenario: As user I want to upload image
    Given I am in "https://demoqa.com/automation-practice-form"
    When I upload image
    Then image is uploaded

  @State
  Scenario Outline: As user I want to fill state information
    Given I am in "https://demoqa.com/automation-practice-form"
    When I type "<state>" state
    And  I type "<city>" city
    Then "<state>" state is selected
    And "<city>" city is selected
  Examples:
    |state  |city  |
    |Haryana|Karnal|

  @Submit
  Scenario: As user I want to submit information
    Given I am in "https://demoqa.com/automation-practice-form"
    When I fill "name" input with "myName"
    Then validate "name" input with "myName"
    And  I fill "lastName" input with "myLastName"
    Then validate "lastName" input with "myLastName"
    And I fill "mobile" input with "1234567890"
    Then validate "mobile" input with "1234567890"
    And I click on gender button
    And I click on submit
    Then I close popup