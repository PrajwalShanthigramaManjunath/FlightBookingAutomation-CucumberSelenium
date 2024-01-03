Feature: Flight Booking on Ryanair Website

  Scenario Outline: Search and Select Flight with Basic Fare Enter Passenger Details and Navigate Extras
    Given User navigates to the Ryanair website

    When User accepts cookies
    And User searches for a one-way flight
    And User selects the departure "<departure>"
    And User selects the destination "<destination>"
    And User selects the departure date "<date>"
    And User specifies the number of adults
    And User initiates the search
    Then User should land on the flight search results page

    When User selects a flight and chooses the fare
    And User confirms the Basic travel light option
    Then Regular is ideal pop-up should appear
    And User continues with Basic fare
    And User opts to log in later

    When User selects the title
    And User enters the First Name
    And User enters the Last Name
    And User proceeds
    And User adds recommended seats
    And User selects 20 kg baggage options
    And User continues to Car Hire page
    And User proceeds towards payment
    Then Login pop-up appears before payment


    Examples:
      | departure | destination | date        |
      | Dublin    | barcelona   | 2024-01-26  |