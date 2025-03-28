@Login @LMS

Feature: LMS API Login

  @LoginValid   
  Scenario Outline: Verify Login invalid functionality
    Given Admin creates Login Valid Request
    When Admin sends Post HTTPS Login Request
    Then Admin receives status with token

  @LoginInvalid   
  Scenario Outline: Verify Login invalid functionality
    Given Admin creates Login "<Scenario>" Request
    When Admin sends "<Scenario>" HTTPS Login Request
    Then Admin receives status "<Code>" for Login request
    Examples: 
      | Scenario     		       |   Code     |
      |  LoginInvalidCreds     |   400      |
      |  LoginInvalidEndpoint  |   404      |
			|  LoginEmptyCredential  |   400      |
			|  LoginEmptyUserEmail   |   400      |
			|  LoginEmptyPassword    |   400      |
			|LoginInvalidEmailFormat |   400      |
			|LoginIncorrectPassword  |   400      |
