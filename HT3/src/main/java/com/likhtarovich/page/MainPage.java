package com.likhtarovich.page;

import com.likhtarovich.util.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class MainPage {
    private final WebDriver driver;

    private static final String TITLE = "localhost:8080 / localhost | phpMyAdmin 4.7.6";
    private static final By DATABASE_LOCATOR = By.cssSelector("#topmenu > li:nth-child(1) > a");
    private static final By GENERAL_SETTINGS_HEADER = By.xpath(".//h2[contains(text(), 'General settings')]");


    public MainPage(WebDriver driver) {
        this.driver = driver;
        Waiter.getWaiter(driver, 15).until(ExpectedConditions.titleIs(TITLE));
        // Checking for right page is opened
        if ((!driver.getTitle().equals(TITLE))) {
            throw new IllegalStateException("Invalid page opened");
        }
    }

    // open database
    public DatabasePage openDatabasePage() {
        Waiter.getWaiter(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(GENERAL_SETTINGS_HEADER));
        driver.findElement(DATABASE_LOCATOR).click();
        return new DatabasePage(driver);
    }

}
