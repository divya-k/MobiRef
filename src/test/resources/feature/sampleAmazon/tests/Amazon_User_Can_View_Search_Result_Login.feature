@Sample_Search_Page
Feature: Sample search page

  @Amazon_User_Can_View_Search_Result_Item_Login
  Scenario: User can view a search result Item
    Given Launch Application
    When Do "Login_UserOne"
    And Do "Search_Page_For_65-inch_TV"
    And scroll into "Search_Result" in "searchResultPage.json" using test data key "Samsung" in "TV_Details.yml"
    Then verify using OpenCV that the image "Samsung.png" is displayed
    When click on test data key "Samsung" in "TV_Details.yml"
    And verify that at id "title" shows test data value "value" in key"Samsung" in "TV_Details.yml"
    And verify that at id "priceblock_ourprice" shows test data value "price" in key"Samsung" in "TV_Details.yml"