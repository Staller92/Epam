package com.likhtarovich.page;

import com.likhtarovich.util.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InsertDataPage {

    private final WebDriver driver;
    private static final By ROW_INSERT_LOCATOR = By.xpath(".//*[@id='insert_rows']");
    private static final By GO_BUTTON_LOCATOR = By.xpath(".//input[@value='Go']");
    private static final By ROWS_LOCATOR = By.xpath(".//tr[@class='noclick']");
    private static final By COLUMNS_LOCATOR = By.xpath(".//td[@class='center']");
    private static final By VALUES_LOCATOR = By.xpath(".//td[last()]/*[2]");

    public InsertDataPage(WebDriver driver) {
        this.driver = driver;

    }

    private HashMap<String, WebElement> getElements() {

        HashMap<String, WebElement> map = new HashMap<>();
        Waiter.getWaiter(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(ROWS_LOCATOR));
        List<WebElement> rows = driver.findElements(ROWS_LOCATOR);

        for (WebElement row : rows) {

            map.put(row.findElement(COLUMNS_LOCATOR).getText(), row.findElement(VALUES_LOCATOR));

        }

        return map;
    }

    public InsertDataPage setValue(String column, Object value) {
        WebElement element = driver.findElement(ROW_INSERT_LOCATOR);
        Select select = new Select(element);
        select.selectByVisibleText("1");

        for (Map.Entry<String, WebElement> pair : getElements().entrySet()) {
            if (pair.getKey().equals(column)) {
                pair.getValue().clear();
                pair.getValue().sendKeys(value.toString());
            }
        }
        return this;
    }


    public DatabaseStructurePage submitCreatingTable() {
        driver.findElement(GO_BUTTON_LOCATOR).submit();
        return new DatabaseStructurePage(driver);
    }
}
