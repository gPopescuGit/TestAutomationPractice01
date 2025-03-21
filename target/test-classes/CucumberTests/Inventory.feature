@tag
Feature: Inventory Page
  I want to use this template for my feature file

	Background:
  Given I landed on Inventory page
  

  #@Inventory
  #Scenario: Add product to cart
    #When I add a new product to cart
    #Then Cart icon indicates correct number of products in the cart

  #@Inventory
  #Scenario: Remove product from cart
    #When I remove one existing product from cart
    #Then Cart icon indicates correct number of products in the cart

#
  #@Inventory
  #Scenario: Products are ordered in page depending on active filter
    #When Active filter is <filter>
    #Then Products are ordered according to active filter
    #
        #Examples: 
      #| filter  						| 
      #| Name (A to Z) 			| 
      #| Name (Z to A) 			| 
      #| Price (low to high) | 
      #| Price (high to low) | 
    
  #@Inventory
  #Scenario: Link sends user to the corresponding page
    #When I click on social hyperlink
    #Then Corresponding page is opened
    #
    #fail: twitter response over 400
#	@Inventory
  #Scenario: Links displayed on page are in working condition
    #Then Soft check on hyperlinks indicates response code value under 400
    #
  #@Inventory
  #Scenario: Menu options are displayed
  #	Given I click on hamburger button from Inventory page
    #Then Options menu bar is displayed on Inventory page
    #
     #@Inventory
  #Scenario: User clicks on 'About' button
  #	Given I click on hamburger button from Inventory page
    #When I click on "About" button from Inventory page
    #Then I have landed on webpage "saucelabs.com"
  #
    #@Inventory
  #Scenario: User clicks on 'Log out' button
  #	Given I click on hamburger button from Inventory page
    #When I click on "Logout" button from Inventory page
    #Then I logged out and Login page is opened
    #
#
    @Inventory
  Scenario: User clicks on 'Reset App State' button
  	Given I click on hamburger button from Inventory page
  	And At least one product is added to cart
    When I click on "Reset App State" button from Inventory page
    Then Cart icon from Inventory page indicates no items added
    And Items including previously added ones can be added to cart
    
      @Inventory
  Scenario: View product details
  	When I click on product title
    Then Corresponding product page is opened
  
  
    