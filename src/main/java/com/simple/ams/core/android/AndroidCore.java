package com.simple.ams.core.android;


import com.simple.ams.model.Elements;
import com.simple.ams.util.JSONReader;;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.Getter;
import lombok.Setter;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Setter
public class AndroidCore {

    public AndroidDriver<AndroidElement> androidDriver ;
    private String port = "4723";

    public void launchApplication(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        capabilities.setCapability("noReset", "false");

        try {
            this.androidDriver = new AndroidDriver<>(new URL("http://localhost:"+port+"/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    public void quit(){
        this.androidDriver.quit();
    }

    private AndroidElement init(WebDriver driver, MobileBy by) {

        Wait wait = new FluentWait(driver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(3, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
        AndroidElement element;
        element = (AndroidElement) wait.until((Function<AndroidDriver, AndroidElement>) driver1 -> (AndroidElement) driver1.findElement(by));
        wait.until(ExpectedConditions.elementToBeClickable(by));
        return element;
    }

    public WebElement element(JSONReader jsonReader, String key){
        Elements elements = jsonReader.page.getElements().stream().filter(x->x.getName().equals(key)).
                collect(Collectors.toList()).get(0);
        String identifier = elements.getIdentifier();
        String element = elements.getValue();
        return init(this.androidDriver, Identifier.valueOf(identifier.toUpperCase()).locator(element));
    }

    public AndroidElement element(MobileBy mobileBy) {

        Wait wait = new FluentWait(androidDriver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(3, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
        AndroidElement element;
        element = (AndroidElement) wait.until((Function<AndroidDriver, AndroidElement>) driver1 -> (AndroidElement) driver1.findElement(mobileBy));
        wait.until(ExpectedConditions.elementToBeClickable(mobileBy));
        return element;
    }

    public AndroidElement elementScroll(JSONReader jsonReader, String key){
        Elements elements = jsonReader.page.getElements().stream().filter(x->x.getName().equals(key)).
                collect(Collectors.toList()).get(0);
        String identifier = elements.getIdentifier();
        String element = elements.getValue();
        String[] nestedElement = element.split("-");
        System.out.println(nestedElement[0]+nestedElement[1]);
        return init(this.androidDriver, NestedIdentifier.valueOf(identifier.toUpperCase()).locator(nestedElement[0],nestedElement[1]));
    }

    public AndroidElement elementScroll(JSONReader jsonReader, String key, String childElement){
        Elements elements = jsonReader.page.getElements().stream().filter(x->x.getName().equals(key)).
                collect(Collectors.toList()).get(0);
        String identifier = elements.getIdentifier();
        String parentElement = elements.getValue();
        return init(this.androidDriver, NestedIdentifier.valueOf(identifier.toUpperCase()).locator(parentElement,childElement));
    }

    public void pressEnterKey() {
        this.androidDriver.pressKeyCode(AndroidKeyCode.ENTER);
    }

    public String encoder(String imagePath) {
        String base64Image = "";
        File file = new File(imagePath);
        try (FileInputStream imageInFile = new FileInputStream(file)) {
            // Reading a Image file from file system
            byte imageData[] = new byte[(int) file.length()];
            imageInFile.read(imageData);
            base64Image = Base64.getEncoder().encodeToString(imageData);
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
        return base64Image;
    }

    public void isImageAppearOnApplication(String imagePath) {
        try {
            Assert.assertTrue( this.androidDriver.findElementByImage(encoder(imagePath)).isDisplayed());
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Expected image didn't display on Application!");
        }
    }





    }
