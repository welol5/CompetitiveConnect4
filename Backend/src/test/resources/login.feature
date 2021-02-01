#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Login and Register testing

#  Scenario: Trying to login without registering
#  	Given I am on the home page and not logged in
#  	When I try to login without having an account
#  	Then I should be told my login info was invalid
  	
  	
	Scenario Outline: I try to register an account
		Given I am on the home page and not logged in
		When I click the register button
		And I can type in a new username "<username>" and password "<password>"
		And I click Register
		Then I should be able to login using "<username>" and "<password>"
		And I can logout
		
		Examples:
		|username|password|
		|test1|password|
		|test2|password|