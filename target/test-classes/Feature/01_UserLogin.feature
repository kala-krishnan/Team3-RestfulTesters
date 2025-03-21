

Feature: LMS API Login

Scenario: check user can able to Login into LMSAPI by using valid endpoint and request body
Given Admin creates request with valid credentials 
When Admin calls Post Https method  with valid endpoint
Then Admin receives 201 created with auto generated token


