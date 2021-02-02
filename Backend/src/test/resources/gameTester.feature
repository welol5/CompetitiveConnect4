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
Feature: Test Queueing up and playing the game

	Scenario: Test queueing up
		Given a player is logged in
		When they press the queue button
		Then they will see the queue screen
		And they should be able to leave the queue
		
	Scenario: Test being put into a game
		Given two players are logged in
		When they both press the queue button
		Then they will be put into a game
		
		Scenario: Test playing a game
		Given two players are logged in
		When they both press the queue button
		And they will be put into a game
		And they will make moves
		And someone will have won the game
		Then they will be able to either requeue or go back to the home page