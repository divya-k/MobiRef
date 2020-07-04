package com.simple.ams.core.android;

import io.appium.java_client.MobileBy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.openqa.selenium.By;

@AllArgsConstructor
@Getter
@ToString
public enum Identifier {


    CHROME_ID("chromeID") {
        @Override
        public MobileBy locator(String element) {
            return new MobileBy.ByAndroidUIAutomator("new UiSelector().resourceId(\""+element+"\")");
        }
    },
    NAME("name") {
            @Override
            public MobileBy locator(String element) {
                return new MobileBy.ByAndroidUIAutomator("new UiSelector().resourceId(\"com.android.packageinstaller:id/"+element+"\")");
            }

    },
    ID("id") {
        @Override
        public MobileBy locator(String element) {
            return new MobileBy.ByAndroidUIAutomator("new UiSelector().resourceId(\"com.android.packageinstaller:id/"+element+"\")");
        }

    },
    DESCRIPTION("description") {
        @Override
        public MobileBy locator(String element) {
            return new MobileBy.ByAndroidUIAutomator("description(\""+element+"\")");
        }

    };

    private final String name;

    public abstract MobileBy locator(String element);
}
