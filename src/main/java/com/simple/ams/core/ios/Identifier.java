package com.simple.ams.core.ios;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.openqa.selenium.By;

@AllArgsConstructor
@Getter
@ToString
public enum Identifier {

    XPATH("xpath") {
    @Override
    public By locator(String element) {
        return By.xpath(element);
    }
    },
    CSS("css") {
        @Override
        public By locator(String element) {
            return By.className(element);
        }
    },
    CLASS("class") {
        @Override
        public By locator(String element) {
            return By.className(element);
        }
    },
    ID("id") {
        @Override
        public By locator(String element) {
            return By.id(element);
        }
    },
    NAME("name") {
        @Override
        public By locator(String element) {
            return By.name(element);
        }
    },
    PARTIAL_LINK_TEXT("partialLinkText") {
        @Override
        public By locator(String element) {
            return By.partialLinkText(element);
        }
    };

    private final String name;

    public abstract By locator(String element);
}
