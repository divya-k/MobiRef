package com.simple.ams.core;

import com.simple.ams.util.JSONReader;

/**
 * Created By abhilashsrinivasa on 8/4/20
 */
public abstract class CommonCore {
    public abstract void launchApplication();
    public abstract void clickByID(JSONReader jsonReader, String key);
    public abstract void clickByID(String id);
    public abstract void sendValueAtID(String id, String value);
    public abstract void pressGo();
    public abstract void nestedScroll(JSONReader jsonReader, String key);
    public abstract void nestedScroll(String key, JSONReader jsonReader, String childElement);
    public abstract void verify(String id, String value);
    public abstract void verifyImage(String image);
    public abstract void clickPkgResourceId(String id);
    public abstract void sendValuePkgResourceId(String id, String value);
    public abstract void clickText(String text);


}
