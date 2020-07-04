Feature: Restriction Page

  @Search_Page_For_65-inch_TV
  Scenario: Search Page for content
    When click by pkg resourceId "rs_search_src_text"
    And enter value as "65-inch TV" at pkg resourceId "rs_search_src_text"
    And click Go from keyboard



