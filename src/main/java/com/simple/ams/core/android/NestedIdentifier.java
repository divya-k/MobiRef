package com.simple.ams.core.android;

import io.appium.java_client.MobileBy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum NestedIdentifier {


    DESCRIPTION_TEXT("description_text") {
        @Override
        public MobileBy locator(String parentElement, String childElement) {
            return new MobileBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().description(\"" + parentElement + "\"))" +
                    ".scrollIntoView(new UiSelector().textStartsWith(\"" + childElement + "\"))");
        }
    },
    CLASSNAME_TEXT("className_text") {
        @Override
        public MobileBy locator(String parentElement, String childElement) {
            return new MobileBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"" + parentElement + "\"))" +
                    ".scrollIntoView(new UiSelector().textStartsWith(\""+childElement+ "\"))");
        }

    },
    CLASSNAME_ID("className_id") {
        @Override
        public MobileBy locator(String parentElement, String childElement) {
            return new MobileBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"" + parentElement + "\"))" +
                    ".scrollIntoView(new UiSelector().resourceId(\""+childElement+ "\"))");
        }

    };


    private final String name;

    public abstract MobileBy locator(String parentElement, String childElement);
}
