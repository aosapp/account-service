#Auto generated Octane revision tag
@TID18001REV0.2.0
Feature: order service

	Scenario: buy & checkout.
		Given user is logged in
		When  user buy an item and preform checkout.
		Then  get a success massege - the purchese success .
       

	Scenario: check order history.
		Given the user perform checkout in success and still logged in.
		When  navigate to 'my orders' and do search on specific item.
		Then  user see the specific item that he buy and delete it
