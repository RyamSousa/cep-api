Feature: Get address data and calculate freight

  Scenario: Get an address data successful
    Given isvalid CEP is provided
    When its search a CEP
    Then returns the address data with freight of the region