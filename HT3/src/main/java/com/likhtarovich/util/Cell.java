package com.likhtarovich.util;

import org.openqa.selenium.WebElement;

public class Cell {

    String name;
    WebElement element;

    public Cell(String name, WebElement element) {
        this.name = name;
        this.element = element;
    }

    public String getName() {
        return name;
    }

    public WebElement getElement() {
        return element;
    }
}
