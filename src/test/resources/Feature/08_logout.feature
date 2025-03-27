@Logout @LMS

Feature: LMS API Login

Background: Authorization SetUp
	Given Admin sets Authorization to Bearer Token

  @LogoutInvalid   
  Scenario Outline: Verify LMS Logout functionality
    Given Admin creates Logout "<Scenario>" Request
    When Admin sends GET HTTPS "<Scenario>" Logout Request
    Then Admin receives status "<Code>" for Logout request
    Examples: 
      | Scenario          |   Code     |
      |  LogoutValid      |   200      |
      |  LogoutInvalidEP  |   404      |
      