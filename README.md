# Automating Amazon shopping apk using AMS

What is AMS ?
-------------------

Following up from AMS for web [AMS](https://gitlab.com/Abhilashsiyer/ams), AMS Mobile (Automation Made Simple for Mobile) is a Java-Maven-Appium based project that is designed to enable one to automate their test cases in English using [Gherkin](https://cucumber.io/docs/gherkin/reference/)

How it works ?
-------------------

Lets take an example Android application - Amazon shopping 

Your test scenario is to verify result for search value 65-inch TV .

Say following are the test steps-

1. Launch the application
2. Navigate to Search bar and enter value as 65-inch TV
3. Verify if Samsung TV is displayed in search results

With AMS, You write the above test steps as :
```
Given Launch Application
When click by pkg resourceId "skip_sign_in_button"
And click by pkg resourceId "rs_search_src_text"
And enter value as "65-inch TV" at pkg resourceId "rs_search_src_text"
And click Go from keyboard
And scroll into "Search_Result" in "searchResultPage.json" using test data key "Samsung" in "TV_Details.yml"
Then verify using OpenCV that the image "Samsung.png" is displayed
```
That's it you have automated your test now !! You are only required to write the steps in english and you're good to go ! 


Sample- Automating Amazon apk to verify search results
-------------------
Following is the test scenario to verify search result in Amazon apk without user sign in

```
Scenario: User can view a search result Item
    Given Launch Application
    When Do "Skip_User_Login"
    And Do "Search_Page_For_65-inch_TV"
    And scroll into "Search_Result" in "searchResultPage.json" using test data key "Samsung" in "TV_Details.yml"
    Then verify using OpenCV that the image "Samsung.png" is displayed
    When click on test data key "Samsung" in "TV_Details.yml"
    Then verify that at id "title" shows "SAMSUNG 65"
    And verify that at id "priceblock_ourprice" shows test data value "price" in key"Samsung" in "TV_Details.yml"
```


Precondition
-------------------
1. Appium 1.17.0 is installed
2. opencv4nodejs is installed and linked to appium node modules
3. Appium server is started using - 
    appium --pre-launch --platform-name android --platform-version x.x.x --device-name xxxx -U xxxx --app-pkg com.amazon.mShop.android.shopping --app-activity com.amazon.mShop.home.HomeActivity --app ~/xxxx/Amazon_shopping.apk
    
Running the test
-------------------
Go to root of the project and run maven command -

```
mvn clean test -Dcucumber.options="--tags @Amazon_User_Can_View_Search_Result_Item" -Dos.type=android
```

Steps details -
-------------------
 1. Launch the Application (Launch Application)
    The application provided in appium server is installed into the device and launched.

2. Skip Login (Do "Skip_User_Login")
    Leveraging page object model, Skip_User_Login is defined in Login_Page.feature so that any change in id values of skip button can be changed at one place i.e Login_Page.feature in this instance
    As in landscape mode the skip button could be hidden in smaller size devices, Using UiScrollable the step will be
```
When scroll to "skipLogin" using "LoginPage.json"
``` 
    
3. Search for 65 inch TV
   Again following Page object model, All steps to search for a 65 inch TV is defined in Search_Page.feature as
   
```
  @Search_Page_For_65-inch_TV
  Scenario: Search Page for content
    When click by pkg resourceId "rs_search_src_text"
    And enter value as "65-inch TV" at pkg resourceId "rs_search_src_text"
    And click Go from keyboard
```
4. Scroll to search value Samsung using Test Data
   Scrolling is achieved using UiScrollable so that scrolling remains stable across any device size. Here we need to search for a specific value of a Samsung TV. 
   This value is stored in support/data/TV_Details.yml/Samsung and the value of parent scrollable container is stored in support/pages/searchResultPage.json at key Search_Result.
   Again bringing in a page object model to change the parent identifiers in json when required. The complete steps looks like -
```
And scroll into "Search_Result" in "searchResultPage.json" using test data key "Samsung" in "TV_Details.yml"
```       

5. Verify if search result contains the Samsung TV
   With Step 4 we have achieved scrolling, now we need to verify if the search result is correct. One option of achieving this is using webview. 
   However, the application needs to be enabled with WebKitDebugging to use the webview. The other simple and efficient option is to use OpenCV. 
   As Open CV is works based on real time computer vision. This method can again be used across device sizes.
   The image we are verifying is stored in support/img/Samsung.png and the step to do this verification is -
```
Then verify using OpenCV that the image "Samsung.png" is displayed
``` 

6. Open the result of Samsung TV
   Leveraging AMS, this is simply achieved as 
```
When click on test data key "Samsung" in "TV_Details.yml"
```  

7. Verify the details on Samsung TV title and price 
    The Samsung TV title and price is stored in TV_Details.yml and steps are
```
And verify that at id "title" shows test data value "value" in key"Samsung" in "TV_Details.yml"
And verify that at id "priceblock_ourprice" shows test data value "price" in key"Samsung" in "TV_Details.yml"
```    

I hope you have enjoyed seeing the tests written in simplified english. As you might have observed all steps are reusable across any application.
The core of the framework is written in main part of this project. 

Login Test
---------
The steps to do Login is written in User Login scenario in Login_Page.feature. If required to run login test, add a valid profile in userDetails.yml under Login_UserOne.
Then simply run the test - Amazon_User_Can_View_Search_Result_Item_Login

Note that since the Amazon application we're using here is pointing to production. Any test account used more than twice would block the account. 
Thus, I wouldn't recommend to run Login tests in production unless you have a low-risk production account 


##  Author -
Abhilashsiyer@gmail.com


