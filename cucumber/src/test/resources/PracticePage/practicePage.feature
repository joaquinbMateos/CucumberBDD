Feature: rahulshetty Practice Page

  Background:
    Given I am in practice page

  @RadioButtons
  Scenario: As user I want to click a radio button.
    When I click on radio button
    Then radio button status change

  @SearchBox
  Scenario Outline: As user I want to search for countries suggestions.
    When I type "<uncomplete name>"
    Then search box show complete name
  Examples:
    |uncomplete name|
    |El Sal         |

  @Dropdown
  Scenario Outline: As user I want to select options.
    When I select "<option>" from dropdown
    Then option selected is shown
  Examples:
    |option        |
    |Option2       |
    |Option3       |

  @HeaderButtons
  Scenario Outline: As user I want to click on the header buttons and go back to practice page.
    When I click on "<button>"
    Then I should navigate to "<button>" section
  Examples:
    |button    |
    |Home      |
    |Practice  |
    |Login     |
    |Signup    |

  @BrowserTabs
  Scenario Outline: As user I want to open as many tabs I want, because...why not?
    When I click 'open tab' button <N> times
    Then print the number of tabs I opened
  Examples:
    |N|
    |9|

