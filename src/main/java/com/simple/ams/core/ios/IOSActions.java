package com.simple.ams.core.ios;

import com.simple.ams.core.CommonCore;
import com.simple.ams.util.JSONReader;

/**
 * Created By abhilashsrinivasa on 8/4/20
 */
public class IOSActions extends CommonCore {

    private IOSCore iosCore;

    public IOSActions(IOSCore iosCore){
        this.iosCore = iosCore;
    }


    @Override
    public void launchApplication() {

    }

    @Override
    public void clickByID(JSONReader jsonReader, String key) {

    }

    @Override
    public void clickByID(String id) {

    }

    @Override
    public void sendValueAtID(String id, String value) {

    }

    @Override
    public void pressGo() {

    }

    @Override
    public void nestedScroll(JSONReader jsonReader, String key) {

    }

    @Override
    public void nestedScroll(String key, JSONReader jsonReader, String childElement) {

    }

    @Override
    public void verify(String id, String value) {

    }

    @Override
    public void verifyImage(String image) {

    }

    @Override
    public void clickPkgResourceId(String id) {

    }

    @Override
    public void sendValuePkgResourceId(String id, String value) {

    }

    @Override
    public void clickText(String text) {

    }
}
