package com.simple.ams.core.ios;


import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;


@Getter
@Setter
public class IOSCore {

    private IOSDriver<IOSElement> iosDriver;
    private String port = "4723";

    public void launchApplication(String url){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("useNewWDA", true);
        capabilities.setCapability("noReset", "false");

        try {
            this.iosDriver = new IOSDriver<>(new URL("http://localhost:"+port+"/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void quit(){
        this.iosDriver.quit();
    }

}
