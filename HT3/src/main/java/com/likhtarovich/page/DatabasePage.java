package com.likhtarovich.page;

import com.likhtarovich.util.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class DatabasePage {
    private final WebDriver driver;

    private final static String title = "localhost:8080 / localhost | phpMyAdmin 4.7.6";
    private final static By GO_HOME_LOCATOR = By.xpath("//*[@class='icon ic_b_home']");
    private final static By DATABASE_NAME_LOCATOR = By.cssSelector("#text_create_db");
    private final static By DATABASE_COLLATION_LOCATOR = By.name("db_collation");
    private final static By CREATE_DATABASE_BUTTON_LOCATOR = By.id("buttonGo");
    private final static By CHECKBOX_DATABASE_LOCATOR = By.name("selected_dbs[]");
    private final static By DROP_BUTTON_LOCATOR = By.cssSelector("#dbStatsForm > button > span");
    private final static By ALL_DATABASES_LOCATOR = By.xpath("//*[@id='tabledatabases']//td[@class='name']/a");
    private final static By CONFIRM_DROP_BUTTON_LOCATOR = By.xpath("//div[@class='ui-dialog-buttonset']/button[contains(text(),'OK')]");


    public DatabasePage(WebDriver driver) {
        this.driver = driver;
        Waiter.getWaiter(driver, 15).until(ExpectedConditions.titleIs(title));
        // Checking for right page is opened
        if ((!driver.getTitle().equals(title))) {
            throw new IllegalStateException("Invalid page opened");
        }
    }

    public DatabasePage setDataBaseName(String name) {
        driver.findElement(DATABASE_NAME_LOCATOR).clear();
        driver.findElement(DATABASE_NAME_LOCATOR).sendKeys(name);
        return this;
    }

    public DatabasePage setCollacation(String collocation) {
        WebElement element = driver.findElement(DATABASE_COLLATION_LOCATOR);
        Select select = new Select(element);
        select.selectByVisibleText(collocation);
        return this;
    }

    public DatabaseStructurePage submitDatabase() {
        driver.findElement(CREATE_DATABASE_BUTTON_LOCATOR).click();
        return new DatabaseStructurePage(driver);
    }

    public DatabaseStructurePage createNewDatabase(String name, String collocation) {
        setDataBaseName(name);
        setCollacation(collocation);
        return submitDatabase();
    }

    public DatabasePage dropDatabase(String name) {

        List<WebElement> databases = driver.findElements(CHECKBOX_DATABASE_LOCATOR);
        for (WebElement database : databases) {
            if (database.getAttribute("value").equalsIgnoreCase(name)) {
                database.click();
            }
        }
        driver.findElement(DROP_BUTTON_LOCATOR).submit();
        driver.findElement(CONFIRM_DROP_BUTTON_LOCATOR).click();
        return this;
    }

    public DatabaseStructurePage selectDatabase(String name) {

        List<WebElement> databases = driver.findElements(ALL_DATABASES_LOCATOR);
        for (WebElement database : databases) {

            if (database.getText().equalsIgnoreCase(name)) {

                database.click();
                break;

            }
        }

        return new DatabaseStructurePage(driver);
    }


    public boolean isDatabaseExist(String name) {

        List<WebElement> databases = driver.findElements(CHECKBOX_DATABASE_LOCATOR);
        for (WebElement database : databases) {
            if (database.getAttribute("value").equals(name)) {
                return true;
            }
        }
        return false;
    }

    public MainPage goHome() {

        driver.findElement(GO_HOME_LOCATOR).click();
        return new MainPage(driver);
    }
}


