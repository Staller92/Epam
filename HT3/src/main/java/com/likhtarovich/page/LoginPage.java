package com.likhtarovich.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class LoginPage {
    private final WebDriver driver;

    private static final String TITLE = "phpMyAdmin";
    private static final By GO_BUTTON_LOCATOR = By.id("input_go");
    private static final By INPUT_USERNAME_LOCATOR = By.id("input_username");
    private static final By INPUT_PASSWORD_LOCATOR = By.id("input_password");
    private static final By INPUT_LANGUAGE_LOCATOR = By.id("sel-lang");

    public LoginPage(WebDriver driver) {
        this.driver = driver;

        // Checking that right page is opened
        if ((!driver.getTitle().equals(TITLE))
                || (!driver.getCurrentUrl().equals("http://localhost:8080/pma/"))) {
            throw new IllegalStateException("Invalid page opened");
        }
    }


    public LoginPage setUserName(String userName) {
        driver.findElement(INPUT_USERNAME_LOCATOR).clear();
        driver.findElement(INPUT_USERNAME_LOCATOR).sendKeys(userName);
        return this;
    }

    public LoginPage setPassword(String password) {
        driver.findElement(INPUT_PASSWORD_LOCATOR).clear();
        driver.findElement(INPUT_PASSWORD_LOCATOR).sendKeys(password);
        return this;
    }
    public LoginPage setLanguage(String language) {
        WebElement element = driver.findElement(INPUT_LANGUAGE_LOCATOR);
        Select select = new Select(element);
        select.selectByVisibleText(language);
        return this;
    }

    public MainPage submitLogin() {
        driver.findElement(GO_BUTTON_LOCATOR).click();
        return new MainPage(driver);
    }

    public MainPage loginAs(String language,String userName, String password) {
        setLanguage(language);
        setUserName(userName);
        setPassword(password);
        return submitLogin();
    }
}

