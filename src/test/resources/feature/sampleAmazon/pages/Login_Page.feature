Feature: Login Page

  @Skip_User_Login
  Scenario: Skip Login
    When scroll to "skipLogin" using "LoginPage.json"

  @Login_UserOne
  Scenario: User Login
    When click by pkg resourceId "sign_in_button"
    And enter value of key "username" using testData "userDetails.yml" at id "ap_email_login"
    And click by Id "continue"
    And enter value of key "password" using testData "userDetails.yml" at id "ap_password"
    And click by Id "signInSubmit"