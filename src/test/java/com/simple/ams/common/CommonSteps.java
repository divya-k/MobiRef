package com.simple.ams.common;

import com.simple.ams.core.CommonCore;
import com.simple.ams.core.android.AndroidActions;
import com.simple.ams.core.android.AndroidCore;
import com.simple.ams.core.ios.IOSActions;
import com.simple.ams.core.ios.IOSCore;
import com.simple.ams.util.CustomRunner;
import com.simple.ams.util.JSONReader;
import com.simple.ams.util.YAMLReader;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class CommonSteps {

    private CommonCore commonCore;

    private String tagValue = "@default";

    private JSONReader jsonReader;

    private YAMLReader yamlReader;

    public CommonSteps(AndroidCore androidCore, IOSCore iosCore) {
        if (System.getProperty("os.type").toLowerCase().startsWith("android")){
            commonCore = new AndroidActions(androidCore);
        }
        else if (System.getProperty("os.type").toLowerCase().startsWith("ios")) {
            commonCore = new IOSActions(iosCore);
        }
        else {
            commonCore = new AndroidActions(androidCore);
        }
    }

    @Given("^Launch Application")
    public void launch() {
        commonCore.launchApplication();
    }

    @When("^click by pkg resourceId \"([^\"]*)\"$")
    public void clickByPkgResourceID(String id){
        commonCore.clickPkgResourceId(id);
    }

    @And("^click by text \"([^\"]*)\"$")
    public void clickText(String text) {
        commonCore.clickText(text);
    }

    @And("^click \"([^\"]*)\" using json \"([^\"]*)\"$")
    public void clickUsing(String key, String page){
        jsonReader = new JSONReader("support/pages/"+page);
        commonCore.clickByID(jsonReader,key);
    }

    @Then("^verify that at id \"([^\"]*)\" shows \"([^\"]*)\"$")
    public void shows(String id, String value) {
        commonCore.verify(id,value);
    }

    @Given("^Do \"([^\"]*)\"$")
    public void setupAppWithScenario(String tag) {
        tagValue=tag;
        CustomRunner.runFeature("@"+tag);
    }

    @When("^enter value of key \"([^\"]*)\" using testData \"([^\"]*)\" at id \"([^\"]*)\"$")
    public void enterValueOfKeyUsingTestDataAtId(String key, String ymlFile, String id) {
        yamlReader = new YAMLReader("support/data/"+ymlFile);
        String value = yamlReader.getKey(tagValue+"."+key);
        enterValueAsAtId(value,id);
    }

    @When("^enter value as \"([^\"]*)\" at pkg resourceId \"([^\"]*)\"$")
    public void enterValueAsAtPkgResourceId(String value, String id) {
        commonCore.sendValuePkgResourceId(id,value);
    }

    @And("^click Go from keyboard$")
    public void clickGoFromKeyboard() {
        commonCore.pressGo();
    }

    @And("^scroll to \"([^\"]*)\" using \"([^\"]*)\"$")
    public void scrollToUsing(String key, String page) {
        jsonReader = new JSONReader("support/pages/"+page);
        commonCore.nestedScroll(jsonReader,key);
    }

    @And("^scroll to \"([^\"]*)\" in \"([^\"]*)\" using test data \"([^\"]*)\"$")
    public void scrollToInUsingTestData(String key, String json, String yaml) {
        jsonReader = new JSONReader("support/pages/"+json);
        yamlReader = new YAMLReader("support/data/"+yaml);
        String value = yamlReader.getKey(tagValue+"."+key);
        commonCore.nestedScroll(jsonReader,key);
    }

    @And("^enter value as \"([^\"]*)\" at id \"([^\"]*)\"$")
    public void enterValueAsAtId(String value, String id) {
        commonCore.sendValueAtID(id,value);
    }

    @And("^click by Id \"([^\"]*)\"$")
    public void clickById(String id) {
        commonCore.clickByID(id);
    }

    @And("^scroll into \"([^\"]*)\" in \"([^\"]*)\" using test data key \"([^\"]*)\" in \"([^\"]*)\"$")
    public void scrollIntoUsingTestDataKey(String keyJson, String json, String keyYaml, String yaml)  {
        jsonReader = new JSONReader("support/pages/"+json);
        yamlReader = new YAMLReader("support/data/"+yaml);
        String value = yamlReader.getKey(keyYaml+".value");
        commonCore.nestedScroll(keyJson,jsonReader,value);
    }

    @And("^verify using OpenCV that the image \"([^\"]*)\" is displayed$")
    public void verifyUsingOpenCVThatTheImageIsDisplayed(String image) {
        commonCore.verifyImage(image);
    }

    @When("^click on test data key \"([^\"]*)\" in \"([^\"]*)\"$")
    public void clickOnTestDataKeyIn(String keyYaml, String yaml) {
        yamlReader = new YAMLReader("support/data/"+yaml);
        String value = yamlReader.getKey(keyYaml+".value");
        clickText(value);
    }

    @And("^verify that at id \"([^\"]*)\" shows test data value \"([^\"]*)\" in key\"([^\"]*)\" in \"([^\"]*)\"$")
    public void verifyThatAtIdShowsTestDataValueInKeyIn(String id, String value, String key, String yamlFile) {
        yamlReader = new YAMLReader("support/data/"+yamlFile);
        String getValue = yamlReader.getKey(key+"."+value);
        commonCore.verify(id,getValue);


    }
}
