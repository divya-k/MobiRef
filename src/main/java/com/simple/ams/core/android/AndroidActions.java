package com.simple.ams.core.android;

import com.simple.ams.core.CommonCore;
import com.simple.ams.core.Constant;
import com.simple.ams.util.JSONReader;
import io.appium.java_client.MobileBy;
import org.junit.Assert;

/**
 * Created By abhilashsrinivasa on 8/4/20
 */
public class AndroidActions extends CommonCore {

    private AndroidCore core ;

    public AndroidActions(AndroidCore core){
    this.core = core;
    }

    public void launchApplication() {
        core.launchApplication();
    }

    public void clickByID(JSONReader jsonReader, String key){
        core.element(jsonReader, key).click();
    }

    public void clickByID(String id){

        core.element(new MobileBy.ByAndroidUIAutomator("new UiSelector().resourceId(\""+id+"\")")).click();
    }

    public void clickText(String text){

        core.element(new MobileBy.ByAndroidUIAutomator ("textStartsWith(\""+text+"\")")).click();
    }

    public void clickPkgResourceId(String id){

        core.element(new MobileBy.ByAndroidUIAutomator("new UiSelector().resourceId(\""+ Constant.PACKAGE_NAME+":id/"+id+"\")")).click();
    }

    public void sendValueAtID(String id, String value){
        core.element(new MobileBy.ByAndroidUIAutomator("new UiSelector().resourceId(\""+id+"\")")).sendKeys(value);
    }

    public void sendValuePkgResourceId(String id, String value){
        core.element(new MobileBy.ByAndroidUIAutomator("new UiSelector().resourceId(\""+ Constant.PACKAGE_NAME+":id/"+id+"\")")).sendKeys(value);
    }

    public void pressGo() {
        core.pressEnterKey();
    }

    public void verify(String id, String value) {
        Assert.assertTrue(core.element(new MobileBy.ByAndroidUIAutomator("new UiSelector().resourceId(\""+
                id+"\")")).getText().startsWith(value));
    }

    public void nestedScroll(JSONReader jsonReader, String key){
        core.elementScroll(jsonReader,key).click();
    }

    public void nestedScroll(String key, JSONReader jsonReader, String childElement){
        core.elementScroll(jsonReader,key,childElement);
    }

    public void verifyImage(String image){
        core.isImageAppearOnApplication("support/img/"+image);
    }
}
